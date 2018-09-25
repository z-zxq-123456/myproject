package com.dcits.orion.api.model;

import com.dcits.galaxy.base.bean.AbstractBean;

import java.util.List;

/**
 * Created by Tim on 2016/3/8.
 */
public class FileData<H , B  , T  > extends AbstractBean {

    private static final long serialVersionUID = 301775335679447883L;
    /**
     * 文件头数据
     */
    private H headData;

    /**
     * 文件体数据
     */
    private List<B> bodyData;

    /**
     * 文件尾数据
     */
    private T tailData;

    public H getHeadData() {
        return headData;
    }

    public void setHeadData(H headData) {
        this.headData = headData;
    }

    public List<B> getBodyData() {
        return bodyData;
    }

    public void setBodyData(List<B> bodyData) {
        this.bodyData = bodyData;
    }

    public T getTailData() {
        return tailData;
    }

    public void setTailData(T tailData) {
        this.tailData = tailData;
    }
}
