package com.company.service.impl;

import com.company.service.IExecute;
import com.company.service.IService;
import com.company.tools.CheckTools;

public class TranPartUnit implements IService, IExecute {

    private static final String space = " ";

    @Override
    public void execute(String args) {

        String[] commands = args.split(space);

        if (CheckTools.checkPart(commands)){
            throw new IllegalArgumentException("the input args size is illegal!");
        }

        String baseAcctNo = commands[1];
        String channelSeqNo = commands[2];

        if (!CheckTools.checkBaseAcctNo(baseAcctNo)){
            throw new IllegalArgumentException("baseAcctNo is illegal!");
        }

        if (!CheckTools.checkChannelSeqNo(channelSeqNo)){
            throw new IllegalArgumentException("channelSeqNo is illegal!");
        }

        this.calcTranHistPartation(baseAcctNo,channelSeqNo);

    }


    @Override
    public void calcTranHistPartation(String baseAcctNo, String channelSeqNo) {


    }

    @Override
    public void showAcctInfo(String baseAcctNo) {
        throw new IllegalStateException("the op is not require!");
    }
}
