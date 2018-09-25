package com.dcits.galaxy.cache.template;

import static com.dcits.galaxy.cache.utils.RedisUtils.getByteKey;
import static com.dcits.galaxy.cache.utils.RedisUtils.getByteKeys;
import static com.dcits.galaxy.cache.utils.RedisUtils.getByteValue;
import static com.dcits.galaxy.cache.utils.RedisUtils.getByteValueMap;
import static com.dcits.galaxy.cache.utils.RedisUtils.getByteValues;
import static com.dcits.galaxy.cache.utils.RedisUtils.handleResult;
import static com.dcits.galaxy.cache.utils.RedisUtils.handleResultString;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dcits.galaxy.cache.utils.RedisUtils;

public abstract class RedisTemplate extends BinaryRedisTemplate {
	
	public Object get(String key) {
		byte[] byteKey = getByteKey(key);
		return handleResult(get(byteKey));
	}

	public Object getSet(String key, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return handleResult(getSet(byteKey, byteValue));
	}

	public Boolean set(String key, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return set(byteKey, byteValue);
	}
	
	public Long del(String key) {
		byte[] byteKey = getByteKey(key);
		return del(byteKey);
	}
	
	public Long del(String... keys) {
		byte[][] bKeys = RedisUtils.getByteKeys(keys);
		return del(bKeys);
	}

	public Boolean setnx(String key, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return setnx(byteKey, byteValue);
	}

	public Boolean setex(String key, int seconds, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return setex(byteKey, seconds,byteValue);
	}

	public Long incr(String key) {
		byte[] byteKey = getByteKey(key);
		return incr(byteKey);
	}

	public Long incrBy(String key, long value) {
		byte[] byteKey = getByteKey(key);
		return incrBy(byteKey,value);
	}

	public Double incrBy(String key, double value) {
		byte[] byteKey = getByteKey(key);
		return incrBy(byteKey,value);
	}

	public Long decr(String key) {
		byte[] byteKey = getByteKey(key);
		return decr(byteKey);
	}

	
	public Long decrBy(String key, long value) {
		byte[] byteKey = getByteKey(key);
		return decrBy(byteKey,value);
	}

	
	public Long append(String key, byte[] value) {
		byte[] byteKey = getByteKey(key);
		return append(byteKey,value);
	}

	
	public byte[] getRange(String key, long begin, long end) {
		byte[] byteKey = getByteKey(key);
		return getRange(byteKey,begin,end);
	}

	
	public Long setRange(String key, long offset, byte[] value) {
		byte[] byteKey = getByteKey(key);
		return setRange(byteKey,offset,value);
	}

	
	public Boolean getBit(String key, long offset) {
		byte[] byteKey = getByteKey(key);
		return getBit(byteKey,offset);
	}

	
	public Boolean setBit(String key, long offset, boolean value) {
		byte[] byteKey = getByteKey(key);
		return setBit(byteKey,offset,value);
	}

	
	public Long strLen(String key) {
		byte[] byteKey = getByteKey(key);
		return strLen(byteKey);
	}

	
	public Boolean exists(String key) {
		byte[] byteKey = getByteKey(key);
		return exists(byteKey);
	}

	
//	public Long del(String... keys){
//		byte[][] byteskeys = getByteKeys(keys);
//		return del(byteskeys);		
//	};

	
	public String type(String key) {
		byte[] byteKey = getByteKey(key);
		return type(byteKey);
	}
	
	public Boolean expire(String key, int seconds) {
		byte[] byteKey = getByteKey(key);
		return expire(byteKey,seconds);
	}

	
	public Boolean expireAt(String key, long unixTime) {
		byte[] byteKey = getByteKey(key);
		return expireAt(byteKey,unixTime);
	}

	
	public Boolean persist(String key) {
		byte[] byteKey = getByteKey(key);
		return persist(byteKey);
	}

	
	public Boolean move(String key, int dbIndex) {
		byte[] byteKey = getByteKey(key);
		return move(byteKey,dbIndex);
	}

	
	public Long ttl(String key) {
		byte[] byteKey = getByteKey(key);
		return ttl(byteKey);
	}
	
