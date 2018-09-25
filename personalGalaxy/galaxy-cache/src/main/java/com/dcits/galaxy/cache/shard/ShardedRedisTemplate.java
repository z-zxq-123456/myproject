package com.dcits.galaxy.cache.shard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.api.Route;
import com.dcits.galaxy.cache.binary.RedisCommands;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.exception.CommandNotSupportException;
import com.dcits.galaxy.cache.template.RedisTemplate;

public class ShardedRedisTemplate extends RedisTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(ShardedRedisTemplate.class);
	
	private Route route = null;
	
	private Set<RedisSource> redisSources = null;
	
	public ShardedRedisTemplate(Route route ,Set<RedisSource> redisSources) {
		this.route = route;
		this.redisSources = redisSources;
	}
	
	public ShardedRedisTemplate() {
	}
	
	public void init(){
		if(redisSources == null) {
			throw new CacheException("redisSources must not be null!");
		}
		
		if(route == null){
			throw new CacheException("route must not be null!");
		}
	}

	@Override
	protected RedisCommands getRedis(byte[] key) {
		return route.route(key).getRedis();
	}
	
	protected Map<RedisCommands,byte[][]> getRedis(byte[][] keys) {
		Map<RedisSource,List<byte[]>> map = new HashMap<>();
		
		for(byte[] key : keys) {
			RedisSource source = route.route(key);
			if(!map.containsKey(source)){
				map.put(source, new ArrayList<byte[]>());
			}
			map.get(source).add(key);
		}
		
		Map<RedisCommands,byte[][]> resultMap = new HashMap<>();
		for(Entry<RedisSource, List<byte[]>> entry : map.entrySet()){
			RedisCommands redis = entry.getKey().getRedis();
			List<byte[]> byteList = entry.getValue();
			byte[][] bytes  = new byte[byteList.size()][];
			for(int i =0;i<byteList.size();i++){
				bytes[i] = byteList.get(i);
			}
			resultMap.put(redis, bytes);
		}
		return resultMap;
	}

	@Override
	protected void closeRedis(RedisCommands redis) {
		try {
			redis.close();
		} catch (IOException e) {
			throw new CacheException("Cann't close the redis:" + redis.toString(), e);
		}
	}

	@Override
	public void close() {
		for(RedisSource redisSource : redisSources) {
			try {
				redisSource.close();
			} catch (Exception e) {
				logger.warn("caught exception when close redisSource. ", e);
			}
		}
		
	}

	@Override
	public Boolean smove(byte[] srcKey, byte[] destKey, byte[] value) {
		if(!checkslots(srcKey, destKey)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute smove command.");
		}
		
		return super.smove(srcKey, destKey, value);
	}

	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		if(!checkslots(keys)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sinter command.");
		}
		return super.sinter(keys);
	}

	@Override
	public Long sinterStore(byte[] destKey, byte[]... keys) {
		if(!checkslots(destKey, keys)) {
				throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sinterStore command.");
		}
		return super.sinterStore(destKey, keys);
	}

	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		if(!checkslots(keys)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sunion command.");
		}
		return super.sunion(keys);
	}

	@Override
	public Long sunionstore(byte[] destKey, byte[]... keys) {
		if(!checkslots(destKey, keys)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sunionstore command.");
		}
		return super.sunionstore(destKey, keys);
	}

	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		if(!checkslots(keys)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sdiff command.");
		}
		return super.sdiff(keys);
	}

	@Override
	public Long sdiffstore(byte[] destKey, byte[]... keys) {
		if(!checkslots(destKey, keys)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute sdiffstore command.");
		}
		return super.sdiffstore(destKey, keys);
	}

	@Override
	public List<byte[]> mget(byte[]... keys) {
		List<byte[]> result = new ArrayList<>();
		Map<RedisCommands, byte[][]>  map = getRedis(keys);
		for(Entry<RedisCommands, byte[][]> entry :map.entrySet()){
			RedisCommands redis = entry.getKey();
			try{
				result.addAll(redis.mget(entry.getValue()));
			} finally {
				closeRedis(redis);
			}
		}
		return result;
	}

	@Override
	public Boolean mset(Map<byte[], byte[]> map) {
		if(map.isEmpty()){
			throw new CacheException("sdiff can't accept zero array paramter. ");
		}
		RedisSource source = null;
		for(byte[] key:map.keySet()){
			if(source == null){
				source = route.route(key);
			} else if(source != route.route(key)) {
				throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute mset command.");
			}
		}
		return super.mset(map);
	}

	@Override
	public Long msetnx(Map<byte[], byte[]> map) {
		if(map.isEmpty()){
			throw new CacheException("sdiff can't accept zero array paramter. ");
		}
		RedisSource source = null;
		for(byte[] key:map.keySet()){
			if(source == null){
				source = route.route(key);
			} else if(source != route.route(key)) {
				throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute msetnx command.");
			}
		}
		return super.msetnx(map);
	}

	@Override
	public Long zunionStore(byte[] destKey, byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zunionStore command.");
		}
		return super.zunionStore(destKey, sets);
	}

	@Override
	public Long zunionStore(byte[] destKey, double[] weights, byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zunionStore command.");
		}
		return super.zunionStore(destKey, weights, sets);
	}

	@Override
	public Long zunionStoreByMax(byte[] destKey, double[] weights,
			byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zunionStore command.");
		}
		return super.zunionStoreByMax(destKey, weights, sets);
	}

	@Override
	public Long zunionStoreByMin(byte[] destKey, double[] weights,
			byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zunionStore command.");
		}
		return super.zunionStoreByMin(destKey, weights, sets);
	}

	@Override
	public Long zinterStore(byte[] destKey, byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zinterStore command.");
		}
		return super.zinterStore(destKey, sets);
	}

	@Override
	public Long zinterStore(byte[] destKey, double[] weights,	byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zinterStore command.");
		}
		return super.zinterStore(destKey, weights, sets);
	}

	@Override
	public Long zinterStoreByMax(byte[] destKey, double[] weights, byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zinterStore command.");
		}
		return super.zinterStoreByMax(destKey, weights, sets);
	}

	@Override
	public Long zinterStoreByMin(byte[] destKey, double[] weights, byte[]... sets) {
		if(!checkslots(destKey, sets)) {
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute zinterStore command.");
		}
		return super.zinterStoreByMin(destKey, weights, sets);
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		Set<byte[]> resultSet = new HashSet<>();
		for(RedisSource source : redisSources){
			RedisCommands redis = source.getRedis();
			try{
				resultSet.addAll(redis.keys(pattern));
			} finally {
				closeRedis(redis);
			}
		}
		return resultSet;
	}

	@Override
	public Boolean rename(byte[] oldName, byte[] newName) {
		if(!checkslots(oldName, newName)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute rename command.");
		}
		return super.rename(oldName, newName);
	}

	@Override
	public Boolean renameNX(byte[] oldName, byte[] newName) {
		if(!checkslots(oldName, newName)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute renameNX command.");
		}
		return super.renameNX(oldName, newName);
	}

	@Override
	public Long sort(byte[] key, byte[] storeKey) {
		if(!checkslots(key, storeKey)){
			throw new CommandNotSupportException("ShardRedisTemplate not support sort that key and store in diff slots.");
		}
		return super.sort(key, storeKey);
	}

	@Override
	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		if(!checkslots(srcKey, dstKey)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute rpoplpush command.");
		}
		return super.rpoplpush(srcKey, dstKey);
	}

	@Override
	public byte[] brpoplpush(int timeout, byte[] srcKey, byte[] dstKey) {
		if(!checkslots(srcKey, dstKey)){
			throw new CommandNotSupportException("ShardRedisTemplate not support diff slot key execute brpoplpush command.");
		}
		return super.brpoplpush(timeout, srcKey, dstKey);
	}
	
	private boolean checkslots(byte[] srcKey, byte[] destKey){
		return route.route(srcKey) == route.route(destKey);
	}
	
	private boolean checkslots(byte[]... keys){
		if(keys == null || keys.length == 0){
			throw new CacheException("keys can't empty.");
		}
		
		if(keys.length == 1){
			return true;
		}
		
		RedisSource source = route.route(keys[0]);
		for (int i = keys.length - 1; i > 0; i--) {
			if(source != route.route(keys[i]))
				return false;
		}
		return true;
	}
	
	private boolean checkslots(byte[] destKey, byte[]... keys){
		if(keys == null || keys.length == 0){
			throw new CacheException("keys can't empty.");
		}
		RedisSource source = route.route(destKey);
		for(byte[] key: keys){
			if(source != route.route(key)){
				return false;
			}
		}
		return true;
	}

	@Override
	public Long del(byte[]... bKeys) {
		Map<RedisCommands, byte[][]> map = getRedis(bKeys);
		long num = 0;
		for(Entry<RedisCommands, byte[][]> entry : map.entrySet()){
			RedisCommands redis = entry.getKey();
			byte[][] subKeys = entry.getValue();
			Long result = redis.del(subKeys);
			if(result != null){
				num += result;
			}
		}
		return num;
	}
}
