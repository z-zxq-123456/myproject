package com.dcits.ensemble.om.oms.module.fullgalaxy;

import com.dcits.galaxy.base.bean.AbstractBean;


public class EcmServerIndex extends AbstractBean {

    // serialVersionUID
    private static final long serialVersionUID = 1L;
    
    private Integer serMoniId;
    
    private Integer serId;
    
    private String  serMoniDate; 
    
    private String  serMoniTime; 
    
    private String  serMoniCpu;
    
    private String  serMoniMem;
    
    private String  serMoniIo;
    
    private String  serMoniNet;

	private String refreshTime;

	private long  serMoniMs;

	public Integer getSerMoniId() {
		return serMoniId;
	}

	public void setSerMoniId(Integer serMoniId) {
		this.serMoniId = serMoniId;
	}

	public Integer getSerId() {
		return serId;
	}

	public void setSerId(Integer serId) {
		this.serId = serId;
	}

	public String getSerMoniDate() {
		return serMoniDate;
	}

	public void setSerMoniDate(String serMoniDate) {
		this.serMoniDate = serMoniDate;
	}

	public String getSerMoniTime() {
		return serMoniTime;
	}

	public void setSerMoniTime(String serMoniTime) {
		this.serMoniTime = serMoniTime;
	}

	public String getSerMoniCpu() {
		return serMoniCpu;
	}

	public void setSerMoniCpu(String serMoniCpu) {
		this.serMoniCpu = serMoniCpu;
	}

	public String getSerMoniMem() {
		return serMoniMem;
	}

	public void setSerMoniMem(String serMoniMem) {
		this.serMoniMem = serMoniMem;
	}

	public String getSerMoniIo() {
		return serMoniIo;
	}

	public void setSerMoniIo(String serMoniIo) {
		this.serMoniIo = serMoniIo;
	}

	public String getSerMoniNet() {
		return serMoniNet;
	}

	public void setSerMoniNet(String serMoniNet) {
		this.serMoniNet = serMoniNet;
	}

	@Override
	public String toString() {
		return "EcmServerIndex [serMoniId=" + serMoniId + ", serId=" + serId
				+ ", serMoniDate=" + serMoniDate + ", serMoniTime="
				+ serMoniTime + ", serMoniCpu=" + serMoniCpu + ", serMoniMem="
				+ serMoniMem + ", serMoniIo=" + serMoniIo + ", serMoniNet="
				+ serMoniNet + "]";
	}


	public long getSerMoniMs() {
		return serMoniMs;
	}

	public void setSerMoniMs(long serMoniMs) {
		this.serMoniMs = serMoniMs;
	}

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}
}
