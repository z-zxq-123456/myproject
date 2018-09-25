package com.dcits.galaxy.dal.mybatis.shard;

import javax.sql.DataSource;

/**
 * 存放单个分库的信息
 * @author fan.kaijie
 */

public class Shard {
	
	/**
	 * 分库标识
	 */
    private String id;
    private DataSource dataSource;
    
    /**
     * we will initialize proper thread pools which stand in front of data
     * sources as per connection pool size. <br>
     * usually, they will have same number of objects.<br>
     * you have to set a proper size for this attribute as per your data source
     * attributes. In case you forget it, we set a default value with
     * "number of CPU" * 5.
     */
    private int poolSize = Runtime.getRuntime().availableProcessors() * 5;
    private String attach;
    private String description;
    
    
    
    public Shard() {
	}
    
    @Deprecated
	public Shard(String id, DataSource dataSource, String description, int poolSize) {
        this.id = id;
        this.dataSource = dataSource;
        this.description = description;
        this.poolSize = poolSize;
    }

    public Shard(String id, DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
    }
    
	public Shard(String id, DataSource dataSource, String attach, String description) {
        this.id = id;
        this.dataSource = dataSource;
        this.description = description;
        this.attach = attach;
    }


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Deprecated
    public int getPoolSize() {
		return poolSize;
	}

    @Deprecated
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	@Override
	public String toString() {
		return "Shard [id=" + id + ", dataSource=" + dataSource + ", poolSize="
				+ poolSize + ", description=" + description + "]";
	}

}
