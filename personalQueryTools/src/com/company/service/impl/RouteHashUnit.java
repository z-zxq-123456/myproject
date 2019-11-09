package com.company.service.impl;

import com.company.service.IExecute;
import com.company.tools.ConfigUtils;

public class RouteHashUnit implements IExecute {

    private static RouteHashUnit routeUnit;

    public static RouteHashUnit getInstance(){
        if (routeUnit == null){
            routeUnit = new RouteHashUnit();
        }
        return routeUnit;
    }

    public void printRoute(String args){
        String [] commands = args.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("result = [ ")
                .append(" pk = ")
                .append(String.valueOf(Math.abs(commands[1].hashCode())% ConfigUtils.getPkSize()))
                .append(" tab = " ) .append(Math.abs(commands[1].hashCode())%ConfigUtils.getDataNum())
                .append(" ]");
        System.out.println(stringBuilder.toString());
    }

    @Override
    public void execute(String args) {
        printRoute(args);
    }
}
