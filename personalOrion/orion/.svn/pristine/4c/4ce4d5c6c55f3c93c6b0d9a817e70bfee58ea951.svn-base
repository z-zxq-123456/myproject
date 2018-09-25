package com.dcits.orion.stria.api.rpc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Tim on 2015/5/19.
 */
public interface IFlowService {

    void deploy(JSONObject flow, String creator);

    void unDeploy(String id);

    void cacheRemove(String id);

    JSONArray getFLow();

    JSONArray getFLow(String flowType);

    JSONObject getFLowById(String flowId);

    JSONArray getAllNodeClazz();

    JSONArray getAllMethodName();
}
