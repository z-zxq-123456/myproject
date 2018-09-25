package com.dcits.galaxy.dal.mybatis.proactor.page.approximate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author yin.weicai
 *
 */
public class ApproximateContext {
	
	private Object lastMinObject = null;

	private Map<String, Integer> offsetMap = new HashMap<String, Integer>();
	
	public void initOffset(Set<String> shardIds){
		for (String shardId : shardIds) {
			putOffset(shardId, 0);
		}
	}
	
	public int getOffset(String shardId){
		int offset = 0;
		if( offsetMap.containsKey(shardId) ){
			offset = offsetMap.get( shardId );
		}
		return offset;
	}
	
	public void putOffset(String shardId, int offset){
		offsetMap.put( shardId, offset);
	}
	
	public void updateOffset(Map<String, Integer> offsetMap){
		for(String shardId: offsetMap.keySet()){
			int offset = offsetMap.get(shardId);
			this.offsetMap.put( shardId, offset);
		}
	}
	
	public void addOffset(String shardId, int offset){
		int offset_old = getOffset(shardId);
		int offset_sum = offset_old + offset;
		putOffset(shardId, offset_sum);
	}
	
	public void addOffset(Set<String> shardIds,int offset){
		for (String shardId : shardIds) {
			addOffset(shardId, offset);
		}
	}
	
//	public void mergeOffset(Map<String, Integer> offsetMap){
//		for(String shardId: offsetMap.keySet()){
//			int offset = offsetMap.get(shardId);
//			addOffset(shardId, offset);
//		}
//	}
	
	public int sumOffsets(){
		int sum = 0;
		for(String shardId: offsetMap.keySet()){
			int offset = offsetMap.get(shardId);
			sum += offset;
		}
		return sum;
	}
	
	public Object getLastMinObject() {
		return lastMinObject;
	}

	public void setLastMinObject(Object lastMinObject) {
		this.lastMinObject = lastMinObject;
	}
	
}
