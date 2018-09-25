/**
 * <p>Title: FilePOJobContext.java</p>
 * <p>Description: 持久化context信息到文本</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月23日 下午5:55:01
 * @version V1.0
 */
package com.dcits.orion.schedule.persist;

import java.io.File;

import com.alibaba.fastjson.JSON;
import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.model.ExecutedJob;
import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.galaxy.base.util.FileUtils;
import com.dcits.galaxy.base.util.JsonUtils;

/**
 * @author Tim
 * @version V1.0
 * @description 持久化context信息到文本
 * @update 2014年9月23日 下午5:55:01
 */

public class FilePOJobContext implements POJobContext {

    private String filedir;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.dcits.galaxy.schedule.job.POJobContext#persistJobContext(com.dcits
     * .galaxy.schedule.model.ExecutedJob)
     */
    @Override
    public void persistJobContext(ExecutedJob ej) {
        setFiledir(ej);
        String fileName = filedir + System.getProperty("file.separator")
                + ej.getJobId() + "_context.log";
        String content = JsonUtils.formatJson(JSON.toJSONString(ej));
        if (FileUtils.createDir(filedir)) {
            FileUtils.writeFile(fileName, content, "UTF-8");
            if (!ej.getListenner().getJobStatus().isFailure()) {
                // 写_succeed
                FileUtils.writeFile(
                        filedir + System.getProperty("file.separator")
                                + "_SUCCEED", "", "UTF-8");
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.dcits.galaxy.schedule.persist.POJobContext#checkJobStatus(com.dcits
     * .galaxy.schedule.model.ExecutedJob)
     */
    @Override
    public boolean checkJobStatus(ExecutedJob ej) {
        // 到${日期}/${WorkerName}/${JobType}/${JobName}检查目录是否存在执行成功的_SUCCEED文件
        setFiledir(ej);
        String fileName = filedir + System.getProperty("file.separator")
                + "_SUCCEED";
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private void setFiledir(ExecutedJob ej) {
        filedir = GlobalConfiguration.JOB_RESULTS_PATH + System.getProperty("file.separator")
                + ej.getRunDate() + System.getProperty("file.separator")
                + ej.getWorkName() + System.getProperty("file.separator")
                + ej.getJobRunType().toString()
                + System.getProperty("file.separator") + ej.getName();
    }
}