	@SuppressWarnings("rawtypes")
	public List sort(String key) {
		byte[] byteKey = getByteKey(key);
		return handleResult(sort(byteKey));
	}
	
	@SuppressWarnings("rawtypes")
	public List sort(String key, boolean desc, boolean alpha){
		byte[] byteKey = getByteKey(key);
		return handleResult(sort(byteKey, desc, alpha));
	}
	
	public Long lsize(String key) {
		byte[] byteKey = getByteKey(key);
		return lsize(byteKey);
	}

	public Object lget(String key, long index){
		byte[] byteKey = getByteKey(key);
		return handleResult(lget(byteKey,index));
	}

	public Boolean lset(String key, long index, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return lset(byteKey,index,byteValue);
	}

	public Long rpush(String key, Object... value){
		byte[] byteKey = getByteKey(key);
		byte[][] byteValues = getByteValues(value);
		return rpush(byteKey,byteValues);
	}

	public Long lpush(String key, Object... value){
		byte[] byteKey = getByteKey(key);
		byte[][] byteValues = getByteValues(value);
		return lpush(byteKey,byteValues);
	}
	
	public Long linsert(String key, Object pivot, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		byte[] bytePivot = getByteValue(pivot);
		return linsert(byteKey,bytePivot,byteValue);
	}
	
	public Long lappend(String key, Object pivot, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		byte[] bytePivot = getByteValue(pivot);
		return lappend(byteKey,bytePivot,byteValue);
	}

	public Long lremove(String key, long count, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return lremove(byteKey,count,byteValue);
	}

	public Object lpop(String key) {
		byte[] byteKey = getByteKey(key);
		return handleResult(lpop(byteKey));
	}

	public Object rpop(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(rpop(byteKey));
	}

	public Long rpushx(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return rpushx(byteKey,byteValue);
	}

	public Long lpushx(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return lpushx(byteKey,byteValue);
	}

	@SuppressWarnings("rawtypes")
	public List lrange(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return handleResult(lrange(byteKey,begin,end));
	}

	public Boolean ltrim(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return ltrim(byteKey,begin,end);
	}

	public Long hset(String key, String field, Object value) {
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		byte[] byteValue = getByteValue(value);
		return hset(byteKey, byteField, byteValue);
	}
	
	public Long hsetnx(String key, String field, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		byte[] byteValue = getByteValue(value);
		return hsetnx(byteKey, byteField, byteValue);
	}
	public String hmset(String key, Map<String, ?> hash){
		byte[] byteKey = getByteKey(key);
		Map<byte[],byte[]> byteHash = getByteValueMap(hash);
		return hmset(byteKey, byteHash);
	}
	public Object hget(String key, String field){
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		return handleResult(hget(byteKey, byteField));
	}
	@SuppressWarnings("rawtypes")
	public List hmget(String key, String... fields){
		byte[] byteKey = getByteKey(key);
		byte[][] byteFields = getByteKeys(fields);
		return hmget(byteKey, byteFields);
	}
	public Long hincrBy(String key, String field, long value){
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		return hincrBy(byteKey, byteField, value);
	}
	public Double hincrBy(String key, String field, double value){
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		return hincrBy(byteKey, byteField, value);
	}
	public Boolean hcontainsKey(String key, String field){
		byte[] byteKey = getByteKey(key);
		byte[] byteField = getByteKey(field);
		return hcontainsKey(byteKey, byteField);
	}
	public Long hremove(String key, String... fields){
		byte[] byteKey = getByteKey(key);
		byte[][] byteFields = getByteKeys(fields);
		return hremove(byteKey, byteFields);
	}
	public Long hsize(String key){
		byte[] byteKey = getByteKey(key);
		return hsize(byteKey);
	}
	public Set<String> hkeySet(String key){
		byte[] byteKey = getByteKey(key);
		return handleResultString(hkeySet(byteKey));
	}
	@SuppressWarnings("rawtypes")
	public Collection hValues(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(hValues(byteKey));
	}
	public Map<String, Object> hgetAll(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(hgetAll(byteKey),String.class,Object.class);
	}

