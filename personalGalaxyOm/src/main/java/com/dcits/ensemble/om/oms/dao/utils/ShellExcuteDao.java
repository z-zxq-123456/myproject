package com.dcits.ensemble.om.oms.dao.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.dcits.ensemble.om.oms.module.utils.ShellResult;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * shell调用辅助类*
 * @author tangxlf
 * @date   2015-8-6
 */
public class ShellExcuteDao {
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	private final String host,//主机名称或者IP地址
	                     user,//用户名
	                     pwd; //密码
	private  Connection  conn = null;//远程服务器连接
	private  boolean     isAuthenticated = false;//远程连接是否验证通过
	private  static final int TIME_OUT = 1000*5*60;
	//private  Session     sess = null;//远程绘画
	
    /**
	  * 构造器
	  * @param String host  缓主机名称或者IP地址标志          
	  * @param String user  用户名
	  * @param String pwd   密码	 
	  */		
	public ShellExcuteDao(String host,String user,String pwd) {
		this.host = host;
		this.user = user;
		this.pwd = pwd;
		initConnection();
	}
    /**
	  * 初始化远程连接	  
	  */			
	private  void  initConnection() {		
		try {
			conn = new Connection(host);
			conn.connect();
			isAuthenticated= conn.authenticateWithPassword(user, pwd);
		} catch (IOException e) {
			throw new GalaxyException(e.getMessage());
		}		
	}
    /**
	  * 获取远程连接	  
	  */		
	public synchronized Connection getConnection(){
		if(isAuthenticated){
			return conn;
		}else{
			throw new GalaxyException("ssh 连接验证失败");
		}
	}
//	 /**
//	  * 获取远程会话 
//	 * @throws IOException 
//	  */		
//	public synchronized Session getSession() throws IOException{
//		if(sess!=null){
//			return sess;
//		}
//		return getConnection().openSession();
//	}
	 /**
	  * shell命令执行   该方法在一个Shell连接中可执行多个命令，最后需要手动关闭连接
	  * @param String mark  缓存标志        	
	  * @return String      返回主键对应记录的该字段取值
	  */	
	public ShellResult excuteCmd(String cmd){
		ShellResult  info = new ShellResult();
		Session sess = null;
		try {
			log.info("开始执行shell host="+ host +" user="+ user+" cmd="+  cmd);
			sess = getConnection().openSession();
			//sess = getSession();
		   	sess.execCommand(cmd);
		    InputStream stdout = new StreamGobbler(sess.getStdout());
		    InputStream stderr = new StreamGobbler(sess.getStderr());
		    @SuppressWarnings("resource")
		    BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
		    @SuppressWarnings("resource")
		    BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));	
		    while (true){
			   String line = stdoutReader.readLine();
			   if (line == null)break;
			   info.addOutLine(line);
		    }
		    while (true){
			   String line = stderrReader.readLine();
			   if (line == null)break;
			   info.addErrLine(line);
		    }
		    //if(sess.getExitStatus()!=null)
		    sess.waitForCondition(ChannelCondition.EXIT_STATUS,TIME_OUT);
		    info.setExitStatus(sess.getExitStatus());
		    if(errorHandler(info)){
		    	log.error("host="+ host +" user="+ user+" cmd="+  cmd+", 执行失败，返回信息为："+info);
		    	throw new GalaxyException("shell命令为："+cmd+",返回信息为："+info+",执行未成功，请检查!");
		    }
		    log.info("host="+ host +" user="+ user+" cmd="+  cmd+", 执行成功，返回信息为："+info);
		} catch (IOException e) {
			throw new GalaxyException(e.getMessage());
		}finally{
			 if(sess!=null)sess.close();
		}
		return info;
	}
	
	//判断shell访问是否出错
	private boolean errorHandler(ShellResult info){
		if(info.getExitStatus()!=null&&info.getExitStatus()!=0){
			if(info.getOutList().size()>0){
				return true;
			}
	    }
		if(info.getErrList().size()>0){
			return true;
		}
		return false;
	}
    /**
	  * 关闭远程连接	  
	  */		
	public void closeShellConn(){
		if(conn!=null){
			conn.close();
		}
	}
    /**
	  * 一次Shell命令执行 本次SHell连接只执行一个命令，执行完自动关闭远程连接
	  * @param String mark  缓存标志        	
	  * @return String      返回主键对应记录的该字段取值
	  */	
	public ShellResult oneExcuteCmd(String cmd) {		
		ShellResult  info = null;		
		try{
			info = excuteCmd(cmd);
		}finally{
			closeShellConn();
		}
		return info;
	}
}
