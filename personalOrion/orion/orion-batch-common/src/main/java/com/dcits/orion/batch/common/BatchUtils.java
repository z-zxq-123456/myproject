package com.dcits.orion.batch.common;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.dcits.galaxy.core.serializer.JDKSerializer;
import com.dcits.galaxy.core.serializer.SerializationUtils;
import com.dcits.galaxy.core.serializer.Serializer;
import com.dcits.orion.batch.api.IBatchLocal;
import com.dcits.orion.batch.api.IBatchManage;
import com.dcits.orion.batch.api.IQuartzManager;
import com.dcits.orion.batch.api.bean.ITaskParam;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.AttributesBuilderSupport;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.galaxy.core.spring.SpringApplicationContext;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by lixbb on 2015/11/9.
 */
public class BatchUtils
{
    private static Logger logger = LoggerFactory.getLogger(BatchUtils.class);
    public static String getCurTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  formatter.format(new Date());
    }
    public static String getCurTimes()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("ddHHmmss");
        return  formatter.format(new Date());
    }
    public static String getRunDate()
    {
       IBatchLocal batchLocal = (IBatchLocal) SpringApplicationContext.getContext().getBean("batchLocal");
        return  (String)batchLocal.getSystemParam().get("runDate");
    }
    public static String getLocalIP()
    {
        String IP = "UnknownHost";
        try {
            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return IP;
    }
    public static String getTaskID()
    {
        /*SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curTime = formatter.format(new Date());
        return curTime + System.nanoTime();*/
        return "TASK_"+ getLocalIP() +"_"+ System.currentTimeMillis();

    }

    public static String getBatchInd()
    {
        return "BATCH_"+ getLocalIP() +"_"+  System.currentTimeMillis();
    }

    public static String getTaskInd()
    {
        return "TASK_IND_"+ getLocalIP() +"_"+  System.currentTimeMillis();
    }

    public static int getNodeCount(String systemId)
    {
        return 10;
    }

    public static Map getMapByList(List list, String keyName)
    {
        Map map = new HashMap();
        for(Object obj:list)
        {
            if (obj instanceof Map)
            {
                map.put(((Map)obj).get(keyName),obj);
            }

        }
        return map;
    }
    public static Map getSystemParam()
    {
        Map param = new HashMap();

        IBatchLocal batchLocal=(IBatchLocal)SpringApplicationContext.getContext().getBean("batchLocal");
        try
        {
            param.putAll(batchLocal.getSystemParam());
        }
        catch (Exception e)
        {
            if(logger.isInfoEnabled())
                logger.info(e.getMessage());;
        }
        return param;
    }

    public static int parseInt(Object obj)
    {
        try {
            return Integer.parseInt(obj.toString());
        }
        catch (Exception e)
        {
            return 0;
        }
    }
    public static long parseLong(Object obj)
    {
        return Long.parseLong(obj.toString());
    }
    public static double parseDouble(Object obj)
    {
        return Double.parseDouble(obj.toString());
    }


    public static IBatchManage getBatchMange(ITaskParam taskParam)
    {
        ServiceAttributesBuilder builder = new ServiceAttributesBuilder()
                .setLoadbalance(AttributesBuilderSupport.LOADBALANCE_RANDOM)
                .setCheck(true)
                .setGroup(taskParam.getSendGroup());
        IBatchManage iBatchManage = ServiceProxy.getInstance().getService(IBatchManage.class, builder.build());
        return iBatchManage;
    }


    public static List<TwoTuple<Long,Long>> getSplitList(long start,long end,int size)
    {
        List<TwoTuple<Long,Long>> ret = new ArrayList();
        for (long iStart = start; iStart <= end;iStart += size)
        {
            long iEnd;
            if (iStart + size -1 < end)
                iEnd = iStart + size -1;
            else iEnd = end;
            TwoTuple<Long,Long> p = new TwoTuple<>(iStart,iEnd);
            ret.add(p);
        }
        return ret;
    }


    public static void main(String[] args)
    {
        /*List<TwoTuple<Long,Long>> ret = getSplitList(0,0,10);
        for (TwoTuple<Long,Long> twoTuple:ret)
        {
            System.out.println(twoTuple.first+":"+twoTuple.second);
        }*/


        System.out.println(getCurTimes());

    }

    public static byte[] serialize(Object obj) {
        Serializer serializer = new JDKSerializer();
        return serializer.serialize(obj);
    }
    public static byte[] blobToBytes(Blob blob)
    {
        if (blob == null)
            return null;
        InputStream is = null;
        try {
            is = blob.getBinaryStream();
            byte[] bytes = new byte[(int)blob.length()];
            is.read(bytes);
            return bytes;
        }
        catch (Exception e)
        {
            return null;
        }
        finally {
            if (is != null){
                try{
                    is.close();
                }
                catch (Exception e)
                {

                }
            }
        }
    }
    public static <T> T deserialize(Object obj) {
        if (obj == null)
            return null;
        byte[] bytes = null;
        InputStream is;
        if (obj instanceof Blob)
        {
            bytes = blobToBytes((Blob)obj);
        }
        else {
            bytes = (byte[])obj;
        }
        Serializer serializer = new JDKSerializer();
        return (T)serializer.deserialize(bytes);

    }
}
