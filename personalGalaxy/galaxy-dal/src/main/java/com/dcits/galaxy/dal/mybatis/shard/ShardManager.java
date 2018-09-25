package com.dcits.galaxy.dal.mybatis.shard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.dal.mybatis.exception.NoShardFoundException;

/**
 * 封装所有的分库以及默认库信息，提供相应的访问方法
 *
 * @author fan.kaijie
 */
public class ShardManager {

    private Map<String, Shard> shardMap = null;

    private String defaultShardId = null;

    public ShardManager() {
    }

    public ShardManager(Map<String, Shard> shardMap) {
        this.shardMap = shardMap;
    }

    public ShardManager(Set<Shard> shardSet) {
        setShards(shardSet);
    }

    public void setShardMap(Map<String, Shard> shardMap) {
        this.shardMap = shardMap;
    }

    public void setShards(Set<Shard> shardSet) {
        if (this.shardMap == null)
            this.shardMap = new HashMap<String, Shard>();
        for (Shard shard : shardSet) {
            shardMap.put(shard.getId(), shard);
        }
    }

    public String getDefaultShardId() {
        return defaultShardId;
    }

    /**
     * 判断是否拥有对应id的Shard
     *
     * @param id
     * @return
     */
    public boolean hasShard(String id) {
        return shardMap.containsKey(id);
    }

    /**
     * 判断是否持有此Shard
     *
     * @param shard
     * @return
     */
    public boolean hasShard(Shard shard) {
        return shardMap.containsValue(shard);
    }

    /**
     * 通过id获得相应的Shard
     *
     * @param id
     * @return
     */
    public Shard getShard(String id) {
        return shardMap.get(id);
    }

    /**
     * 获取Shard的总数
     *
     * @return
     */
    public int getShardSize() {
        return shardMap.size();
    }

    /**
     * 返回所有的Shard的id
     *
     * @return
     */
    public Set<String> getShardIds() {
        return shardMap.keySet();
    }

    /**
     * 返回所有的Shard
     *
     * @return
     */
    public List<Shard> getAllShard() {
        List<Shard> shardList = new ArrayList<>();
        for (Map.Entry<String, Shard> entry : shardMap
                .entrySet()) {
            shardList.add(entry.getValue());
        }
        return shardList;
    }

    /**
     * 返回默认Shard
     *
     * @return
     */
    public Shard getDefaultShard() {
    	if(defaultShardId == null)
    		return null;
        return this.getShard(defaultShardId);
    }

    public void setDefaultShardId(String defaultShardId) {
        this.defaultShardId = defaultShardId;
    }
    
    @Deprecated
    public void setDefaultShard(Shard defaultShard) {
		if (this.defaultShardId == null)
			this.defaultShardId = defaultShard.getId();
    }

    /**
     * 返回除默认Shard以外的所以Shard
     */
    @Deprecated
    public List<Shard> getShardsWithOutDefault() {
        List<Shard> shardList = new ArrayList<>();
        for (Map.Entry<String, Shard> entry : shardMap.entrySet()) {
            if (!entry.getKey().equals(defaultShardId)) {
                shardList.add(entry.getValue());
            }
        }
        return shardList;
    }
    
    /**
     * 返回多个shard，根据ids
     * @param ids 多个分库id
     * @return Set<Shard> 分库集合
     */
    public Set<Shard> getShards(Set<String> ids) {
    	Set<Shard> shardSet = new HashSet<Shard>();
		if (!ids.isEmpty()) {
			for (String id : ids) {
				Shard s = getShard(id);
				if (s == null) {
					throw new NoShardFoundException("No shard found in shardManager which shardId = " + id );
				}
				shardSet.add(s);
			}
		}
		return shardSet;
	}
}
