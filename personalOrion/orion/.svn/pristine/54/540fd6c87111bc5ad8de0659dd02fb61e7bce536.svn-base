/**
 * <p>Title: IDao.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2015年2月10日 下午3:13:35
 * @version V1.0
 */
package com.dcits.orion.core.dao;

import com.dcits.galaxy.base.bean.AbstractBean;

import java.util.List;

/**
 * Dao接口 <br>
 * 处于业务场景考虑，几乎没有删除操作，不提供删除接口
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年2月10日 下午3:13:35
 */

public interface IDao {

    /**
     * 通用动态新增
     *
     * @param record
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    <T extends AbstractBean> int insert(T record);

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
    <T extends AbstractBean> int updateByPrimaryKey(T record);


    /**
     * 根据主键删除记录
     *
     * @param record dbmodel bean，必须定义了@TablePk主键
     * @param <T>    记录mapper泛型
     * @return
     */
    <T extends AbstractBean> int deleteByPrimaryKey(T record);

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
    <T extends AbstractBean> T selectByPrimaryKey(T record, Object... pkValue);

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
    <T extends AbstractBean> List<T> selectListByPrimaryKey(T record,
                                                            Object... pkValue);

}
