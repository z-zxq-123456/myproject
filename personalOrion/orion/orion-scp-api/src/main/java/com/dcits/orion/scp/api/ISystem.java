package com.dcits.orion.scp.api;


import com.dcits.orion.scp.api.exception.UnknownException;

import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public interface ISystem {
    Map execute(Map request);

    List<Map> getErrors(Map response);

    boolean isError(Map response);

    boolean isUnknownError(Map response);

    boolean isReversalAgain(Map response);

    boolean isReversalError(Map response);

    boolean isConfirmAgain(Map response);

}
