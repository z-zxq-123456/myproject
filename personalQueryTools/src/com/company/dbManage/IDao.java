package com.company.dbManage;

import java.sql.SQLException;

public interface IDao {

    public void getAcctInfo(String baseAcctNo) throws SQLException;

    public void getTranHistInfo(String baseAcctNo,String channelSeqNo) throws SQLException;
}
