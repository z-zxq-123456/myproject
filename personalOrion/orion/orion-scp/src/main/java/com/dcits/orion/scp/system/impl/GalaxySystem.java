package com.dcits.orion.scp.system.impl;

import com.dcits.galaxy.core.client.ServiceProxy;
import com.dcits.galaxy.core.client.builder.Attributes;
import com.dcits.galaxy.core.client.builder.ServiceAttributesBuilder;
import com.dcits.orion.api.Handler;
import com.dcits.orion.scp.system.AbstractSystem;
import com.dcits.orion.scp.utils.ScpUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class GalaxySystem extends AbstractSystem {

    private String group;

    @Override
    public Map execute(Map request) {
        Attributes attributes  = new ServiceAttributesBuilder()
                .setGroup(group)
                .setCheck(false).build();
        Handler service = ServiceProxy.getInstance().getService(
                Handler.class, attributes);
        return service.handle(request);
    }

    @Override
    public List<Map> getErrors(Map response) {
        if (isError(response))
        {
            return ScpUtils.getExprObject(response,"[SYS_HEAD][RET]",List.class);
        }
        return null;
    }

    @Override
    public boolean isError(Map response) {
        return !ScpUtils.getExprBoolean(response,"[SYS_HEAD][RET_STATUS]=='S'");
    }

    @Override
    public boolean isUnknownError(Map response) {
        return false;
    }


    @Override
    public boolean isReversalAgain(Map response) {
        return false;
    }

    @Override
    public boolean isReversalError(Map response) {
        return true;
    }

    @Override
    public boolean isConfirmAgain(Map response) {
        return false;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}