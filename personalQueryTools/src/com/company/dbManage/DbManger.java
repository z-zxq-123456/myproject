package com.company.dbManage;

import com.company.tools.ConfigUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询管理类
 */
public class DbManger implements IDao {

    private String acctInfo = "INTERNAL_KEY,ACCT_SEQ_NO,PROD_TYPE,ACCT_STATUS,INT_IND,ROUTER_KEY";
    private String acctAttachInfo = "GL_CODE,BAL_UPD_TYPE,OD_FACILITY,BAL_DIRECT_TYPE";
    private String mbTranHistInfo = "INTERNAL_KEY,TRAN_DATE,TRAN_TYPE,EVENT_TYPE,CR_DR_MAINT_IND,TRAN_AMT,BRANCH,BASE_ACCT_NO,ACCT_SEQ_NO,PROD_TYPE,OTH_SEQ_NO,OTH_INTERNAL_KEY,OTH_BASE_ACCT_NO,OTH_ACCT_SEQ_NO,OTH_PROD_TYPE,TRAN_STATUS,REFERENCE";

    private static DbManger manger;
    public static DbManger getInstance(){
        if (manger == null){
            manger = new DbManger();
        }
        return manger;
    }

    @Override
    public void getAcctInfo(String baseAcctNo) throws SQLException {

        Connection conn = DbConn.getConnection();
        Statement statement = DbConn.getStatement(conn);
        ResultSet resultSet = DbConn.getResultSet(statement,getQureyAcctSql(baseAcctNo));
        ResultSet resultSet2 = null;

        if (!resultSet.next()){
            throw new RuntimeException("账户不存在或已销户!");
        }

        try{
            resultSet.previous();
            List<Map<String,Object>> mbacctList = new ArrayList();
            List mbacctattach = new ArrayList();
            while(resultSet.next()){

                Map<String,Object> mbAcct = new HashMap();
                mbAcct.put("INTERNAL_KEY",resultSet.getString("INTERNAL_KEY"));
                mbAcct.put("ACCT_SEQ_NO",resultSet.getString("ACCT_SEQ_NO"));
                mbAcct.put("PROD_TYPE",resultSet.getString("PROD_TYPE"));
                mbAcct.put("ACCT_STATUS",resultSet.getString("ACCT_STATUS"));
                mbAcct.put("INT_IND",resultSet.getString("INT_IND"));
                mbAcct.put("ROUTER_KEY",resultSet.getString("ROUTER_KEY"));
                mbacctList.add(mbAcct);
            }

            for (Map mbacct:mbacctList){

                resultSet2 = DbConn.getResultSet(statement,
                        getQueryAttach((String) mbacct.get("INTERNAL_KEY"),(String) mbacct.get("ROUTER_KEY")));

                while(resultSet2.next()){
                    Map<String,Object> mbAcctAttach = new HashMap();
                    mbAcctAttach.put("GL_CODE",resultSet2.getString("GL_CODE"));
                    mbAcctAttach.put("BAL_UPD_TYPE",resultSet2.getString("BAL_UPD_TYPE"));
                    mbAcctAttach.put("OD_FACILITY",resultSet2.getString("OD_FACILITY"));
                    mbAcctAttach.put("BAL_DIRECT_TYPE",resultSet2.getString("BAL_DIRECT_TYPE"));
                    mbacctattach.add(mbAcctAttach);
                }
            }

            PrintUtils.print("mbAcct",acctInfo,mbacctList);
            PrintUtils.print("mbAcctAttach",acctAttachInfo,mbacctattach);
        }finally {
            DbConn.close(statement);
            DbConn.close(resultSet);
            DbConn.close(resultSet2);
            DbConn.close(conn);
        }

    }



