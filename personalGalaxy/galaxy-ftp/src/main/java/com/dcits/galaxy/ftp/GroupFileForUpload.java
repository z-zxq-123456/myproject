package com.dcits.galaxy.ftp;

import java.util.List;

/**
 * Created by Tim on 2015/10/15.
 */
public class GroupFileForUpload {

    private List<FileForUpload> fileGroup;

    public List<FileForUpload> getFileGroup() {
        return fileGroup;
    }

    public void setFileGroup(List<FileForUpload> fileGroup) {
        this.fileGroup = fileGroup;
    }

    public static class FileForUpload {

        private Configuration conf;

        private String ftpDir;

        private String localDir;

        private String file;

        public Configuration getConf() {
            return conf;
        }

        public void setConf(Configuration conf) {
            this.conf = conf;
        }

        public String getFtpDir() {
            return ftpDir;
        }

        public void setFtpDir(String ftpDir) {
            this.ftpDir = ftpDir;
        }

        public String getLocalDir() {
            return localDir;
        }

        public void setLocalDir(String localDir) {
            this.localDir = localDir;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }
}
