package com.dcits.ensemble.om.oms.service.log;

import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.db.DbIntHelpUtil;
import com.dcits.ensemble.om.oms.manager.server.LinuxMonitorServer;
import com.dcits.ensemble.om.oms.module.log.EcmTcecinStat;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangbinaf on 2016/10/24.
 */
@Service
public class EcmDeleteLogInfoService {


    @Resource
    PkServiceUtil serviceUtil;

    @Resource
    ParamComboxService paramComboxService;

    @Resource
    private ShardSqlSessionTemplate logPlatShardSqlSessionTemplate;

    private ExecutorService executorService ;

  private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final static int  THREAD_NUM = 50;//并发线程数量




    /**
     * 查询需要删除的表的信息
     */
    @Transactional
    public  void deleteLogInfoThread() {
        List<String> SqlList = findDeleteTableInfo();//批量执行的sql语句列表
        CountDownLatch threadSignal = new CountDownLatch(SqlList.size());//初始化countDown
        executorService = Executors.newFixedThreadPool(THREAD_NUM);//初始化固定大小的线程
        if (SqlList.size() > 0) {
            for (String Sql : SqlList) {
                executorService.execute(new DeleteLogbackQuartzThread(Sql, threadSignal));
            }
            try {
                threadSignal.await();//等待所有子线程执行完
                executorService.shutdown();//关闭线程池
            } catch (InterruptedException e) {
                log.error("线程并行出错,错误信息为：" + e.getMessage());
            }
        }
    }
    //执行删除语句
    public class DeleteLogbackQuartzThread implements Runnable{
        String sql;
        CountDownLatch threadsSignal;
        DeleteLogbackQuartzThread(String sql, CountDownLatch threadsSignal){
            this.sql = sql;
            this.threadsSignal = threadsSignal;
        }
        @Override
        public void run() {
            deletLogbackThread(sql) ;//执行删除的sql
            threadsSignal.countDown();//线程结束时计数器减1
        }
    }
    //删除表中过期的信息
    private void deletLogbackThread(String rowsSql) {
        log.info("rowsSql="+rowsSql);
        Statement stmt = null;
        ResultSet rs = null;
        Connection conn =null;
        try {
            conn= logPlatShardSqlSessionTemplate.getShardManager().getDefaultShard().getDataSource().getConnection();//链接数据库
      //      conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute(rowsSql);
        //    conn.commit();
        //    conn.setAutoCommit(true);
        } catch (Exception e) {
            log.error(" rowsSql: "+rowsSql+" batch insert failed: "+DataUtil.printErrorStack(e));
        }finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                log.error(" batch insert failed:"+ DataUtil.printErrorStack(e));
            }
            if(conn!=null){
                try {
                    conn.close();//关闭链接
                }catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    //在参数表中获取要删除表的信息组成的sql语句列表，包括表名，字段名。
    @Transactional
    public List<String> findDeleteTableInfo() {
        List <String>  sqlList = new ArrayList<String>();//返回sql语句列表
        try {
            List<EcmParam> result =paramComboxService.getParaColls("0075","false");//查找需要数据清理的表的信息
            log.info("result="+result);
            for(EcmParam  ecmParam:result){
                String date = getDate(ecmParam.getRemark2());
                String  deleteSql = new String(" DELETE FROM  "+ecmParam.getParaName()+" WHERE "+ecmParam.getRemark1()+" < "+"'"+date+"'");
                log.info("deleteSql=" + deleteSql);
                sqlList.add(deleteSql);
            }
            log.info("sqlList="+sqlList);
        } catch (Exception e) {
         //   log.error("数据库查询出错信息："+e);
        }
        return sqlList;
    }
    //获取当前系统的时间减去周期 即获取一段时间的时间格式是：20161020   2016-10-20
    private String getDate(String temp) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, Integer.valueOf("-"+temp));
        date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        log.info("time = " + time);
        return time;
    }
}
