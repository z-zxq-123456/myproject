package com.dcits.ensemble.om.oms.service.log;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.log.EcmChartSeries;
import com.dcits.ensemble.om.oms.module.log.EcmTcecinStat;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.pf.util.SmartAccounting.DateUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luolang on 2016/11/23.
 */
@Service
public class EcmTcecirStatService {
    @Resource
    IService omsBaseService;

    @Resource
    ParamComboxService paramComboxService;

   public EcmChartSeries getData(String cirServerSer,String cirServerMtd,String endTime,String type) {
       Map<String ,Object> map= new HashMap<String,Object>();
       map.put("cirServerSer",cirServerSer);
       map.put("cirServerMtd",cirServerMtd);
       map.put("startTime",getStartTime(endTime,-6));
       map.put("endTime", endTime);
       List<EcmTcecirStat> tcecirStatList = omsBaseService.findListByCond(new EcmTcecirStat(),"findListByTime",map);
       EcmChartSeries amount = new EcmChartSeries();

       if(type.equals("cirStatAmount")) {
           amount.setName("服务调用量");
       }
       if(type.equals("cirStatOknum")) {
           amount.setName("服务调用成功量");
       }
       if(type.equals("cirStatOkperc")) {
           amount.setName("服务调用成功率");
       }
       if(type.equals("cirStatAvertime")) {
           amount.setName("服务调用平均耗时");
       }
       if(type.equals("cirStatAverexutime")) {
           amount.setName("服务调用本地耗时");
       }
       if(type.equals("cirStatPeaknum")) {
           amount.setName("服务调用峰值");
       }
       List<Double> dataList = new ArrayList<Double>();
       List<String> dateList = new ArrayList<String>();
       for(int i = -6;i<=0;i++) {
           String date = getStartTime(endTime, i);
           double data = 0;
           for(EcmTcecirStat tcecirStat:tcecirStatList) {
              if(tcecirStat.getCirStatDate().equals(date)) {
                  if(type.equals("cirStatAmount")) {
                  data =Double.parseDouble( quUtil(tcecirStat.getCirStatAmount()));
                  break;
                  }
                  if(type.equals("cirStatOknum")) {
                      data =Double.parseDouble(quUtil( tcecirStat.getCirStatOknum()));
                      break;
                  }
                  if(type.equals("cirStatOkperc")) {
                      data =Double.parseDouble(quUtil(tcecirStat.getCirStatOkperc()));
                      break;
                  }
                  if(type.equals("cirStatAvertime")) {
                      data =Double.parseDouble(quUtil(tcecirStat.getCirStatAvertime()));
                      break;
                  }
                  if(type.equals("cirStatAverexutime")) {
                      data =Double.parseDouble(quUtil( tcecirStat.getCirStatAverexutime()));
                      break;
                  }
                  if(type.equals("cirStatPeaknum")) {
                      data =Double.parseDouble(quUtil(tcecirStat.getCirStatPeaknum()));
                      break;
                  }
              }
           }
           dataList.add(data);
           dateList.add(date);
       }
       amount.setDate(dateList.toArray());
       amount.setData(dataList.toArray());
       return amount;
   }
    /**
     * @param String type 排序的条件code
     * @param String startTime 开始时间
     * @param String endTime 结束时间
     * return  EcmChartSeries amount  图标渲染的数据
     */
    public EcmChartSeries findTcecirTop(String startTime,String endTime,String type) {
        EcmChartSeries amount = new EcmChartSeries();
        List<EcmTcecirStat> tcecirStatList = getCirList(amount,startTime,endTime,type);
        List<EcmTcecirStat> tcecirTopList =new ArrayList<>();//获取前10条记录
        getTopCir(amount, type, tcecirStatList, tcecirTopList);
        return amount;
    }
    /**
     * @param String type 排序的条件code
     * @param String startTime 开始时间
     * @param String endTime 结束时间
     * return  List<EcmTcecirStat> tcecirTopList  满足条件的调用环list
     */
    public  List<EcmTcecirStat> findTcecirInfo(String startTime,String endTime,String type) {
        EcmChartSeries amount = new EcmChartSeries();
        List<EcmTcecirStat> tcecirStatList = getCirList(amount,startTime,endTime,type);
        List<EcmTcecirStat> tcecirTopList =new ArrayList<>();//获取前10条记录
        getTopCir(amount, type, tcecirStatList, tcecirTopList);
        return tcecirTopList;
    }
    /**
     * 获取前10的元素，parse结果
     * @param EcmChartSeries amount  图标渲染的数据模型
     * @param String type 排序的条件code
     * @param String startTime 开始时间
     * @param String endTime 结束时间
     * return  List<EcmTcecirStat> getCirList 满足条件的调用环list
     */
    public   List<EcmTcecirStat> getCirList( EcmChartSeries amount ,String startTime,String endTime,String type){
        Map<String ,Object> cirMap= new HashMap<String,Object>();
        cirMap.put(paramComboxService.getParaRemark1(type), paramComboxService.getParaRemark1(type));
        amount.setName(paramComboxService.getParaName(type));
        if(!DataUtil.isNull(startTime)) {
            cirMap.put("startTime",startTime);
        }else{
            cirMap.put("startTime", getDate());//默认查找前一天的
        }
        if(!DataUtil.isNull(endTime)) {
            cirMap.put("endTime", endTime);
        }else{
            cirMap.put("endTime",getDate());
        }
       return omsBaseService.findListByCond(new EcmTcecirStat(), "findTopList", cirMap);
    }
    /**
     * 获取前10的元素，parse结果
     * @param EcmChartSeries amount  图标渲染的数据模型
     * @param String type 排序的条件code
     * @param  List<EcmTcecirStat> tcecirStatList 调用环所有的结果List
     * @param  List<EcmTcecirStat> tcecirTopList 调用环前10的结果List
     */
    public void getTopCir ( EcmChartSeries amount ,String type, List<EcmTcecirStat> tcecirStatList,List<EcmTcecirStat> tcecirTopList){
        List<Double> dataList = new ArrayList<Double>();
        List<String> dateList = new ArrayList<String>();
        List<EcmTcecirStat>  cirList =  new ArrayList<EcmTcecirStat>();
        String serName = "";
        double data = 0;
        int  i=-9;//只取前10的元素
        for (EcmTcecirStat tcecirStat : tcecirStatList) {
            if(i<=0) {
                serName =tcecirStat.getCirServerSer().substring(tcecirStat.getCirServerSer().lastIndexOf(".")+1)+"."+tcecirStat.getCirServerMtd();
               // Method method1 = ((EcmTcecirStat)obj1).getClass().getMethod(methodStr(type), null);
                data = Double.parseDouble(getMethod(type,tcecirStat));
                dataList.add(data);
                dateList.add(serName);
                tcecirTopList.add(tcecirStat);
            }
            i++;
        }
        amount.setDate(dateList.toArray());
        amount.setData(dataList.toArray());
    }
    private String quUtil(String str ) {
          return str.replaceAll("%","").replaceAll("ms","");
    }
    //根据传入的字段动态的拼写  get方法
    private String getMethod(String str, EcmTcecirStat temp){
        String result = "";
        try {
            Method method = temp.getClass().getMethod(methodStr(str), null);
            try {
                result =  quUtil(method.invoke(temp, null).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }
    //获取需要排序字段的“get方法名”
    private String  methodStr(String str){
        String  temp =   paramComboxService.getParaRemark1(str);
        String newStr = temp.substring(0, 1).toUpperCase()+temp.replaceFirst("\\w","");
        return  "get"+newStr;
    }
    private String getStartTime(String endTime,int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startTime ="";
        try {
            Date endTimeD = format.parse(endTime);
            Date startTimeD = DateUtil.addDate(endTimeD, day);
            startTime = DateUtil.formatDate(startTimeD,"yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startTime;
    }

    //获取系统的当前的日期
    //获取当前系统的时间-1 即获取昨天的时间格式是：20161020   2016年10月20日
    private String getDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }
}


