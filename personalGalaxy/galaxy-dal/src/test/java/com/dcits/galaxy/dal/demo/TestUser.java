package com.dcits.galaxy.dal.demo;

import com.dcits.galaxy.dal.demo.entities.User;
import com.dcits.galaxy.dal.demo.service.IUserService;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.junit.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUser extends TestBase {

	private IUserService service = (IUserService) context.getBean("userService");

	// ********************测试基本操作开始**********************//
	/**
	 * 测试insert,基本规则生效
	 */
	public void testInsert() {
		User user = new User();
		user.setSid( 1L );
		user.setUserName("a1");
		user.setGroupName("g1");
		user.setAge(6);
		user.setRemark("123");
		service.insert(user);
	}
	
	/**
	 * 测试update,基本规则生效
	 */
	public void testUpdate() {
		User user = new User();
		user.setSid( 1L);
		user.setRemark("111");
		
		service.update(user);
	}

	/**
	 * 测试delete,基本规则生效
	 */
	public void testDelete() {
		User user = new User();
		user.setSid( 1L );
		service.delete(user);
	}


	/**
	 * 测试get,基本规则生效
	 */
	public void testQuery() {
		User user = new User();
		user.setSid( 1L );
		
		user = (User) service.get(user);
		System.out.println(user);
	}

	// ********************测试基本操作结束**********************//

	// ********************测试批量操作开始**********************//
	/**
	 * 测试insertBatch,基本规则生效 观察事务
	 */
	public void testInsertBatch() {
		List<User> list = new ArrayList<User>();
		
		Random random = new Random();
		random.nextInt(100);
		for (int i = 0; i <= 500; i++) {
			User user = new User();
			user.setSid( (long)i );
			user.setUserName("a" + i);
			int j = i % 2;
			if( j == 0){
				user.setGroupName("g2");
			}else{
				user.setGroupName("g1");
			}
			user.setAge( random.nextInt(100) );
			user.setRemark("t" + random.nextInt(100) );
			list.add(user);
		}
		service.insertBatch(list);
	}

	/**
	 * 测试deleteBatch,基本规则生效
	 */
	public void testDeleteBatch() {
		List<User> list = new ArrayList<User>();

		for (int i = 1; i <= 200; i++) {
			User user = new User();
			user.setSid( (long)i );
			list.add(user);
		}

		service.deleteBatch(list);
	}

	/**
	 * 测试updateBatch,基本规则生效
	 */
	public void testUpdateBatch() {
		List<User> list = new ArrayList<User>();

		User userInfo = new User();
		userInfo.setSid(994321483644729474L);
		userInfo.setRemark("TESTUpdate");
		list.add(userInfo);

		userInfo = new User();
		userInfo.setSid(880369258840067806L);
		userInfo.setRemark("TESTUpdate");
		list.add(userInfo);

		userInfo = new User();
		userInfo.setSid(1622229833332579898L);
		userInfo.setRemark("TESTUpdate");
		list.add(userInfo);

		service.updateBatch(list);
	}

	/**
	 * 测试queryBatch,基本规则生效
	 */
	public void testQueryBatch() {
		List<User> list = new ArrayList<User>();

		User user = new User();
		user.setSid(994321483644729474L);
		list.add(user);

		user = new User();
		user.setSid(880369258840067806L);
		list.add(user);

		user = new User();
		user.setSid(804692009680319579L);
		list.add(user);

		List<User> result = service.queryBatch(list);
		System.out.println(result);
	}

	// ********************测试批量操作结束**********************//

	/**
	 * 测试findByCondition,特殊规则生效
	 */
	public void testFindByCondition() {
		User user = new User();
		user.setRemark("TESTUpdate");

		List<User> result = service.findByCondition(user);
		System.out.println(result);
	}
	
	public void testFindAll() {
		String statement = "findAll";
		User user = new User();
		user.setSid(1L);
		List<Object> resultList = service.query(statement, user);
		System.out.println("FindAll result: " );
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindMax() {
		String statement = "findMax";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindMax result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindMin() {
		String statement = "findMin";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindMax result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindCount() {
		String statement = "findCount";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindCount result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindSum() {
		String statement = "findSum";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindSum result: ");
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	/**
	 * 多聚合
	 */
	public void testFindMulti() {
		String statement = "findMulti";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindMulti result: " );
        for( Object result : resultList ){
        	System.out.println( result );
        }
	}
	
	public void testFindOrderBy() {;
		String statement = "findOrderBy";
		List<Object> resultList = service.query(statement, null);
		System.out.println("FindOrderBy result: ");
		for( Object result : resultList ){
			System.out.println( result );
		}
	}
	
	public void testFindPage() {
		String statement = "findPage";
		User user = new User();
		user.setSid(0L);
		RowArgs rowArgs = new RowArgs(400, 5);
		ParameterWrapper paraWrapper = new ParameterWrapper();
		paraWrapper.setBaseParam(user);
		paraWrapper.setRowArgs(rowArgs);
		
		List<Object> resultList = service.query(statement, paraWrapper);
		System.out.println("FindPage result: ");
		for( Object result : resultList ){
			System.out.println( result );
		}
		
		rowArgs.setOffset(405);
		rowArgs.setLimit( 5 );
		paraWrapper.setExtParam( null);
		resultList = service.query(statement, paraWrapper);
		System.out.println("FindPage result: ");
		for( Object result : resultList ){
			System.out.println( result );
		}
		
		rowArgs.setOffset(400);
		rowArgs.setLimit( 10 );
		paraWrapper.setExtParam( null);
		resultList = service.query(statement, paraWrapper);
		System.out.println("FindPage result: ");
		for( Object result : resultList ){
			System.out.println( result );
		}
	}
	
}
