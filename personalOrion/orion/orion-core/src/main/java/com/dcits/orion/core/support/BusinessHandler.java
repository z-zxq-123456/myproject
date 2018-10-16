package com.dcits.orion.core.support;

import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.data.BaseRequest;
import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.ISysHead;
import com.dcits.galaxy.base.data.SysHead;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.galaxy.base.util.SeqUtils;
import com.dcits.galaxy.base.util.StringUtils;
import com.dcits.galaxy.core.access.ThreadLocalManager;
import com.dcits.orion.api.Convert;
import com.dcits.orion.api.Handler;
import com.dcits.orion.api.Service;
import com.dcits.orion.base.ConvertFactory;
import com.dcits.orion.base.util.ConvertUtils;
import com.dcits.orion.core.Context;
import com.dcits.orion.core.antirepeate.AntiRepeatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 默认Handle实现
 *
 * @author xuecy
 *         <p/>
 *         修改unpack、pack部分实现，增加大写报文与驼峰报文的互转。
 * @author Tim
 */
public class BusinessHandler implements Handler {

    private static final Logger logger = LoggerFactory
            .getLogger(BusinessHandler.class);

    @Resource
    ConvertFactory convertFactory;

    @Resource
    AntiRepeatUtil antiRepeatUtil;


    private Service service;

    /**
     * @return service : return the property service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service
     *         : set the property service.
     */
    public void setService(Service service) {
        this.service = service;
    }


    @Override
    @Deprecated
    public String handle(String strInMsg) {
        TwoTuple<Convert, String> two = convertFactory.getConvertAndMsg(strInMsg);
        Convert<String> convert = two.first;
        String inMsg = two.second;
        if (StringUtils.isEmpty(inMsg))
            return "Galaxy server is starting!";
        //平台流水ID为null，重新设置
        boolean isRemove = false;
        BaseRequest request = convert.unpack(inMsg);
        BeanResult br = handle(request);
        return convert.pack(request.getSysHead(), br);
    }

    /**
     * 业务系统对外暴露的处理接口
     *
     * @param msg
     * @return
     */
    @Override
    public Map handle(Map msg) {

        TwoTuple<Convert, Map> two = convertFactory.getConvertAndMsg(msg);
        Convert<Map> convert = two.first;
        if (msg == null || msg.isEmpty()) {
            return convert.pack(new SysHead(), new BeanResult("000000", "Galaxy server is starting!"));
        }

        BaseRequest request = convert.unpack(msg);
        BeanResult br = handle(request);
        return convert.pack(request.getSysHead(), br);
    }

    private BeanResult handle(BaseRequest request) {

        boolean isRemove = false;
        if (ThreadLocalManager.getUID() == null) {
            String uid = SeqUtils.getStringSeq();
            ThreadLocalManager.setUID(uid);
            isRemove = true;
        }
        BeanResult outRs = null;
        ISysHead sysHead = null;
        try {
            sysHead = request.getSysHead();
            ConvertUtils.inServiceCode(sysHead);
            //设置slf4j的平台ID
            MDC.put(GalaxyConstants.PLATFORM_ID, sysHead.getReference() == null ? ThreadLocalManager.getUID() : ThreadLocalManager.getUID() + "-" + sysHead.getReference());
            //防重处理
            boolean isExecute = false;
            outRs = antiRepeatUtil.process(request);
            if (outRs == null) {
                outRs = service.execute(request);
                isExecute = true;
            }
            if (isExecute)
                antiRepeatUtil.updateTranInfo(request, outRs);

            Context context = Context.getInstance();
            if (!StringUtils.isBlank(context.getRunDate()))
                request.getSysHead().setRunDate(context.getRunDate());
        } catch (Throwable t) {
            outRs = new BeanResult(t);
        } finally {
            if (isRemove) {
                ThreadLocalManager.remove();
                //清除平台ID slf4j的平台ID
                MDC.remove(GalaxyConstants.PLATFORM_ID);
            }
        }
        return outRs;
    }
}