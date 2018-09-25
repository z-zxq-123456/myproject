package com.dcits.galaxy.cache.binary;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HashCommands {
	
	  Long hset(byte[] key, byte[] field, byte[] value);

	  Long hsetnx(byte[] key, byte[] field, byte[] value);

	  String hmset(byte[] key, Map<byte[], byte[]> hash);

	  byte[] hget(byte[] key, byte[] field);

	  List<byte[]> hmget(byte[] key, byte[]... fields);

	  Long hincrBy(byte[] key, byte[] field, long value);

	  Double hincrBy(byte[] key, byte[] field, double value);

	  Boolean hcontainsKey(byte[] key, byte[] field);

	  Long hremove(byte[] key, byte[]... field);

	  Long hsize(byte[] key);

	  Set<byte[]> hkeySet(byte[] key);

	  Collection<byte[]> hValues(byte[] key);

	  Map<byte[], byte[]> hgetAll(byte[] key);
}
