package com.dcits.galaxy.dal.demo1;

import com.dcits.galaxy.dal.demo1.entities.Employee;
import com.dcits.galaxy.dal.demo1.service.IEmployeeService;
import com.dcits.galaxy.dal.mybatis.plugins.RowArgs;
import com.dcits.galaxy.dal.mybatis.proactor.page.ParameterWrapper;
import com.dcits.galaxy.junit.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestEmployee extends TestBase {

	private IEmployeeService service = (IEmployeeService) context.getBean("employeeService");

	// ********************测试基本操作开始**********************//
	/**
	 * 测试insert,基本规则生效
	 */
	public void testInsert() {
		Employee employee = new Employee();
		employee.setSid( 1L );
		employee.setName("a1");
		employee.setAge(6);
		employee.setJob("test");
		service.insert(employee);
	}
	
	/**
	 * 测试update,基本规则生效
	 */
	public void testUpdate() {
		Employee employee = new Employee();
		employee.setSid( 1L);
		employee.setJob("111");
		service.update(employee);
	}

	/**
	 * 测试delete,基本规则生效
	 */
	public void testDelete() {
		Employee employee = new Employee();
		employee.setSid( 1L );
		service.delete(employee);
	}


	/**
	 * 测试get,基本规则生效
	 */
	public void testQuery() {
		Employee employee = new Employee();
		employee.setSid( 3L );
		
		employee = (Employee) service.get(employee);
		System.out.println(employee);
	}

	// ********************测试基本操作结束**********************//

	// ********************测试批量操作开始**********************//
	/**
	 * 测试insertBatch,基本规则生效 观察事务
	 */
	public void testInsertBatch() {
		List<Employee> list = new ArrayList<Employee>();
		
		Random random = new Random();
		random.nextInt(100);
		for (int i = 1; i <= 500; i++) {
			Employee employee = new Employee();
			employee.setSid( (long)i );
			employee.setName("a" + i);
			int j = i % 2;
			if( j == 0){
				employee.setGroupName("g2");
			}else{
				employee.setGroupName("g1");
			}
			employee.setAge( random.nextInt(100) );
			employee.setJob("t" + random.nextInt(100) );
			list.add(employee);
		}
		service.insertBatch(list);
	}

	/**
	 * 测试deleteBatch,基本规则生效
	 */
	public void testDeleteBatch() {
		List<Employee> list = new ArrayList<Employee>();

		for (int i = 1; i <= 500; i++) {
			Employee employee = new Employee();
			employee.setSid( (long)i );
			list.add(employee);
		}
		
		service.deleteBatch(list);
	}

	/**
	 * 测试updateBatch,基本规则生效
	 */
	public void testUpdateBatch() {
		List<Employee> list = new ArrayList<Employee>();

		for (int i = 1; i <= 500; i++) {
			Employee employee = new Employee();
			employee.setSid( (long)i );
			employee.setJob("testUpdateBatch");
			list.add(employee);
		}
		service.updateBatch(list);
	}

	/**
	 * 测试queryBatch,基本规则生效
	 */
	public void testQueryBatch() {
		List<Employee> list = new ArrayList<Employee>();

		Employee employee = new Employee();
		employee.setSid(1L);
		list.add(employee);

		employee = new Employee();
		employee.setSid(2L);
		list.add(employee);

		employee = new Employee();
		employee.setSid(3L);
		list.add(employee);

		List<Employee> resultList = service.queryBatch(list);
		System.out.println("QueryBatch result: " );
		for (Employee result : resultList) {
			System.out.println(result);
		}
	}

	// ********************测试批量操作结束**********************//

	/**
	 * 测试findByCondition,特殊规则生效
	 */
	public void testFindByCondition() {
		Employee employee = new Employee();
		employee.setGroupName("g1");
		List<Employee> resultList = service.findByCondition(employee);
		System.out.println("FindByCondition result: " );
		for (Employee result : resultList) {
			System.out.println(result);
		}
	}
	
	public void testFindAll() {
		String statement = "findAll";
		Employee employee = new Employee();
		employee.setSid(200L);
		List<Object> resultList = service.query(statement, employee);
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
		Employee employee = new Employee();
		employee.setSid(0L);
		RowArgs rowArgs = new RowArgs(400, 5);
		ParameterWrapper paraWrapper = new ParameterWrapper();
		paraWrapper.setBaseParam(employee);
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
