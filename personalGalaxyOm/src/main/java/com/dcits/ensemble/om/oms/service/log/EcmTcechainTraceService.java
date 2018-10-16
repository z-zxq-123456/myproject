package com.dcits.ensemble.om.oms.service.log;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.dubbo.DubboServcieAppIntatNameParse;
import com.dcits.ensemble.om.oms.module.app.EcmAppInfo;
import com.dcits.ensemble.om.oms.module.log.EcmTraceChain;
import com.dcits.ensemble.om.oms.module.log.EcmTraceCircle;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luolang on 2016/10/20.
 */
@Service
public class EcmTcechainTraceService {
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private   final static   String  SETCONSUPTIME ="20";//设置网络消耗时间

    /**
     *
     * @param request servlet请求对象
     * @param startTime 调用链开始时间
     * @param endTime 调用链结束时间
     * @return
     */
    public PageData<EcmTraceChain> getTcechainInfo(HttpServletRequest request ,String startTime,String endTime,String branchId, String tellerId) {
        Map<String, Integer> appHead = ActionResultWrite.getPaginationNumber(request);
        PageData<EcmTraceChain> pageData=null;
        EcmTraceChain traceChainM = new EcmTraceChain();
        traceChainM.setBranchId(branchId);
        traceChainM.setTellerId(tellerId);
        traceChainM.setStartTime(startTime);
        traceChainM.setEndTime(endTime);
        if ( appHead!= null) {
            int totalNum = appHead.get("TOTAL_NUM");
            int currentNum = appHead.get("CURRENT_NUM");//获得本页记录总数,当前记录号

            pageData = omsBaseService.findPageByCond(traceChainM, getPagNo(currentNum, totalNum), totalNum);
            List<EcmTraceChain> chainList = pageData.getList();
            for (EcmTraceChain traceChain : chainList) {
                traceChain.setUuid(traceChain.getMessageCode() + traceChain.getMessageType() + traceChain.getServiceCode());
                if (null == traceChain.getTraceSttime() || null == traceChain.getTraceEntime()) {
                    traceChain.setExeTime("");
                } else {
                    traceChain.setExeTime(Long.toString(getExeTime(traceChain.getTraceSttime(), traceChain.getTraceEntime())));
                }
                //把Action中的magicList  要处理的放在该循环中，减少循环次数，提高效率,（注意： 针对 之前罗浪的修改）
                traceChain.setTraceStatusName(paramComboxService.getParaName(traceChain.getTraceStatus()));
                traceChain.setAllInfo("<a type=\"text\"  title="+"\'"+getChainInfo(traceChain)+"\'"+" >详情</a>");
                traceChain.setCircleView("<a type=\"text\"  title=查看环视图 onclick=test("+"\""+traceChain.getTraceId()+"\""+")>点击</a>");
            }
        }
        return pageData;
    }

    /**
     *
     * @param traceChain 调用链信息
     * @return 调用换列表
     */
    public List<EcmTraceCircle> getTceCircleInfo(EcmTraceChain traceChain) {
        Map<String ,Object> map = new HashMap<>();
        map.put("traceId", traceChain.getTraceId());
        List<EcmTraceCircle> circleList = omsBaseService.findListByCond(new EcmTraceCircle(), "findTcecirList", map);
        for(EcmTraceCircle traceCircle :circleList) {
            traceCircle.setCirServerSerAndMtd(getSerAndMtdName(traceCircle.getCirServerSer(),traceCircle.getCirServerMtd()));
            traceCircle.setCirInMsg(subBlank(traceCircle.getCirInMsg()));
            traceCircle.setCirOutMsg(subBlank(traceCircle.getCirOutMsg()));
            if(null==traceCircle.getCirClientSttm()||null==traceCircle.getCirClientEntm()) {
                traceCircle.setCirExeTime("");
            }else {
                traceCircle.setCirExeTime(exchangType(getExeTime(traceCircle.getCirClientSttm(), traceCircle.getCirClientEntm())));
            }
            traceCircle.setCirClientStatusName(paramComboxService.getParaName(traceCircle.getCirClientStatus()));
            traceCircle.setCirTypeName(paramComboxService.getParaName(traceCircle.getCirType()));
            if (traceCircle.getCirType().equals(SysConfigConstants.CIR_TYPE_INJVM)) {
                        traceCircle.setCirconsupTime("0");
            } else {
                traceCircle.setCirServerStatusName(paramComboxService.getParaName(traceCircle.getCirServerStatus()));
                if(null==traceCircle.getCirClientSttm()||null==traceCircle.getCirServerSttm()||null==traceCircle.getCirServerEntm()||null==traceCircle.getCirClientEntm()) {
                    traceCircle.setCirconsupTime("0");
                } else {
                    String   exeTime = exchangType(getExeTime(traceCircle.getCirClientSttm(), traceCircle.getCirServerSttm()).longValue() + getExeTime(traceCircle.getCirServerEntm(), traceCircle.getCirClientEntm()).longValue());
                    if(Integer.valueOf(exeTime) < 0){//如果网络消耗时间小于零，则设置网络消耗时间为一个定值
                        exeTime = SETCONSUPTIME;
                    }
                    traceCircle.setCirconsupTime(exeTime);
                }
            }
        }
        return circleList;
    }

    /**
     * 获取组装名称
     * @param serName 服务名
     * @param mtdName 方法名
     * @return 类名：：方法名
     */
    private  String getSerAndMtdName(String serName,String mtdName) {
        return serName.substring(serName.lastIndexOf(".")+1,serName.length())+"::"+mtdName;
    }

    /**
     * 得到执行时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 执行时间
     */
    private Long getExeTime(String startTime,String endTime) {
        if (null != startTime && null != endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        long exeTime = -1;
        try {
            exeTime = format.parse(endTime).getTime() - format.parse(startTime).getTime();
            if (exeTime <0) {
                exeTime = 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return exeTime;
        }else {
            return null;
        }
    }

    //去掉json报文里面的换行符、回车符等
    private String subBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    //类型转换
    private  String exchangType(Long lg) {
        if(null!=lg) {
            return Long.toString(lg);
        }else {
            return " ";
        }
    }

    /**
     *
     * @param currentNum 当前索引
     * @param totalNum  每页总记录数
     * @return
     */
    public static int getPagNo(int currentNum ,int totalNum){
        return currentNum/totalNum+1;
    }
    //添加字段title的显示信息
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

}
