package com.dcits.galaxy.cache.paratab;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.dcits.galaxy.base.util.JsonUtils;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.paratab.metadata.Column;
import com.dcits.galaxy.cache.paratab.metadata.Table;

/**
 * 线程并发处理装载缓存表相关信息
 * 
 * @author tangxlf
 * @date 2015-1-14
 */

@Component
public class ReadTableMetaThread  {

	@Resource
	private MusterParaTabCacheConfig musterParaTabCacheConfig;	


	
	public void execute(ParaTabCacheInfo info) {
		info.setDbTableName(transTableName(info.getTableName()));
		info.setColumnMap(loadParaTabColumns(info.getDbTableName()));
		info.setDbQueryCon(handleQueryCon(info.getQueryCon()));
		musterParaTabCacheConfig.addParaTabCacheInfo(info);
	}


	private String transTableName(String tableName) {
		return JsonUtils.convertUpperCase(tableName);
	}

	private String transColName(String dbColName) {
		return JsonUtils.convertHumpCase(dbColName);
	}

	private String handleQueryCon(String queryCon) {
		if (queryCon == null || queryCon.trim().equals("")) {
			return " 1=1 ";
		}
		StringBuilder buffer = new StringBuilder(queryCon.length());
		StringBuilder queryConSb = new StringBuilder(queryCon);
		int startPos = queryConSb.indexOf("${");
		int endPos = -2;
		while (startPos != -1) {
			buffer.append(queryConSb.substring(0, startPos));
			endPos = queryConSb.indexOf("}", startPos);
			if (endPos == -1) {
				break;
			} else {
				buffer.append(JsonUtils.convertUpperCase(queryConSb.substring(
						startPos + 2, endPos)));
				queryConSb = queryConSb.delete(0, endPos + 1);
			}
			startPos = queryConSb.indexOf("${");
		}
		if (endPos == -2) {
			return queryCon;
		}
		buffer.append(queryConSb.toString());
		return buffer.toString();
	}


	
	private Map<String, Column> loadParaTabColumns(String tableName) {
		Map<String, Column> colMap = new HashMap<String, Column>();		
		try {				
			Table table = musterParaTabCacheConfig.getDatabase().getTable(tableName);
			if (null == table)
				throw new CacheException("[" + tableName + "]表不存在！");
			String[] colNames = table.getColumnNames();
			for (String dbColName : colNames) {
				colMap.put(transColName(dbColName), table.getColumn(dbColName));
			}
		} catch (Exception e) {
			throw new CacheException("[" + tableName + "]参数表缓存初始化出错！\n"
					+ e.getClass().getSimpleName() + ":" + e.getMessage());
		} 
		return colMap;
	}

}
