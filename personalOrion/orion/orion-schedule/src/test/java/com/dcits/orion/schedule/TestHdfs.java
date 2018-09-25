package com.dcits.orion.schedule;


import junit.framework.TestCase;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixbb on 2015/12/18.
 */
public class TestHdfs extends TestCase {

    public void testHdfs() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.default.name", "hdfs://slave1:9000");
        Path out = new Path("hdfs://slave1:9000/user/gl_tran_hist/2015-12-18");
        FileSystem fs = out.getFileSystem(conf);
        if (fs.exists(out)) {
            FileStatus[] fileStatuses = fs.listStatus(out);
            List<FileStatus> subPaths = new ArrayList<FileStatus>();
            for (FileStatus fileStatus:fileStatuses)
            {
                if (fileStatus.isDirectory())
                {
                    subPaths.add(fileStatus);
                }
            }



            Path[] paths = org.apache.hadoop.fs.FileUtil.stat2Paths(subPaths.toArray(new FileStatus[]{} ));
            for(Path path :paths){
                System.out.println(path);
            }
        } else {
            fs.mkdirs(out);
        }
    }
}
