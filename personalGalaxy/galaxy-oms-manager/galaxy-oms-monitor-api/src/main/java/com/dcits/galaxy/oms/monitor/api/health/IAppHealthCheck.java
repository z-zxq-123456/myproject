package com.dcits.galaxy.oms.monitor.api.health;

/**
 * IAppHealthCheck* 
 * 健康检查接口，所有的应用如果需要健康检查，需要实现此接口
 * @author tangxlf
 * @date 2015-10-20
 */
public interface IAppHealthCheck {
   public HealthCheckResult  checkAppHealth();
}
