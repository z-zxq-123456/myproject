package com.company.service;

import com.company.service.impl.AcctInfoUnit;
import com.company.service.impl.RouteHashUnit;
import com.company.service.impl.RouteUnit;
import com.company.service.impl.TranPartUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CommandParser {

    private static final String space = " ";

    private static AtomicReference<IExecute> tranPart = new AtomicReference<>();
    private static AtomicReference<IExecute> acctInfo = new AtomicReference<>();
    private static List<String> commandHist = new ArrayList<>();
    public static IExecute parseArgs(String args){

        if (args == null)
            System.out.println("command is required!");

        if (args.trim().length()  == 0){
            System.out.println("command length is illegal!,must contain type and param! like <INFO/PART acctNo clientNo channelSeqNo >");
        }

        String[] commands = args.split(space);

        try {
            switch (Command.valueOf(commands[0].trim().toUpperCase())){
                case TRAN:
                    if (tranPart.get() == null){
                        tranPart.compareAndSet(null,new TranPartUnit());
                    }
                    addToList(args);
                    return tranPart.get();
                case INFO:
                    if (acctInfo.get() == null){
                        acctInfo.compareAndSet(null,new AcctInfoUnit());
                    }
                    addToList(args);
                    return acctInfo.get();
                case ROUTE:
                    addToList(args);
                    return RouteHashUnit.getInstance();
                case CALC:
                    addToList(args);
                    return RouteUnit.getInstance();
                case HELP:
                    help();
                    return null;
                case HIST:
                    printHistory();
                    return null;
                default:
                    System.out.println("not include the op, please check it !");
                    return null;
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return null;
    }

    private static void printHistory(){
        for (String s:commandHist){
            System.out.println(s);
        }
    }

    private static void addToList(String command){
        if (commandHist.size() <= 10){
            commandHist.add(command);
        }else {
            commandHist.remove(9);
            commandHist.add(command);
        }
    }

    private static void help(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("INFO 账户").append("\r\n").append("--查看账户属性,透支属性").append("\r\n");
        stringBuffer.append("TRAN 账户 全局流水号").append("\r\n").append("--查看交易流水信息").append("\r\n");
        stringBuffer.append("ROUTE 路由字段").append("\r\n").append("--hash路由").append("\r\n");
        stringBuffer.append("CALC 路由字段").append("\r\n").append("--整形路由").append("\r\n");
        stringBuffer.append("HIST").append("\r\n").append("--查看历史记录").append("\r\n");
        System.out.println(stringBuffer.toString());
    }

}
