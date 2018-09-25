package com.dcits.orion.core.dao;

import com.dcits.galaxy.cache.redis.ParaTabCacheProxy;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.annotation.TablePkScanner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基于Radis缓存实现的系统参数表dao抽象父类
 *
 * @author Tim
 * @version V1.0
 * @description
 * @update 2015年2月9日 下午5:24:34
 */

public abstract class CacheDao implements IDao {

    protected abstract String getTableName();

    @Override
    public <T extends AbstractBean> int insert(T record) {
        return ParaTabCacheProxy.insertRow(getTableName(), record);
    }

    @Override
    public <T extends AbstractBean> int updateByPrimaryKey(T record) {
        return ParaTabCacheProxy.updateRow(getTableName(), record);
    }

    @Override
    public <T extends AbstractBean> int deleteByPrimaryKey(T record) {
        return ParaTabCacheProxy.deleteRow(getTableName(), record);
    }

    @Override
    public <T extends AbstractBean> T selectByPrimaryKey(T record,
                                                         Object... pkValue) {
        String[] pks = TablePkScanner.pkColsScanner(record);
        if (null == pkValue || (pkValue.length == 0 && pks.length == 0))
            return ParaTabCacheProxy.getSigleRowBean(getTableName(), record);
        else if (pkValue.length == 1 && pks.length == 1)
            return ParaTabCacheProxy.getSigleKeyRowBean(getTableName(),
                    pkValue[0], record);
        else if (pkValue.length >= 1 && pks.length >= 1
                && pks.length <= pkValue.length) {
            Map<String, Object> p = new HashMap<String, Object>();
            int i = 0;
            for (String pk : pks) {
                if (null != pkValue[i])
                    p.put(pk, pkValue[i]);
                i++;
            }
            return ParaTabCacheProxy.getRowBean(getTableName(), p, record);
        }
        return null;
    }

    /**
     * 主键通用查询<br>
     * 目前仅支持双主键数据，根据某一个主键获取结果。
     *
     * @param record
     * @param pkValue
     * @return
     * @description
     * @version 1.0
     * @author Tim
     * @update 2015年2月10日 上午10:53:13
     */
    @Override
    public <T extends AbstractBean> List<T> selectListByPrimaryKey(T record,
                                                                   Object... pkValue) {
        String[] pks = TablePkScanner.pkColsScanner(record);
        // 仅支持双主键
        if (pks.length != 2)
            return null;
        int i = 0;
        String pf = null;
        Object pv = null;
        for (String pk : pks) {
            if (null != pkValue[i]) {
                pf = pk;
                pv = pkValue[i];
                break;
            }
            i++;
        }
        // 主键值
        if (null == pf || null == pv)
            return null;

        return ParaTabCacheProxy.getListBean(getTableName(), pf, pv, record);
    }
}