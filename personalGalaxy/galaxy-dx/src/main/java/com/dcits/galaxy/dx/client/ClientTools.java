/**
 * <p>Title: ClientTools.java</p>
 * <p>Description: 对SqoopClient的二次封装</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */
package com.dcits.galaxy.dx.client;

import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MConnection;
import org.apache.sqoop.model.MConnectionForms;
import org.apache.sqoop.model.MForm;
import org.apache.sqoop.model.MInput;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MJob.Type;
import org.apache.sqoop.model.MJobForms;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.submission.SubmissionStatus;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.dx.common.SCError;
import com.dcits.galaxy.dx.exception.SCException;
import com.dcits.galaxy.dx.param.DxParam;

/**
 * @author Tim
 * @version V1.0
 * @description 对SqoopClient的二次封装
 * @update 2014年9月15日 下午2:16:16
 */

public class ClientTools {

    private static Logger logger = LoggerFactory.getLogger(ClientTools.class);

    public DxParam dxParam = new DxParam();

    /**
     * Default Localhost Sqoop Server, Port[12000].
     */
    private String url = "http://localhost:12000/sqoop/";

    /**
     * SqoopClient
     */
    private SqoopClient client;

    /**
     * Create Sqoop client.
     */
    public ClientTools() {
        client = new SqoopClient(this.url);
    }

    /**
     * Create Sqoop Client.
     *
     * @param url Sqoop Server URL
     */
    public ClientTools(String url) {
        this.url = url;
        client = new SqoopClient(this.url);
    }

    /**
     * Set new server URL.
     *
     * @param newUrl Server URL
     */
    public void setServerUrl(String newUrl) {
        client.setServerUrl(newUrl);
    }

    /**
     * Create the JDBC connection on server.
     *
     * @return Connnection id
     */
    public long createConnection() {
        MConnection mc = getConnection(dxParam
                .getParam(dxParam.CONNECTION_NAME));
        if (null != mc && mc.getEnabled())
            throw new SCException(SCError.SC_003, "Connection name ["
                    + dxParam.getParam(dxParam.CONNECTION_NAME)
                    + "] already exists!");
        // Dummy connection object,default connector id is 1
        MConnection newCon = client.newConnection(1);

        // Get connection and framework forms. Set name for connection
        MConnectionForms conForms = newCon.getConnectorPart();
        MConnectionForms frameworkForms = newCon.getFrameworkPart();
        newCon.setName(dxParam.getParam(dxParam.CONNECTION_NAME));

        // Set connection forms values
        conForms.getStringInput("connection.connectionString").setValue(
                dxParam.getParam(dxParam.CONNECTION_URL));
        conForms.getStringInput("connection.jdbcDriver").setValue(
                dxParam.getParam(dxParam.JDBC_DRIVER));
        conForms.getStringInput("connection.username").setValue(
                dxParam.getParam(dxParam.USER_NAME));
        conForms.getStringInput("connection.password").setValue(
                dxParam.getParam(dxParam.PASS_WORD));

        frameworkForms.getIntegerInput("security.maxConnections")
                .setValue(
                        Integer.parseInt(dxParam.getParam(
                                dxParam.MAX_CONNECTIONS, "0")));

        Status status = client.createConnection(newCon);
        printMessage(newCon.getConnectorPart().getForms());
        printMessage(newCon.getFrameworkPart().getForms());
        if (status.canProceed()) {
            logger.debug("Created. New Connection ID : "
                    + newCon.getPersistenceId());
            return newCon.getPersistenceId();
        } else {
            throw new SCException(SCError.SC_005, "status ["
                    + status.canProceed() + "]");
        }
    }

    /**
     * Retrieve connection with given id.
     *
     * @param xid Connnection id
     * @return
     */
    public MConnection getConnection(long xid) {
        return client.getConnection(xid);
    }

    /**
     * Retrieve connection with given id.
     *
     * @param cname connection name
     * @return
     */
    public MConnection getConnection(String cname) {
        List<MConnection> mccs = getConnections();
        Iterator<MConnection> it = mccs.iterator();
        MConnection mc;
        for (; it.hasNext(); ) {
            mc = it.next();
            if (cname.equals(mc.getName()) && mc.getEnabled()) {
                return mc;
            }
        }
        return null;
    }

    /**
     * Retrieve list of all connections.
     *
     * @return
     */
    public List<MConnection> getConnections() {
        return client.getConnections();
    }

