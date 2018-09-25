package com.dcits.galaxy.cache.binary;

import java.util.List;
import java.util.Map;


public interface ValueCommands {

	byte[] get(byte[] key);

	byte[] getSet(byte[] key, byte[] value);

	List<byte[]> mget(byte[]... keys);

	Boolean set(byte[] key, byte[] value);

	Boolean setnx(byte[] key, byte[] value);

	Boolean setex(byte[] key, int seconds, byte[] value);

	Boolean mset(Map<byte[], byte[]> tuple);

	Long msetnx(Map<byte[], byte[]> tuple);

	Long incr(byte[] key);

	Long incrBy(byte[] key, long value);

	Double incrBy(byte[] key, double value);

	Long decr(byte[] key);

	Long decrBy(byte[] key, long value);

	Long append(byte[] key, byte[] value);

	byte[] getRange(byte[] key, long begin, long end);

	Long setRange(byte[] key, long offset,byte[] value);

	Boolean getBit(byte[] key, long offset);

	Boolean setBit(byte[] key, long offset, boolean value);

	Long strLen(byte[] key);

}
