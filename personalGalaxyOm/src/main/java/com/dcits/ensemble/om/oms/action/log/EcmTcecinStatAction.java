package com.dcits.ensemble.om.oms.action.log;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.log.EcmChartSeries;
import com.dcits.ensemble.om.oms.module.log.EcmTcecinStat;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.log.EcmTcecinStatService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Calendar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wangbinaf on 2016/10/24.
 */
@Controller
public class EcmTcecinStatAction {

    @Resource
    IService  omsBaseService;
    @Resource
    EcmTcecinStatService tcecinStatService;
    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("findTcecinStat")
    public void find(HttpServletRequest request, PrintWriter printWriter,EcmTcecinStat trceinStat) {
        try {
            Map<String, Object> cinMap = new HashMap<String, Object>();
            if(trceinStat.getMessageCode()==""||trceinStat.getMessageCode()==null){
                cinMap.put("cirServerSer",null);
            }else {
                cinMap.put("messageCode",trceinStat.getMessageCode());
            }
            if(trceinStat.getMessageType()==null||trceinStat.getMessageType()==""){
                cinMap.put("messageType",null);
            }else {
                cinMap.put("messageType",trceinStat.getMessageType());
            }
            if(trceinStat.getServiceCode()==null||trceinStat.getServiceCode()==""){
                cinMap.put("serviceCode",null);
            }else {
                cinMap.put("serviceCode",trceinStat.getServiceCode());
            }
            if((request.getParameter("startTime")==null||request.getParameter("startTime")=="")&&(request.getParameter("endTime")==null||request.getParameter("endTime")=="")) {
                cinMap.put("startTime",request.getParameter(null));
                cinMap.put("endTime",request.getParameter(null));
            }else {
                cinMap.put("startTime", (request.getParameter("startTime")));
                cinMap.put("endTime", (request.getParameter("endTime")));
            }
            PageData<EcmTcecinStat> pageData=null;
            Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
            if(null!=appHead) {
                int totalNum = appHead.get("TOTAL_NUM");
                int currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号
                pageData = omsBaseService.findPageByCond(trceinStat,"findPageList", EcmTcechainTraceService.getPagNo(currentNum, totalNum), totalNum,cinMap);
            }
            if(null!=pageData) {
                ActionResultWrite.setPaginationQueryDataResult(request, printWriter, magicList(pageData.getList()), pageData.getTotalNum());
            }else{
                ActionResultWrite.setReadResult(printWriter, magicList(new ArrayList<EcmTcecinStat>()));}
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findChartData")
    public void findOn(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String messageCode = request.getParameter("messageCode");
            String messageType = request.getParameter("messageType");
            String serviceCode = request.getParameter("serviceCode");
            String endTime = request.getParameter("traceStatDate");
            String type = request.getParameter("type");
            EcmChartSeries chartSeries = tcecinStatService.getData(messageCode, messageType, serviceCode, endTime, type);
            List<EcmChartSeries> chartSeriesList= new ArrayList<EcmChartSeries>();
            chartSeriesList.add(chartSeries);
            ActionResultWrite.setReadResult(printWriter, chartSeriesList);
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    /**
     * 返回加载柱状图的前10条记录的信息
     */
    @RequestMapping("findTcecinTop")
    public void findAppIndexMonitor(HttpServletRequest request, PrintWriter printWriter,EcmTcecinStat trceinStat) {
            try {
                String type = request.getParameter("statue");
                String startTime =  request.getParameter("startTime");
                String endTime =  request.getParameter("endTime");
                EcmChartSeries chartSeries = tcecinStatService.findTcecinTop(startTime, endTime, type);
                List<EcmChartSeries> chartSeriesList= new ArrayList<EcmChartSeries>();
                chartSeriesList.add(chartSeries);
                ActionResultWrite.setReadResult(printWriter, chartSeriesList);
            } catch (Exception e) {
                e.printStackTrace();
                ActionResultWrite.setErrorResult(printWriter,e.getMessage());
            }
    }
    /**
     * 统计出调用环的前10条记录
     */
    @RequestMapping("findTcecinInfo")
    public void findTcecirInfo(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String type = request.getParameter("statue");
            String startTime =  request.getParameter("startTime");
            String endTime =  request.getParameter("endTime");
            List<EcmTcecinStat> chartSeriesList= tcecinStatService.findTcecinInfo(startTime, endTime, type);
            ActionResultWrite.setReadResult(printWriter, magicList(chartSeriesList));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
        }
    }
    /**
     * 去除日期中的"-"字符
     * @parm String 字符串
     */

    private String rmSomething(String str) {
        return str.replaceAll("-", "");

    }
    private List<EcmTcecinStat> magicList(List<EcmTcecinStat> list) {
        for(EcmTcecinStat tcecinStat:list) {
            tcecinStat.setTraceStatAmount("<a type=\"text\"  title=查看最近七天 onclick=drawTraceStatChart("+"\""+tcecinStat.getMessageCode()+"\""+","+"\""+tcecinStat.getMessageType()+"\""+","+"\""+tcecinStat.getServiceCode()+"\""+","+"\""+tcecinStat.getTraceStatDate()+"\""+","+"\""+"traceStatAmount"+"\""+","+"\""+"七日访问量趋势图"+"\""+","+"\""+"访问量指标"+"\""+")>"+tcecinStat.getTraceStatAmount()+"</a>");
            tcecinStat.setTraceStatOknum ("<a type=\"text\"  title=查看最近七天 onclick=drawTraceStatChart("+"\""+tcecinStat.getMessageCode()+"\""+","+"\""+tcecinStat.getMessageType()+"\""+","+"\""+tcecinStat.getServiceCode()+"\""+","+"\""+tcecinStat.getTraceStatDate()+"\""+","+"\""+"traceStatOknum"+"\""+","+"\""+"七日成功量趋势图"+"\""+","+"\""+"成功量指标"+"\""+")>"+tcecinStat.getTraceStatOknum()+"</a>");
            tcecinStat.setTraceStatOkperc("<a type=\"text\"  title=查看最近七天 onclick=drawTraceStatChart("+"\""+tcecinStat.getMessageCode()+"\""+","+"\""+tcecinStat.getMessageType()+"\""+","+"\""+tcecinStat.getServiceCode()+"\""+","+"\""+tcecinStat.getTraceStatDate()+"\""+","+"\""+"traceStatOkperc"+"\""+","+"\""+"七日成功率趋势图"+"\""+","+"\""+"成功率指标"+"\""+")>"+tcecinStat.getTraceStatOkperc()+"</a>");
            tcecinStat.setTraceStatAvertime("<a type=\"text\"  title=查看最近七天 onclick=drawTraceStatChart("+"\""+tcecinStat.getMessageCode()+"\""+","+"\""+tcecinStat.getMessageType()+"\""+","+"\""+tcecinStat.getServiceCode()+"\""+","+"\""+tcecinStat.getTraceStatDate()+"\""+","+"\""+"traceStatAvertime"+"\""+","+"\""+"七日平均耗时趋势图"+"\""+","+"\""+"平均耗时指标"+"\""+")>"+tcecinStat.getTraceStatAvertime()+"</a>");
            tcecinStat.setTraceStatPeaknum("<a type=\"text\"  title=查看最近七天 onclick=drawTraceStatChart("+"\""+tcecinStat.getMessageCode()+"\""+","+"\""+tcecinStat.getMessageType()+"\""+","+"\""+tcecinStat.getServiceCode()+"\""+","+"\""+tcecinStat.getTraceStatDate()+"\""+","+"\""+"traceStatPeaknum"+"\""+","+"\""+"七日峰值趋势图"+"\""+","+"\""+"峰值指标"+"\""+")>"+tcecinStat.getTraceStatPeaknum()+"</a>");
        }
        return list;
    }
}