	public Long sadd(String key, Object... value){
		byte[] byteKey = getByteKey(key);
		byte[][] byteValues = getByteValues(value);
		return sadd(byteKey,byteValues);
	}
	
	public Long sremove(String key, Object... value){
		byte[] byteKey = getByteKey(key);
		byte[][] byteValues = getByteValues(value);
		return sremove(byteKey,byteValues);
	}
	public Object spop(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(spop(byteKey));
	}

	public Long ssize(String key){
		byte[] byteKey = getByteKey(key);
		return ssize(byteKey);
	}
	
	public Boolean scontains(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return scontains(byteKey, byteValue);
	}
	
	@SuppressWarnings("rawtypes")
	public Set smembers(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(smembers(byteKey));
	}
	
	public Object srandmember(String key){
		byte[] byteKey = getByteKey(key);
		return handleResult(srandmember(byteKey));
	}
	
	public Boolean zadd(String key, double score, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return zadd(byteKey, score, byteValue);
	}
	
	public Long zrem(String key, Object... value) {
		byte[] byteKey = getByteKey(key);
		byte[][] byteValues = getByteValues(value);
		return zrem(byteKey, byteValues);
	}
	
	public Double zincrBy(String key, double increment, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return zincrBy(byteKey, increment, byteValue);
	}
	
