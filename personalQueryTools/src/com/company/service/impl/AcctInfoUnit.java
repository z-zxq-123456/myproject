package com.company.service.impl;


import com.company.dbManage.DbManger;
import com.company.service.IExecute;
import com.company.service.IService;
import com.company.tools.CheckTools;

import java.sql.SQLException;

/**
 * 查看账户属性
 */
public class AcctInfoUnit implements IExecute, IService {

    private static final String space = " ";

    @Override
    public void printLineBefore() {
        System.out.println("=================== acct info Before  ==================");
    }

    @Override
    public void printLineAfter() {
        System.out.println("=================== acct info after  ==================");
    }

    @Override
    public void execute(String args) {

        String[] commands = args.split(space);

        if (!CheckTools.checkAcctInfo(commands)){
            throw new IllegalArgumentException("the input args size is illegal!");
        }

        String baseAcctNo = commands[1];
        if (!CheckTools.checkBaseAcctNo(baseAcctNo)){
            throw new IllegalArgumentException("baseAcctNo is illegal!");
        }
        this.showAcctInfo(baseAcctNo);
    }

    @Override
    public void calcTranHistPartation(String baseAcctNo, String channelSeqNo) {
        throw new IllegalStateException("the op is not require!");
    }

    @Override
    public void showAcctInfo(String baseAcctNo) {

        try {
            DbManger.getInstance().getAcctInfo(baseAcctNo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
