package com.dcits.ensemble.om.cmc.dao;

import java.util.List;
import java.util.Map;

/**
 * @Description :
 * @Author :zhouxqh
 * @Date : Create on 2018/9/9
 */
public interface BaseDbTableDao {

    String NAMESPACE = "com.dcits.ensemble.om.cmc.dao.BaseDbTableDao";

    List<Map> selectByPrimaryKey(Map requestParams);

    List<Map> getAllContrast(Map requestParams);

    List<Map> getAllStatus(Map requestParams);

    void delete(Map requestParams);
}
