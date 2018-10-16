package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.PkList;
import com.dcits.ensemble.om.pf.module.dataFactory.MbAttrValue;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/24 17:03:58.
 */
@Repository
public class MbAttrValueDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbAttrValueDao";
    public List<MbAttrValue> getAll(){
        return super.selectList(getNameSpace(),"getAll");
    }
    public String getAttrValueDesc(MbAttrValue mbAttrValue){
        return super.selectOne(getNameSpace(), "getAttrValueDesc", mbAttrValue);
    }
    public String getAttrValue(MbAttrValue mbAttrValue){
        return super.selectOne(getNameSpace(),"getAttrValue",mbAttrValue);
    }
    public MbAttrValue selectByPrimaryKey1(MbAttrValue mbAttrValue){
        return super.selectOne(getNameSpace(),"selectByPrimaryKey1",mbAttrValue);
    }
    public List<MbAttrValue> getValueList(MbAttrValue mbAttrValue){
        return super.selectList(getNameSpace(), "getValueList", mbAttrValue);
    }
    public List<PkList> getAttrValueByKey(String attrKey) {
        return super.selectList(getNameSpace(), "getAttrValueByKey",attrKey);
    }
    public List<MbAttrValue> getRfValueList(MbAttrValue mbAttrValue){
        return super.selectList(getNameSpace(), "getRfValueList1",mbAttrValue);
    }
    /**
     * This method corresponds to the database table MB_ATTR_VALUE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}