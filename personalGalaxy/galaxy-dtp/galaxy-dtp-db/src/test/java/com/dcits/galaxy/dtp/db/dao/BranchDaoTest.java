package com.dcits.galaxy.dtp.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.dcits.galaxy.dtp.branch.BranchTransaction;

public class BranchDaoTest extends TestCase {
	
	private SqlSessionTemplate sqlSessionTemplate = null;

	@Override
	protected void setUp() throws Exception {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl("jdbc:oracle:thin:@192.168.165.141:1521:GALAXY");
		druidDataSource.setUsername("ENSEMBLE_DEMO");
		druidDataSource.setPassword("ENSEMBLE_DEMO");
		druidDataSource.setMinIdle(1);
		druidDataSource.setInitialSize(1);
		druidDataSource.setMaxActive(1);
		druidDataSource.init();
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(druidDataSource);
		factoryBean.setMapperLocations(resolver.getResources("classpath*:META-INF/mapper/**/*.xml"));
		factoryBean.setConfigLocation(resolver.getResource("classpath:META-INF/mybatis/mybatis-config.xml"));
		
		try{
			factoryBean.afterPropertiesSet();
		}catch(Throwable t) {
			t.printStackTrace();
			throw t;
		}
		
		this.sqlSessionTemplate = new SqlSessionTemplate(factoryBean.getObject());
		
	}
	
	private List<BranchTransaction> fetchAllBranch(){
		BranchTransaction bt = new BranchTransaction();
		bt.setTxid("1607120000000202");
		List<BranchTransaction> list = sqlSessionTemplate.selectList("com.dcits.galaxy.dtp.branch.BranchTransaction.getAllByTxid",bt);
		return list;
	}

	public void testPrintBranch(){
		List<BranchTransaction> list = fetchAllBranch();
		
		Map<String, BranchTransaction> branchMap = new HashMap<>();
		for(BranchTransaction branch : list) {
			branchMap.put(branch.getBxid(),branch);
		}
		
		branchMap.put("unknow", new BranchTransaction());
		
		Map<BranchTransaction, BranchComponent> map = new HashMap<>();
		
		BranchComponent root = new BranchComponent(branchMap.get("unknow"));
		map.put(branchMap.get("unknow"), root);
		
		for(BranchTransaction branch : list) {
			if(map.containsKey(branch)) {
				continue;
			}
			map.put(branch, new BranchComponent(branch));
			BranchComponent component = map.get(branch);
			
			BranchTransaction parent = branchMap.get(branch.getParentBxid());
			
			while(parent!=null) {
				BranchComponent parentComponent = map.get(parent);
				if(parentComponent != null) {
					parentComponent.list.add(component);
					break;
				}
				parentComponent = new BranchComponent(parent);
				map.put(parent, parentComponent);
				parentComponent.list.add(component);
				component = parentComponent;
				parent = branchMap.get(parent.getParentBxid());
			}
			
		}
		
		print(root,"");
	}
	
	private void print(BranchComponent root,String prefix) {
		if(root.branch.getBxid() == null) {
			System.out.println(prefix + "unkonw");
		} else {
			StringBuilder builder = new StringBuilder(prefix).append(root.branch.getBxid());
			System.out.println(builder.toString());
		}
		
		for(BranchComponent component : root.list) {
			print(component, prefix + "    ");
		}
	}
	
	private class BranchComponent {
		
		public BranchComponent(BranchTransaction branch) {
			super();
			this.branch = branch;
		}
		BranchTransaction branch = null;
		List<BranchComponent> list = new ArrayList<>();
	}
}
