package com.dcits.galaxy.cache.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

import com.dcits.galaxy.cache.exception.CommandNotSupportException;

public class SCSImpl extends RedisImplJedis {
	public SCSImpl(Jedis jedis) {
		super(jedis);
	}
	
	@Override
	public byte[] rpoplpush(byte[] srcKey, byte[] dstKey) {
		throw new CommandNotSupportException("scs cluster don't support rpoplpush command. ");
//		return super.rpoplpush(srcKey, dstKey);
	}
	
	@Override
	public Boolean smove(byte[] srcKey, byte[] destKey, byte[] value) {
		throw new CommandNotSupportException("scs cluster don't support smove command. ");
//		return super.smove(srcKey, destKey, value);
	}
	
	@Override
	public Long sinterStore(byte[] destKey, byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sinterStore command. ");
//		return super.sinterStore(destKey, keys);
	}
	
	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sinter command. ");
//		return super.sinter(keys);
	}
	
	@Override
	public Long sunionstore(byte[] destKey, byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sunionstore command. ");
//		return super.sunionstore(destKey, keys);
	}
	
	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sunion command. ");
//		return super.sunion(keys);
	}
	
	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sdiff command. ");
//		return super.sdiff(keys);
	}
	
	@Override
	public Long sdiffstore(byte[] destKey, byte[]... keys) {
		throw new CommandNotSupportException("scs cluster don't support sdiffstore command. ");
//		return super.sdiffstore(destKey, keys);
	}
	
	@Override
	public Long zunionStore(byte[] destKey, byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zunionStore command. ");
//		return super.zunionStore(destKey, sets);
	}
	
	@Override
	public Long zunionStore(byte[] destKey, double[] weights, byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zunionStore command. ");
//		return super.zunionStore(destKey, weights, sets);
	}
	
	@Override
	public Long zunionStoreByMax(byte[] destKey, double[] weights,
			byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zunionStore command. ");
//		return super.zunionStoreByMax(destKey, weights, sets);
	}
	
	@Override
	public Long zunionStoreByMin(byte[] destKey, double[] weights,
			byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zunionStore command. ");
//		return super.zunionStoreByMin(destKey, weights, sets);
	}
	
	@Override
	public Long zinterStore(byte[] destKey, byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zinterStore command. ");
//		return super.zinterStore(destKey, sets);
	}
	
	@Override
	public Long zinterStore(byte[] destKey, double[] weights, byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zinterStore command. ");
//		return super.zinterStore(destKey, weights, sets);
	}
	
	@Override
	public Long zinterStoreByMax(byte[] destKey, double[] weights,
			byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zinterStore command. ");
//		return super.zinterStoreByMax(destKey, weights, sets);
	}
	
	@Override
	public Long zinterStoreByMin(byte[] destKey, double[] weights,
			byte[]... sets) {
		throw new CommandNotSupportException("scs cluster don't support zinterStore command. ");
//		return super.zinterStoreByMin(destKey, weights, sets);
	}
	
	@Override
	public Set<byte[]> keys(byte[] pattern) {
		throw new CommandNotSupportException("scs cluster don't support keys command. ");
	}
	
	@Override
	public Boolean move(byte[] key, int dbIndex) {
		throw new CommandNotSupportException("scs cluster don't support move command. ");
	}
	
	@Override
	public Boolean rename(byte[] oldName, byte[] newName) {
		throw new CommandNotSupportException("scs cluster don't support rename command. ");
	}
	
	@Override
	public Boolean renameNX(byte[] oldName, byte[] newName) {
		throw new CommandNotSupportException("scs cluster don't support renameNX command. ");
	}
	
	@Override
	public List<byte[]> sort(byte[] key) {
		throw new CommandNotSupportException("scs cluster don't support sort command. ");
	}
	
	@Override
	public List<byte[]> sort(byte[] key, boolean desc, boolean alpha) {
		throw new CommandNotSupportException("scs cluster don't support sort command. ");
	}
	
	@Override
	public Long sort(byte[] key, byte[] storeKey) {
		throw new CommandNotSupportException("scs cluster don't support sort command. ");
	}
	
	@Override
	public List<byte[]> blpop(int timeout, byte[] key) {
		throw new CommandNotSupportException("scs cluster don't support blpop command. ");
	}
	
	@Override
	public List<byte[]> brpop(int timeout, byte[] key) {
		throw new CommandNotSupportException("scs cluster don't support brpop command. ");
	}
	
	@Override
	public byte[] brpoplpush(int timeout, byte[] srcKey, byte[] dstKey) {
		throw new CommandNotSupportException("scs cluster don't support brpoplpush command. ");
	}
}
