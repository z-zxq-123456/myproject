package com.dcits.orion.stria.model;

/**
 * 原子服务类型
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2014年12月17日 上午10:52:10
 */

public enum AtomType {

    /**
     * JAVA服务
     */
    LOCAL("GENERAL JAVA"),
    /**
     * Spring Ioc Bean服务
     */
    BEAN("SPRING BEAN"),
    /**
     * Rpc远程服务
     */
    RPC("RPC SERVICE API"),;

    private final String msg;

    AtomType(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}