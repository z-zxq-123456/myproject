package com.dcits.ensemble.om.pm.util.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.springframework.stereotype.Repository;
import com.dcits.orion.core.dao.BaseDao;

@Repository
public class LimCheckExpressionDao extends BaseDao {

	@Override
	protected String getNameSpace() {
		return this.getClass().getName();
	}


	public List<Map<String, String>> findAmtType(Map<String,String> fmRefCode) {
		return super.selectList(getNameSpace() , "findAmtType",fmRefCode);




	}
}
