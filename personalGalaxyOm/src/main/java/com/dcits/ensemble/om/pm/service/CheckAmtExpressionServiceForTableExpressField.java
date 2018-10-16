package com.dcits.ensemble.om.pm.service;

import com.dcits.ensemble.om.pm.common.ExpressHander;
import com.dcits.ensemble.om.pm.util.dao.LimCheckExpressionDao;
import com.ql.util.express.DefaultContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 校验金额表达式
 * 		参数平台金额表达式交易提交时，调用该接口金额校验表达式是否正确。
 * @author zhangjig
 *
 */
@Service
public class CheckAmtExpressionServiceForTableExpressField {

    private static final Logger logger = LoggerFactory.getLogger(CheckAmtExpressionServiceForTableExpressField.class);

    @Resource
    private LimCheckExpressionDao pkCreateDaofmRefCodeDao;

    public boolean execute(String expression,String tableName) {
        try {
            BigDecimal ret = BigDecimal.ZERO;
            DefaultContext<String, Object> map = new DefaultContext<String, Object>();
            Map<String, String> fmRefCode = new HashMap<String, String>();
            fmRefCode.put("tableName", tableName);
            //获取全部金额类型
            List<Map<String, String>> result = pkCreateDaofmRefCodeDao.findAmtType(fmRefCode);
            for (Map<String, String> amtType : result) {
                map.put(amtType.get("FIELD_VALUE"), BigDecimal.ONE);
            }
            //调用方法，得到返回结果
            ExpressHander.parse(expression, map);
            //System.out.println(ret);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
}
