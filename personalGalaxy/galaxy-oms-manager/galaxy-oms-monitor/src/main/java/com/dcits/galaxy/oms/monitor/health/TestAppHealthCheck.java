package com.dcits.galaxy.oms.monitor.health;

import com.dcits.galaxy.oms.monitor.api.health.HealthCheckResult;
import com.dcits.galaxy.oms.monitor.api.health.IAppHealthCheck;

public class TestAppHealthCheck implements IAppHealthCheck {

	@Override
	public HealthCheckResult checkAppHealth() {
		HealthCheckResult result = new HealthCheckResult();	
		result.setHealthStatus(0);		
		return result;
	}

}
