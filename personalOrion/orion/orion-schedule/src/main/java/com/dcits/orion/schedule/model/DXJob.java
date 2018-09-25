/**
 * <p>Title: DXJob.java</p>
 * <p>Description: Sqoop Job定义</p>
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
 * @description Sqoop Job定义
 * @update 2014年9月15日 下午3:25:27
 */

public class DXJob extends AbstractJob {

    private String connection_name;

    private String operate_type;

    private String table;

    private String columns;

    private String sql;

    private String boundary_query;

    private String partition_column;

    private String rows;

    private String compression_format;

    private String output_directory;

    private String input_directory;

    private String extractors;

    private String loaders;

    public String getConnection_name() {
        return connection_name;
    }

    public void setConnection_name(String connection_name) {
        this.connection_name = connection_name;
    }

    public String getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(String operate_type) {
        this.operate_type = operate_type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getPartition_column() {
        return partition_column;
    }

    public void setPartition_column(String partition_column) {
        this.partition_column = partition_column;
    }

    public String getCompression_format() {
        return compression_format;
    }

    public void setCompression_format(String compression_format) {
        this.compression_format = compression_format;
    }

   /* public String getOutput_directory() {
        return RenderParam.render(output_directory);
    }*/

    public String getOutput_directory(String runDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        return RenderParam.render(output_directory, param);
    }

    public String getOutput_directory(String runDate,String shardId) {
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        param.put("shardId", shardId);
        return RenderParam.render(output_directory, param);
    }


    public void setOutput_directory(String output_directory) {
        this.output_directory = output_directory;
    }

    public String getExtractors() {
        return extractors;
    }

    public void setExtractors(String extractors) {
        this.extractors = extractors;
    }

    public String getLoaders() {
        return loaders;
    }

    public void setLoaders(String loaders) {
        this.loaders = loaders;
    }

    public String getBoundary_query() {
        return boundary_query;
    }

    public void setBoundary_query(String boundary_query) {
        this.boundary_query = boundary_query;
    }

    /**
     * @return rows : return the property rows.
     */
    public String getRows() {
        return rows;
    }

    /**
     * @param rows : set the property rows.
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

   /* public String getInput_directory() {
        return RenderParam.render(input_directory);
    }*/

    public String getInput_directory(String runDate) {
        Map<String, Object> param = new HashMap<>();
        param.put("sysdate", runDate);
        return RenderParam.render(input_directory, param);
    }

    public void setInput_directory(String input_directory) {
        this.input_directory = input_directory;
    }
}
