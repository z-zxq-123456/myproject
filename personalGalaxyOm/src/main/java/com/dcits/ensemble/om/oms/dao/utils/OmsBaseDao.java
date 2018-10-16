package com.dcits.ensemble.om.oms.dao.utils;



import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dcits.galaxy.base.bean.AbstractBean;
import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;

/**
 * 基础dao，对cobar的封装 *
 * @author tangxlf
 * @version V1.0
 * @param <>
 * @description
 * @update 2015年7月9日 下午5:24:38
 */

@Repository
public  class OmsBaseDao implements OMSIDao {

    private static final Logger log = LoggerFactory.getLogger(OmsBaseDao.class);

    public static final String INSERT = "insert";

    public static final String UPDATE = "updateByPrimaryKey";

    public static final String DELETE = "deleteByPrimaryKey";

    public static final String SELECT = "selectByPrimaryKey";
    
    public static final String FINDLIST = "findList";
    @Resource
	private ShardSqlSessionTemplate sessionTemplate;
    
	@Override
	public <T extends AbstractBean> void insert(T record) {
		 log.info("start "+record.getClass().getSimpleName()+" insert action");
		 sessionTemplate.insert(magicNamespace(record) + "."+INSERT, record);
		 log.info("finished "+record.getClass().getSimpleName()+" insert action");
	}
	
	@Override
	public <T extends AbstractBean> void insertBatchList(List<T> recordList) {
		 if(recordList.size()>0){
			 log.info("start "+recordList.get(0).getClass().getSimpleName()+" insert action");
			 int count = sessionTemplate.insertAddBatch(magicNamespace(recordList.get(0)) + "."+INSERT, recordList);
			 log.info("finished "+recordList.get(0).getClass().getSimpleName()+" insert action");
		 }
	}


	@Override
	public <T extends AbstractBean> void insertList(T record,List<Map<String,Object>> recordList) {
		log.info("start "+record.getClass().getSimpleName()+" batch insert action");
		sessionTemplate.insertBatch(magicNamespace(record) + "."+INSERT, recordList);
		log.info("finished "+record.getClass().getSimpleName()+" batch insert action");
	}


	@Override
	public <T extends AbstractBean> void updateByPrimaryKey(T record) {
		log.info("start "+record.getClass().getSimpleName()+" update action");
		sessionTemplate.update(magicNamespace(record) + "."+UPDATE, record);
		log.info("finished "+record.getClass().getSimpleName()+" update action");
	}

	@Override
	public <T extends AbstractBean> void deleteByPrimaryKey(T record) {
		log.info("start "+record.getClass().getSimpleName()+" delete action");
		sessionTemplate.delete(magicNamespace(record) + "."+DELETE, record);
		log.info("finished "+record.getClass().getSimpleName()+" delete action");
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> T selectByPrimaryKey(T record) {		
		log.info("start "+record.getClass().getSimpleName()+" selectByPrimaryKey action");
		T obj = (T)sessionTemplate.selectOne(magicNamespace(record) + "."+SELECT, record);
		log.info("finished "+record.getClass().getSimpleName()+" selectByPrimaryKey action");
		return  obj;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findListByCond(T record) {
		log.info("start "+record.getClass().getSimpleName()+" findListByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+FINDLIST, record);
		log.info("finished "+record.getClass().getSimpleName()+" findListByCond action");
		return  list;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findListByCond(T record,Map<String,Object> queryMap) {		
		log.info("start "+record.getClass().getSimpleName()+" findListByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+FINDLIST, queryMap);
		log.info("finished "+record.getClass().getSimpleName()+" findListByCond action");
		return  list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findListByCond(T record,String sqlId, Map<String, Object> queryMap) {
		log.info("start "+record.getClass().getSimpleName()+" findListByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+sqlId, queryMap);
		log.info("finished "+record.getClass().getSimpleName()+" findListByCond action");
		return  list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findPageByCond(T record,int pageNo, int pageSize) {
		log.info("start "+record.getClass().getSimpleName()+" findPageByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+FINDLIST,createParaWrapper(pageNo,pageSize,record));
		log.info("finished "+record.getClass().getSimpleName()+" findPageByCond action");
		return  list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findPageByCond(T record,int pageNo,int pageSize,Map<String,Object> queryMap) {
		log.info("start "+record.getClass().getSimpleName()+" findPageByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+FINDLIST,createParaWrapper(pageNo,pageSize,queryMap));
		log.info("finished "+record.getClass().getSimpleName()+" findPageByCond action");
		return  list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractBean> List<T> findPageByCond(T record,String sqlId, int pageNo, int pageSize, Map<String, Object> queryMap) {
		log.info("start "+record.getClass().getSimpleName()+" findPageByCond action");
		List<T> list = (List<T>)sessionTemplate.selectList(magicNamespace(record) + "."+sqlId,createParaWrapper(pageNo,pageSize,queryMap));
		log.info("finished "+record.getClass().getSimpleName()+" findPageByCond action");
		return  list;
	}

    private ParameterWrapper createParaWrapper(int pageNo,int pageSize,Object queryObj){
    	ParameterWrapper paraWrapper = new ParameterWrapper();
		paraWrapper.setBaseParam(queryObj);
		paraWrapper.setRowArgs(createRowArgs(pageNo,pageSize));
		return paraWrapper;
    }
    
    private RowArgs createRowArgs(int pageNo,int pageSize){
      if(pageNo==0){
    	  pageNo=1; 
      }
      int offset = (pageNo-1)*pageSize;
      return new RowArgs(offset, pageSize);		
    }
    
	private String magicNamespace(Object obj){
	  return obj.getClass().getName().replaceFirst(".module.", ".dao.")+"Dao";
	}

	@Override
	public <T extends AbstractBean> int findTotalRecord(T record) {		
		return OMSTotalRecordHelp.findTotalRecoudNum(sessionTemplate, magicNamespace(record) + "." + FINDLIST, record);
	}

	@Override
	public <T extends AbstractBean> int findTotalRecord(T record,Map<String, Object> queryMap) {
		return OMSTotalRecordHelp.findTotalRecoudNum(sessionTemplate, magicNamespace(record) + "." + FINDLIST, queryMap);
	}

	@Override
	public <T extends AbstractBean> int findTotalRecord(T record, String sqlId,Map<String, Object> queryMap) {
		return OMSTotalRecordHelp.findTotalRecoudNum(sessionTemplate, magicNamespace(record) + "." + sqlId, queryMap);
	}

	@Override
	public <T extends AbstractBean> void deleteByCon(T record, String sqlId,Map<String, Object> deleteMap) {
		log.info("start "+record.getClass().getSimpleName()+" deleteByCon action");
		sessionTemplate.delete(magicNamespace(record) + "."+sqlId, deleteMap);
		log.info("finished "+record.getClass().getSimpleName()+" deleteByCon action");
	}


	

	
}
