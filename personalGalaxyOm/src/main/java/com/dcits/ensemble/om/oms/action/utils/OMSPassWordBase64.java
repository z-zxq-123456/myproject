package com.dcits.ensemble.om.oms.action.utils;


import com.dcits.galaxy.base.exception.GalaxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Base64密码加密双向算法
 * @author LuoLang
 * @date 2015-09-15
 */ 
 public class OMSPassWordBase64 {

	 /*编码参数表，原码三个字符的8位xxxxxxxx经过加密变成4个高两位为0的00xxxxxx，
	  * 而00xxxxxx即对应64种结果，每个结果对应一个字符如下表，这也是加密算法名字的由来。*/
	 private static final Logger log = LoggerFactory.getLogger(OMSPassWordBase64.class);
     private static char[] base64EncodeChars = new char[] {     
         'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',     
         'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',     
         'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',     
         'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',     
         'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',     
         'o', 'p', 'q', 'r', 's', 't', 'u', 'v',     
         'w', 'x', 'y', 'z', '0', '1', '2', '3',     
         '4', '5', '6', '7', '8', '9', '+', '/' };     
     /*
      * 这是解码参数表，是编码的逆过程，具体请认真体会加密过程！
      */
     private static byte[] base64DecodeChars = new byte[] {     
     -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,     
     -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,     
     -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,     
     52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,     
     -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,     
     15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,     
     -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,     
     41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };   


     /*编码算法(encode) 将3个xxxxxxxx变为4个00xxxxxx。编码规程：
      * ①.把3个字符变成4个字符、
      * ②每76个字符加一个换行符。
      * ③.最后的结束符也要处理。
      */  
     public static String encode(byte[] data) {     
         StringBuffer stb = new StringBuffer();     
         int len = data.length;     
         int i = 0;     
         int b1, b2, b3;     
         while (i < len) {     
             b1 = data[i++] & 0xff;     
             if (i == len)     
             {     
                 stb.append(base64EncodeChars[b1 >>> 2]);     
                 stb.append(base64EncodeChars[(b1 & 0x3) << 4]);     
                 stb.append("==");     
                 break;     
             }     
             b2 = data[i++] & 0xff;     
             if (i == len)     
             {     
                 stb.append(base64EncodeChars[b1 >>> 2]);     
                 stb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);     
                 stb.append(base64EncodeChars[(b2 & 0x0f) << 2]);     
                 stb.append("=");     
                 break;     
             }     
             b3 = data[i++] & 0xff;     
             stb.append(base64EncodeChars[b1 >>> 2]);     
             stb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);     
             stb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);     
             stb.append(base64EncodeChars[b3 & 0x3f]);     
         }     
         return stb.toString();     
     }  

     /*
      * 解码算法(decode)
      * 规则：源第一字节右移两位，去掉低2位，高2位补零
      */
     public static byte[] decode(String str) throws UnsupportedEncodingException {     
         StringBuffer stb = new StringBuffer();     
         byte[] data = str.getBytes("US-ASCII");     
         int len = data.length;     
         int i = 0;     
         int b1, b2, b3, b4;     
         while (i < len) {     
             /* b1 */    
             do {     
                 b1 = base64DecodeChars[data[i++]];     
             } while (i < len && b1 == -1);     
             if (b1 == -1) break;     
             /* b2 */    
             do {     
                 b2 = base64DecodeChars[data[i++]]; 
             } while (i < len && b2 == -1);     
             if (b2 == -1) break;     
             stb.append((char)((b1 << 2) | ((b2 & 0x30) >>> 4)));     
             /* b3 */    
             do {     
                 b3 = data[i++];     
                 if (b3 == 61) return stb.toString().getBytes("iso8859-1");     
                 b3 = base64DecodeChars[b3];     
             } while (i < len && b3 == -1);     
             if (b3 == -1) break;     
             stb.append((char)(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));     
             /* b4 */    
             do {     
                 b4 = data[i++];     
                 if (b4 == 61) return stb.toString().getBytes("iso8859-1");     
                 b4 = base64DecodeChars[b4];     
             } while (i < len && b4 == -1);     
             if (b4 == -1) break;     
             stb.append((char)(((b3 & 0x03) << 6) | b4));     
         }     
            return stb.toString().getBytes("iso8859-1");     
     }     
    /**
     * 解码成字符串
  	 * @param   String  str   待解码字符串   	
  	 * @return  String       解码后的字符串
  	 */	      
     public static String decodeToString(String str){
    	try {
			return new String(OMSPassWordBase64.decode(str),"iso8859-1");
		} catch (UnsupportedEncodingException e) {			
			log.error(""+e.getStackTrace());
			throw new GalaxyException(e.getMessage());
		}
     }
 }   