package com.dcits.ensemble.om.oms.action.log;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.log.EcmChartSeries;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.log.EcmTcecirStatService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangbinaf on 2016/10/24.
 */
@Controller
public class EcmTcecirStatAction {
    @Resource
    IService omsBaseService;
    @Resource
    EcmTcecirStatService tcecirStatService;
    @Resource
    ParamComboxService paramComboxService;

    private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("findTcecirStat")
    public void find(HttpServletRequest request, PrintWriter printWriter,EcmTcecirStat tcecirStat) {
        try {
            Map<String, Object> cirMap = new HashMap<String, Object>();
            if(tcecirStat.getCirServerSer()==""||tcecirStat.getCirServerSer()==null){
                cirMap.put("cirServerSer",null);
            }else {
                cirMap.put("cirServerSer",tcecirStat.getCirServerSer());
            }
            if(tcecirStat.getCirServerMtd()==null||tcecirStat.getCirServerMtd()==""){
                cirMap.put("cirServerMtd",null);
            }else {
                cirMap.put("cirServerMtd",tcecirStat.getCirServerMtd());
            }
            if(request.getParameter("startTime")==null&&request.getParameter("endTime")==null) {
                cirMap.put("startTime",request.getParameter("startTime")) ;
                cirMap.put("endTime",request.getParameter("endTime"));
            }else {
                cirMap.put("startTime", (request.getParameter("startTime")));
                cirMap.put("endTime", (request.getParameter("endTime")));
            }
            PageData<EcmTcecirStat> pageData=null;
            Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
            if(null!=appHead) {
                int totalNum = appHead.get("TOTAL_NUM");
                int currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号
                pageData = omsBaseService.findPageByCond(tcecirStat, "findPageList",EcmTcechainTraceService.getPagNo(currentNum, totalNum), totalNum,cirMap);
            }
            if(null!=pageData) {
                ActionResultWrite.setPaginationQueryDataResult(request, printWriter, magicList(pageData.getList()), pageData.getTotalNum());
            }else{
                ActionResultWrite.setReadResult(printWriter, magicList(new ArrayList<EcmTcecirStat>()));}
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findCirChartData")
    public void findCir(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String cirServerSer = request.getParameter("cirServerSer");
            String cirServerMtd = request.getParameter("cirServerMtd");
            String endTime = request.getParameter("cirStatDate");
            String type = request.getParameter("type");
            EcmChartSeries chartSeries = tcecirStatService.getData(cirServerSer, cirServerMtd, endTime,type);
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
    @RequestMapping("findTcecirTop")
    public void findTcecirTop(HttpServletRequest request, PrintWriter printWriter,EcmTcecirStat trceirStat) {
        try {
            String type = request.getParameter("statue");
            String startTime =  request.getParameter("startTime");
            String endTime =  request.getParameter("endTime");
            EcmChartSeries chartSeries = tcecirStatService.findTcecirTop(startTime,endTime,type);
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
    @RequestMapping("findTcecirInfo")
    public void findTcecirInfo(HttpServletRequest request, PrintWriter printWriter,EcmTcecirStat trceirStat) {
        try {
            String type = request.getParameter("statue");
            String startTime =  request.getParameter("startTime");
            String endTime =  request.getParameter("endTime");
            List<EcmTcecirStat> chartSeriesList= tcecirStatService.findTcecirInfo(startTime,endTime,type);
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

    private List<EcmTcecirStat> magicList(List<EcmTcecirStat> list) {
        for(EcmTcecirStat tcecirStat:list) {
            tcecirStat.setCirStatAmount("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatAmount"+"\""+","+"\""+"七日服务调用量趋势图"+"\""+","+"\""+"访问量指标"+"\""+")>"+tcecirStat.getCirStatAmount()+"</a>");
            tcecirStat.setCirStatOknum ("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatOknum"+"\""+","+"\""+"七日服务调用成功量趋势图"+"\""+","+"\""+"成功量指标"+"\""+")>"+tcecirStat.getCirStatOknum()+"</a>");
            tcecirStat.setCirStatOkperc("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatOkperc"+"\""+","+"\""+"七日服务调用成功成功率趋势图"+"\""+","+"\""+"成功率指标"+"\""+")>"+tcecirStat.getCirStatOkperc()+"</a>");
            tcecirStat.setCirStatAvertime("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatAvertime"+"\""+","+"\""+"七日服务调用平均耗时趋势图"+"\""+","+"\""+"平均耗时指标"+"\""+")>"+tcecirStat.getCirStatAvertime()+"</a>");
            tcecirStat.setCirStatAverexutime("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatAverexutime"+"\""+","+"\""+"七日服务调用本地耗时趋势图"+"\""+","+"\""+"峰值指标"+"\""+")>"+tcecirStat.getCirStatAverexutime()+"</a>");
            tcecirStat.setCirStatPeaknum("<a type=\"text\"  title=查看最近七天 onclick=drawCirStatChart("+"\""+tcecirStat.getCirServerSer()+"\""+","+"\""+tcecirStat.getCirServerMtd()+"\""+","+"\""+tcecirStat.getCirStatDate()+"\""+","+"\""+"cirStatPeaknum"+"\""+","+"\""+"七日服务调用峰值趋势图"+"\""+","+"\""+"峰值指标"+"\""+")>"+tcecirStat.getCirStatPeaknum()+"</a>");
        }
        return list;
    }

}
