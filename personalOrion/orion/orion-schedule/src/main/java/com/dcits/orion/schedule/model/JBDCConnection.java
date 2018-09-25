/**   
 * <p>Title: JBDCConnection.java</p>
 * <p>Description: Sqoop JDBC连接容器</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0   
 */

package com.dcits.orion.schedule.model;

import com.dcits.orion.schedule.common.GlobalConfiguration;
import com.dcits.orion.schedule.util.DESUtil;
import com.dcits.galaxy.base.util.BeanUtils;

/**
 * @description JDBC连接容器
 * @version V1.0
 * @author Tim
 * @update 2014年9月15日 下午3:24:41 
 */

public class JBDCConnection {
	
	private String description;

	private String connection_name;

	private String connection_url;

	private String jdbc_driver;

	private String user_name;

	private String pass_word;

	private String max_connections;

	public String getConnection_name() {
		return connection_name;
	}

	public void setConnection_name(String connection_name) {
		this.connection_name = connection_name;
	}

	public String getConnection_url() {
		return connection_url;
	}

	public void setConnection_url(String connection_url) {
		this.connection_url = connection_url;
	}

	public String getJdbc_driver() {
		return jdbc_driver;
	}

	public void setJdbc_driver(String jdbc_driver) {
		this.jdbc_driver = jdbc_driver;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPass_word() {
		DESUtil des = new DESUtil(GlobalConfiguration.DES_KEY);
		return des.decryptStr(pass_word);
	}

	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}

	public String getMax_connections() {
		return max_connections;
	}

	public void setMax_connections(String max_connections) {
		this.max_connections = max_connections;
	}
	
	public String toString(){
		return BeanUtils.getString(this);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
