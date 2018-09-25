/**
 * <p>Title: ResultsUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年10月30日 下午3:59:05
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.ILocalHead;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;

/**
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年10月30日 下午3:59:05
 */

public class ResultsUtils {
    /**
     * 将本地报文头的授权信息转化为Results
     *
     * @param localData
     * @param rs
     */
    public static void convertRetToResults(JSONObject localData, Results rs) {
        // 本地头里面包含授权错误信息，转换到Results暂存
        if (localData.containsKey(GalaxyConstants.RETS)) {
            JSONArray rets = JsonUtils.getJSONArray(GalaxyConstants.RETS,
                    localData);
            if (null != rets) {
                JSONObject ret;
                for (int i = 0; i < rets.size(); i++) {
                    ret = rets.getJSONObject(i);
                    if (null == rs)
                        new Results(JsonUtils.getString(
                                GalaxyConstants.RET_CODE, ret),
                                JsonUtils.getString(GalaxyConstants.RET_MSG,
                                        ret));
                    else
                        rs.addResult(new Result(JsonUtils.getString(
                                GalaxyConstants.RET_CODE, ret), JsonUtils
                                .getString(GalaxyConstants.RET_MSG, ret)));
                }
            }
        }
    }

    /**
     * 将本地报文头的授权信息转化为Results
     *
     * @param localData
     * @param rs
     */
    public static Results convertRetToResults(ILocalHead localData, Results rs) {
        // 本地头里面包含授权错误信息，转换到Results暂存
        if (null != localData && null != localData.getRet() && localData.getRet().size() > 0) {
            for (Result ret : localData.getRet()) {
                if (null == rs)
                    rs = new Results(ret.getRetCode(), ret.getRetMsg());
                else
                    rs.addResult(new Result(ret.getRetCode(), ret.getRetMsg()));
            }
        }
        return rs;
    }

    /**
     * 合并授权错误到授权结果集合
     *
     * @param auth
     * @param rs
     */
    public static void mergeResults(Results auth, Results rs) {
        if (null == auth)
            auth = new Results();
        for (Result r : rs.getRets()) {
            auth.addResult(r);
        }
    }
}
