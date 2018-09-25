package com.dcits.galaxy.dtp.demo.dao;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;
import com.dcits.galaxy.dtp.demo.entities.Money;

@Repository
public class MoneyDao {
	
	@Resource
	ShardSqlSessionTemplate sqlSession;
	
	private static String NAMESPACE = Money.class.getName();
	
	protected String getNamespace() {
		return NAMESPACE;
	}
	
	public int updateMoney(Money money) throws SQLException{
		int result = sqlSession.update(getNamespace(), "updateMoney", money);
		return result;
	}
	
	public Money getMoney(String userId) throws SQLException{
		Money money = new Money();
		money.setUserId(userId);
		money = (Money) sqlSession.selectOne(getNamespace(), "getMoney",money);
		return money;
	}
}
