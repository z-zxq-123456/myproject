package com.dcits.ensemble.om.pm.util.dao;

import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by jiaxina on 2016/9/1.
 */
@Repository
public class PkCreateInDao  extends BaseDao{

        @Override
        protected String getNameSpace() {
            return DEFAULT_NAME_SPACE;
        }
        private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pm.util.dao.PkCreateInDao";

        public Object createPk(Map<String,String> sqlMap){
            Map<String,Object> map = super.selectOne(getNameSpace(), "createPk", sqlMap);
            return map == null?null:map.get("MAX_ID");
        }
        public String createMaxByCon(Map<String, String> params){
            return super.selectOne(getNameSpace(), "createMaxByCon", params);
        }
        public List<Map<String, String>> findAmtType(Map<String,String> fmRefCode) {
            return super.selectList(getNameSpace() , "findAmtType",fmRefCode);

        }
    }


