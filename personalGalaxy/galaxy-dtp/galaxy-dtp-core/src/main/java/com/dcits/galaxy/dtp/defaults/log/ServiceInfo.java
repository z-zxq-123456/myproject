package com.dcits.galaxy.dtp.defaults.log;

import java.io.Serializable;

public class ServiceInfo implements Serializable{
	
	private static final long serialVersionUID = -8025439142153515214L;

	private Object[] args = null;
	
	private Class<?>[] argsType = null;
	
	private String MethodName = null;
	
	private String serviceClass = null;
	
	private ServiceType serviceType = null;

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public Class<?>[] getArgsType() {
		return argsType;
	}

	public void setArgsType(Class<?>[] argsType) {
		this.argsType = argsType;
	}

	public String getMethodName() {
		return MethodName;
	}

	public void setMethodName(String methodName) {
		MethodName = methodName;
	}

	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = ServiceType.valueOf(serviceType);
	}
}
