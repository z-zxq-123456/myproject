package com.dcits.ensemble.om.oms.module.middleware;

import java.util.ArrayList;
import java.util.List;




/**
 * dubbo服务路由规则信息集合* 
 * @author tangxlf
 * @date 2016-06-13 
 */
public class DubboServiceRouterRuleList {
   
	
	private List<DubboRouterInfo> providerRuleList = new ArrayList<DubboRouterInfo>();//提供者路由规则信息集合
	
	private List<DubboRouterInfo> consumerRuleList = new ArrayList<DubboRouterInfo>();//消费者路由规则信息集合
	
	
	public List<DubboRouterInfo> getProviderRuleList() {
		return providerRuleList;
	}

	public void setProviderRuleList(List<DubboRouterInfo> providerRuleList) {
		this.providerRuleList = providerRuleList;
	}

	public List<DubboRouterInfo> getConsumerRuleList() {
		return consumerRuleList;
	}

	public void setConsumerRuleList(List<DubboRouterInfo> consumerRuleList) {
		this.consumerRuleList = consumerRuleList;
	}

	public void addProviderRuleInfo(DubboRouterInfo ruleInfo){
		providerRuleList.add(ruleInfo);
	}
	
	public void addConsumerRuleInfo(DubboRouterInfo ruleInfo){
		consumerRuleList.add(ruleInfo);
	}

	@Override
	public String toString() {
		return "DubboServiceRouterRuleList [providerRuleList="
				+ providerRuleList + ", consumerRuleList=" + consumerRuleList
				+ "]";
	}
}
