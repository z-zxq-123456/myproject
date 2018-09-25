package com.dcits.galaxy.oms.monitor.health;

import java.util.ArrayList;
import java.util.List;

import com.dcits.galaxy.oms.monitor.api.health.HealthCheckResult;
import com.dcits.galaxy.oms.monitor.api.health.IAppHealthCheck;

/**
 * AppHealthCheckFactory* 
 * 健康检查工厂，内嵌各应用检查
 * @author tangxlf
 * @date 2015-10-20
 */
public class AppHealthCheckFactory implements IAppHealthCheck{
    List<IAppHealthCheck> checkList  = new ArrayList<IAppHealthCheck>();    
    private   int    healthStatus = 0;  //健康检查状态  0健康  1不健康
    private   StringBuilder message ;       //检查信息  
	@Override
	public HealthCheckResult checkAppHealth() {
		message = new StringBuilder();
		for(IAppHealthCheck appHealthCheck:checkList){
			HealthCheckResult  result = appHealthCheck.checkAppHealth();
			healthStatus+=result.getHealthStatus();
			if(result.getMessage()!=null&&!result.getMessage().equals("")){
				message.append(result.getMessage()+";");
			}
		}
		HealthCheckResult result = new HealthCheckResult();
		result.setMessage(message.toString());
		if(healthStatus>0){
			result.setHealthStatus(1);
		}else{
			result.setHealthStatus(0);
		}
		return result;
	}
	
	public List<IAppHealthCheck> getCheckList() {
		return checkList;
	}
	public void setCheckList(List<IAppHealthCheck> checkList) {
		this.checkList = checkList;
	}
	
	public static void main(String[] args){
		AppHealthCheckFactory factory  = new AppHealthCheckFactory();
		HealthCheckResult result =factory.checkAppHealth();
		System.out.println("message="+result.getMessage() + " healthStatus="+result.getHealthStatus());
	}

}
