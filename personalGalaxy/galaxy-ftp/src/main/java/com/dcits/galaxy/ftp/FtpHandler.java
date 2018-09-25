package com.dcits.galaxy.ftp;

import com.dcits.galaxy.base.util.DESUtils;
import com.dcits.galaxy.ftp.exception.FtpException;
import com.sshtools.j2ssh.configuration.SshConnectionProperties;

import net.sf.jftp.config.Settings;
import net.sf.jftp.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * Created by Tim on 2015/10/13.
 */
public class FtpHandler implements ConnectionListener {

    private static final Logger logger = LoggerFactory
            .getLogger(FtpHandler.class);


    @Override
    public void updateRemoteDirectory(BasicConnection basicConnection) {
        if (logger.isDebugEnabled())
            logger.debug("new path is: " + basicConnection.getPWD());
    }

    @Override
    public void updateProgress(String file, String type, long bytes) {
        if (logger.isDebugEnabled())
            logger.debug("file is: " + file + ", type is:" + type + ", bytes is:" + bytes);
    }

    @Override
    public void connectionInitialized(BasicConnection basicConnection) {
        isThere = true;
    }

    @Override
    public void connectionFailed(BasicConnection basicConnection, String s) {
        throw new FtpException("connection failed!");
    }

    @Override
    public void actionFinished(BasicConnection basicConnection) {
    }

    private boolean isThere = false;

    private ConnectionHandler handler = new ConnectionHandler();

    private HashMap connectPool = new HashMap();

    private Settings setting = new Settings();

    private BasicConnection initConnection(ServerType serverType, Configuration conf) {
        isThere = false;
        setting.setProperty("jftp.ftpPasvMode", false);//关闭被动模式
        setting.setProperty("jftp.disableLog", true);//设置Log功能关闭，关闭控制台msg
        if (serverType == ServerType.FTP) {//建立ftp链接
            FtpConnection con = new FtpConnection(conf.getHost(), conf.getPort(), "/");
            con.addConnectionListener(this);
            con.setConnectionHandler(handler);
            con.login(conf.getUser(), conf.isDecrypt() ? DESUtils.decrypt(conf.getPasswd()) : conf.getPasswd());
            while (!isThere) {
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (isThere) {
                return con;
            } else {
                return null;
            }
        } else if (serverType == ServerType.SFTP) {//建立sftp连接
            SftpConnection sftp_con = null;
            SshConnectionProperties pro = new SshConnectionProperties();
            pro.setHost(conf.getHost());
            pro.setPort(conf.getPort());
            sftp_con = new SftpConnection(pro);
            sftp_con.addConnectionListener(this);
            if (sftp_con.login(conf.getUser(), conf.isDecrypt() ? DESUtils.decrypt(conf.getPasswd()) : conf.getPasswd())) {
                return sftp_con;
            } else {
                return null;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public BasicConnection getConnection(ServerType serverType, Configuration conf) {
        if (this.connectPool.isEmpty()) {
            BasicConnection tmp = this.initConnection(serverType, conf);
            if (tmp == null || !tmp.isConnected()) {
                throw new FtpException("Login Failed!");
            } else {
                this.connectPool.put(conf.getHost(), tmp);
                return tmp;
            }
        } else {
            BasicConnection connection = (BasicConnection) this.connectPool.get(conf.getHost());
            if (connection != null) {
                return connection;
            } else {
                BasicConnection tmp = this.initConnection(serverType, conf);
                if (tmp == null || !tmp.isConnected()) {
                    throw new FtpException("Login Failed!");
                } else {
                    this.connectPool.put(conf.getHost(), tmp);
                    return tmp;
                }
            }
        }
    }

    public boolean upload(BasicConnection con, String ftpDir, String localDir, String file) {
        if (con == null) {
            return false;
        } else {
            //make dirs
            String path = "";
            int stat;
            boolean f;
            String[] paths = ftpDir.split("/");
            for (int i = 0; i < paths.length; i++) {
                path += "/" + paths[i];
                if (!con.chdir(path)) {
                    f = con.mkdir(path);
                    if (!f)
                        throw new FtpException("Create Folder Failed!");
                }
            }
            f = con.chdir(ftpDir);
            if (!f)
                throw new FtpException("Invalid FTP path!");
            f = con.setLocalPath(localDir);
            if (!f)
                throw new FtpException("Set Local Path failed!");
            stat = con.upload(file);
            //FTP返回1，SFTP返回0为正常
            if (stat == 0 || stat == 1)
                return true;
            else
                throw new FtpException("Upload Failed! stat:" + stat);
        }
    }

    public boolean download(BasicConnection con, String ftpDir, String localDir, String file) {
        if (con == null) {
            return false;
        } else {
            int stat;
            boolean f;
            f = con.chdir(ftpDir);
            if (!f)
                throw new FtpException("Invalid FTP path!");
            f = con.setLocalPath(localDir);
            if (!f)
                throw new FtpException("Set Local Path failed!");
            stat = con.download(file);
            //FTP返回1，SFTP返回0为正常
            if (stat == 0 || stat == 1)
                return true;
            else
                throw new FtpException("Download Failed! stat:" + stat);
        }
    }

    /**
     * 释放Ftp连接
     *
     * @throws Exception
     */
    public void release() {
        try {
            if (connectPool != null && !connectPool.isEmpty()) {
                Object[] obj = connectPool.values().toArray();
                for (int i = 0; i < obj.length; i++) {
                    BasicConnection connect = (BasicConnection) obj[i];
                    if (connect != null && connect.isConnected()) {
                        connect.disconnect();
                    }
                }
                connectPool.clear();
            }
        } catch (Exception e) {
            throw new FtpException("Disconnect Failed!", e);
        }
    }
}
