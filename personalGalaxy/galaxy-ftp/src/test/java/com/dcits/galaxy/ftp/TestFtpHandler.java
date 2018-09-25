package com.dcits.galaxy.ftp;

import com.dcits.galaxy.junit.TestBase;
import net.sf.jftp.net.BasicConnection;

/**
 * Created by Tim on 2015/10/15.
 */
public class TestFtpHandler extends TestBase {

    public void testUpload() {
        GroupFileForUpload gffu = (GroupFileForUpload) context.getBean("upLoadFileGroup0");
        FtpHandler ftp = new FtpHandler();
        try {
            for (GroupFileForUpload.FileForUpload ffu : gffu.getFileGroup()) {
                BasicConnection con = ftp.getConnection(ServerType.FTP, ffu.getConf());
//            ftp.download(con,ffu.getFtpDir(), ffu.getLocalDir(), ffu.getFile())  ;
                ftp.upload(con, ffu.getFtpDir(), ffu.getLocalDir(), ffu.getFile());
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------");
        } finally {
            ftp.release();
        }
    }

    public void testDownload() {
        GroupFileForDownLoad gffd = (GroupFileForDownLoad) context.getBean("downLoadFileGroup0");
        FtpHandler ftp = new FtpHandler();
        for (GroupFileForDownLoad.FileForDownLoad ffd : gffd.getFileGroup()) {
            BasicConnection con = ftp.getConnection(ServerType.FTP, ffd.getConf());
            ftp.download(con, ffd.getFtpDir(), ffd.getLocalDir(), ffd.getFile());
        }
        ftp.release();
    }
}