    /**
     * @param xid Connection id
     * @return
     */
    public long createImportJob(long xid) {
        MJob mj = getJob(dxParam.getParam(dxParam.IMPORT_JOBNAME));
        if (null != mj && mj.getEnabled())
            throw new SCException(SCError.SC_002, "Job name ["
                    + dxParam.getParam(dxParam.IMPORT_JOBNAME)
                    + "] already exists!");
        return forceCreateImportJob(xid);
    }

    public long forceCreateImportJob(long xid) {
        // Creating dummy job object
        MJob newjob = client.newJob(xid,
                org.apache.sqoop.model.MJob.Type.IMPORT);
        MJobForms connectorForm = newjob.getConnectorPart();
        MJobForms frameworkForm = newjob.getFrameworkPart();

        newjob.setName(dxParam.getParam(dxParam.IMPORT_JOBNAME));
        // Database configuration
        // Input either table name or sql
        // 要全量导出一张表，请填写表名，table name 和 table sql statement不能同时配置
        if (null == dxParam.getParam(dxParam.TABLENAME, null)
                && null == dxParam.getParam(dxParam.SQL, null)) {
            throw new SCException(SCError.SC_002, "parameter ["
                    + dxParam.TABLENAME + "] or [" + dxParam.SQL
                    + "] must be defined!");
        } else if (null != dxParam.getParam(dxParam.TABLENAME, null)) {
            connectorForm.getStringInput(dxParam.SCHEMANAME).setValue(
                    dxParam.getParam(dxParam.SCHEMANAME, null));
            connectorForm.getStringInput(dxParam.TABLENAME).setValue(
                    dxParam.getParam(dxParam.TABLENAME));
        } else if (null != dxParam.getParam(dxParam.SQL, null)) {
            connectorForm.getStringInput(dxParam.SQL).setValue(
                    dxParam.getParam(dxParam.SQL));
            connectorForm.getStringInput(dxParam.BOUNDARY_QUERY).setValue(
                    dxParam.getParam(dxParam.BOUNDARY_QUERY, null));
        }
        connectorForm.getStringInput(dxParam.COLUMNS).setValue(
                dxParam.getParam(dxParam.COLUMNS, null));
        connectorForm.getStringInput(dxParam.PARTITION_COLUMN).setValue(
                dxParam.getParam(dxParam.PARTITION_COLUMN));
        // Set boundary value only if required
        // Output configurations
        frameworkForm.getEnumInput(dxParam.STORAGE_TYPE).setValue("HDFS");
        frameworkForm.getEnumInput(dxParam.OUTPUT_FORMAT).setValue(
                dxParam.getParam(dxParam.OUTPUT_FORMAT, "TEXT_FILE"));// Other
        frameworkForm.getEnumInput(dxParam.COMPRESSION_FORMAT).setValue(
                dxParam.getParam(dxParam.COMPRESSION_FORMAT, "NONE"));// Other
        // option:
        // SEQUENCE_FILE
        frameworkForm.getStringInput(dxParam.OUTPUT_DIRECTORY).setValue(
                dxParam.getParam(dxParam.OUTPUT_DIRECTORY));

        // Job resources
        frameworkForm.getIntegerInput(dxParam.EXTRACTORS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.EXTRACTORS, "10")));
        frameworkForm.getIntegerInput(dxParam.LOADERS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.LOADERS, "10")));

        Status status = client.createJob(newjob);
        // Print errors or warnings
        printMessage(newjob.getConnectorPart().getForms());
        printMessage(newjob.getFrameworkPart().getForms());
        if (status.canProceed()) {
            logger.debug("New Job ID: " + newjob.getPersistenceId());
            return newjob.getPersistenceId();
        } else {
            throw new SCException(SCError.SC_005, "status ["
                    + status.canProceed() + "]");
        }
    }

    /**
     * @param xid Connection id
     * @return
     */
    public long createExportJob(long xid) {
        MJob mj = getJob(dxParam.getParam(dxParam.EXPROT_JOBNAME));
        if (null != mj && mj.getEnabled())
            throw new SCException(SCError.SC_004, "Job name ["
                    + dxParam.getParam(dxParam.EXPROT_JOBNAME)
                    + "] already exists!");
        return forceCreateExportJob(xid);
    }

    public long forceCreateExportJob(long xid) {
        // Creating dummy job object
        MJob newjob = client.newJob(xid,
                org.apache.sqoop.model.MJob.Type.EXPORT);
        MJobForms connectorForm = newjob.getConnectorPart();
        MJobForms frameworkForm = newjob.getFrameworkPart();

        newjob.setName(dxParam.getParam(dxParam.EXPROT_JOBNAME));
        // Database configuration
        connectorForm.getStringInput(dxParam.SCHEMANAME).setValue(
                dxParam.getParam(dxParam.SCHEMANAME, null));
        // Input either table name or sql
        connectorForm.getStringInput(dxParam.TABLENAME).setValue(
                dxParam.getParam(dxParam.TABLENAME));
        // connectorForm.getStringInput("table.sql").setValue("select id,name from table where ${CONDITIONS}");
        connectorForm.getStringInput(dxParam.COLUMNS).setValue(
                dxParam.getParam(dxParam.COLUMNS, null));

        // Input configurations
        frameworkForm.getStringInput(dxParam.INPUT_DIRECTORY).setValue(
                dxParam.getParam(dxParam.INPUT_DIRECTORY));
        // Job resources
        frameworkForm.getIntegerInput(dxParam.EXTRACTORS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.EXTRACTORS, "10")));
        frameworkForm.getIntegerInput(dxParam.LOADERS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.LOADERS, "10")));

        Status status = client.createJob(newjob);
        // Print errors or warnings
        printMessage(newjob.getConnectorPart().getForms());
        printMessage(newjob.getFrameworkPart().getForms());
        if (status.canProceed()) {
            logger.debug("New Job ID: " + newjob.getPersistenceId());
            return newjob.getPersistenceId();
        } else {
            throw new SCException(SCError.SC_005, "status ["
                    + status.canProceed() + "]");
        }
    }

    /**
     * Retrieve job for given id.
     *
     * @param jname Job name
     * @return
     */
    public MJob getJob(String jname) {
        List<MJob> mjs = getJobs();
        Iterator<MJob> it = mjs.iterator();
        MJob mj;
        for (; it.hasNext(); ) {
            mj = it.next();
            if (jname.equals(mj.getName()) && mj.getEnabled()) {
                return mj;
            }
        }
        return null;
    }

    /**
     * Retrieve job for given id.
     *
     * @param jid Job id
     * @return
     */
    public MJob getJob(long jid) {
        return client.getJob(jid);
    }

    /**
     * Retrieve list of all jobs.
     *
     * @return
     */
    public List<MJob> getJobs() {
        return client.getJobs();
    }

    /**
     * Update connection on the server.
     *
     * @param xid Connection id
     * @return
     */
    public long updateConnection(long xid) {
        return updateConnection(getConnection(xid));
    }

    /**
     * Update connection on the server.
     *
     * @param connection Connection that should be updated
     * @return
     */
    public long updateConnection(MConnection connection) {
        // Get connection and framework forms. Set name for connection
        MConnectionForms conForms = connection.getConnectorPart();
        MConnectionForms frameworkForms = connection.getFrameworkPart();
        // Set connection forms values
        conForms.getStringInput("connection.connectionString").setValue(
                dxParam.getParam(dxParam.CONNECTION_URL));
        conForms.getStringInput("connection.jdbcDriver").setValue(
                dxParam.getParam(dxParam.JDBC_DRIVER));
        conForms.getStringInput("connection.username").setValue(
                dxParam.getParam(dxParam.USER_NAME));
        conForms.getStringInput("connection.password").setValue(
                dxParam.getParam(dxParam.PASS_WORD));

        frameworkForms.getIntegerInput("security.maxConnections")
                .setValue(
                        Integer.parseInt(dxParam.getParam(
                                dxParam.MAX_CONNECTIONS, "0")));
        Status status = client.updateConnection(connection);
        // Print errors or warnings
        printMessage(connection.getConnectorPart().getForms());
        printMessage(connection.getFrameworkPart().getForms());
        if (status.canProceed()) {
            logger.debug("Update Connection ID: "
                    + connection.getPersistenceId());
            return connection.getPersistenceId();
        } else {
            throw new SCException(SCError.SC_005, "status ["
                    + status.canProceed() + "]");
        }
    }

    /**
     * Delete connection with given id.
     *
     * @param xid Connection id
     */
    public void deleteConnection(long xid) {
        client.deleteConnection(xid);
    }

    /**
     * Update job on server.
     *
     * @param jid Job id
     * @return
     */
    public long updateJob(long jid) {
        return updateJob(getJob(jid));
    }

    /**
     * Update job on server.
     *
     * @param job Job that should be updated
     * @return
     */
    public long updateJob(MJob job) {
        MJobForms connectorForm = job.getConnectorPart();
        MJobForms frameworkForm = job.getFrameworkPart();
        if (null == dxParam.getParam(dxParam.TABLENAME, null)
                && null == dxParam.getParam(dxParam.SQL, null)) {
            throw new SCException(SCError.SC_002, "parameter ["
                    + dxParam.TABLENAME + "] or [" + dxParam.SQL
                    + "] must be defined!");
        } else if (null != dxParam.getParam(dxParam.TABLENAME, null)) {
            connectorForm.getStringInput(dxParam.SCHEMANAME).setValue(
                    dxParam.getParam(dxParam.SCHEMANAME, null));
            connectorForm.getStringInput(dxParam.TABLENAME).setValue(
                    dxParam.getParam(dxParam.TABLENAME));
        } else if (null != dxParam.getParam(dxParam.SQL, null)) {
            connectorForm.getStringInput(dxParam.SQL).setValue(
                    dxParam.getParam(dxParam.SQL));
            connectorForm.getStringInput(dxParam.BOUNDARY_QUERY).setValue(
                    dxParam.getParam(dxParam.BOUNDARY_QUERY, null));
        }
        connectorForm.getStringInput(dxParam.COLUMNS).setValue(
                dxParam.getParam(dxParam.COLUMNS, null));

        if (job.getType() == Type.IMPORT) {
            connectorForm.getStringInput(dxParam.PARTITION_COLUMN).setValue(
                    dxParam.getParam(dxParam.PARTITION_COLUMN));
            // Set boundary value only if required
            // Output configurations
            frameworkForm.getEnumInput(dxParam.STORAGE_TYPE).setValue("HDFS");
            frameworkForm.getEnumInput(dxParam.OUTPUT_FORMAT).setValue(
                    dxParam.getParam(dxParam.OUTPUT_FORMAT, "TEXT_FILE"));// Other
            frameworkForm.getEnumInput(dxParam.COMPRESSION_FORMAT).setValue(
                    dxParam.getParam(dxParam.COMPRESSION_FORMAT, "NONE"));// Other
            // option:
            // SEQUENCE_FILE
            frameworkForm.getStringInput(dxParam.OUTPUT_DIRECTORY).setValue(
                    dxParam.getParam(dxParam.OUTPUT_DIRECTORY));
        } else if (job.getType() == Type.EXPORT) {
            frameworkForm.getStringInput(dxParam.INPUT_DIRECTORY).setValue(
                    dxParam.getParam(dxParam.INPUT_DIRECTORY));
        }

        // Job resources
        frameworkForm.getIntegerInput(dxParam.EXTRACTORS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.EXTRACTORS, "10")));
        frameworkForm.getIntegerInput(dxParam.LOADERS).setValue(
                Integer.parseInt(dxParam.getParam(dxParam.LOADERS, "10")));
        Status status = client.updateJob(job);
        // Print errors or warnings
        printMessage(job.getConnectorPart().getForms());
        printMessage(job.getFrameworkPart().getForms());
        if (status.canProceed()) {
            logger.debug("Update Job ID: " + job.getPersistenceId());
            return job.getPersistenceId();
        } else {
            throw new SCException(SCError.SC_005, "status ["
                    + status.canProceed() + "]");
        }
    }

    /**
     * Delete job with given id.
     *
     * @param jid Job id
     */
    public void deleteJob(long jid) {
        client.deleteJob(jid);
    }

    /**
     * Start job with given id.
     *
     * @param jid Job id
     * @return
     */
    public String jobSubmission(long jid) {
        // Job submission start
        MSubmission submission = client.startSubmission(jid);
        logger.debug("Status : " + submission.getStatus());
        if (submission.getStatus().isRunning()
                && submission.getProgress() != -1) {
            logger.debug("Progress : "
                    + String.format("%.2f %%", submission.getProgress() * 100));
        }
        logger.debug("Hadoop job id :" + submission.getExternalId());
        logger.debug("Job link : " + submission.getExternalLink());
        Counters counters = submission.getCounters();
        if (counters != null) {
            logger.debug("Counters:");
            for (CounterGroup group : counters) {
                logger.debug("\t" + group.getName());
                for (Counter counter : group) {
                    logger.debug("\t\t" + counter.getName() + ": "
                            + counter.getValue());
                }
            }
        }
        if (submission.getExceptionInfo() != null) {
            throw new SCException(SCError.SC_006, submission.getExceptionInfo());
        }

        return submission.getExternalId();
    }

    /**
     * Get status for given job id.
     *
     * @param jid Job id
     */
    public SubmissionStatus getSubmissionStatus(long jid) {
        // Check job status
        MSubmission submission = client.getSubmissionStatus(jid);
        SubmissionStatus sms = submission.getStatus();
        logger.debug("job id[" + jid + "]" + sms);
        if (sms.isRunning() && submission.getProgress() != -1) {
            logger.debug("Progress : "
                    + String.format("%.2f %%", submission.getProgress() * 100));
        }
        logger.debug("Hadoop job id :" + submission.getExternalId());
        logger.debug("Job link : " + submission.getExternalLink());
        Counters counters = submission.getCounters();
        if (counters != null) {
            logger.debug("Counters:");
            for (CounterGroup group : counters) {
                logger.debug("\t" + group.getName());
                for (Counter counter : group) {
                    logger.debug("\t\t" + counter.getName() + ": "
                            + counter.getValue());
                }
            }
        }

        if (submission.getExceptionInfo() != null) {
            throw new SCException(SCError.SC_006, "Job[" + jid
                    + "],Exception info : " + submission.getExceptionInfo());
        }
        return sms;
    }

    /**
     * Stop job with given id.
     *
     * @param jid Job id
     */
    public void stopSubmission(long jid) {
        // Stop a running job
        MSubmission submission = client.stopSubmission(jid);
        if (submission.getStatus().isRunning()
                && submission.getProgress() != -1) {
            logger.debug("Progress : "
                    + String.format("%.2f %%", submission.getProgress() * 100));
        }
    }

    /**
     * Use for describing job.
     *
     * @param xid Connection id
     */
    public void descrbConnection(long xid) {
        // Use getJob(xid) for describing job.
        MConnection mc = getConnection(xid);
        logger.debug("ConnectionId:" + mc.getPersistenceId());
        logger.debug("ConnectionName:" + mc.getName());
        logger.debug("CreationDate:" + mc.getCreationDate());
        logger.debug("ConnectorId:" + mc.getConnectorId());
        logger.debug("CreationUser:" + mc.getCreationUser());
        logger.debug("Enabled:" + mc.getEnabled() + "\n");
        describe(mc.getConnectorPart().getForms(),
                client.getResourceBundle(mc.getConnectorId()));
        describe(mc.getFrameworkPart().getForms(),
                client.getFrameworkResourceBundle());
    }

    /**
     * Use getJob(jid) for describing job.
     *
     * @param jid Job id
     */
    public void descrbJob(long jid) {
        // Use getJob(jid) for describing job.
        MJob mj = getJob(jid);
        logger.debug("JobId:" + mj.getPersistenceId());
        logger.debug("JobName:" + mj.getName());
        logger.debug("JobType:" + mj.getType());
        logger.debug("CreationDate:" + mj.getCreationDate());
        logger.debug("ConnectionId:" + mj.getConnectionId());
        logger.debug("CreationUser:" + mj.getCreationUser());
        logger.debug("Enabled:" + mj.getEnabled() + "\n");
        describe(mj.getConnectorPart().getForms(),
                client.getResourceBundle(mj.getConnectorId()));
        describe(mj.getFrameworkPart().getForms(),
                client.getFrameworkResourceBundle());
    }

    /**
     * Print form message
     *
     * @param formList connection or job forms
     */
    private void printMessage(List<MForm> formList) {
        for (MForm form : formList) {
            List<MInput<?>> inputlist = form.getInputs();
            if (form.getValidationMessage() != null) {
                logger.debug("Form message: " + form.getValidationMessage());
            }
            for (MInput<?> minput : inputlist) {
                if (minput.getValidationStatus() == Status.ACCEPTABLE) {
                    logger.debug("Warning:" + minput.getValidationMessage());
                } else if (minput.getValidationStatus() == Status.UNACCEPTABLE) {
                    throw new SCException(SCError.SC_005, "Error:"
                            + minput.getValidationMessage());
                }
            }
        }
    }

    /**
     * Print connection or job forms
     *
     * @param forms    connection or job forms
     * @param resource framework bundle.
     */
    private void describe(List<MForm> forms, ResourceBundle resource) {
        for (MForm mf : forms) {
            logger.debug(resource.getString(mf.getLabelKey()) + ":");
            List<MInput<?>> mis = mf.getInputs();
            for (MInput<?> mi : mis) {
                logger.debug(resource.getString(mi.getLabelKey()) + " : "
                        + mi.getValue());
            }
        }
    }
}
