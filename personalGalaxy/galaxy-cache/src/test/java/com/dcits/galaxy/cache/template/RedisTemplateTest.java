package com.dcits.galaxy.cache.template;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dcits.galaxy.core.serializer.SerializationUtils;

public class RedisTemplateTest extends TestCase {

    protected ApplicationContext context;
    protected RedisTemplate redisTemplate;
	
	@Override
	protected void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext(
	            "classpath:META-INF/spring/*.xml");
		redisTemplate = context.getBean(RedisTemplate.class);
	}

	public void testGetString() {
		System.out.println(redisTemplate.get("hello_incr"));
	}

	public void testGetSetStringObject() {
		System.out.println(redisTemplate.getSet("hello", "world"));
	}

	public void testSetStringObject() {
		redisTemplate.set("abc", "123");
	}

	public void testDelString() {
		fail("Not yet implemented");
	}

	public void testSetnxStringObject() {
		fail("Not yet implemented");
	}

	public void testSetexStringIntObject() {
		fail("Not yet implemented");
	}

	public void testIncrString() {
		System.out.println(redisTemplate.incr("hello_incr"));
	}

	public void testIncrByStringLong() {
		System.out.println(redisTemplate.incrBy("hello_incr", 100));
	}

	public void testIncrByStringDouble() {
		fail("Not yet implemented");
	}

	public void testDecrString() {
		fail("Not yet implemented");
	}

	public void testDecrByStringLong() {
		fail("Not yet implemented");
	}

	public void testAppendStringByteArray() {
		fail("Not yet implemented");
	}

	public void testGetRangeStringLongLong() {
		fail("Not yet implemented");
	}

	public void testSetRangeStringLongByteArray() {
		fail("Not yet implemented");
	}

	public void testGetBitStringLong() {
		fail("Not yet implemented");
	}

	public void testSetBitStringLongBoolean() {
		fail("Not yet implemented");
	}

	public void testStrLenString() {
		fail("Not yet implemented");
	}

	public void testExistsString() {
		fail("Not yet implemented");
	}

	public void testTypeString() {
		fail("Not yet implemented");
	}

	public void testExpireStringInt() {
		fail("Not yet implemented");
	}

	public void testExpireAtStringLong() {
		fail("Not yet implemented");
	}

	public void testPersistString() {
		fail("Not yet implemented");
	}

	public void testMoveStringInt() {
		fail("Not yet implemented");
	}

	public void testTtlString() {
		fail("Not yet implemented");
	}

	public void testSortString() {
		fail("Not yet implemented");
	}

	public void testSortStringBooleanBoolean() {
		fail("Not yet implemented");
	}

	public void testLsizeString() {
		System.out.println(redisTemplate.lsize("hello_list"));
	}

	public void testLgetStringLong() {
		System.out.println(redisTemplate.lget("hello_list", 4));
	}

	public void testLsetStringLongObject() {
		System.out.println(redisTemplate.lset("hello_list", 4, "0"));
	}

	public void testRpushStringObjectArray() {
		fail("Not yet implemented");
	}

	public void testLpushStringObjectArray() {
		redisTemplate.lpush("hello_list", "4","5","3");
	}

	public void testLinsertStringObjectObject() {
		fail("Not yet implemented");
	}

	public void testLappendStringObjectObject() {
		fail("Not yet implemented");
	}

	public void testLremoveStringLongObject() {
		fail("Not yet implemented");
	}

	public void testLpopString() {
		fail("Not yet implemented");
	}

	public void testRpopString() {
		fail("Not yet implemented");
	}

	public void testRpushxStringObject() {
		fail("Not yet implemented");
	}

	public void testLpushxStringObject() {
		fail("Not yet implemented");
	}

	public void testLrangeStringLongLong() {
		fail("Not yet implemented");
	}

	public void testLtrimStringLongLong() {
		fail("Not yet implemented");
	}

	public void testHsetStringStringObject() {
		fail("Not yet implemented");
	}

	public void testHsetnxStringStringObject() {
		fail("Not yet implemented");
	}

	public void testHmsetStringMapOfStringQ() {
		fail("Not yet implemented");
	}

	public void testHgetStringString() {
		fail("Not yet implemented");
	}

	public void testHmgetStringStringArray() {
		fail("Not yet implemented");
	}

	public void testHincrByStringStringLong() {
		fail("Not yet implemented");
	}

	public void testHincrByStringStringDouble() {
		fail("Not yet implemented");
	}

	public void testHcontainsKeyStringString() {
		fail("Not yet implemented");
	}

	public void testHremoveStringStringArray() {
		fail("Not yet implemented");
	}

	public void testHsizeString() {
		fail("Not yet implemented");
	}

	public void testHkeySetString() {
		fail("Not yet implemented");
	}

	public void testHValuesString() {
		fail("Not yet implemented");
	}

	public void testHgetAllString() {
		fail("Not yet implemented");
	}

	public void testSaddStringObjectArray() {
		fail("Not yet implemented");
	}

	public void testSremoveStringObjectArray() {
		fail("Not yet implemented");
	}

	public void testSpopString() {
		fail("Not yet implemented");
	}

	public void testSsizeString() {
		fail("Not yet implemented");
	}

	public void testScontainsStringObject() {
		fail("Not yet implemented");
	}

	public void testSmembersString() {
		fail("Not yet implemented");
	}

	public void testSrandmemberString() {
		fail("Not yet implemented");
	}

	public void testZaddStringDoubleObject() {
		fail("Not yet implemented");
	}

	public void testZremStringObjectArray() {
		fail("Not yet implemented");
	}

	public void testZincrByStringDoubleObject() {
		fail("Not yet implemented");
	}

	public void testZrankStringObject() {
		fail("Not yet implemented");
	}

	public void testZrevRankStringObject() {
		fail("Not yet implemented");
	}

	public void testZrangeStringLongLong() {
		fail("Not yet implemented");
	}

	public void testZrangeWithScoreStringLongLong() {
		fail("Not yet implemented");
	}

	public void testZrevRangeStringLongLong() {
		fail("Not yet implemented");
	}

	public void testZrevRangeWithScoreStringLongLong() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreWithScoreStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreStringDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreWithScoreStringDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreWithScoreStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreStringDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreWithScoreStringDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZcountStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZcardString() {
		fail("Not yet implemented");
	}

	public void testZscoreStringObject() {
		fail("Not yet implemented");
	}

	public void testZremRangeStringLongLong() {
		fail("Not yet implemented");
	}

	public void testZremRangeByScoreStringDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testClose() {
		fail("Not yet implemented");
	}

	public void testCloseRedis() {
		fail("Not yet implemented");
	}

	public void testGetByteArray() {
		byte[] bytes = redisTemplate.get(SerializationUtils
				.serialize("hello_incr"));
		try {
			System.out.println(SerializationUtils.deserialize(bytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testSetByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testDelByteArray() {
		fail("Not yet implemented");
	}

	public void testHsetByteArrayByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testHmsetByteArrayMapOfbytebyte() {
		fail("Not yet implemented");
	}

	public void testHgetByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testHmgetByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testHgetAllByteArray() {
		fail("Not yet implemented");
	}

	public void testExistsByteArray() {
		fail("Not yet implemented");
	}

	public void testTypeByteArray() {
		fail("Not yet implemented");
	}

	public void testRandomKey() {
		fail("Not yet implemented");
	}

	public void testExpireByteArrayInt() {
		fail("Not yet implemented");
	}

	public void testExpireAtByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testPersistByteArray() {
		fail("Not yet implemented");
	}

	public void testMoveByteArrayInt() {
		fail("Not yet implemented");
	}

	public void testTtlByteArray() {
		fail("Not yet implemented");
	}

	public void testSortByteArray() {
		fail("Not yet implemented");
	}

	public void testSortByteArrayBooleanBoolean() {
		fail("Not yet implemented");
	}

	public void testHsetnxByteArrayByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testHincrByByteArrayByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testHincrByByteArrayByteArrayDouble() {
		fail("Not yet implemented");
	}

	public void testHcontainsKeyByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testHremoveByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testHsizeByteArray() {
		fail("Not yet implemented");
	}

	public void testHkeySetByteArray() {
		fail("Not yet implemented");
	}

	public void testHValuesByteArray() {
		fail("Not yet implemented");
	}

	public void testLsizeByteArray() {
		fail("Not yet implemented");
	}

	public void testLgetByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testLsetByteArrayLongByteArray() {
		fail("Not yet implemented");
	}

	public void testRpushByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testLpushByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testLinsertByteArrayByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testLappendByteArrayByteArrayByteArray() {
		redisTemplate.lpush("hello_list", "1","2","3");
	}

	public void testLremoveByteArrayLongByteArray() {
		fail("Not yet implemented");
	}

	public void testLpopByteArray() {
		fail("Not yet implemented");
	}

	public void testRpopByteArray() {
		fail("Not yet implemented");
	}

	public void testRpushxByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testLpushxByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testLrangeByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testLtrimByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testSaddByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testSremoveByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testSpopByteArray() {
		fail("Not yet implemented");
	}

	public void testSsizeByteArray() {
		fail("Not yet implemented");
	}

	public void testScontainsByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testSmembersByteArray() {
		fail("Not yet implemented");
	}

	public void testSrandmemberByteArray() {
		fail("Not yet implemented");
	}

	public void testGetSetByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testSetnxByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testSetexByteArrayIntByteArray() {
		fail("Not yet implemented");
	}

	public void testIncrByteArray() {
		fail("Not yet implemented");
	}

	public void testIncrByByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testIncrByByteArrayDouble() {
		fail("Not yet implemented");
	}

	public void testDecrByteArray() {
		fail("Not yet implemented");
	}

	public void testDecrByByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testAppendByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testGetRangeByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testSetRangeByteArrayLongByteArray() {
		fail("Not yet implemented");
	}

	public void testGetBitByteArrayLong() {
		fail("Not yet implemented");
	}

	public void testSetBitByteArrayLongBoolean() {
		fail("Not yet implemented");
	}

	public void testStrLenByteArray() {
		fail("Not yet implemented");
	}

	public void testZaddByteArrayDoubleByteArray() {
		fail("Not yet implemented");
	}

	public void testZaddByteArrayMapOfbyteDouble() {
		fail("Not yet implemented");
	}

	public void testZremByteArrayByteArrayArray() {
		fail("Not yet implemented");
	}

	public void testZincrByByteArrayDoubleByteArray() {
		fail("Not yet implemented");
	}

	public void testZrankByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testZrevRankByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testZrangeByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testZrangeWithScoreByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testZrevRangeWithScoreByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreWithScoreByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreByteArrayDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrangeByScoreWithScoreByteArrayDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreWithScoreByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreByteArrayDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZrevRangeByScoreWithScoreByteArrayDoubleDoubleIntInt() {
		fail("Not yet implemented");
	}

	public void testZcountByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

	public void testZcardByteArray() {
		fail("Not yet implemented");
	}

	public void testZscoreByteArrayByteArray() {
		fail("Not yet implemented");
	}

	public void testZremRangeByteArrayLongLong() {
		fail("Not yet implemented");
	}

	public void testZremRangeByScoreByteArrayDoubleDouble() {
		fail("Not yet implemented");
	}

}
