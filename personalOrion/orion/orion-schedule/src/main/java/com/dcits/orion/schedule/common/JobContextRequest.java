/**   
 * <p>Title: JobContextRequest.java</p>
 * <p>Description: 从Hadoop AM获取application信息请求<br>
 *              继承Sqoop Client的Request</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.common;

import org.apache.sqoop.client.request.Request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.orion.schedule.context.JobContext;
import com.dcits.galaxy.base.util.ClassLoaderUtils;

/**
 * @description 从Hadoop AM获取application信息请求<br>
 *              继承Sqoop Client的Request
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:48:27
 */

public class JobContextRequest extends Request {

    private final String APPLICATION_URl = "ws/v1/cluster/apps/";

    private final String CONTEXT_IMPL = "com.dcits.galaxy.schedule.context.JobContext";

    public JobContext getJobContext(String applicationId) {
        JobContext ac = null;
        if (null == applicationId)
            return ac;
        String response = super.get(JobConfiguration.getInstance().getJobPro(
                GlobalConfiguration.HADOOP_AM_URL)
                + APPLICATION_URl + applicationId);
        JSONObject jsonObject = (JSONObject) JSON.parse(response);
        Class clazz;
        try {
            clazz = ClassLoaderUtils.loadClass(CONTEXT_IMPL);
        } catch (ClassNotFoundException e) {
            clazz = JobContext.class;
        }
        if (jsonObject.containsKey("app")) {
            ac = (JobContext) JSON.parseObject(
                    jsonObject.getString("app"),
                    clazz);
        } else if (jsonObject.containsKey("RemoteException")) {
            ac = (JobContext) JSON.parseObject(
                    jsonObject.getString("RemoteException"),
                    clazz);
        }
        return ac;
    }
}
