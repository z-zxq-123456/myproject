/**
 * <p>Title: HBJob.java</p>
 * <p>Description: Hbase Job定义</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 *
 * @author Tim
 * @update 2014年9月15日 下午2:02:19
 * @version V1.0
 */

package com.dcits.orion.schedule.model;


import com.dcits.orion.schedule.common.RenderParam;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tim
 * @version V1.0
 * @description Hbase Job定义
 * @update 2014年9月15日 下午3:22:31
 */
public class HBJob extends MRJob {

    private String mapperTable;

    private String mapperScanCaching;

    private String mapperScanCacheBlocks;

    private String mapperScanPrefixFilter;

    private String reducerTable;

    public String getMapperTable() {
        if (null == mapperTable || "".equals(mapperTable))
            return null;
        return mapperTable;
    }

    public void setMapperTable(String mapperTable) {
        this.mapperTable = mapperTable;
    }

    public String getMapperScanCaching() {
        return mapperScanCaching;
    }

    public void setMapperScanCaching(String mapperScanCaching) {
        this.mapperScanCaching = mapperScanCaching;
    }

    public String getMapperScanCacheBlocks() {
        return mapperScanCacheBlocks;
    }

    public void setMapperScanCacheBlocks(String mapperScanCacheBlocks) {
        this.mapperScanCacheBlocks = mapperScanCacheBlocks;
    }

    public String getReducerTable() {
        if (null == reducerTable || "".equals(reducerTable))
            return null;
        return reducerTable;
    }

    public void setReducerTable(String reducerTable) {
        this.reducerTable = reducerTable;
    }

    public String getMapperScanPrefixFilter() {
        return this.mapperScanPrefixFilter;
    }

    public String getMapperScanPrefixFilter(String runDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        return RenderParam.render(this.mapperScanPrefixFilter, param);
    }

    public void setMapperScanPrefixFilter(String mapperScanPrefixFilter) {
        this.mapperScanPrefixFilter = mapperScanPrefixFilter;
    }

//	@Override
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public Class<? extends TableMapper> getMapperClazz()
//			throws ClassNotFoundException {
//		if (null == mapperClass || "".equals(mapperClass))
//			return null;
//		return (Class<? extends TableMapper>) ClassLoaderUtils
//				.loadClass(mapperClass);
//	}

}
