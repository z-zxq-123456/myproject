package com.dcits.orion.scp.system.impl;

import com.dcits.orion.scp.system.AbstractSystem;
import com.dcits.orion.scp.utils.ScpUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by lixiaobin on 2017/3/21.
 */
public class CommonSystem extends AbstractSystem{

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
}
