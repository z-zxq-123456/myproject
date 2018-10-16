package com.dcits.ensemble.om.oms.module.middleware;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePk;

/**
 * Created by oms on 2016/02/16 10:54:34.
 */
public class EcmMidwareZkSer extends AbstractBean {

	// serialVersionUID
	private static final long serialVersionUID = 1L;

	/**
	 * This field is ECM_MIDWARE_ZKINT.ZKINT_ID
	 */
	@TablePk(index = 1)
	private String zkSerId;

	/**
	 * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID
	 */
	private String zkIpPort;
	/**
	 * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID
	 */
	private String midwareId;
	/**
	 * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID
	 */
	private String zkIp;
	/**
	 * This field is ECM_MIDWARE_DBINT.SER_NAME
	 */
	private String zkSerName;
	/**
	 * This field is ECM_MIDWARE_ZKINT.ZKINT_NAME
	 */
	private String zkSerTypeName;
	/**
	 * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID
	 */
	private String zkSerType;
	private String zkSerGrop;
	private String zkVersion;
	private String ZkReversion;
	/**
	 * This field is ECM_MIDWARE_ZKINT.ZKINT_STATUS
	 */
	private String zkSerStatus;
	/**
	 * This field is ECM_MIDWARE_ZKINT.MIDWARE_ID
	 */
	private String zkSerStatusName;

	public String getZkSerId() {
		return zkSerId;
	}
	public void setZkSerId(String string) {
		this.zkSerId = string;
	}
	public String getZkSerName() {
		return zkSerName;
	}
	public void setZkSerName(String zkSerName) {
		this.zkSerName = zkSerName;
	}
	public String getZkSerType() {
		return zkSerType;
	}
	public void setZkSerType(String zkSerType) {
		this.zkSerType = zkSerType;
	}
	public String getZkSerTypeName() {
		return zkSerTypeName;
	}
	public void setZkSerTypeName(String zkSerTypeName) {
		this.zkSerTypeName = zkSerTypeName;
	}
	public String getZkSerGrop() {
		return zkSerGrop;
	}
	public void setZkSerGrop(String zkSerGrop) {
		this.zkSerGrop = zkSerGrop;
	}
	public String getZkSerStatus() {
		return zkSerStatus;
	}
	public void setZkSerStatus(String zkSerStatus) {
		this.zkSerStatus = zkSerStatus;
	}
	public String getZkSerStatusName() {
		return zkSerStatusName;
	}
	public void setZkSerStatusName(String zkSerStatusName) {
		this.zkSerStatusName = zkSerStatusName;
	}

	public String getZkIpPort() {
		return zkIpPort;
	}
	public void setZkIpPort(String zkIpPort) {
		this.zkIpPort = zkIpPort;
	}
	public String getZkIp() {
		return zkIp;
	}
	public void setZkIp(String zkIp) {
		this.zkIp = zkIp;
	}

	public String getMidwareId() {
		return midwareId;
	}
	public void setMidwareId(String midwareId) {
		this.midwareId = midwareId;
	}

	public String getZkVersion() {
		return zkVersion;
	}
	public void setZkVersion(String zkVersion) {
		this.zkVersion = zkVersion;
	}

	public String getZkReversion() {
		return ZkReversion;
	}
	public void setZkReversion(String zkReversion) {
		ZkReversion = zkReversion;
	}
	@Override
	public String toString() {
		return "EcmMidwareZkSer [zkSerId=" + zkSerId + ", zkIpPort=" + zkIpPort
				+ ", midwareId=" + midwareId + ", zkIp=" + zkIp
				+ ", zkSerName=" + zkSerName + ", zkSerTypeName="
				+ zkSerTypeName + ", zkSerType=" + zkSerType + ", zkSerGrop="
				+ zkSerGrop + ", zkVersion=" + zkVersion + ", ZkReversion="
				+ ZkReversion + ", zkSerStatus=" + zkSerStatus
				+ ", zkSerStatusName=" + zkSerStatusName + "]";
	}
	
}