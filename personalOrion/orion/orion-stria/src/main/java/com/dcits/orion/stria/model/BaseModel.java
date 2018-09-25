package com.dcits.orion.stria.model;

import com.dcits.orion.stria.core.Runner;
import com.dcits.orion.stria.handlers.IHandler;

import java.io.Serializable;

/**
 * Created by Tim on 2015/5/19.
 */
public class BaseModel  implements Serializable {

    private static final long serialVersionUID = -3224144712605153406L;

    /**
     * 元素名称
     */
    private String name;
    /**
     * 显示名称
     */
    private String displayName;

    /**
     * 将执行对象execution交给具体的处理器处理
     * @param handler
     * @param runner
     */
    protected void fire(IHandler handler, Runner runner) {
        handler.handle(runner);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
