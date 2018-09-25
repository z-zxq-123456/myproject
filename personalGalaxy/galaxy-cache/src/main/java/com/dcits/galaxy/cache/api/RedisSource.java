package com.dcits.galaxy.cache.api;

import java.io.IOException;

import com.dcits.galaxy.cache.binary.RedisCommands;

public interface RedisSource {
	
	public RedisCommands getRedis();
	public String getHost();
	public int getPort();
	public int getTimeout();
	public void close() throws IOException;

}
