package com.dcits.galaxy.dtp.resource;

import java.sql.SQLException;

import javax.annotation.Resource;

import com.dcits.galaxy.dal.mybatis.ShardSqlSessionTemplate;

public class DtpResourceDao {

	@Resource
	private ShardSqlSessionTemplate shardSqlSessionTemplate;

	private static final String NAMESPACE = DtpResource.class.getName();

	protected String getNamespace() {
		return NAMESPACE;
	}

	public DtpResource getResource(DtpResource r) throws SQLException {
		return (DtpResource) shardSqlSessionTemplate.selectOne(getNamespace(), "getResource", r);
	}

	public boolean lockResource(DtpResource r) throws SQLException {
		boolean result = false;
		int i = shardSqlSessionTemplate.insert(getNamespace(), "lockResource", r);
		if (i == 1) {
			result = true;
		}
		return result;
	}

	public boolean unLockResource(DtpResource r) throws SQLException {
		boolean result = false;
		int i = shardSqlSessionTemplate.delete(getNamespace(), "unLockResource", r);
		if (i == 1) {
			result = true;
		}
		return result;
	}
}
