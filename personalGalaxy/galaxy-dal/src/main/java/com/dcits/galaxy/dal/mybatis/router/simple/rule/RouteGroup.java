package com.dcits.galaxy.dal.mybatis.router.simple.rule;

import java.util.HashSet;
import java.util.Set;

/**
 * 保存一组具有相同sqlmap的路由规则
 * 可以通过fallbackRoute设置默认路由
 * @author fan.kaijie
 */
public class RouteGroup {

	/**
	 * 一般为Expression为空的Route
	 * 当specificRoutes中定义的规则未匹配到且fallbackRoute不为null时
	 * 返回此Route，
	 */
    private Route fallbackRoute = null;
    
    /**
     * 具体进行匹配的路由规则集合
     */
    private Set<Route> specificRoutes = new HashSet<Route>();

    public RouteGroup() {
    }

    public RouteGroup(Route fallbackRoute, Set<Route> specificRoutes) {
        this.fallbackRoute = fallbackRoute;
        if (!(specificRoutes == null || specificRoutes.isEmpty())) {
            this.specificRoutes.addAll(specificRoutes);
        }
    }

    public Route getFallbackRoute() {
        return fallbackRoute;
    }

    public void setFallbackRoute(Route fallbackRoute) {
        this.fallbackRoute = fallbackRoute;
    }

    public Set<Route> getSpecificRoutes() {
        return specificRoutes;
    }

    public void setSpecificRoutes(Set<Route> specificRoutes) {
        this.specificRoutes = specificRoutes;
    }
}
