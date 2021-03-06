package com.company.service.impl;

import com.company.service.IExecute;
import com.company.tools.ConfigUtils;

import java.io.*;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RouteUnit implements IExecute {

    private static RouteUnit routeUnit;

    @Override
    public void printLineBefore() {
        System.out.println("=================== calc Before  ===================");
    }

    @Override
    public void printLineAfter() {
        System.out.println("=================== calc after  ===================");
    }

    public static RouteUnit getInstance(){
        if (routeUnit == null){
            routeUnit = new RouteUnit();
        }
        return routeUnit;
    }

    public void printRoute(String args){
        String [] commands = args.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("env [ ").append(ConfigUtils.env).append(" ]")
                 .append(" pkSize [ ").append(ConfigUtils.pkSize).append(" ]")
                .append(" dataNum [ ").append(ConfigUtils.dataNum).append(" ]");
        stringBuilder.append("result = [ ")
                .append(" pk = ")
                .append(String.valueOf(Long.valueOf(commands[1]) % ConfigUtils.getPkSize()))
                .append(" tab = " ) .append(Long.valueOf(commands[1])%ConfigUtils.getDataNum())
                .append(" ]");
        System.out.println(stringBuilder.toString());
    }

    @Override
    public void execute(String args) {
        printRoute(args);
    }
}
