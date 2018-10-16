package com.dcits.ensemble.om.oms.service.utils;

import java.util.List;
import java.util.Map;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.ensemble.om.oms.module.utils.PageData;

public interface IService {

    /**
     * 通用动态新增
     * @param record
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> void insert(T record);

    /**
     * 主键通用动态更新
     *
     * @param record
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> void updateByPrimaryKey(T record);


    /**
     * 根据主键删除记录
     *
     * @param record module bean，必须定义了@TablePk主键
     * @param <T>    记录mapper泛型
     * @return
     */
    <T extends AbstractBean> void deleteByPrimaryKey(T record);

    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> T selectByPrimaryKey(T record);
    
    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> List<T> findListByCond(T record);
    
    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> PageData<T> findPageByCond(T record, int pageNo, int pageSize);

    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */    
	<T extends AbstractBean> List<T> findListByCond(T record, Map<String, Object> queryMap);
    
    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */    
	<T extends AbstractBean> PageData<T> findPageByCond(T record, int pageNo, int pageSize, Map<String, Object> queryMap);
	
	
	
	/**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */    
	<T extends AbstractBean> List<T> findListByCond(T record, String sqlId, Map<String, Object> queryMap);
    
    /**
     * 主键通用查询
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */    
	<T extends AbstractBean> PageData<T> findPageByCond(T record, String sqlId, int pageNo, int pageSize, Map<String, Object> queryMap);
    

}
