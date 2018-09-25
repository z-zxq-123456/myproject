package com.dcits.galaxy.cache.binary;

import java.util.Set;


public interface SetCommands {

	Long sadd(byte[] key, byte[]... value);

	Long sremove(byte[] key, byte[]... value);

	byte[] spop(byte[] key);

	Long ssize(byte[] key);

	Boolean scontains(byte[] key, byte[] value);

	Set<byte[]> smembers(byte[] key);

	byte[] srandmember(byte[] key);
	
	Boolean smove(byte[] srcKey, byte[] destKey, byte[] value);
	
	Set<byte[]> sinter(byte[]... keys);
	
	Long sinterStore(byte[] destKey, byte[]... keys);
	
	Set<byte[]> sunion(byte[]... keys);
	
	Long sunionstore(byte[] destKey, byte[]... keys);
	
	Set<byte[]> sdiff(byte[]... keys);
	
	Long sdiffstore(byte[] destKey, byte[]... keys);
}
