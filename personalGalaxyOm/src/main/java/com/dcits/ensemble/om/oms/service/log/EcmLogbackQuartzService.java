package com.dcits.ensemble.om.oms.service.log;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.manager.server.LinuxMonitorServer;
import com.dcits.ensemble.om.oms.module.log.EcmTcecinStat;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangbinaf on 2016/10/24.
 */
@Service
public class EcmLogbackQuartzService {


    @Resource
    PkServiceUtil serviceUtil;

    @Resource
    LinuxMonitorServer linuxMonitorServer;

    @Resource
    private OMSIDao omsBaseDao;

    private int max_trace_star_Id = 0;//调用链统计ID

    private int max_cir_star_Id = 0;//调用环统计ID

    private String TIME_UNIT = "ms";//时间单位为毫秒

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    /**
     * 插入调用连，调用环的信息到数据库中
     */
    @Transactional
    public void saveCinAndCir() {
        findTcecirStat();//插入调用环统计表的信息
        findTcecinStat();//插入调用链统计表的信息
    }

    /**
     * 查询调用环统计表所需的信息
     */
    @Transactional
    public void findTcecirStat() {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("traceStatDate", getDate());
        List<EcmTcecirStat> ruleList = omsBaseDao.findListByCond(new EcmTcecirStat(), "findTcecirStat", queryMap);
        log.info("EcmTcecirStat:" + ruleList);
        if (ruleList.size()==0){
            return ;
        }else {
            for (EcmTcecirStat temp : ruleList) {
                log.info("resultEcmTcecirStat:" + temp);
                saveTcecirStat(temp);
            }
        }
    }

    /**
     * 查询调用链统计表所需的信息
     */
    @Transactional
    public void findTcecinStat() {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("traceStatDate", getDate());
        List<EcmTcecinStat> ruleList = omsBaseDao.findListByCond(new EcmTcecinStat(), "findTcecinStat", queryMap);
        System.out.println("ruleList="+ruleList+ruleList.size());
        if (ruleList.size()==0){
            return ;
        }else {
            for (EcmTcecinStat temp : ruleList) {
                log.info("resultEcmTcecinStat:" + temp);
                saveTcecinStat(temp);
            }
        }
    }


    /**
     * 添加调用环统计记录
     * @param   ecmTcecirStat tcecir   调用环实例对象
     */
    @Transactional
    public void saveTcecirStat(EcmTcecirStat tcecir) {
        EcmTcecirStat tcecirStat = new EcmTcecirStat();
        int cirStarId = serviceUtil.getMaxReqID(max_cir_star_Id, "ECM_TCECIR_STAT", "CIR_STAT_ID");
        tcecirStat.setCirStatId(cirStarId);
        tcecirStat.setCirStatDate(getDate());
        tcecirStat.setCirServerSer(tcecir.getCirServerSer());
        tcecirStat.setCirServerMtd(tcecir.getCirServerMtd());
        tcecirStat.setCirStatAmount(tcecir.getCirStatAmount());
        tcecirStat.setCirStatOknum(tcecir.getCirStatOknum());
        tcecirStat.setCirStatOkperc(linuxMonitorServer.getPercentFormat(Float.parseFloat(tcecir.getCirStatOkperc()), 3, 2));
        tcecirStat.setCirStatAvertime(tcecir.getCirStatAvertime()+TIME_UNIT);
        tcecirStat.setCirStatAverexutime(tcecir.getCirStatAverexutime()+TIME_UNIT);
        tcecirStat.setCirStatPeaknum(tcecir.getCirStatPeaknum()+TIME_UNIT);
        log.info("tcecirStat = " + tcecirStat);
        omsBaseDao.insert(tcecirStat);
    }

    /**
     * 添加调用链统计记录
     * @param EcmTcecinStat tcecin   调用链实例对象
     */
    @Transactional
    public void saveTcecinStat(EcmTcecinStat tcecin) {
        EcmTcecinStat tcecinStat = new EcmTcecinStat();
        int traceStarId = serviceUtil.getMaxReqID(max_trace_star_Id, "ECM_TCECIN_STAT", "TRACE_STAT_ID");
        tcecinStat.setTraceStatId(traceStarId);
        tcecinStat.setTraceStatDate(getDate());
        tcecinStat.setMessageCode(tcecin.getMessageCode());
        tcecinStat.setMessageType(tcecin.getMessageType());
        tcecinStat.setServiceCode(tcecin.getServiceCode());
        tcecinStat.setTraceStatAmount(tcecin.getTraceStatAmount());
        tcecinStat.setTraceStatOknum(tcecin.getTraceStatOknum());
        tcecinStat.setTraceStatOkperc(linuxMonitorServer.getPercentFormat(Float.parseFloat(tcecin.getTraceStatOkperc()), 3, 2));
        tcecinStat.setTraceStatAvertime(tcecin.getTraceStatAvertime()+TIME_UNIT);
        tcecinStat.setTraceStatPeaknum(tcecin.getTraceStatPeaknum()+TIME_UNIT);
        log.info("tcecinStat = " + tcecinStat);
        omsBaseDao.insert(tcecinStat);
    }

    //获取当前系统的时间-1 即获取昨天的时间格式是：20161020   2016年10月20日
    private String getDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        log.info("time = " + time);
        return time;
    }
}
