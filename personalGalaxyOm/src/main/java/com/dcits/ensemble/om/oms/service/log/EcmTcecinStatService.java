package com.dcits.ensemble.om.oms.service.log;

import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.log.EcmChartSeries;
import com.dcits.ensemble.om.oms.module.log.EcmTcecinStat;
import com.dcits.ensemble.om.oms.module.log.EcmTcecirStat;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.ensemble.om.pf.util.SmartAccounting.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by luolang on 2016/11/23.
 */
@Service
public class EcmTcecinStatService {
    @Resource
    IService omsBaseService;
    @Resource
    ParamComboxService paramComboxService;
   public EcmChartSeries getData(String messageCode,String messageType,String serviceCode,String endTime,String type) {
       Map<String ,Object> map= new HashMap<String,Object>();
       map.put("messageCode",messageCode);
       map.put("messageType",messageType);
       map.put("serviceCode",serviceCode);
       map.put("startTime",getStartTime(endTime,-6));
       map.put("endTime", endTime);
       List<EcmTcecinStat> tcecinStatList = omsBaseService.findListByCond(new EcmTcecinStat(),"findListByTime",map);
       EcmChartSeries amount = new EcmChartSeries();

       if(type.equals("traceStatAmount")) {
           amount.setName("交易访问量");
       }
       if(type.equals("traceStatOknum")) {
           amount.setName("交易成功量");
       }
       if(type.equals("traceStatOkperc")) {
           amount.setName("交易成功率");
       }
       if(type.equals("traceStatAvertime")) {
           amount.setName("交易平均耗时");
       }
       if(type.equals("traceStatPeaknum")) {
           amount.setName("交易峰值");
       }
       List<Double> dataList = new ArrayList<Double>();
       List<String> dateList = new ArrayList<String>();
       for(int i = -6;i<=0;i++) {
           String date = getStartTime(endTime, i);
           double data = 0;
           for(EcmTcecinStat tcecinStat:tcecinStatList) {
              if(tcecinStat.getTraceStatDate().equals(date)) {
                  if(type.equals("traceStatAmount")) {
                  data =Double.parseDouble( quUtil(tcecinStat.getTraceStatAmount()));
                  break;
                  }
                  if(type.equals("traceStatOknum")) {
                      data =Double.parseDouble(quUtil(tcecinStat.getTraceStatOknum()));
                      break;
                  }
                  if(type.equals("traceStatOkperc")) {
                      data =Double.parseDouble(quUtil( tcecinStat.getTraceStatOkperc()));
                      break;
                  }
                  if(type.equals("traceStatAvertime")) {
                      data =Double.parseDouble(quUtil( tcecinStat.getTraceStatAvertime()));
                      break;
                  }
                  if(type.equals("traceStatPeaknum")) {
                      data =Double.parseDouble(quUtil(tcecinStat.getTraceStatPeaknum()));
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
    public EcmChartSeries findTcecinTop(String startTime,String endTime,String type) {
        EcmChartSeries amount = new EcmChartSeries();
        List<EcmTcecinStat> tcecirStatList = getCinList(amount, startTime, endTime, type);
        List<EcmTcecinStat> tcecinTopList = new ArrayList<>();//获取前10条记录
        getTopCin(amount, type, tcecirStatList, tcecinTopList);
        return amount;
    }
    /**
     * @param String type 排序的条件code
     * @param String startTime 开始时间
     * @param String endTime 结束时间
     * return  List<EcmTcecinStat> findTcecinInfo  满足条件的调用环list
     */
    public  List<EcmTcecinStat> findTcecinInfo(String startTime,String endTime,String type) {
        EcmChartSeries amount = new EcmChartSeries();
        List<EcmTcecinStat> tcecinStatList = getCinList(amount, startTime, endTime, type);
        List<EcmTcecinStat> tcecinTopList = new ArrayList<>();//获取前10条记录
        getTopCin(amount, type, tcecinStatList, tcecinTopList);
        return tcecinTopList;
    }
    /**
     * 获取前10的元素，parse结果
     * @param EcmChartSeries amount  图标渲染的数据模型
     * @param String type 排序的条件code
     * @param  List<EcmTcecinStat> tcecinStatList 调用环所有的结果List
     * @param  List<EcmTcecinStat> tcecinTopList 调用环前10的结果List
     */
    public void getTopCin ( EcmChartSeries amount ,String type, List<EcmTcecinStat> tcecinStatList,List<EcmTcecinStat> tcecinTopList){
        List<Double> dataList = new ArrayList<Double>();
        List<String> dateList = new ArrayList<String>();
        String serName = "";
        double data = 0;
        int  i=-9;//只取前10的元素
        for (EcmTcecinStat tcecinStat : tcecinStatList) {
            if(i<=0) {
                serName = tcecinStat.getMessageType()+tcecinStat.getMessageCode();
                data = Double.parseDouble(getMethod(type,tcecinStat));
                dataList.add(data);
                dateList.add(serName);
                tcecinTopList.add(tcecinStat);
            }
            i++;
        }
        amount.setDate(dateList.toArray());
        amount.setData(dataList.toArray());
    }
    /**
     * 获取前10的元素，parse结果
     * @param EcmChartSeries amount  图标渲染的数据模型
     * @param String type 排序的条件code
     * @param String startTime 开始时间
     * @param String endTime 结束时间
     * return  List<EcmTcecinStat> getCirList 满足条件的调用链list
     */
    public   List<EcmTcecinStat> getCinList( EcmChartSeries amount ,String startTime,String endTime,String type){
        Map<String ,Object> cinMap= new HashMap<String,Object>();
        cinMap.put(paramComboxService.getParaRemark1(type), paramComboxService.getParaRemark1(type));
        amount.setName(paramComboxService.getParaName(type));
        if(!DataUtil.isNull(startTime)) {
            cinMap.put("startTime",startTime);
        }else{
            cinMap.put("startTime", getDate());//默认查找前一天的
        }
        if(!DataUtil.isNull(endTime)) {
            cinMap.put("endTime", endTime);
        }else{
            cinMap.put("endTime", getDate());
        }
        return omsBaseService.findListByCond(new EcmTcecinStat(),"findTopList",cinMap);
    }
    private static String getStartTime(String endTime,int day){
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
    //获取需要排序字段的“get方法名”
    private String  methodStr(String str){
        String  temp =   paramComboxService.getParaRemark1(str);
        String newStr = temp.substring(0, 1).toUpperCase()+temp.replaceFirst("\\w","");
        return  "get"+newStr;
    }
    private String getMethod(String str, EcmTcecinStat temp){
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
    private String quUtil(String str ) {
        return str.replaceAll("%","").replaceAll("ms","");
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
    /**
     *
     * @param targetList    要排序的集合（使用泛型）
     * @param sortField     要排序的集合中的实体类的某个字段
     * @param sortMode      排序的方式（升序asc/降序desc）
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(List<EcmTcecinStat> targetList, final String sortField, final String sortMode) {
        //使用集合的sort方法  ，并且自定义一个排序的比较器
        /**
         * API文档：
         * public static <T> void sort(List<T> list,Comparator<? super T> c)
         * 根据指定比较器产生的顺序对指定列表进行排序。此列表内的所有元素都必须可使用指定比较器 相互比较
         * （也就是说，对于列表中的任意 e1 和 e2 元素， c.compare(e1, e2) 不得抛出 ClassCastException）。
         * 参数：     list - 要排序的列表。
         *      c - 确定列表顺序的比较器。 null 值指示应该使用元素的 自然顺序。
         */
        Collections.sort(targetList, new Comparator() {
            //匿名内部类，重写compare方法
            public int compare(Object obj1, Object obj2) {
                int result = 0;
                try {
                    //首字母转大写
                    String newStr = sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");
                    //获取需要排序字段的“get方法名”
                    String methodStr = "get"+newStr;
                    /** API文档：：
                     *  getMethod(String name, Class<?>... parameterTypes)
                     *  返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
                     */
                    Method method1 = ((EcmTcecinStat)obj1).getClass().getMethod(methodStr, null);
                    Method method2 = ((EcmTcecinStat)obj2).getClass().getMethod(methodStr, null);
                    if (sortMode != null && "desc".equals(sortMode)) {
                        /**
                         * Method类代表一个方法，invoke（调用）就是调用Method类代表的方法。
                         * 它可以让你实现动态调用，也可以动态的传入参数。
                         * API文档：（这个英文解释更地道易懂，原谅我是英文渣,哎！）
                         * invoke(Object obj, Object... args)
                         * Invokes the underlying method represented by this Method object,
                         *  on the specified object with the specified parameters.
                         */
                        /** API文档：
                         * String-----public int compareTo(String anotherString)
                         * 按字典顺序比较两个字符串。该比较基于字符串中各个字符的 Unicode 值。
                         * 按字典顺序将此 String 对象表示的字符序列与参数字符串所表示的字符序列进行比较
                         */
                        result = Integer.valueOf(quUtil(method2.invoke(((EcmTcecinStat) obj2), null).toString()))
                                .compareTo(Integer.valueOf(quUtil(method1.invoke(((EcmTcecinStat) obj1), null).toString()))); // 倒序
                    } else {
                        result =  Integer.valueOf(quUtil(method1.invoke(((EcmTcecinStat) obj1), null).toString()))
                                .compareTo(Integer.valueOf(quUtil(method2.invoke(((EcmTcecinStat) obj2), null).toString()))); // 正序
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                return result;
            }
        });
    }
}


