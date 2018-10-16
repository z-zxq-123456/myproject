package com.dcits.ensemble.om.oms.module.middleware;
/**
 * dubbo路由规则信息* 
 * @author tangxlf
 * @date 2016-06-13 
 */
public class DubboRouterInfo {
   
	private String  group;
	
	private String  version;
	
	private String  consumerBlackName;//路由规则中消费者黑名单

	private String  providerWhiteName;//路由规则中提供者白名单

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getConsumerBlackName() {
		return consumerBlackName;
	}

	public void setConsumerBlackName(String consumerBlackName) {
		this.consumerBlackName = consumerBlackName;
	}

	public String getProviderWhiteName() {
		return providerWhiteName;
	}

	public void setProviderWhiteName(String providerWhiteName) {
		this.providerWhiteName = providerWhiteName;
	}

	@Override
	public String toString() {
		return "DubboRouterInfo [group=" + group + ", version=" + version
				+ ", consumerBlackName=" + consumerBlackName
				+ ", providerWhiteName=" + providerWhiteName + "]";
	}
	
	
	
}
