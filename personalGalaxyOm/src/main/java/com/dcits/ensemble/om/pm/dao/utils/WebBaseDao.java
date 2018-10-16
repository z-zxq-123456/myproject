package com.dcits.ensemble.om.pm.dao.utils;

import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository
public  class WebBaseDao implements IDao {

    private static final Logger log = LoggerFactory.getLogger(WebBaseDao.class);

    public static final String INSERT = "insert";

    public static final String INSERTNEWDATATOW = "insertForNewData";

    public static final String INSERTNEWDATATOWONLYKEY = "insertForNewDataOnlyKeyValue";

    public static final String UPDATE = "updateByPrimaryKey";

    public static final String UPDATENEWDATATON = "updateForNewData";

    public static final String DELETE = "deleteByPrimaryKey";

    public static final String SELECT = "selectByPrimaryKeyForY";

    public static final String SELECTFORW = "selectDataByPKForW";

    public static final String SELECTPKFORN ="selectDataByPrimaryKeyForN";

    public static final String SELECTINSERTSTATUS = "selectByInsertStatusPK";

    public static final String FINDLIST = "findList";

    public static final String SELECTBASEFORPKLIST = "selectBaseForPklist";

    public static final String UPDATESTATUS = "upDateParaDataStatus";

    public static final String SELECTBYTABLENAME = "selectByTableName";

    public static final String SELECTDELETEDATABYTABLENAME = "getDeleteDataByTableName";

    public static final String SELECTPKLIST = "selectParameterPklist";

    public static final String SELECTPKLISTWITHCON = "selectParameterPklistWithCondition";

    public static final String SELECTCHECK = "selectTableAndCol";

    public static final String UPDATESUCCESSFLOW = "updateDataForSuccessPublishFlow";

    public static final String DELETESUCCESSFLOW = "deleteDataForSuccessPulishFlow";

    public static final String UPDATEFAILEDFLOW = "updateDataForCancel";

    public static final String DELETEFAILEDFLOW = "deleteDataForCancel";

    public static final String SELECTSTATUSW = "selectStatusW";

    public static final String SELECTSTATUSN = "selectStatusN";

    public static final String SELECTTIMESFOROPERATE = "getTableUpdateTimes";

    public static final String UPDATENOTCHECKDATAFORW = "updateNotCheckData";

    @Resource
    private ShardSqlSessionTemplate sessionTemplate;

    @Override
    public <T extends AbstractBean> void insert(T record) {
        log.info("start " + record.getClass().getSimpleName() + " insert action");
        sessionTemplate.insert(magicNamespace(record) + "." + INSERT, record);
        log.info("finished " + record.getClass().getSimpleName() + " insert action");
    }

