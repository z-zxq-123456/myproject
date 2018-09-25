package com.dcits.galaxy.cache.binary;

import java.util.List;

public interface ListCommands {

	Long lsize(byte[] key);

	byte[] lget(byte[] key, long index);

	Boolean lset(byte[] key, long index, byte[] value);

	Long rpush(byte[] key, byte[]... value);

	Long lpush(byte[] key, byte[]... value);
	
	Long linsert(byte[] key, byte[] pivot, byte[] value);
	
	Long lappend(byte[] key, byte[] pivot, byte[] value);

	Long lremove(byte[] key, long count, byte[] value);

	byte[] lpop(byte[] key);

	byte[] rpop(byte[] key);

	Long rpushx(byte[] key, byte[] value);

	Long lpushx(byte[] key, byte[] value);

	List<byte[]> blpop(int timeout, byte[] key);

	List<byte[]> brpop(int timeout, byte[] key);

	List<byte[]> lrange(byte[] key, long begin, long end);

	Boolean ltrim(byte[] key, long begin, long end);
	
	byte[] rpoplpush(byte[] srcKey, byte[] dstKey);

	byte[] brpoplpush(int timeout, byte[] srcKey, byte[] dstKey);
}