	public Long zrank(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return zrank(byteKey, byteValue);
	}
	public Long zrevRank(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return zrevRank(byteKey, byteValue);
	}
	@SuppressWarnings("rawtypes")
	public Set zrange(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrange(byteKey, begin, end));
	}
	
	public Map<Object, Double> zrangeWithScore(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrangeWithScore(byteKey, begin, end),Object.class);
	}
	@SuppressWarnings("rawtypes")
	public Set zrevRange(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRange(byteKey, begin, end));
	}
	public Map<Object, Double> zrevRangeWithScore(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRangeWithScore(byteKey, begin, end),Object.class);
	}
	@SuppressWarnings("rawtypes")
	public Set zrangeByScore(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrangeByScore(byteKey, min, max));
	}
	public Map<Object, Double> zrangeByScoreWithScore(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrangeByScoreWithScore(byteKey, min, max),Object.class);
	}
	@SuppressWarnings("rawtypes")
	public Set zrangeByScore(String key, double min, double max, int offset, int count){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrangeByScore(byteKey, min, max,offset,count));
	}
	public Map<Object,Double> zrangeByScoreWithScore(String key, double min, double max, int offset, int count){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrangeByScoreWithScore(byteKey, min, max,offset,count),Object.class);
	}
	@SuppressWarnings("rawtypes")
	public Set zrevRangeByScore(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRangeByScore(byteKey, min, max));
	}
	public Map<Object,Double> zrevRangeByScoreWithScore(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRangeByScoreWithScore(byteKey, min, max),Object.class);
	}
	@SuppressWarnings("rawtypes")
	public Set zrevRangeByScore(String key, double min, double max, int offset, int count){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRangeByScore(byteKey, min, max,offset,count));
	}
	public Map<Object,Double> zrevRangeByScoreWithScore(String key, double min, double max, int offset, int count){
		byte[] byteKey = getByteKey(key);
		return handleResult(zrevRangeByScoreWithScore(byteKey, min, max,offset,count),Object.class);
	}
	public Long zcount(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return zcount(byteKey, min, max);
	}
	public Long zcard(String key){
		byte[] byteKey = getByteKey(key);
		return zcard(byteKey);
	}
	public Double zscore(String key, Object value){
		byte[] byteKey = getByteKey(key);
		byte[] byteValue = getByteValue(value);
		return zscore(byteKey, byteValue);
	}
	public Long zremRange(String key, long begin, long end){
		byte[] byteKey = getByteKey(key);
		return zremRange(byteKey, begin, end);
	}
	public Long zremRangeByScore(String key, double min, double max){
		byte[] byteKey = getByteKey(key);
		return zremRangeByScore(byteKey, min, max);
	}

	public Boolean smove(String srcKey, String destKey, Object value) {
		byte[] srckeyByte = getByteKey(srcKey);
		byte[] destkeyByte = getByteKey(destKey);
		byte[] valueByte = getByteValue(value);
		return smove(srckeyByte, destkeyByte, valueByte);
	}

	@SuppressWarnings("rawtypes")
	public Set sinter(String... keys){
		byte[][] byteKeys = getByteKeys(keys);
		return handleResult(sinter(byteKeys));
	}
	
	public Long sinterStore(String destKey, String... keys){
		byte[][] byteSrcKeys = getByteKeys(keys);
		byte[] byteDestKey = getByteKey(destKey);
		return sinterStore(byteDestKey, byteSrcKeys);
	}
	
	@SuppressWarnings("rawtypes")
	public Set sunion(String... keys){
		byte[][] byteKeys = getByteKeys(keys);
		return handleResult(sunion(byteKeys));
	}
	
	public Long sunionstore(String destKey,String... keys){
		byte[][] byteSrcKeys = getByteKeys(keys);
		byte[] byteDestKey = getByteKey(destKey);
		return sunionstore(byteDestKey, byteSrcKeys);
	}
	
	@SuppressWarnings("rawtypes")
	public Set sdiff(String... keys){
		byte[][] byteKeys = getByteKeys(keys);
		return handleResult(sdiff(byteKeys));
	}
	
	public Long sdiffstore(String destKey, String... keys){
		byte[][] byteSrcKeys = getByteKeys(keys);
		byte[] byteDestKey = getByteKey(destKey);
		return sdiffstore(byteDestKey, byteSrcKeys);
	}

	@SuppressWarnings("rawtypes")
	public List mget(String... keys) {
		byte[][] byteskeys = getByteKeys(keys);
		return handleResult(mget(byteskeys));
	}

	public Long zunionStore(String destKey, double[] weights,	String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zunionStore(byteKey, weights, byteSets);
	}
	
	public Long zunionStoreByMax(String destKey, double[] weights, String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zunionStoreByMax(byteKey, weights, byteSets);
	}
	
	public Long zunionStoreByMin(String destKey, double[] weights, String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zunionStoreByMin(byteKey, weights, byteSets);
	}
	
	public Long zinterStore(String destKey, String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zinterStore(byteKey, byteSets);
	}
	
	public Long zinterStore(String destKey, double[] weights,String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zinterStore(byteKey, weights, byteSets);
	}
	
	public Long zinterStoreByMax(String destKey, double[] weights,String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zinterStoreByMax(byteKey, weights, byteSets);
	}
	
	public Long zinterStoreByMin(String destKey, double[] weights,String... sets){
		byte[] byteKey = getByteKey(destKey);
		byte[][] byteSets = getByteKeys(sets);
		return zinterStoreByMin(byteKey, weights, byteSets);
	}

	public Set<String> keys(String pattern) {
		byte[] bytePattern = RedisUtils.getByteKey(pattern);
		return RedisUtils.handleResultString(keys(bytePattern));
	}

	public Boolean rename(String oldName, String newName) {
		byte[] byteKey = getByteKey(oldName);
		byte[] byteNewKey = getByteKey(oldName);
		return rename(byteKey,byteNewKey);
	}

	public Boolean renameNX(String oldName, String newName){
		byte[] byteKey = getByteKey(oldName);
		byte[] byteNewKey = getByteKey(oldName);
		return renameNX(byteKey,byteNewKey);
	};

	public Long sort(String key, String storeKey) {
		byte[] byteKey = getByteKey(key);
		byte[] byteSortKey = getByteKey(key);
		return sort(byteKey,byteSortKey);
	}

	public Object rpoplpush(String srcKey, String dstKey){
		byte[] byteSrc = getByteKey(srcKey);
		byte[] byteDst = getByteKey(dstKey);
		return handleResult(rpoplpush(byteSrc,byteDst));
	}

	public Object brpoplpush(int timeout, String srcKey, String dstKey){
		byte[] byteSrc = getByteKey(srcKey);
		byte[] byteDst = getByteKey(dstKey);
		return handleResult(brpoplpush(timeout,byteSrc,byteDst));
	}
}
