package com.dcits.galaxy.cache.api;

public interface Route {
	RedisSource route(byte[] key);
}
