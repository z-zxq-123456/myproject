package com.dcits.galaxy.ftp;

/**
 * Created by Tim on 2015/10/13.
 */
public class Configuration {

    /**
     * 主机地址
     */
    private String host;

    /**
     * 主机端口
     */
    private int port;

    /**
     * 用户名
     */
    private String user;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 是否加密
     */
    private Boolean decrypt = false;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Boolean isDecrypt() {
        return decrypt;
    }

    public void setDecrypt(Boolean decrypt) {
        this.decrypt = decrypt;
    }
}
