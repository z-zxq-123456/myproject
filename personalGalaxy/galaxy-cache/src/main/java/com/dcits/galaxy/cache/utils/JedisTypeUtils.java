package com.dcits.galaxy.cache.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.Tuple;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.ZParams.Aggregate;

public class JedisTypeUtils {
	
	private static final String OK = "OK";
	
	public static Boolean toBoolean(Long num) {
		if (num == null || num.equals(0))
			return Boolean.FALSE;
		else
			return Boolean.TRUE;
	}
	
	public static Boolean toBoolean(String str) {
		if(str == null) 
			return Boolean.FALSE;
		if(str.toUpperCase().equals(OK))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public static Map<byte[],Double> tuplesToMap(Set<Tuple> tuples){
		Map<byte[],Double> map = new HashMap<>();
		for(Tuple tuple : tuples) {
			map.put(tuple.getBinaryElement(), tuple.getScore());
		}
		return map;
	}
	
	public static Set<Tuple>  mapToTuples(Map<byte[],Double> map){
		Set<Tuple> tuples = new HashSet<>();
		for(Entry<byte[],Double> entry : map.entrySet()) {
			tuples.add(new Tuple(entry.getKey(), entry.getValue()));
		}
		return tuples;
	}
	
	public static ZParams newZparams(Aggregate aggregate , double... weights){
		ZParams zparams = new ZParams();
		zparams.weightsByDouble(weights);
		zparams.aggregate(aggregate);
		return zparams;
	}
}
