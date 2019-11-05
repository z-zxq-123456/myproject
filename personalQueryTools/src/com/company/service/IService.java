package com.company.service;

/**
 * 业务抽象接口
 */
public interface IService {

    /**
     * 计算流水路由键<></>
     * @param baseAcctNo
     * @param channelSeqNo
     */
    public void calcTranHistPartation(String baseAcctNo,String channelSeqNo);


    /**
     * 查询账户属性<是否透支、是否热点、账户internalKey,路由分区></>
     * @param baseAcctNo
     */
    public void showAcctInfo(String baseAcctNo);


}
