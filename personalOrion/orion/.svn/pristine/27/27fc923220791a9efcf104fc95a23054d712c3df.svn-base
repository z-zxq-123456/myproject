package com.dcits.orion.base;

import com.dcits.galaxy.base.GalaxyConstants;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.tuple.TwoTuple;
import com.dcits.orion.api.Convert;
import com.dcits.orion.base.json.JsonConvert;
import com.dcits.orion.base.map.MapConvert;
import com.dcits.orion.base.xml.XmlConvert;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/19.
 */
@Repository
public class ConvertFactory implements ApplicationListener<ContextRefreshedEvent>{

    @Resource
    XmlConvert xmlConvert;
    @Resource
    MapConvert mapConvert;
    @Resource
    JsonConvert jsonConvert;

    private final String defaultMsgFormat = "json";

    public Map<String, Convert> getConverts() {
        return converts;
    }

    public void setConverts(Map<String, Convert> converts) {
        if (this.converts != null) {
            if (converts != null && !converts.isEmpty())
                this.converts.putAll(converts);
        } else
            this.converts = converts;
    }

    private Map<String, Convert> converts;

    public <T> TwoTuple<Convert, T> getConvertAndMsg(Object msg) {
        if (msg == null) {
            throw new GalaxyException("报文不能为空");
        }
        TwoTuple<Convert, T> two = null;
        if (msg instanceof String) {
            String msgFormat;
            String message;
            String[] msgs = ((String) msg).split("\\|", 2);
            if (msgs.length == 2 && msgs[0].startsWith(GalaxyConstants.MSG_FORMAT)) {
                String[] msgFormats = msgs[0].split("\\=");
                msgFormat = msgFormats[1];
                message = msgs[1];
            } else {
                msgFormat = defaultMsgFormat;
                message = (String) msg;
            }
            Convert convert = converts.get(msgFormat);
            two = new TwoTuple<>(convert, (T) message);
        } else if (msg instanceof Map) {
            Convert convert = converts.get("map");
            two = new TwoTuple<>(convert, (T) msg);
        }
        return two;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        if (converts== null)
            converts = new HashMap<>();
        converts.put("xml", xmlConvert);
        converts.put("json", jsonConvert);
        converts.put("map", mapConvert);
    }

    /*
    public TwoTuple<Convert,String> getConvertAndMsg(String msg)
    {
        String msgFormat;
        String message;
        String[] msgs = msg.split("\\|",2);
        if (msgs.length==2&&msgs[0].startsWith(GalaxyConstants.MSG_FORMAT))
        {
            String[] msgFormats = msgs[0].split("\\=");
            msgFormat = msgFormats[1];
            message = msgs[1];
        }
        else
        {
            msgFormat = defaultMsgFormat;
            message = msg;
        }
        Convert convert = converts.get(msgFormat);
        TwoTuple<Convert,String> two = new TwoTuple<>(convert,message);
        return two;
    }*/
}
