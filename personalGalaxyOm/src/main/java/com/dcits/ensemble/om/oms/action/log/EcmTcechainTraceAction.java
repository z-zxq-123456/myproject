package com.dcits.ensemble.om.oms.action.log;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.log.EcmTraceChain;
import com.dcits.ensemble.om.oms.module.log.EcmTraceCircle;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.log.EcmTcechainTraceService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * EcmTcechainTraceAction*
 *
 * @author LuoLang
 * @date 2016-10-19
 */
@Controller
public class EcmTcechainTraceAction {


    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;

    @Resource
    EcmTcechainTraceService tcechainTraceService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("findTcechainTraceInfo")
    public void find(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String branchId = request.getParameter("branchId");
            String tellerId = request.getParameter("tellerId");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if(branchId=="") {
                branchId = null;
            }
            if(tellerId=="") {
                tellerId = null;
            }
            if(startTime=="") {
                startTime = null;
            }
            if(endTime=="") {
                endTime = null;
            }
            if(DataUtil.isNull(startTime)) {
                startTime=getStartDate();
            }
            if(DataUtil.isNull(endTime)) {
                endTime =geteEndDate();
            }
            PageData<EcmTraceChain> pageData = tcechainTraceService.getTcechainInfo(request, startTime, endTime ,branchId , tellerId);
            if(null!=pageData) {
                ActionResultWrite.setPaginationQueryDataResult(request, printWriter, pageData.getList(), pageData.getTotalNum());
            }else{
                ActionResultWrite.setReadResult(printWriter, new ArrayList<EcmTraceChain>());}
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    @RequestMapping("findChainInfoByTraceId")
    public void findByTraceId(HttpServletRequest request, PrintWriter printWriter) {
        try {
            String traceId = request.getParameter("traceId");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("traceId", traceId);
            List<EcmTraceChain> chainList = omsBaseService.findListByCond(new EcmTraceChain(), "findChainList", map);
            ActionResultWrite.setReadResult(printWriter, magicList(chainList));
        } catch (Exception e) {
            e.printStackTrace();
            ActionResultWrite.setErrorResult(printWriter, e.getMessage());
        }
    }

    private List<EcmTraceChain> magicList(List<EcmTraceChain> list) {
        for(EcmTraceChain traceChain:list) {
            traceChain.setTraceStatusName(paramComboxService.getParaName(traceChain.getTraceStatus()));
            traceChain.setAllInfo("<a type=\"text\"  title="+"\'"+getChainInfo(traceChain)+"\'"+" >详情</a>");
            traceChain.setCircleView("<a type=\"text\"  title=查看环视图 onclick=test("+"\""+traceChain.getTraceId()+"\""+")>点击</a>");
        }
        return list;
    }

    private String getChainInfo(EcmTraceChain traceChain) {

        String chainInfo="入口服务名："+traceChain.getTraceInSer() +
                         "\n入口方法名："+traceChain.getTraceInMtd()+
                         "\n账号:"+traceChain.getAcctNo()+
                         "\n卡号:"+traceChain.getCardNo()+
                         "\n网点号:"+traceChain.getBranchId()+
                         "\n柜员号:"+traceChain.getTellerId()
        ;
        return chainInfo;
    }
    //获取系统的当前的日期
    //获取当前系统的时间  即获取昨天的时间格式是：20161020   2016年10月20日
    private String getStartDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }
    private String geteEndDate(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }
}
