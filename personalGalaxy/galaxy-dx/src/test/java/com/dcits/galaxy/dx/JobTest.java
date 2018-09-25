package com.dcits.galaxy.dx;

import com.dcits.galaxy.dx.client.ClientTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

public class JobTest {
    private static final Logger logger = LoggerFactory
            .getLogger(JobTest.class);
    static ClientTools ct;

    static String connectionName = "connection_ensmbale";
    static String jobNmame1 = "job_rb_tran_hist";
    static String jobNmame2 = "job_rb_tran_def";
    static String jobNmame3 = "job_rb_acct_type";

    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

    static {
        try {
            ct = new ClientTools("http://192.168.161.221:12000/sqoop/");
        } catch (Throwable t) {
        }
    }

    public static void main(String[] args) {
        long xid;
        long jid1;
        long jid2;
        long jid3;
        // TODO Auto-generated method stub
        if (null == ct.getConnection(connectionName)) {
            ct.dxParam.setParam(ct.dxParam.CONNECTION_NAME, connectionName);
            ct.dxParam.setParam(ct.dxParam.CONNECTION_URL,
                    "jdbc:oracle:thin:@192.168.161.56:1521:ybdb");
            ct.dxParam.setParam(ct.dxParam.JDBC_DRIVER,
                    "oracle.jdbc.driver.OracleDriver");
            ct.dxParam.setParam(ct.dxParam.USER_NAME, "symbols");
            ct.dxParam.setParam(ct.dxParam.PASS_WORD, "symbols");
            ct.dxParam.setParam(ct.dxParam.MAX_CONNECTIONS, "0");
            xid = ct.createConnection();
            if (xid != -1)
                ct.descrbConnection(xid);
        } else {
            xid = ct.getConnection(connectionName).getPersistenceId();
            ct.descrbConnection(xid);
        }

        // job1
        if (null == ct.getJob(jobNmame1)) {
            ct.dxParam.setParam(ct.dxParam.IMPORT_JOBNAME, jobNmame1);
            ct.dxParam
                    .setParam(
                            ct.dxParam.SQL,
                            "SELECT * FROM (SELECT to_char(rth.seq_no) source_ref_no, ra.base_acct_no acct_no, rth.ccy,to_char(rth.tran_date,'yyyymmdd') tran_date, to_char(rth.tran_amt) amount, rth.branch tran_branch, rth.cr_dr_maint_ind cr_dr_ind, rth.source_type, rth.officer_id, rth.tran_type, to_char( rth.effect_date,'yyyymmdd') effect_date, to_char(  rth.post_date,'yyyymmdd') post_date, rth.reference, rth.bank_seq_no, ra.acct_type, ra.branch open_branch, ra.client_no FROM rb_tran_hist rth, rb_acct ra, rb_tran_def rtd WHERE rth.internal_key = ra.internal_key AND rth.tran_type = rtd.tran_type) temp WHERE ${CONDITIONS}");
            ct.dxParam
                    .setParam(
                            ct.dxParam.BOUNDARY_QUERY,
                            "SELECT MIN (seq_no), MAX (seq_no)  FROM rb_tran_hist WHERE tran_date = (SELECT last_run_date FROM fm_system)");
            ct.dxParam.setParam(ct.dxParam.PARTITION_COLUMN, "source_ref_no");
            ct.dxParam.setParam(ct.dxParam.COMPRESSION_FORMAT, "NONE");
            ct.dxParam.setParam(ct.dxParam.OUTPUT_DIRECTORY,
                    "/user/20140808/rb_tran_hist");
            // + postfix);
            jid1 = ct.createImportJob(xid);
            ct.descrbJob(jid1);
        } else {
            jid1 = ct.getJob(jobNmame1).getPersistenceId();
            ct.descrbJob(jid1);
        }

        ct.jobSubmission(jid1);
        while (ct.getSubmissionStatus(jid1).isRunning()) {
            try {
                System.out.println("wait 3s");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // job2
        if (null == ct.getJob(jobNmame2)) {
            ct.dxParam.setParam(ct.dxParam.IMPORT_JOBNAME, jobNmame2);
            ct.dxParam.setParam(ct.dxParam.SQL, "SELECT * FROM rb_tran_type");
            ct.dxParam.setParam(ct.dxParam.BOUNDARY_QUERY, null);
            ct.dxParam.setParam(ct.dxParam.PARTITION_COLUMN, "source_ref_no");
            ct.dxParam.setParam(ct.dxParam.COMPRESSION_FORMAT, "NONE");
            ct.dxParam.setParam(ct.dxParam.OUTPUT_DIRECTORY,
                    "/user/20140808/rb_tran_def");
            // + postfix);
            jid2 = ct.createImportJob(xid);
            ct.descrbJob(jid2);
        } else {
            jid2 = ct.getJob(jobNmame2).getPersistenceId();
            ct.descrbJob(jid2);
        }

        ct.jobSubmission(jid2);
        while (ct.getSubmissionStatus(jid2).isRunning()) {
            try {
                System.out.println("wait 3s");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // job3
        if (null == ct.getJob(jobNmame3)) {
            ct.dxParam.setParam(ct.dxParam.IMPORT_JOBNAME, jobNmame3);
            ct.dxParam
                    .setParam(
                            ct.dxParam.SQL,
                            "SELECT * FROM (SELECT to_char(rth.seq_no) source_ref_no, ra.base_acct_no acct_no, rth.ccy,to_char(rth.tran_date,'yyyymmdd') tran_date, to_char(rth.tran_amt) amount, rth.branch tran_branch, rth.cr_dr_maint_ind cr_dr_ind, rth.source_type, rth.officer_id, rth.tran_type, to_char( rth.effect_date,'yyyymmdd') effect_date, to_char(  rth.post_date,'yyyymmdd') post_date, rth.reference, rth.bank_seq_no, ra.acct_type, ra.branch open_branch, ra.client_no FROM rb_tran_hist rth, rb_acct ra, rb_tran_def rtd WHERE rth.internal_key = ra.internal_key AND rth.tran_type = rtd.tran_type) temp WHERE ${CONDITIONS}");
            ct.dxParam
                    .setParam(
                            ct.dxParam.BOUNDARY_QUERY,
                            "SELECT MIN (seq_no), MAX (seq_no)  FROM rb_tran_hist WHERE tran_date = (SELECT last_run_date FROM fm_system)");
            ct.dxParam.setParam(ct.dxParam.PARTITION_COLUMN, "source_ref_no");
            ct.dxParam.setParam(ct.dxParam.COMPRESSION_FORMAT, "NONE");
            ct.dxParam.setParam(ct.dxParam.OUTPUT_DIRECTORY,
                    "/user/20140808/rb_tran_def");
            // + postfix);
            jid3 = ct.createImportJob(xid);
            ct.descrbJob(jid3);
        } else {
            jid3 = ct.getJob(jobNmame3).getPersistenceId();
            ct.descrbJob(jid3);
        }

        ct.jobSubmission(jid3);
        while (ct.getSubmissionStatus(jid3).isRunning()) {
            try {
                System.out.println("wait 3s");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
