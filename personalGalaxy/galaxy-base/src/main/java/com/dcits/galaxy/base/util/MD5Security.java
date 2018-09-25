/**   
 * <p>Title: MD5Security.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: dcits</p>
 * @author Liang
 * @update 2015年2月12日 下午1:11:55
 * @version V1.0
 */
package com.dcits.galaxy.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description
 * @version V1.0
 * @author Liang
 * @update 2015年2月12日 下午1:11:55
 */

public class MD5Security{

	public static String encrypt(String str) {
		// TODO Auto-generated method stub
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] bInput = str.getBytes();
			MessageDigest mdigest = MessageDigest.getInstance("MD5");
			mdigest.update(bInput);
			// 获得密文
			byte[] md = mdigest.digest();
			int len = md.length;
			char s[] = new char[len * 2];
			int k = 0;
			for (int i = 0; i < len; i++) {
				byte byte0 = md[i];
				s[k++] = hexDigits[byte0 >>> 4 & 0xf];
				s[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(s);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		MD5Security md5 = new MD5Security();
		if(md5.encrypt("111111").equals(md5.encrypt("111111"))){

			System.out.println(md5.encrypt("111111"));
			System.out.println(md5.encrypt("111111"));
			System.out.println("ok");
		}else{
			System.out.println("error");
		}
		System.out.println(new MD5Security().encrypt("111111"));
		System.out.println(new MD5Security().encrypt("19821024"));
	}
}
