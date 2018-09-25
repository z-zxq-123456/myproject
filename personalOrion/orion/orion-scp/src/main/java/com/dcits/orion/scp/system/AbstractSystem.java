package com.dcits.orion.scp.system;

import com.dcits.galaxy.adapter.Adapter;
import com.dcits.galaxy.base.parser.MapParser;
import com.dcits.orion.scp.api.ISystem;

import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public abstract class  AbstractSystem implements ISystem {
    Adapter adapter;
    MapParser mapParser;


    @Override
    public Map execute(Map request) {
        Object requestMsg = mapParser.toObj(request);
        Object responseMsg =  adapter.execute(requestMsg);
        Map response = mapParser.toMap(responseMsg);
        return response;
    }

    public Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public MapParser getMapParser() {
        return mapParser;
    }

    public void setMapParser(MapParser mapParser) {
        this.mapParser = mapParser;
    }
}