    @Override
    public <T extends AbstractBean> void updateByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " update action");
        sessionTemplate.update(magicNamespace(record) + "." + UPDATE, record);
        log.info("finished " + record.getClass().getSimpleName() + " update action");
    }

    @Override
    public <T extends AbstractBean> void deleteByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " delete action");
        sessionTemplate.delete(magicNamespace(record) + "." + DELETE, record);
        log.info("finished " + record.getClass().getSimpleName() + " delete action");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractBean> T selectByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " selectByPrimaryKey action");
        T obj = (T) sessionTemplate.selectOne(magicNamespace(record) + "." + SELECT, record);
        log.info("finished " + record.getClass().getSimpleName() + " selectByPrimaryKey action");
        return obj;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) sessionTemplate.selectList(magicNamespace(record) + "." + FINDLIST, record);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record, Map<String, Object> queryMap) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) sessionTemplate.selectList(magicNamespace(record) + "." + FINDLIST, queryMap);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractBean> List<PkList> selectForBasePklist(T record) {
        log.info("start " + record.getClass().getSimpleName() + " selectForBasePklist action");
        List<PkList> list = (List<PkList>) sessionTemplate.selectList(magicNamespace(record) + "." + SELECTBASEFORPKLIST);
        log.info("finished " + record.getClass().getSimpleName() + " selectForBasePklist action");
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record, String sqlId, Map<String, Object> queryMap) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) sessionTemplate.selectList(magicNamespace(record) + "." + sqlId, queryMap);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractBean> List<Map<String, Object>> listBase(Map<String, Object> map) {
        log.info("start " + " findListByCond action");
        List<Map<String, Object>> list = (List<Map<String, Object>>) sessionTemplate.selectList(map.get("baseSpace") + "." + FINDLIST, map);
        log.info("finished " + " findListByCond action");
        return list;
    }

    public <T extends AbstractBean> void insertNewDataToW(Map<String, Object> map) {
        log.info("start " + " findListByCond action");
        sessionTemplate.insert(map.get("baseSpace").toString() + "." + INSERTNEWDATATOW, map);
        log.info("finished " + " findListByCond action");
    }

    public <T extends AbstractBean> void insertForNewDataOnlyKeyValue(Map<String, Object> map) {
        log.info("start " + " findListByCond action");
        sessionTemplate.insert(map.get("baseSpace").toString() + "." + INSERTNEWDATATOWONLYKEY, map);
        log.info("finished " + " findListByCond action");
    }

    public <T extends AbstractBean> void updateStatus(Map<String, Object> map) {
        log.info("start " + " findListByCond action");
        sessionTemplate.update(map.get("baseSpace").toString() + "." + UPDATESTATUS, map);
        log.info("finished " + " findListByCond action");
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectByTableName(Map<String, String> map) {
        log.info("start " + " selectByTableName action");
        return sessionTemplate.selectList(map.get("baseSpace") + "." + SELECTBYTABLENAME, map);
    }

    @SuppressWarnings("unchecked")
    public Integer getDeleteDataByTableName(Map<String, Object> map) {
        log.info("start " + " getDeleteDataByTableName action");
        return (Integer) sessionTemplate.selectOne(map.get("baseSpace").toString() + "." + SELECTDELETEDATABYTABLENAME, map);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> selectDataByPrimaryKey(Map<String, Object> map) {
        log.info("start " + " selectDataByPrimaryKey action");
        return (Map<String, Object>) sessionTemplate.selectOne(map.get("baseSpace").toString() + "." + SELECT, map);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> selectDataByPKForW(Map<String, Object> map) {
        log.info("start " + " selectDataByPKForW action");
        return (Map<String, Object>) sessionTemplate.selectOne(map.get("baseSpace").toString() + "." + SELECTFORW, map);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> selectDataByPrimaryKeyForN(Map<String, Object> map) {
        log.info("start " + " selectDataByPrimaryKeyForN action");
        return (Map<String, Object>) sessionTemplate.selectOne(map.get("baseSpace").toString() + "." + SELECTPKFORN, map);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectByInsertStatusPK(Map<String, Object> map) {
        log.info("start " + " selectDataByPrimaryKey action");
        return sessionTemplate.selectList(map.get("baseSpace").toString() + "." + SELECTINSERTSTATUS, map);
    }

    @SuppressWarnings("unchecked")
    public List<PkList> selectParameterPklist(Map<String, String> map) {
        return sessionTemplate.selectList(map.get("baseSpace") + "." + SELECTPKLIST, map);
    }

    @SuppressWarnings("unchecked")
    public List<PkList> selectParameterPklistWithWhere(Map<String, Object> map) {
        return sessionTemplate.selectList(map.get("baseSpace") + "." + SELECTPKLISTWITHCON, map);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectTableAndCol(Map<String, String> map) {
        return sessionTemplate.selectList(map.get("baseSpace") + "." + SELECTCHECK, map);
    }

    public <T extends AbstractBean> void deleteBasebyPk(Map<String, Object> map) {
        log.info("start " + " findListByCond action");
        sessionTemplate.delete(map.get("baseSpace").toString() + "." + DELETE, map);
        log.info("finished " + " findListByCond action");
    }

    public <T extends AbstractBean> void updateForNewDataToN(Map<String, Object> map) {
        log.info("start " + " update action");
        sessionTemplate.update(map.get("baseSpace").toString() + "." + UPDATENEWDATATON, map);
        log.info("start " + " update action");
    }

    public <T extends AbstractBean> void deleteForSuccess(Map<String, Object> map) {
        log.info("start " + " deleteForSuccess action");
        sessionTemplate.delete(map.get("baseSpace").toString(), DELETESUCCESSFLOW, map);
        log.info("finished " + " deleteForSuccess action");
    }

    public <T extends AbstractBean> void updateForSuccess(Map<String, Object> map) {
        log.info("start " + " updateForSuccess action");
        sessionTemplate.update(map.get("baseSpace").toString(), UPDATESUCCESSFLOW, map);
        log.info("finished " + " updateForSuccess action");
    }

    public <T extends AbstractBean> void deleteForCancel(Map<String, Object> map) {
        log.info("start " + map.get("baseSpace").toString() + " deleteForCancel action ");
        sessionTemplate.delete(map.get("baseSpace").toString(), DELETEFAILEDFLOW, map);
        log.info("finished " + " deleteForCancel action");
    }

    public <T extends AbstractBean> void updateForCancel(Map<String, Object> map) {
        log.info("start " + map.get("baseSpace").toString() + " updateForCancel action ");
        sessionTemplate.update(map.get("baseSpace").toString(), UPDATEFAILEDFLOW, map);
        log.info("finished " + " updateForCancel action");
    }

    private ParameterWrapper createParaWrapper(int pageNo, int pageSize, Object queryObj) {
        ParameterWrapper paraWrapper = new ParameterWrapper();
        paraWrapper.setBaseParam(queryObj);
        paraWrapper.setRowArgs(createRowArgs(pageNo, pageSize));
        return paraWrapper;
    }

    private RowArgs createRowArgs(int pageNo, int pageSize) {
        if (pageNo == 0) {
            pageNo = 1;
        }
        int offset = (pageNo - 1) * pageSize;
        return new RowArgs(offset, pageSize);
    }

    private String magicNamespace(Object obj) {
        return obj.getClass().getName().replaceFirst(".mapper.", ".dao.") + "Dao";
    }

    @Override
    public <T extends AbstractBean> void deleteByCon(T record, String sqlId, Map<String, Object> deleteMap) {
        log.info("start " + record.getClass().getSimpleName() + " deleteByCon action");
        sessionTemplate.delete(magicNamespace(record) + "." + sqlId, deleteMap);
        log.info("finished " + record.getClass().getSimpleName() + " deleteByCon action");
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectStatusW(Map<String, String> map) {
        log.info("start " + " selectStatusW action");
        return sessionTemplate.selectList(map.get("baseSpace"), SELECTSTATUSW, map);
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectStatusN(Map<String, String> map) {
        log.info("start " + " selectStatusN action");
        return sessionTemplate.selectList(map.get("baseSpace"), SELECTSTATUSN, map);
    }

    public Integer selectNumberRyTb(Map<String, Object> map) {
        log.info("start " + " selectNumberRyTb action");
        return (Integer) sessionTemplate.selectOne(map.get("baseSpace").toString(), SELECTTIMESFOROPERATE, map);
    }

    public void updateNotCheckDataForW(Map<String, Object> map) {
        log.info("start " + "updateNotCheckDate action");
        sessionTemplate.update(map.get("baseSpace").toString() + "." + UPDATENOTCHECKDATAFORW, map);
    }
}
