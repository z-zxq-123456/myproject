package com.dcits.galaxy.base.util;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

/**
 * 序列工具类
 * @author wang.yq
 */
public class SeqUtils {

	/**
	 * 产生字符串序列(长度:32)
	 * @return String 32位字符串序列
	 */
	public static String getStringSeq() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("\\-", "").toUpperCase();
	}

	/**
	 * 产生数字序列(长度:32)
	 * [日期(8)+纳秒+随机数]
	 * @return BigInteger 32位数字序列
	 */
	public static BigInteger getNumberSeq() {
		StringBuilder sb = new StringBuilder();
		sb.append(DateUtils.getDate());
		sb.append(System.nanoTime());
		int length = 32-sb.length();
		String randNum = getRandomNumber(length);
		sb.append(randNum);
		return new BigInteger(sb.toString());
	}

	/**
	 * 产生指定长度随机字母
	 * @param length
	 * @return String
	 */
	public static String getRandomLetter(int length) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int rang = 26;
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(base.charAt(rand.nextInt(rang)));
		}
		return sb.toString();
	}

	/**
	 * 产生指定长度随机数字
	 * @param length
	 * @return String
	 */
	public static String getRandomNumber(int length) {
		String base = "0123456789";
		int rang = 10;
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(base.charAt(rand.nextInt(rang)));
		}
		return sb.toString();
	}

	/**
	 * 产生指定区间的随机整数
	 * @param min
	 * @param max
	 * @return int
	 */
	public static int getRandomNumber(int min, int max) {
		Random rand = new Random();
		int tmp = Math.abs(rand.nextInt());
		return tmp % (max - min + 1) + min;
	}
	
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			System.out.println(getStringSeq());
			System.out.println(getNumberSeq());
		}
	}
}
