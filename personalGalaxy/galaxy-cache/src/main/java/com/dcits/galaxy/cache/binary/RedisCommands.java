package com.dcits.galaxy.cache.binary;

import java.io.Closeable;

public interface RedisCommands extends KeyCommands, HashCommands,
		ListCommands, SetCommands, ValueCommands,
		ZSetCommands,Closeable {
	
	public boolean isClose();
	
}
