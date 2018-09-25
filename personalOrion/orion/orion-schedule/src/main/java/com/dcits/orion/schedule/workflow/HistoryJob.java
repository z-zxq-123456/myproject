/**
 * <p>Title: HistoryJob.java</p>
 * <p>Description: 存放执行结束的Job，目前缓存在内存，后续可以考虑实现序列化</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.workflow;

import com.dcits.orion.schedule.model.AbstractJob;
import com.dcits.orion.schedule.model.ExecutedJob;
import com.dcits.galaxy.base.util.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tim
 * @version V1.0
 * @description 存放执行结束的Job，目前缓存在内存，后续可以考虑实现持久化
 * @update 2014年9月15日 下午3:31:05
 */

public class HistoryJob {
    private Map<String, ExecutedJob> hjob = new TreeMap<String, ExecutedJob>();

    /**
     * 上一次运行日期
     */
    private String lastRunDate;

    public Map<String, ExecutedJob> getJobs() {
        return hjob;
    }

    public AbstractJob getJob(String jobId) {
        return hjob.get(jobId);
    }

    public void putJob(String jobId, ExecutedJob job) {
        if (StringUtils.isEmpty(lastRunDate))
            lastRunDate = job.getRunDate();
        else if (!lastRunDate.equals(job.getRunDate())) {
            // 清空当前历史流水
            // 只保存当天的job记录
            hjob.clear();
            lastRunDate = job.getRunDate();
        }
        this.hjob.put(jobId, job);
    }

    public void removeJob(String jobId) {
        this.hjob.remove(jobId);
    }
}
