package com.dcits.galaxy.base.exception;

import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.tuple.TwoTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiBusinessException extends GalaxyException {

    protected static final Logger logger = LoggerFactory.getLogger(MultiBusinessException.class);

    private static final long serialVersionUID = -282923477306477396L;

    private List<BusinessException> causeList = new ArrayList<>();

    public MultiBusinessException() {
        super();
    }

    public MultiBusinessException(String message) {
        super(message);
    }

    public MultiBusinessException(List<BusinessException> causes) {
        causeList.addAll(causes);
    }

    public MultiBusinessException(BusinessException... cause) {
        // super("");
        this.causeList.addAll(Arrays.asList(cause));
    }

    public void addCause(BusinessException cause) {
        causeList.add(cause);
    }

    public List<BusinessException> getCauseList() {
        return causeList;
    }

    public TwoTuple<String, Results> getRet() {
        boolean B = false;
        boolean O = false;
        boolean C = false;
        boolean F = false;
        String retStatus = null;
        Results result = new Results();
        Results res = new Results();
        for (BusinessException exception : causeList) {
            List<Result> list = exception.getRets().getRet();
            if ((exception instanceof WithoutAuthAndConfirmException)) {
                arrangeResult(exception, result, list);
                B = true;
            } else if ((exception instanceof WithoutAuthorizationException)) {
                arrangeResult(exception, result, list);
                result.addResults(list);
                O = true;
            } else if ((exception instanceof WithoutConfirmationException)) {
                arrangeResult(exception, result, list);
                result.addResults(list);
                C = true;
            } else if ((exception instanceof BusinessException)
                    && !(exception instanceof WithoutAuthAndConfirmException)
                    && !(exception instanceof WithoutAuthorizationException)
                    && !(exception instanceof WithoutConfirmationException)) {
                arrangeResult(exception, res, list);
                F = true;
            }
        }

        if (F) {
            retStatus = GalaxyConstants.STATUS_FAILED;
        } else if (B || (O && C)) {
            retStatus = GalaxyConstants.STATUS_AUTH_CONF;
        } else if (!B && !C && O) {
            retStatus = GalaxyConstants.STATUS_AUTH;
        } else if (C && !B && !O) {
            retStatus = GalaxyConstants.STATUS_CONF;
        }

        TwoTuple<String, Results> twoTuple = null;
        if (res.getRet().size() == 0) {
            twoTuple = new TwoTuple<String, Results>(retStatus, result);
        } else {
            twoTuple = new TwoTuple<String, Results>(retStatus, res);
        }

        return twoTuple;
    }

    private void arrangeResult(BusinessException exception, Results result, List<Result> list) {
            boolean isExist;
            for (Result rs : list) {
                isExist = false;
                for (Result r : result.getRet()) {
                    if ((rs.getRetCode().equals(r.getRetCode())) && (rs.getRetMsg().equals(r.getRetMsg()))) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist)
                    result.addResult(rs);
            }

    }

}
