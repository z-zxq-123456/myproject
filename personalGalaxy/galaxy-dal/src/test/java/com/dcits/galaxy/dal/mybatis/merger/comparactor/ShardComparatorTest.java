package com.dcits.galaxy.dal.mybatis.merger.comparactor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.dcits.galaxy.dal.demo.entities.User;

public class ShardComparatorTest extends TestCase {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testEntity(){
		Class objectClass = User.class;
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setSid(10L);
		user1.setUserName("zz");
		user1.setGroupName("g2");
		user1.setAge(8);
		users.add(user1);
		
		User user2 = new User();
		user2.setSid(11L);
		user2.setUserName("zz");
		user2.setGroupName("g3");
		user2.setAge(18);
		users.add( user2 );
		
		User user3 = new User();
		user3.setSid(10L);
		user3.setUserName("xx");
		user3.setGroupName("g1");
		user3.setAge(1);
		users.add( user3 );
		
		User user4 = new User();
		user4.setSid(11L);
		user4.setUserName("mm");
		user4.setGroupName("g5");
		user2.setAge( 2 );
		users.add( user4 );
		
		User user5 = new User();
		user5.setSid(8L);
		user5.setUserName("tt");
		user5.setGroupName("g8");
		user2.setAge( 8 );
		users.add( user5 );
		
//		ShardComparator<Object> c = ShardComparator.newInstance(objectClass);
//		c.setColumn("sid");
//		c.setMapUnderscoreToCamelCase(true);
		
		ShardComparator<Object> c = ShardComparator.newInstance(objectClass);
		c.setColumn("int_Age");
		c.setMapUnderscoreToCamelCase(true);
		
		ShardComparator<Object> c1 = ShardComparator.newInstance(objectClass);
		c1.setColumn("user_Name");
		c1.setMapUnderscoreToCamelCase(true);
		c.setNextComparator( c1 );
		
		ShardComparator<Object> c2 = ShardComparator.newInstance(objectClass);
		c2.setColumn("group_Name");
		c2.setMapUnderscoreToCamelCase(true);
		c2.setAsc( false );
		
		c1.setNextComparator( c2 );
		
		Collections.sort(users, c);
		
		for (User user : users ) {
			System.out.println( user);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testWrapper(){
		Class objectClass = Long.class;
		List<Object> users = new ArrayList<Object>();
		users.add(10L);
		users.add(11L);
		users.add(8L);
		users.add(20L);
		users.add(21L);
		
		ShardComparator<Object> c1 = ShardComparator.newInstance(objectClass);
		c1.setColumn("userName");
		c1.setMapUnderscoreToCamelCase(true);
		
		ShardComparator<Object> c2 = ShardComparator.newInstance(objectClass);
		c2.setColumn("groupName");
		c2.setMapUnderscoreToCamelCase(true);
		c2.setAsc( false );
		
		c1.setNextComparator( c2 );
		
		Collections.sort(users, c1);
		
		for (Object user : users ) {
			System.out.println( user);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testMap(){
		Class objectClass = Map.class;
		List<Map> users = new ArrayList<Map>();
		Map user1 = new HashMap<String,Object>();
		user1.put("sid", 10L);
		user1.put("userName", "zz");
		user1.put("groupName", "g2");
		users.add(user1);
		
		Map user2 = new HashMap<String,Object>();
		user2.put("sid", 11L);
		user2.put("userName", "zz");
		user2.put("groupName", "g3");
		users.add(user2);
		
		Map user3 = new HashMap<String,Object>();
		user3.put("sid", 8L);
		user3.put("userName", "xx");
		user3.put("groupName", "g1");
		users.add(user3);
		
		Map user4 = new HashMap<String,Object>();
		user4.put("sid", 20L);
		user4.put("userName", "mm");
		user4.put("groupName", "g5");
		users.add(user4);
		
		Map user5 = new HashMap<String,Object>();
		user5.put("sid", 21L);
		user5.put("userName", "tt");
		user5.put("groupName", "g8");
		users.add(user5);
		
		ShardComparator<Object> c1 = ShardComparator.newInstance(objectClass);
		c1.setColumn("userName");
		c1.setMapUnderscoreToCamelCase(true);
		
		ShardComparator<Object> c2 = ShardComparator.newInstance(objectClass);
		c2.setColumn("groupName");
		c2.setMapUnderscoreToCamelCase(true);
		c2.setAsc( false );
		
		c1.setNextComparator( c2 );
		
		Collections.sort(users, c1);
		
		for (Object user : users ) {
			System.out.println( user);
		}
	}
}
