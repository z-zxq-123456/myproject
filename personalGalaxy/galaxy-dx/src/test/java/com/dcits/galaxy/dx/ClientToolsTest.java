package com.dcits.galaxy.dx;

import junit.framework.TestCase;

public class ClientToolsTest extends TestCase {
	public void testJobsDescrbJob() {
		assertTrue(true);
	}
//	static ClientTools ct;
//	long exec_jid;
//	final boolean local = false;
//	final boolean table = false;
//	final boolean createJobExec = true;
//	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//	static {
//		ct = new ClientTools("http://10.10.10.100:12000/sqoop/");
//		PropertyConfigurator.configure("conf/log4j.properties");
//	}
//
//	/**
//	 * Create the test case
//	 *
//	 * @param testName
//	 *            name of the test case
//	 */
//	public ClientToolsTest(String testName) {
//		super(testName);
//	}
//
//	/**
//	 * @return the suite of tests being tested
//	 */
//	public static Test suite() {
//		return new TestSuite(ClientToolsTest.class);
//	}
//
//	public void testJobsDescrbJob() {
//		List<MJob> job = ct.getJobs();
//		Iterator<MJob> it = job.iterator();
//		for (; it.hasNext();) {
//			ct.descrbJob(it.next().getPersistenceId());
//		}
//		assertTrue(true);
//	}
//
//	public void testConnectionsDescrbJob() {
//		List<MConnection> cc = ct.getConnections();
//		Iterator<MConnection> it = cc.iterator();
//		for (; it.hasNext();) {
//			ct.descrbConnection(it.next().getPersistenceId());
//		}
//		assertTrue(true);
//	}
//
//	public void testGetSubmissionStatus() {
//		List<MJob> job = ct.getJobs();
//		Iterator<MJob> it = job.iterator();
//		for (; it.hasNext();) {
//			MJob mj = it.next();
//			if (mj.getEnabled())
//				ct.getSubmissionStatus(mj.getPersistenceId());
//		}
//		assertTrue(true);
//	}
//
//	public void testCreateConnection() {
//		if (null == ct.getConnection("sysmbols_10_100")) {
//			SqoopParam.setParam(SqoopParam.CONNECTION_NAME, "sysmbols_10_100");
//			SqoopParam.setParam(SqoopParam.CONNECTION_URL,
//					"jdbc:oracle:thin:@10.10.10.100:1521:presale1");
//			SqoopParam.setParam(SqoopParam.JDBC_DRIVER,
//					"oracle.jdbc.driver.OracleDriver");
//			SqoopParam.setParam(SqoopParam.USER_NAME, "symbols");
//			SqoopParam.setParam(SqoopParam.PASS_WORD, "symbols");
//			SqoopParam.setParam(SqoopParam.MAX_CONNECTIONS, "0");
//			long xid = ct.createConnection();
//			if (xid != -1)
//				ct.descrbConnection(xid);
//			assertTrue(true);
//		}
//
//		if (null == ct.getConnection("mytest_0_102")) {
//			SqoopParam.setParam(SqoopParam.CONNECTION_NAME, "mytest_0_102");
//			SqoopParam.setParam(SqoopParam.CONNECTION_URL,
//					"jdbc:oracle:thin:@192.168.0.102:1521:orcl");
//			SqoopParam.setParam(SqoopParam.JDBC_DRIVER,
//					"oracle.jdbc.driver.OracleDriver");
//			SqoopParam.setParam(SqoopParam.USER_NAME, "mytest");
//			SqoopParam.setParam(SqoopParam.PASS_WORD, "mytest");
//			SqoopParam.setParam(SqoopParam.MAX_CONNECTIONS, "0");
//			long xid = ct.createConnection();
//			if (xid != -1)
//				ct.descrbConnection(xid);
//			assertTrue(true);
//		}
//	}
//
//	public void testCreateImportJob() {
//		if (null == ct.getConnection("sysmbols_10_100")
//				|| null == ct.getConnection("mytest_0_102")) {
//			testCreateConnection();
//		}
//		String postfix = df.format(new Date());
//		long xid = local ? ct.getConnection("mytest_0_102").getPersistenceId()
//				: ct.getConnection("sysmbols_10_100").getPersistenceId();
//		if (createJobExec){
//			if (local) {
//				SqoopParam.setParam(SqoopParam.IMPORT_JOBNAME, "test_" + postfix);
//				SqoopParam
//						.setParam(
//								SqoopParam.SQL,
//								"SELECT * FROM (SELECT to_char(id) cid,owner FROM test) temp WHERE ${CONDITIONS}");
//				SqoopParam.setParam(SqoopParam.BOUNDARY_QUERY,
//						"SELECT 1,10 FROM dual");
//				SqoopParam.setParam(SqoopParam.PARTITION_COLUMN, "cid");
//				SqoopParam.setParam(SqoopParam.OUTPUT_DIRECTORY, "/user/test/"
//						+ postfix);
//				exec_jid = ct.createImportJob(xid);
//				ct.descrbJob(exec_jid);
//			} else {
//				SqoopParam.setParam(SqoopParam.IMPORT_JOBNAME, "rb_tran_hist_"
//						+ postfix);
//				SqoopParam
//						.setParam(
//								SqoopParam.SQL,
//								"SELECT * FROM (SELECT to_char(rth.seq_no) source_ref_no, ra.base_acct_no acct_no, rth.ccy,to_char(rth.tran_date,'yyyymmdd') tran_date, to_char(rth.tran_amt) amount, rth.branch tran_branch, rth.cr_dr_maint_ind cr_dr_ind, rth.source_type, rth.officer_id, rth.tran_type, to_char( rth.effect_date,'yyyymmdd') effect_date, to_char(  rth.post_date,'yyyymmdd') post_date, rth.reference, rth.bank_seq_no, ra.acct_type, ra.branch open_branch, ra.client_no FROM rb_tran_hist rth, rb_acct ra, rb_tran_def rtd WHERE rth.internal_key = ra.internal_key AND rth.tran_type = rtd.tran_type) temp WHERE ${CONDITIONS}");
//				SqoopParam
//						.setParam(
//								SqoopParam.BOUNDARY_QUERY,
//								"SELECT MIN (seq_no), MAX (seq_no)  FROM rb_tran_hist WHERE tran_date = (SELECT last_run_date FROM fm_system)");
//				SqoopParam.setParam(SqoopParam.PARTITION_COLUMN, "source_ref_no");
//				/**
//				 * 0 : NONE<br>
//				 * 1 : DEFAULT<br>
//				 * 2 : DEFLATE<br>
//				 * 3 : GZIP<br>
//				 * 4 : BZIP2<br>
//				 * 5 : LZO<br>
//				 * 6 : LZ4<br>
//				 * 7 : SNAPPY
//				 */
//				SqoopParam.setParam(SqoopParam.COMPRESSION_FORMAT, "NONE");
//				SqoopParam.setParam(SqoopParam.OUTPUT_DIRECTORY, "/user/tran_hist/20140808");
////						+ postfix);
//				exec_jid = ct.createImportJob(xid);
//				ct.descrbJob(exec_jid);
//			}
//		} else {
//			//update job
//		}
//
//		ct.jobSubmission(exec_jid);
//		while (ct.getSubmissionStatus(exec_jid).isRunning()) {
//			try {
//				System.out.println("wait 3000");
//				Thread.sleep(3000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		assertTrue(true);
//	}
}
