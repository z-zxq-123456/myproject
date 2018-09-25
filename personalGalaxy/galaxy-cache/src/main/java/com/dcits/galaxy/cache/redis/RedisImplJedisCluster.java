package com.dcits.galaxy.cache.redis;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

import com.dcits.galaxy.cache.binary.RedisCommands;
import com.dcits.galaxy.cache.exception.CacheException;
import com.dcits.galaxy.cache.exception.CommandNotSupportException;
import com.dcits.galaxy.cache.utils.JedisTypeUtils;

public class RedisImplJedisCluster implements RedisCommands {

	private JedisCluster jedis = null;

	private boolean close = false;
	
	public RedisImplJedisCluster(JedisCluster jedis) {
		this.jedis = jedis;
	}

	protected JedisCluster getJedis() {
		return this.jedis;
	}

	@Override
	public void close() throws IOException {
//		getJedis().close();
		this.jedis = null;
		this.close = true;
	}

	@Override
	public Long hset(byte[] key, byte[] field, byte[] value) {
		return getJedis().hset(key, field, value);
	}

	@Override
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		return getJedis().hsetnx(key, field, value);
	}

	@Override
	public String hmset(byte[] key, Map<byte[], byte[]> hash) {
		return getJedis().hmset(key, hash);
	}

	@Override
	public byte[] hget(byte[] key, byte[] field) {
		return getJedis().hget(key, field);
	}

	@Override
	public List<byte[]> hmget(byte[] key, byte[]... fields) {
		return getJedis().hmget(key, fields);
	}

	@Override
	public Long hincrBy(byte[] key, byte[] field, long value) {
		return getJedis().hincrBy(key, field, value);
	}

	@Override
	public Double hincrBy(byte[] key, byte[] field, double value) {
		return getJedis().hincrByFloat(key, field, value);
	}

	@Override
	public Boolean hcontainsKey(byte[] key, byte[] field) {
		return getJedis().hexists(key, field);
	}

	@Override
	public Long hremove(byte[] key, byte[]... field) {
		return getJedis().hdel(key, field);
	}

	@Override
	public Long hsize(byte[] key) {
		return getJedis().hlen(key);
	}

	@Override
	public Set<byte[]> hkeySet(byte[] key) {
		return getJedis().hkeys(key);
	}

	@Override
	public Collection<byte[]> hValues(byte[] key) {
		return getJedis().hvals(key);
	}

	@Override
	public Map<byte[], byte[]> hgetAll(byte[] key) {
		return getJedis().hgetAll(key);
	}

	@Override
	public boolean isClose() {
		return this.close || this.jedis == null;
	}


	@Override
	public Boolean exists(byte[] key) {
		return getJedis().exists(key);
	}

	@Override
	public String type(byte[] key) {
		return getJedis().type(key);
	}

	@Override
	public Boolean expire(byte[] key, int seconds) {
		Long result = getJedis().expire(key, seconds);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean expireAt(byte[] key, long unixTime) {
		Long result = getJedis().expireAt(key, unixTime);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean persist(byte[] key) {
		Long result = getJedis().persist(key);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean move(byte[] key, int dbIndex) {
		throw new CacheException("Redis Cluster don't support command: move");
	}

	@Override
	public Long ttl(byte[] key) {
		return getJedis().ttl(key);
	}

	@Override
	public List<byte[]> sort(byte[] key) {
		return getJedis().sort(key);
	}


//	@Override
//	public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
//		return getJedis().sort(key, sortingParameters);
//	}
//
//	@Override
//	public Long sort(byte[] key, SortingParams sortingParameters,
//			byte[] b) {
//		return getJedis().sort(key, sortingParameters,b);
//	}

	@Override
	public byte[] get(byte[] key) {
		return getJedis().get(key);
	}

	@Override
	public byte[] getSet(byte[] key, byte[] value) {
		return getJedis().getSet(key, value);
	}

	@Override
	public Boolean set(byte[] key, byte[] value) {
		String result = getJedis().set(key, value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean setnx(byte[] key, byte[] value) {
		Long result = getJedis().setnx(key, value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean setex(byte[] key, int seconds, byte[] value) {
		String result = getJedis().setex(key, seconds, value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long incr(byte[] key) {
		Long result = getJedis().incr(key);
		return result;
	}

	@Override
	public Long incrBy(byte[] key, long value) {
		Long result = getJedis().incrBy(key, value);
		return result;
	}

	@Override
	public Double incrBy(byte[] key, double value) {
		Double result = getJedis().incrByFloat(key, value);
		return result;
	}

	@Override
	public Long decr(byte[] key) {
		Long result = getJedis().decr(key);
		return result;
	}

	@Override
	public Long decrBy(byte[] key, long value) {
		Long result = getJedis().decrBy(key, value);
		return result;
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		Long result = getJedis().append(key, value);
		return result;
	}

	@Override
	public byte[] getRange(byte[] key, long begin, long end) {
		byte[] result = getJedis().getrange(key, begin, end);
		return result;
	}

	@Override
	public Long setRange(byte[] key, long offset,byte[] value) {
		Long result = getJedis().setrange(key, offset, value);
		return result;
	}

	@Override
	public Boolean getBit(byte[] key, long offset) {
		Boolean result = getJedis().getbit(key, offset);
		return result;
	}

	@Override
	public Boolean setBit(byte[] key, long offset, boolean value) {
		Boolean result = getJedis().setbit(key, offset, value);
		return result;
	}

	@Override
	public Long strLen(byte[] key) {
		Long result = getJedis().strlen(key);
		return result;
	}

	@Override
	public Long lsize(byte[] key) {
		Long result = getJedis().llen(key);
		return result;
	}

	@Override
	public byte[] lget(byte[] key, long index) {
		byte[] result = getJedis().lindex(key, index);
		return result;
	}

	@Override
	public Boolean lset(byte[] key, long index, byte[] value) {
		String result = getJedis().lset(key, index, value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long rpush(byte[] key, byte[]... value) {
		Long result = getJedis().rpush(key, value);
		return result;
	}

	@Override
	public Long lpush(byte[] key, byte[]... value) {
		Long result = getJedis().lpush(key, value);
		return result;
	}

	@Override
	public Long linsert(byte[] key, byte[] pivot, byte[] value) {
		Long result = getJedis().linsert(key, LIST_POSITION.BEFORE, pivot, value);
		return result;
	}
	
	@Override
	public Long lappend(byte[] key, byte[] pivot, byte[] value) {
		Long result = getJedis().linsert(key, LIST_POSITION.AFTER, pivot, value);
		return result;
	}

	@Override
	public Long lremove(byte[] key, long count, byte[] value) {
		Long result = getJedis().lrem(key, count, value);
		return result;
	}

	@Override
	public byte[] lpop(byte[] key) {
		byte[] result = getJedis().lpop(key);
		return result;
	}

	@Override
	public byte[] rpop(byte[] key) {
		byte[] result = getJedis().rpop(key);
		return result;
	}

	@Override
	public Long rpushx(byte[] key, byte[] value) {
		Long result = getJedis().rpushx(key, value);
		return result;
	}

	@Override
	public Long lpushx(byte[] key, byte[] value) {
		Long result = getJedis().lpushx(key, value);
		return result;
	}

	@Override
	public List<byte[]> lrange(byte[] key, long begin, long end) {
		List<byte[]> result = getJedis().lrange(key, begin,end);
		return result;
	}

	@Override
	public Boolean ltrim(byte[] key, long begin, long end) {
		String result = getJedis().ltrim(key, begin,end);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long sadd(byte[] key, byte[]... value) {
		Long result = getJedis().sadd(key, value);
		return result;
	}

	@Override
	public Long sremove(byte[] key, byte[]... value) {
		Long result = getJedis().srem(key, value);
		return result;
	}

	@Override
	public byte[] spop(byte[] key) {
		byte[] result = getJedis().spop(key);
		return result;
	}

	@Override
	public Long ssize(byte[] key) {
		Long result = getJedis().scard(key);
		return result;
	}

	@Override
	public Boolean scontains(byte[] key, byte[] value) {
		Boolean result = getJedis().sismember(key, value);
		return result;
	}


	@Override
	public Set<byte[]> smembers(byte[] key) {
		Set<byte[]> result = getJedis().smembers(key);
		return result;
	}

	@Override
	public byte[] srandmember(byte[] key) {
		byte[] result = getJedis().srandmember(key);
		return result;
	}

	@Override
	public Boolean zadd(byte[] key, double score, byte[] value) {
		Long result = getJedis().zadd(key, score,value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long zadd(byte[] key, Map<byte[],Double> valueMap) {
		Long result = getJedis().zadd(key, valueMap);
		return result;
	}

	@Override
	public Long zrem(byte[] key, byte[]... value) {
		Long result = getJedis().zrem(key, value);
		return result;
	}

	@Override
	public Double zincrBy(byte[] key, double increment, byte[] value) {
		Double result = getJedis().zincrby(key, increment,value);
		return result;
	}

	@Override
	public Long zrank(byte[] key, byte[] value) {
		Long result = getJedis().zrank(key, value);
		return result;
	}

	@Override
	public Long zrevRank(byte[] key, byte[] value) {
		Long result = getJedis().zrevrank(key, value);
		return result;
	}

	@Override
	public Set<byte[]> zrange(byte[] key, long begin, long end) {
		Set<byte[]> result = getJedis().zrange(key, begin,end);
		return result;
	}

	@Override
	public Map<byte[], Double> zrangeWithScore(byte[] key, long begin, long end) {
		Set<Tuple> result = getJedis().zrangeWithScores(key, begin, end);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Set<byte[]> zrevRange(byte[] key, long begin, long end) {
		Set<byte[]> result = getJedis().zrevrange(key, begin, end);
		return result;
	}

	@Override
	public Map<byte[], Double> zrevRangeWithScore(byte[] key, long begin, long end) {
		Set<Tuple> result = getJedis().zrevrangeWithScores(key, begin, end);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		Set<byte[]> result = getJedis().zrangeByScore(key, min,max);
		return result;
	}

	@Override
	public Map<byte[], Double> zrangeByScoreWithScore(byte[] key, double min, double max) {
		Set<Tuple> result = getJedis().zrangeByScoreWithScores(key, max, min);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max,
			int offset, int count) {
		Set<byte[]> result = getJedis().zrangeByScore(key, min,max,offset,count);
		return result;
	}

	@Override
	public Map<byte[], Double> zrangeByScoreWithScore(byte[] key, double min,
			double max, int offset, int count) {
		Set<Tuple> result = getJedis().zrangeByScoreWithScores(key, min, max,offset,count);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Set<byte[]> zrevRangeByScore(byte[] key, double min, double max) {
		Set<byte[]> result = getJedis().zrevrangeByScore(key, min,max);
		return result;
	}

	@Override
	public Map<byte[], Double> zrevRangeByScoreWithScore(byte[] key, double min,
			double max) {
		Set<Tuple> result = getJedis().zrevrangeByScoreWithScores(key, min, max);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Set<byte[]> zrevRangeByScore(byte[] key, double min, double max,
			int offset, int count) {
		Set<byte[]> result = getJedis().zrevrangeByScore(key, max, min);
		return result;
	}

	@Override
	public Map<byte[], Double> zrevRangeByScoreWithScore(byte[] key, double min,
			double max, int offset, int count) {
		Set<Tuple> result = getJedis().zrevrangeByScoreWithScores(key, max, min,offset,count);
		return JedisTypeUtils.tuplesToMap(result);
	}

	@Override
	public Long zcount(byte[] key, double min, double max) {
		Long result = getJedis().zcount(key,min,max);
		return result;
	}

	@Override
	public Long zcard(byte[] key) {
		Long result = getJedis().zcard(key);
		return result;
	}

	@Override
	public Double zscore(byte[] key, byte[] value) {
		Double result = getJedis().zscore(key, value);
		return result;
	}

	@Override
	public Long zremRange(byte[] key, long begin, long end) {
		Long result = getJedis().zremrangeByRank(key, begin, end);
		return result;
	}

	@Override
	public Long zremRangeByScore(byte[] key, double min, double max) {
		Long result = getJedis().zremrangeByScore(key, min, max);
		return result;
	}

	@Override
	public Long del(byte[]... keys) {
		Long result = getJedis().del(keys);
		return result;
	}

	@Override
	public List<byte[]> blpop(int timeout, byte[] key) {
		return getJedis().blpop(timeout, key);
	}

	@Override
	public List<byte[]> brpop(int timeout, byte[] key) {
		return getJedis().brpop(timeout, key);
	}

	@Override
	public List<byte[]> sort(byte[] key, boolean desc, boolean alpha) {
		SortingParams sortingParams = new SortingParams();
		if(desc){
			sortingParams.desc();
		}
		if(alpha){
			sortingParams.alpha();
		}
		
		return getJedis().sort(key, sortingParams);
	}

	@Override
	public Boolean smove(byte[] srcKey, byte[] destKey, byte[] value) {
		Long result = getJedis().smove(srcKey, destKey, value);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		return getJedis().sinter(keys);
	}

	@Override
	public Long sinterStore(byte[] destKey, byte[]... keys) {
		return getJedis().sinterstore(destKey, keys);
	}

	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		return getJedis().sunion(keys);
	}

	@Override
	public Long sunionstore(byte[] destKey, byte[]... keys) {
		return getJedis().sunionstore(destKey, keys);
	}

	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		return getJedis().sdiff(keys);
	}

	@Override
	public Long sdiffstore(byte[] destKey, byte[]... keys) {
		return getJedis().sdiffstore(destKey, keys);
	}

	@Override
	public List<byte[]> mget(byte[]... keys) {
		return getJedis().mget(keys);
	}

	@Override
	public Boolean mset(Map<byte[], byte[]> map) {
		if(map.size() == 0)
			return true;
		int bytesSize = map.size() * 2;
		byte[][] bytes = new byte[bytesSize][];
		int i = 0;
		for(Entry<byte[], byte[]> entry:map.entrySet()){
			bytes[i++] = entry.getKey();
			bytes[i++] = entry.getValue();
		}
		String result = getJedis().mset(bytes);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long msetnx(Map<byte[], byte[]> map) {
		if(map.size() == 0)
			return 0L;
		int bytesSize = map.size() * 2;
		byte[][] bytes = new byte[bytesSize][];
		int i = 0;
		for(Entry<byte[], byte[]> entry:map.entrySet()){
			bytes[i++] = entry.getKey();
			bytes[i++] = entry.getValue();
		}
		Long result = getJedis().msetnx(bytes);
		return result;
	}

	@Override
	public Long zunionStore(byte[] destKey, byte[]... sets) {
		Long result = getJedis().zunionstore(destKey, sets);
		return result;
	}

	@Override
	public Long zunionStore(byte[] destKey, double[] weights, byte[]... sets) {
		return zUnionStore(destKey, Aggregate.SUM, weights, sets);
	}

	@Override
	public Long zunionStoreByMax(byte[] destKey, double[] weights,
			byte[]... sets) {
		return zUnionStore(destKey, Aggregate.MAX, weights, sets);
	}

	@Override
	public Long zunionStoreByMin(byte[] destKey, double[] weights,
			byte[]... sets) {
		return zUnionStore(destKey, Aggregate.MIN, weights, sets);
	}

	private Long zUnionStore(byte[] destKey, Aggregate aggregate,double[] weights,	byte[]... sets) {
		ZParams zparams = JedisTypeUtils.newZparams(aggregate,weights);
		Long result = getJedis().zunionstore(destKey,zparams,sets);
		return result;
	}

	@Override
	public Long zinterStore(byte[] destKey, byte[]... sets) {
		Long result = getJedis().zinterstore(destKey, sets);
		return result;
	}

	@Override
	public Long zinterStore(byte[] destKey, double[] weights,	byte[]... sets) {
		return zInterStore(destKey, Aggregate.SUM, weights, sets);
	}

	@Override
	public Long zinterStoreByMax(byte[] destKey, double[] weights, byte[]... sets) {
		return zInterStore(destKey, Aggregate.MAX, weights, sets);
	}

	@Override
	public Long zinterStoreByMin(byte[] destKey, double[] weights, byte[]... sets) {
		return zInterStore(destKey, Aggregate.MIN, weights, sets);
	}
	
	private Long zInterStore(byte[] destKey, Aggregate aggregate,double[] weights,	byte[]... sets) {
		ZParams zparams = JedisTypeUtils.newZparams(aggregate,weights);
		Long result = getJedis().zinterstore(destKey,zparams,sets);
		return result;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		throw new CommandNotSupportException("redis cluster don't support keys command. ");
	}

	@Override
	public Boolean rename(byte[] oldName, byte[] newName) {
		String result = getJedis().rename(oldName, newName);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Boolean renameNX(byte[] oldName, byte[] newName) {
		Long result = getJedis().renamenx(oldName, newName);
		return JedisTypeUtils.toBoolean(result);
	}

	@Override
	public Long sort(byte[] key, byte[] storeKey) {
		return getJedis().sort(key, storeKey);
	}

	@Override
	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		byte[] result = getJedis().rpoplpush(srcKey, dstKey);
		return result;
	}

	@Override
	public byte[] brpoplpush(int timeout, byte[] srcKey, byte[] dstKey) {
		byte[] result = getJedis().brpoplpush(srcKey, dstKey,timeout);
		return result;
	}
}
