package com.dcits.galaxy.cache.template;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.cache.binary.RedisCommands;
import com.dcits.galaxy.cache.exception.CacheException;

public abstract class BinaryRedisTemplate {
	
//	private static Logger logger = LoggerFactory.getLogger(BinaryRedisTemplate.class);
	
	abstract protected RedisCommands getRedis(byte[] actulyKey);
	
	abstract public void close() throws IOException;
	
	protected void closeRedis(RedisCommands redis) {
		if(redis == null)
			return;
		try {
			redis.close();
		} catch (IOException e) {
			throw new CacheException("Cann't close the redis:" + redis.toString(), e);
		}
	}
	
	public byte[] get(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.get(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Boolean set(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.set(key, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Long del(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.del(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Long hset(byte[] hkey, byte[] field,byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(hkey);
			Long result = redis.hset(hkey,field, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public String hmset(byte[] hkey, Map<byte[],byte[]> map) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(hkey);
			String result = redis.hmset(hkey,map);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public byte[] hget(byte[] hkey, byte[] field) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(hkey);
			byte[] result = redis.hget(hkey,field);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public List<byte[]> hmget(byte[] hkey, byte[]... field) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(hkey);
			List<byte[]> result = redis.hmget(hkey,field);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Map<byte[], byte[]> hgetAll(byte[] hkey) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(hkey);
			Map<byte[], byte[]> result = redis.hgetAll(hkey);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Boolean exists(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.exists(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public String type(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			String result = redis.type(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Boolean expire(byte[] key, int seconds) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.expire(key,seconds);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean expireAt(byte[] key, long unixTime) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.expireAt(key,unixTime);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean persist(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.persist(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean move(byte[] key, int dbIndex) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.move(key, dbIndex);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long ttl(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.ttl(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public List<byte[]> sort(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			List<byte[]> result = redis.sort(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}


	public List<byte[]> sort(byte[] key, boolean desc, boolean alpha) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			List<byte[]> result = redis.sort(key, desc, alpha);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Long hsetnx(byte[] key, byte[] field, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.hsetnx(key,field,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long hincrBy(byte[] key, byte[] field, long value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.hincrBy(key,field,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Double hincrBy(byte[] key, byte[] field, double value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Double result = redis.hincrBy(key,field,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean hcontainsKey(byte[] key, byte[] field) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.hcontainsKey(key, field);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long hremove(byte[] key, byte[]... field) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.hremove(key, field);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long hsize(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.hsize(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> hkeySet(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.hkeySet(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Collection<byte[]> hValues(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Collection<byte[]> result = redis.hValues(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long lsize(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.lsize(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] lget(byte[] key, long index) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.lget(key, index);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean lset(byte[] key, long index, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.lset(key, index,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long rpush(byte[] key, byte[]... value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.rpush(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long lpush(byte[] key, byte[]... value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.lpush(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long linsert(byte[] key, byte[] pivot, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.linsert(key,pivot,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long lappend(byte[] key, byte[] pivot, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.lappend(key,pivot,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long lremove(byte[] key, long count, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.lremove(key,count,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] lpop(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.lpop(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] rpop(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.rpop(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long rpushx(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.rpushx(key, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long lpushx(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.lpushx(key, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public List<byte[]> lrange(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			List<byte[]> result = redis.lrange(key, begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean ltrim(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.ltrim(key, begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long sadd(byte[] key, byte[]... value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.sadd(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long sremove(byte[] key, byte[]... value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.sremove(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] spop(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.spop(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Long ssize(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.ssize(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean scontains(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.scontains(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Set<byte[]> smembers(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.smembers(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public byte[] srandmember(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.srandmember(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] getSet(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.getSet(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Boolean setnx(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.setnx(key, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean setex(byte[] key, int seconds, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.setex(key,seconds, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long incr(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.incr(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long incrBy(byte[] key, long value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.incrBy(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Double incrBy(byte[] key, double value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Double result = redis.incrBy(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long decr(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.decr(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long decrBy(byte[] key, long value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.decrBy(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long append(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.append(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public byte[] getRange(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			byte[] result = redis.getRange(key,begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long setRange(byte[] key, long offset, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.setRange(key,offset,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean getBit(byte[] key, long offset) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.getBit(key,offset);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean setBit(byte[] key, long offset, boolean value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.setBit(key,offset,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long strLen(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.strLen(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Boolean zadd(byte[] key, double score, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Boolean result = redis.zadd(key,score,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zadd(byte[] key, Map<byte[], Double> valueMap) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zadd(key,valueMap);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zrem(byte[] key, byte[]... value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zrem(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Double zincrBy(byte[] key, double increment, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Double result = redis.zincrBy(key,increment,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zrank(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zrank(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zrevRank(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zrevRank(key,value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrange(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrange(key,begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrangeWithScore(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrangeWithScore(key, begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrevRange(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrevRange(key, begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrevRangeWithScore(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrevRangeWithScore(key, begin,end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrangeByScore(key, min,max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrangeByScoreWithScore(byte[] key, double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrangeByScoreWithScore(key, min,max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrangeByScore(byte[] key, double min, double max,
			int offset, int count) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrangeByScore(key,min,max,offset,count);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrangeByScoreWithScore(byte[] key, double min,
			double max, int offset, int count) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrangeByScoreWithScore(key,min,max,offset,count);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrevRangeByScore(byte[] key, double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrevRangeByScore(key,min,max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrevRangeByScoreWithScore(byte[] key,
			double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrevRangeByScoreWithScore(key,min,max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Set<byte[]> zrevRangeByScore(byte[] key, double min, double max,
			int offset, int count) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Set<byte[]> result = redis.zrevRangeByScore(key,min,max,offset,count);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Map<byte[], Double> zrevRangeByScoreWithScore(byte[] key,
			double min, double max, int offset, int count) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Map<byte[], Double> result = redis.zrevRangeByScoreWithScore(key,min,max,offset,count);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zcount(byte[] key, double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zcount(key, min, max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zcard(byte[] key) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zcard(key);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Double zscore(byte[] key, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Double result = redis.zscore(key, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zremRange(byte[] key, long begin, long end) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zremRange(key, begin, end);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	
	public Long zremRangeByScore(byte[] key, double min, double max) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(key);
			Long result = redis.zremRangeByScore(key, min, max);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	public Boolean smove(byte[] srcKey, byte[] destKey, byte[] value) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(srcKey);
			Boolean result = redis.smove(srcKey, destKey, value);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Set<byte[]> sinter(byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(keys[1]);
			Set<byte[]> result = redis.sinter(keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long sinterStore(byte[] destKey, byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.sinterStore(destKey, keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Set<byte[]> sunion(byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(keys[1]);
			Set<byte[]> result = redis.sunion(keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long sunionstore(byte[] destKey, byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.sunionstore(destKey, keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Set<byte[]> sdiff(byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(keys[1]);
			Set<byte[]> result = redis.sdiff(keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long sdiffstore(byte[] destKey, byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.sdiffstore(destKey, keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public List<byte[]> mget(byte[]... keys) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(keys[1]);
			List<byte[]> result = redis.mget(keys);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Boolean mset(Map<byte[], byte[]> map) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Boolean result = redis.mset(map);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long msetnx(Map<byte[], byte[]> map) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Long result = redis.msetnx(map);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zunionStore(byte[] destKey, byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zunionStore(destKey, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zunionStore(byte[] destKey, double[] weights, byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zunionStore(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zunionStoreByMax(byte[] destKey, double[] weights,
			byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zunionStoreByMax(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zunionStoreByMin(byte[] destKey, double[] weights,
			byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zunionStoreByMin(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zinterStore(byte[] destKey, byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zinterStore(destKey, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zinterStore(byte[] destKey, double[] weights,	byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zinterStore(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zinterStoreByMax(byte[] destKey, double[] weights, byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zinterStoreByMax(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long zinterStoreByMin(byte[] destKey, double[] weights, byte[]... sets) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(destKey);
			Long result = redis.zinterStoreByMin(destKey, weights, sets);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Set<byte[]> keys(byte[] pattern) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Set<byte[]> result = redis.keys(pattern);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Boolean rename(byte[] oldName, byte[] newName) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Boolean result = redis.rename(oldName, newName);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Boolean renameNX(byte[] oldName, byte[] newName) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Boolean result = redis.renameNX(oldName, newName);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public Long sort(byte[] key, byte[] storeKey) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			Long result = redis.sort(key, storeKey);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			byte[] result = redis.rpoplpush(srcKey, dstKey);
			return result;
		} finally {
			closeRedis(redis);
		}
	}

	public byte[] brpoplpush(int timeout, byte[] srcKey, byte[] dstKey) {
		RedisCommands redis = null;
		try {
			redis = this.getRedis(null);
			byte[] result = redis.brpoplpush(timeout, srcKey, dstKey);
			return result;
		} finally {
			closeRedis(redis);
		}
	}
	
	abstract public Long del(byte[]... bKeys);
}
