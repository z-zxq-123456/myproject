package com.dcits.ensemble.om.oms.service.shshpoc;

import java.util.List;
import java.util.Map;

/**
 * 查询指标线程传递参数对象* 
 * @author tangxlf
 * @date 2016-07-28
 */
public class AppIndexThreadInfo {
		private String whereSql;
		private String groupSql;		
		private String threadAppIndexrecDime;
		private List<Map<String,Object>> indexList;
		public String getWhereSql() {
			return whereSql;
		}
		public void setWhereSql(String whereSql) {
			this.whereSql = whereSql;
		}
		public String getGroupSql() {
			return groupSql;
		}
		public void setGroupSql(String groupSql) {
			this.groupSql = groupSql;
		}
		public String getThreadAppIndexrecDime() {
			return threadAppIndexrecDime;
		}
		public void setThreadAppIndexrecDime(String threadAppIndexrecDime) {
			this.threadAppIndexrecDime = threadAppIndexrecDime;
		}
		public List<Map<String, Object>> getIndexList() {
			return indexList;
		}
		public void setIndexList(List<Map<String, Object>> indexList) {
			this.indexList = indexList;
		} 
		
}
