package com.dcits.orion.core.dao;

import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixbb on 2016/1/11.
 */
@Repository
public class FwDao {
    @Resource
    private ShardSqlSessionTemplate shardSqlSessionTemplate;

    private static final String NAMESPACE = "com.dcits.orion.core.dao.FwDao";

    public Map getTranInfo(String keys)
    {
        Map map = new HashMap();
        map.put("KEY_VALUE",keys);
        return (Map)shardSqlSessionTemplate.selectOne(NAMESPACE,"getTranInfo",map);
    }

    public void insertTranInfo(Map map)
    {
        String TRAN_DATE = (String)map.get("TRAN_DATE");
        Date tranDate = null;
        try {
            tranDate = new SimpleDateFormat("yyyyMMdd").parse(TRAN_DATE);
        } catch (ParseException e) {
            throw new GalaxyException(e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(tranDate);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        map.put("WEEK_DAY",weekDay);
        shardSqlSessionTemplate.insert(NAMESPACE, "insertTranInfo", map);
    }

    public void updateTranInfo(Map map)
    {
        shardSqlSessionTemplate.update(NAMESPACE, "updateTranInfo", map);
    }
}
