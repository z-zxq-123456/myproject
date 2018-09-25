package com.dcits.orion.scp.factory;


import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.orion.scp.api.ISystem;

/**
 * Created by lixiaobin on 2017/3/14.
 */
public class SystemFactory {
    public static ISystem getSystem(String systemId)
    {
        ISystem iSystem =  (ISystem) SpringApplicationContext.getContext().getBean(systemId);
        return iSystem;
    }

    public static ISystem getDefaultSystem()
    {
        return getSystem("defaultSystem");
    }
}
