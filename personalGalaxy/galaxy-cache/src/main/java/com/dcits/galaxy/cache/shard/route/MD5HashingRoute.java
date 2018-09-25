package com.dcits.galaxy.cache.shard.route;

import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import redis.clients.util.Hashing;

import com.dcits.galaxy.cache.api.RedisSource;
import com.dcits.galaxy.cache.api.Route;

public class MD5HashingRoute implements Route {

	private TreeMap<Long, RedisSource> nodes = new TreeMap<Long, RedisSource>();

	private Hashing hashing;

	public MD5HashingRoute(Map<RedisSource, Integer> redisSources) {
		this(redisSources, Hashing.MURMUR_HASH);
	}

	public MD5HashingRoute(Map<RedisSource, Integer> redisSources, Hashing hashing) {

		this.hashing = hashing;

		for (Entry<RedisSource, Integer> entry : redisSources.entrySet()) {
			RedisSource redisSource = entry.getKey();
			int weight = entry.getValue();
			for (int n = 0; n < 160 * weight; n++) {
				nodes.put(this.getNodeKey(redisSource, weight, n), redisSource);
			}
		}
	}

	@Override
	public RedisSource route(byte[] key) {
		SortedMap<Long, RedisSource> tail = nodes.tailMap(hashing.hash(key));
		if (tail.isEmpty()) {
			return nodes.get(nodes.firstKey());
		}
		return tail.get(tail.firstKey());
	}

	private long getNodeKey(RedisSource redisSource, int weight, int n) {
		StringBuilder sb = new StringBuilder();
		sb.append(redisSource.getHost());
		sb.append(':');
		sb.append(redisSource.getPort());
		sb.append('*');
		sb.append(weight);
		sb.append(n);
		return this.hashing.hash(sb.toString());
	}
}
