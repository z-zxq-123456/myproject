package com.dcits.galaxy.cache.binary;

import java.util.Map;
import java.util.Set;

public interface ZSetCommands {

	Boolean zadd(byte[] key, double score, byte[] value);

	Long zadd(byte[] key, Map<byte[],Double> valueMap);

	Long zrem(byte[] key, byte[]... value);

	Double zincrBy(byte[] key, double increment, byte[] value);

	Long zrank(byte[] key, byte[] value);

	Long zrevRank(byte[] key, byte[] value);

	Set<byte[]> zrange(byte[] key, long begin, long end);

	Map<byte[], Double> zrangeWithScore(byte[] key, long begin, long end);

	Set<byte[]> zrevRange(byte[] key, long begin, long end);

	Map<byte[], Double> zrevRangeWithScore(byte[] key, long begin, long end);

	Set<byte[]> zrangeByScore(byte[] key, double min, double max);

	Map<byte[], Double> zrangeByScoreWithScore(byte[] key, double min, double max);

	Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset,
			int count);

	Map<byte[],Double> zrangeByScoreWithScore(byte[] key, double min, double max,
			int offset, int count);

	Set<byte[]> zrevRangeByScore(byte[] key, double min, double max);

	Map<byte[],Double> zrevRangeByScoreWithScore(byte[] key, double min, double max);

	Set<byte[]> zrevRangeByScore(byte[] key, double min, double max,
			int offset, int count);

	Map<byte[],Double> zrevRangeByScoreWithScore(byte[] key, double min, double max,
			int offset, int count);

	Long zcount(byte[] key, double min, double max);

	Long zcard(byte[] key);

	Double zscore(byte[] key, byte[] value);

	Long zremRange(byte[] key, long begin, long end);

	Long zremRangeByScore(byte[] key, double min, double max);
	
	Long zunionStore(byte[] destKey, byte[]... sets);

	Long zunionStore(byte[] destKey, double[] weights,	byte[]... sets);
	
	Long zunionStoreByMax(byte[] destKey, double[] weights, byte[]... sets);
	
	Long zunionStoreByMin(byte[] destKey, double[] weights, byte[]... sets);

	Long zinterStore(byte[] destKey, byte[]... sets);

	Long zinterStore(byte[] destKey, double[] weights,byte[]... sets);
	
	Long zinterStoreByMax(byte[] destKey, double[] weights,byte[]... sets);
	
	Long zinterStoreByMin(byte[] destKey, double[] weights,byte[]... sets);
}
