package com.dcits.galaxy.instrument;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TransformerContainer {
	
//	private ConcurrentMap<String, ClassFileTransformer> transformers = new ConcurrentHashMap<>();
	private ConcurrentMap<String, String> transformers = new ConcurrentHashMap<>();
	
	public boolean contains(String key){
		return transformers.containsValue( key );
	} 
	
	public String get(String className){
		return transformers.get( className);
	}
	
	public void put(String key , String transformer){
		transformers.put(key, transformer);
	}
	
	public String remove(String key){
		return transformers.remove(key);
	}
}
