package com.dcits.orion.schedule.model;

/**
 * Created by Tim on 2015/6/30.
 */
public class JobPlan {
    private String index;
    private String jobName;
    private String jobDescription;
    private String jobType;
    private String stage;

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getJobName() {
        return this.jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDescription() {
        return this.jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobType() {
        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getStage() {
        return this.stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String toString() {
        return "{\"index\"=" + index + ",\"jobName\"=" + jobName + ",\"jobDescription\"=" + jobDescription + ",\"jobType\"=" + jobType + ",\"stage\"=" + stage + "}";
    }
}
