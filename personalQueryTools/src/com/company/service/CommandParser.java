package com.company.service;

import com.company.service.impl.AcctInfoUnit;
import com.company.service.impl.TranPartUnit;

import java.util.concurrent.atomic.AtomicReference;

public class CommandParser {

    private static final String space = " ";

    private static AtomicReference<IExecute> tranPart = new AtomicReference<>();
    private static AtomicReference<IExecute> acctInfo = new AtomicReference<>();

    public static IExecute parseArgs(String args){

        if (args == null)
            System.out.println("command is required!");

        if (args.trim().length()  == 0){
            System.out.println("command length is illegal!,must contain type and param! like <INFO/PART acctNo clientNo channelSeqNo >");
        }

        String[] commands = args.split(space);
        switch (Command.valueOf(commands[0].trim().toUpperCase())){
            case PART:
                if (tranPart.get() == null){
                    tranPart.compareAndSet(null,new TranPartUnit());
                }
                return tranPart.get();
            case INFO:
                if (acctInfo.get() == null){
                    acctInfo.compareAndSet(null,new AcctInfoUnit());
                }
                return acctInfo.get();
            default:
                System.out.println("not include the op, please check it !");
                return null;
        }
    }
}
