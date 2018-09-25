/**
 * <p>Title: JobClient.java</p>
 * <p>Description: 提供的命令行Sqoop Job Client管理类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.dx.client;

import com.dcits.galaxy.base.util.StringUtils;

import org.apache.sqoop.model.MConnection;
import org.apache.sqoop.model.MJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * @description 提供的命令行Sqoop Job Client管理类
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午2:16:55 
 */

public class JobClient {
    private static final Logger logger = LoggerFactory
            .getLogger(JobClient.class);

    static ClientTools ct;
    static String sqoopurl;
    static String connectionName;
    static String connectionUrl;
    static String userName;
    static String passWord;
    static long xid = -1;
    static long execJobid = -1;
    static String jobName;
    static String sql;
    static String boundary_query;
    static String partition_column;
    static String output_directory;
    static String threadcount;
    static String encoding = "UTF-8";

    static {
        InputStream in;
        Properties p = new Properties();
        try {
            in = new BufferedInputStream(new FileInputStream(
                    "conf/jobclient.properties"));
            p.load(in);
            connectionName = p.getProperty("CONNECTION_NAME");
            connectionUrl = p.getProperty("CONNECTION_URL");
            userName = p.getProperty("USER_NAME");
            passWord = p.getProperty("PASS_WORD");
            sqoopurl = p.getProperty("SQOOP_URL");
            System.out.println(p);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ct = new ClientTools(sqoopurl);
    }

    static void ceateJob() {
        if (null == ct.getConnection(connectionName)) {
            ct.dxParam.setParam(ct.dxParam.CONNECTION_NAME, connectionName);
            ct.dxParam.setParam(ct.dxParam.CONNECTION_URL, connectionUrl);
            ct.dxParam.setParam(ct.dxParam.JDBC_DRIVER,
                    "oracle.jdbc.driver.OracleDriver");
            ct.dxParam.setParam(ct.dxParam.USER_NAME, userName);
            ct.dxParam.setParam(ct.dxParam.PASS_WORD, passWord);
            ct.dxParam.setParam(ct.dxParam.MAX_CONNECTIONS, "0");
            xid = ct.createConnection();
            if (xid != -1)
                ct.descrbConnection(xid);
        } else {
            xid = ct.getConnection(connectionName).getPersistenceId();
            ct.descrbConnection(xid);
        }

        // job1
        if (null != ct.getJob(jobName) && ct.getJob(jobName).getEnabled()) {
            execJobid = ct.getJob(jobName).getPersistenceId();
            ct.descrbJob(execJobid);
        } else {
            ct.dxParam.setParam(ct.dxParam.IMPORT_JOBNAME, jobName);
            ct.dxParam.setParam(ct.dxParam.SQL, sql);
            ct.dxParam.setParam(ct.dxParam.BOUNDARY_QUERY, boundary_query);
            ct.dxParam.setParam(ct.dxParam.PARTITION_COLUMN, partition_column);
            ct.dxParam.setParam(ct.dxParam.COMPRESSION_FORMAT, "NONE");
            ct.dxParam.setParam(ct.dxParam.OUTPUT_DIRECTORY, output_directory);
            ct.dxParam.setParam(ct.dxParam.EXTRACTORS, threadcount);
            ct.dxParam.setParam(ct.dxParam.LOADERS, threadcount);
            execJobid = ct.createImportJob(xid);
            ct.descrbJob(execJobid);
        }
    }

    static void submissionJob() {
        if (-1 == execJobid) {
            execJobid = ct.getJob(jobName).getPersistenceId();
        }
        ct.jobSubmission(execJobid);
        while (ct.getSubmissionStatus(execJobid).isRunning()) {
            try {
                System.out.println("wait 3s");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (null == args || args.length == 0) {
            System.out
                    .println("Usage:JobClient {submission|connection -xid|job -jid|list connection/job|status -jid}");
            return;
        }
        if ("job".equals(args[0]) && args.length <= 1) {
            System.out.println("Usage:JobClient {job -jid}");
            return;
        }
        if ("connection".equals(args[0]) && args.length <= 1) {
            System.out.println("Usage:JobClient {connection -xid}");
            return;
        }
        if ("list".equals(args[0]) && args.length <= 1) {
            System.out.println("Usage:JobClient {list connection/job}");
            return;
        }
        if ("status".equals(args[0]) && args.length <= 1) {
            System.out.println("Usage:JobClient {status -jid}");
            return;
        }
        long jid;
        long xid;
        switch (args[0]) {
            case "connection":
                xid = Long.valueOf(args[1].replace("-", ""));
                ct.descrbConnection(xid);
                break;
            case "job":
                jid = Long.valueOf(args[1].replace("-", ""));
                ct.descrbJob(jid);
                break;
            case "submission":
                try {
                    String f = "";
                    BufferedReader bufr1;
                    System.out.println("Whether the re definition of job (y/n).");

                    bufr1 = new BufferedReader(new InputStreamReader(System.in,
                            encoding));
                    f = bufr1.readLine().trim().toLowerCase();
                    logger.debug("Whether the re definition of job (y/n). --{" + f
                            + "}");

                    switch (f) {
                        case "y":
                            System.out.println("The job name.");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            jobName = bufr1.readLine().trim();
                            logger.debug("The job name. --{" + jobName + "}");
                            System.out
                                    .println("Extraction of SQL, must contain where ${CONDITIONS}。\ne.g:select * from rb_tran_hist where ${CONDITIONS}");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            sql = bufr1.readLine().trim();

                            logger.debug("Extraction of SQL, must contain where ${CONDITIONS}. --{"
                                    + sql + "}");
                            System.out
                                    .println("Sectional column name partition_column, SQL output column name。\ne.g:seq_no");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            partition_column = bufr1.readLine().trim();
                            logger.debug("Sectional column name partition_column, SQL output column name. --{"
                                    + partition_column + "}");
                            System.out
                                    .println("Extraction of boundary_query query, query result set.\ne.g:select min(seq_no),max(seq_no) from rb_tran_hist where tran_date = to_date('20121214','yyyymmdd')");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            boundary_query = bufr1.readLine().trim();
                            logger.debug("Extraction of boundary_query query, query result set. --{"
                                    + boundary_query + "}");
                            System.out
                                    .println("The HDFS output file path output_directory.\ne.g:/user/rb_tran_hist/20121214");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            output_directory = bufr1.readLine().trim();
                            logger.debug("The HDFS output file path output_directory. --{"
                                    + output_directory + "}");
                            System.out.println("Thread number threadcount.\ne.g:10");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            threadcount = bufr1.readLine().trim();
                            logger.debug("Thread number threadcount. --{" + threadcount
                                    + "}");
                            ceateJob();
                            submissionJob();
                            break;
                        case "n":
                            System.out.println("The job name.");
                            bufr1 = new BufferedReader(new InputStreamReader(System.in,
                                    encoding));
                            jobName = bufr1.readLine().trim();
                            logger.debug("The job name. --{" + jobName + "}");
                            submissionJob();
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "list":
                if ("connection".equals(args[1])) {
                    List<MConnection> cons = ct.getConnections();
                    int[] col = {2, 4, 9, 7};

                    for (int i = 0; i < cons.size(); i++) {

                        MConnection mc = cons.get(i);
                        col[0] = String.valueOf(mc.getPersistenceId()).length() > col[0] ? String
                                .valueOf(mc.getPersistenceId()).length() : col[0];
                        col[1] = mc.getName().length() > col[1] ? mc.getName()
                                .length() : col[1];
                        col[2] = String.valueOf(mc.getConnectorId()).length() > col[2] ? String
                                .valueOf(mc.getConnectorId()).length() : col[2];
                        col[3] = String.valueOf(mc.getEnabled()).length() > col[3] ? String
                                .valueOf(mc.getEnabled()).length() : col[3];
                    }

                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+");
                    System.out.println("| " + StringUtils.rfillStr("Id", col[0], " ") + " | "
                            + StringUtils.rfillStr("Name", col[1], " ") + " | "
                            + StringUtils.rfillStr("Connector", col[2], " ") + " | "
                            + StringUtils.rfillStr("Enabled", col[3], " ") + " |");
                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+");
                    for (int i = 0; i < cons.size(); i++) {
                        MConnection mc = cons.get(i);
                        System.out.println("| "
                                + StringUtils.rfillStr(String.valueOf(mc.getPersistenceId()),
                                col[0], " ")
                                + " | "
                                + StringUtils.rfillStr(mc.getName(), col[1], " ")
                                + " | "
                                + StringUtils.rfillStr(String.valueOf(mc.getConnectorId()),
                                col[2], " ")
                                + " | "
                                + StringUtils.rfillStr(String.valueOf(mc.getEnabled()), col[3],
                                " ") + " |");
                    }
                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+");
                } else if ("job".equals(args[1])) {
                    List<MJob> jobs = ct.getJobs();
                    int[] col = {2, 4, 4, 10, 7};

                    for (int i = 0; i < jobs.size(); i++) {
                        MJob mj = jobs.get(i);
                        col[0] = String.valueOf(mj.getPersistenceId()).length() > col[0] ? String
                                .valueOf(mj.getPersistenceId()).length() : col[0];
                        col[1] = mj.getName().length() > col[1] ? mj.getName()
                                .length() : col[1];
                        col[2] = mj.getType().toString().length() > col[2] ? mj
                                .getType().toString().length() : col[2];
                        col[3] = String.valueOf(mj.getConnectionId()).length() > col[3] ? String
                                .valueOf(mj.getConnectionId()).length() : col[3];
                        col[4] = String.valueOf(mj.getEnabled()).length() > col[4] ? String
                                .valueOf(mj.getEnabled()).length() : col[4];
                    }

                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[4], "-") + "-+");
                    System.out.println("| " + StringUtils.rfillStr("Id", col[0], " ") + " | "
                            + StringUtils.rfillStr("Name", col[1], " ") + " | "
                            + StringUtils.rfillStr("Type", col[2], " ") + " | "
                            + StringUtils.rfillStr("Connection", col[3], " ") + " | "
                            + StringUtils.rfillStr("Enabled", col[4], " ") + " |");
                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[4], "-") + "-+");
                    for (int i = 0; i < jobs.size(); i++) {
                        MJob mj = jobs.get(i);
                        System.out.println("| "
                                + StringUtils.rfillStr(String.valueOf(mj.getPersistenceId()),
                                col[0], " ")
                                + " | "
                                + StringUtils.rfillStr(mj.getName(), col[1], " ")
                                + " | "
                                + StringUtils.rfillStr(mj.getType().toString(), col[2], " ")
                                + " | "
                                + StringUtils.rfillStr(String.valueOf(mj.getConnectionId()),
                                col[3], " ")
                                + " | "
                                + StringUtils.rfillStr(String.valueOf(mj.getEnabled()), col[4],
                                " ") + " |");
                    }
                    System.out.println("+-" + StringUtils.rfillStr("-", col[0], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[1], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[2], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[3], "-") + "-+-"
                            + StringUtils.rfillStr("-", col[4], "-") + "-+");
                } else {
                    System.out.println("Usage:JobClient {list connection/job}");
                }
                break;
            case "status":
                jid = Long.valueOf(args[1].replace("-", ""));
                ct.getSubmissionStatus(jid);
                break;
            default:
                break;
        }
    }
}
