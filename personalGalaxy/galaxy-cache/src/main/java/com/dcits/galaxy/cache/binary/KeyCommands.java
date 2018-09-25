package com.dcits.galaxy.cache.binary;

import java.util.List;
import java.util.Set;

public interface KeyCommands {

    Boolean exists(byte[] key);

    Long del(byte[]... keys);

    String type(byte[] key);

    Set<byte[]> keys(byte[] pattern);

    Boolean rename(byte[] oldName, byte[] newName);
    
    Boolean renameNX(byte[] oldName, byte[] newName);

    Boolean expire(byte[] key, int seconds);
    
    Boolean expireAt(byte[] key, long unixTime);
    
    Boolean persist(byte[] key);
    
    Boolean move(byte[] key, int dbIndex);
    
    Long ttl(byte[] key);
    
    List<byte[]> sort(byte[] key);
    
    List<byte[]> sort(byte[] key, boolean desc, boolean alpha);

    Long sort(byte[] key, byte[] storeKey);
    
//    List<byte[]> sort(byte[] key, SortingParams sortingParameters);
//    
//    Long sort(byte[] key, SortingParams sortingParameters, byte[] b);
    
}
