package com.dcits.galaxy.dal.mybatis.router.index.rule;

import java.util.HashSet;
import java.util.Set;

/**
 * 保存一组具有相同sqlmap的路由规则
 * 可以通过fallbackRoute设置默认路由
 * @author huang.zhe
 */
public class IndexRouteGroup {

	/**
	 * 一般为Expression为空的Route
	 * 当specificRoutes中定义的规则未匹配到且fallbackRoute不为null时
	 * 返回此Route
	 */
    private IndexRoute fallbackRoute = null;
    
    /**
     * 具体进行匹配的路由规则集合
     */
    private Set<IndexRoute> specificRoutes = new HashSet<IndexRoute>();

    public IndexRouteGroup() {
    }

    public IndexRouteGroup(IndexRoute fallbackRoute, Set<IndexRoute> specificRoutes) {
        this.fallbackRoute = fallbackRoute;
        if (!(specificRoutes == null || specificRoutes.isEmpty())) {
            this.specificRoutes.addAll(specificRoutes);
        }
    }

    public IndexRoute getFallbackRoute() {
        return fallbackRoute;
    }

    public void setFallbackRoute(IndexRoute fallbackRoute) {
        this.fallbackRoute = fallbackRoute;
    }

    public Set<IndexRoute> getSpecificRoutes() {
        return specificRoutes;
    }

    public void setSpecificRoutes(Set<IndexRoute> specificRoutes) {
        this.specificRoutes = specificRoutes;
    }
}
