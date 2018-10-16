package com.dcits.ensemble.om.oms.action.utils;

import java.security.MessageDigest;

/**
 * PassWordMd5* 
 * @author WangBinaf
 * @date 2015-08-19
 * @updatetime 2015-09-15 16:18
 */
@SuppressWarnings("unused")
//MD5加密
public class PassWordMd5 {
   
	 public final static String MD5(String s) {                           
         char hexDigits[] = { '0', '1', '2', '3', '4',                    
                              '5', '6', '7', '8', '9',                    
                              'A', 'B', 'C', 'D', 'E', 'F'};              
         try {                                                            
             byte[] btInput = s.getBytes();                               
             MessageDigest mdInst = MessageDigest.getInstance("MD5");     
             mdInst.update(btInput);                                      
             byte[] md = mdInst.digest();                                 
             int j = md.length;                                           
             char str[] = new char[j * 2];                                
             int k = 0;                                                   
             for (int i = 0; i < j; i++) {                                
                 byte byte0 = md[i];                                      
                 str[k++] = hexDigits[byte0 >>> 4 & 0xf];                 
                 str[k++] = hexDigits[byte0 & 0xf];                       
             }                                                            
             return new String(str);                                      
         }                                                                
         catch (Exception e) {                                            
             e.printStackTrace();                                         
             return null;                                                 
         }                                                                
     }                    
}
