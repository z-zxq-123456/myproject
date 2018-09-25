package com.dcits.galaxy.dal.mybatis.table.router.rule;

import java.util.HashSet;
import java.util.Set;


public class TableRouteGroup {


    private TableRoute fallbackRoute = null;
    
    private Set<TableRoute> specificRoutes = new HashSet<TableRoute>();

    public TableRouteGroup() {
    }

    public TableRouteGroup(TableRoute fallbackRoute, Set<TableRoute> specificRoutes) {
        this.fallbackRoute = fallbackRoute;
        if (!(specificRoutes == null || specificRoutes.isEmpty())) {
            this.specificRoutes.addAll(specificRoutes);
        }
    }

    public TableRoute getFallbackRoute() {
        return fallbackRoute;
    }

    public void setFallbackRoute(TableRoute fallbackRoute) {
        this.fallbackRoute = fallbackRoute;
    }

    public Set<TableRoute> getSpecificRoutes() {
        return specificRoutes;
    }

    public void setSpecificRoutes(Set<TableRoute> specificRoutes) {
        this.specificRoutes = specificRoutes;
    }
}
