package com.dcits.galaxy.cache.paratab;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;


/**
 * 全局参数配置
 * @author tangxlf
 * @date   2015-1-06
 * 
 */
public class ParaTabCacheConfig {
	
	private List<ParaTabCacheInfo> configList = new ArrayList<ParaTabCacheInfo>();
	
	private SqlSessionTemplate sqlSessionTemplate;	
	
	@Resource
	private  ReadTableMetaThread readTableMeta;

	

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<ParaTabCacheInfo> getConfigList() {
		return configList;
	}

	public void setConfigList(List<ParaTabCacheInfo> configList) {		
		this.configList = configList;
	}

	public void loadMap(){		
		for(ParaTabCacheInfo info:configList){		
			readTableMeta.execute(info);	
		}
	}
}