    @Override
    public void getTranHistInfo(String baseAcctNo, String channelSeqNo) throws SQLException {
        Connection conn = DbConn.getConnection();
        Statement statement = DbConn.getStatement(conn);
        ResultSet resultSet = null,resultSet2 = null,resultSet3 = null;
        try{


             resultSet = DbConn.getResultSet(statement,getQureyAcctSql(baseAcctNo));
            if (!resultSet.next()){
                throw new RuntimeException("账户不存在或已销户!");
            }
            String internalKey = null,baseRoute = null;
            String routerKey = null;

            resultSet.previous();
            int count = 0;
            while(resultSet.next()){
                count++;
                internalKey = resultSet.getString("INTERNAL_KEY");
                baseRoute = resultSet.getString("ROUTER_KEY");
            }

            if (count > 1){
                throw new RuntimeException("暂不支持查询AIO账户交易流水!");
            }

            resultSet2 = DbConn.getResultSet(statement, getQueryAttach(internalKey,baseRoute));
            while(resultSet2.next()){
                if (("T").equals(resultSet2.getString("BAL_UPD_TYPE"))){
                    routerKey =String.valueOf(Math.abs(channelSeqNo.hashCode()));
                }else {
                    routerKey =  baseRoute;
                }
            }

            resultSet3= DbConn.getResultSet(statement,getQueryTransql(channelSeqNo,routerKey));
            if (!resultSet3.next()){
                throw new RuntimeException("无改流水记录!");
            }
            List<Map<String,Object>> mbtranHistList = new ArrayList();
            resultSet3.previous();

            while(resultSet3.next()){

                Map<String,Object> mbTranHist = new HashMap();
                mbTranHist.put("INTERNAL_KEY",resultSet3.getString("INTERNAL_KEY"));
                mbTranHist.put("TRAN_DATE",resultSet3.getString("TRAN_DATE"));
                mbTranHist.put("TRAN_TYPE",resultSet3.getString("TRAN_TYPE"));
                mbTranHist.put("EVENT_TYPE",resultSet3.getString("EVENT_TYPE"));
                mbTranHist.put("CR_DR_MAINT_IND",resultSet3.getString("CR_DR_MAINT_IND"));
                mbTranHist.put("TRAN_AMT",resultSet3.getString("TRAN_AMT"));
                mbTranHist.put("BRANCH",resultSet3.getString("BRANCH"));
                mbTranHist.put("BASE_ACCT_NO",resultSet3.getString("BASE_ACCT_NO"));
                mbTranHist.put("ACCT_SEQ_NO",resultSet3.getString("ACCT_SEQ_NO"));
                mbTranHist.put("PROD_TYPE",resultSet3.getString("PROD_TYPE"));
                mbTranHist.put("OTH_SEQ_NO",resultSet3.getString("OTH_SEQ_NO"));
                mbTranHist.put("OTH_INTERNAL_KEY",resultSet3.getString("OTH_INTERNAL_KEY"));
                mbTranHist.put("OTH_BASE_ACCT_NO",resultSet3.getString("OTH_BASE_ACCT_NO"));
                mbTranHist.put("OTH_ACCT_SEQ_NO",resultSet3.getString("OTH_ACCT_SEQ_NO"));
                mbTranHist.put("OTH_PROD_TYPE",resultSet3.getString("OTH_PROD_TYPE"));
                mbTranHist.put("TRAN_STATUS",resultSet3.getString("TRAN_STATUS"));
                mbTranHist.put("REFERENCE",resultSet3.getString("REFERENCE"));
                mbtranHistList.add(mbTranHist);

            }
            PrintUtils.print("mbTranHist",mbTranHistInfo,mbtranHistList);
        }finally {
            DbConn.close(statement);
            DbConn.close(resultSet);
            DbConn.close(resultSet2);
            DbConn.close(resultSet3);
            DbConn.close(conn);
        }


    }

    private String getQureyAcctSql(String baseAcctNo){
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(acctInfo).append(" from mb_acct where base_acct_no =")
                .append("'"). append(baseAcctNo).append("'");
        if (!baseAcctNo.startsWith("90")){
            sb.append(" and router_key = ").append("'").append(baseAcctNo.substring(0,12)).append("'");
        }else {
            sb.append(" and router_key = ").append("'").append(Math.abs(baseAcctNo.hashCode())).append("'");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Math.abs("9000013001999001000014".hashCode()));
    }

    private String getQueryAttach(String internalKey,String routerKey){
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(acctAttachInfo).append(" from mb_acct_attach where internal_key =").append("'"). append(internalKey).append("'");
        sb.append(" and router_key = ").append("'").append(routerKey).append("'");
        return sb.toString();

    }

    private String getQueryTransql(String channelSeqNo,Object routerKey){
        StringBuilder sb = new StringBuilder();
        sb.append("select ").append(mbTranHistInfo).append(" from mb_tran_hist where ");
        sb.append("channel_seq_no =").append("'").append(channelSeqNo).append("'");
        sb.append(" and router_key = ").append("'").append(routerKey).append("'");
        return sb.toString();
    }
}
