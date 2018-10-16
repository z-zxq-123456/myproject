package com.dcits.ensemble.om.oms.service.utils;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.ensemble.om.oms.dao.utils.OmsBaseDao;
import com.dcits.ensemble.om.oms.module.utils.PageData;

/**
 * 通用Service*
 *
 * @author tangxlf
 * @date 2015-07-24
 */
@Service
public class OmsBaseService implements IService {
    private static final Logger log = LoggerFactory.getLogger(OmsBaseService.class);
    @Resource
    private OmsBaseDao omsBaseDao;

    @Override
    public <T extends AbstractBean> void insert(T record) {
        log.info("start " + record.getClass().getSimpleName() + " " + record.getClass().getSimpleName() + " insert action");
        omsBaseDao.insert(record);
        log.info("finished " + record.getClass().getSimpleName() + " insert action");
    }

    @Override
    public <T extends AbstractBean> void updateByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " update action");
        omsBaseDao.updateByPrimaryKey(record);
        log.info("finished " + record.getClass().getSimpleName() + " update action");
    }

    @Override
    public <T extends AbstractBean> void deleteByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " delete action");
        omsBaseDao.deleteByPrimaryKey(record);
        log.info("finished " + record.getClass().getSimpleName() + " delete action");
    }


    @Override
    public <T extends AbstractBean> T selectByPrimaryKey(T record) {
        log.info("start " + record.getClass().getSimpleName() + " selectByPrimaryKey action");
        T obj = (T) omsBaseDao.selectByPrimaryKey(record);
        log.info("finished " + record.getClass().getSimpleName() + " selectByPrimaryKey action");
        return obj;
    }


    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) omsBaseDao.findListByCond(record);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }


    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record, Map<String, Object> queryMap) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) omsBaseDao.findListByCond(record, queryMap);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }


    @Override
    public <T extends AbstractBean> List<T> findListByCond(T record, String sqlId, Map<String, Object> queryMap) {
        log.info("start " + record.getClass().getSimpleName() + " findListByCond action");
        List<T> list = (List<T>) omsBaseDao.findListByCond(record, sqlId, queryMap);
        log.info("finished " + record.getClass().getSimpleName() + " findListByCond action");
        return list;
    }


    @Override
    public <T extends AbstractBean> PageData<T> findPageByCond(T record, int pageNo, int pageSize) {
        PageData<T> pageData = new PageData<T>();
        log.info("start " + record.getClass().getSimpleName() + " findPageByCond action");
        List<T> list = (List<T>) omsBaseDao.findPageByCond(record, pageNo, pageSize);
        int totalRecord = omsBaseDao.findTotalRecord(record);
        pageData.setList(list);
        pageData.setTotalNum(totalRecord);
        log.info("finished " + record.getClass().getSimpleName() + " findPageByCond action");
        return pageData;
    }


    @Override
    public <T extends AbstractBean> PageData<T> findPageByCond(T record, int pageNo, int pageSize, Map<String, Object> queryMap) {
        PageData<T> pageData = new PageData<T>();
        log.info("start " + record.getClass().getSimpleName() + " findPageByCond action");
        List<T> list = (List<T>) omsBaseDao.findPageByCond(record, pageNo, pageSize, queryMap);
        int totalRecord = omsBaseDao.findTotalRecord(record, queryMap);
        pageData.setList(list);
        pageData.setTotalNum(totalRecord);
        log.info("finished " + record.getClass().getSimpleName() + " findPageByCond action");
        return pageData;
    }


    @Override
    public <T extends AbstractBean> PageData<T> findPageByCond(T record, String sqlId, int pageNo, int pageSize, Map<String, Object> queryMap) {
        PageData<T> pageData = new PageData<T>();
        log.info("start " + record.getClass().getSimpleName() + " findPageByCond action");
        List<T> list = (List<T>) omsBaseDao.findPageByCond(record, sqlId, pageNo, pageSize, queryMap);
        int totalRecord = omsBaseDao.findTotalRecord(record, sqlId, queryMap);
        pageData.setList(list);
        pageData.setTotalNum(totalRecord);
        log.info("finished " + record.getClass().getSimpleName() + " findPageByCond action");
        return pageData;
    }


}
