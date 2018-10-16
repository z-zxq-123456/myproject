package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbPartAttr;
import com.dcits.orion.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/10/14 09:46:11.
 */
@Repository
public class MbPartAttrDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbPartAttrDao";
    public List<MbPartAttr> selectByCondition(MbPartAttr mbPartAttr){
        return super.selectList(getNameSpace(),"selectByPrimaryKey",mbPartAttr);
    }
    public List<MbPartAttr> getByPartType(MbPartAttr mbPartAttr){
        return super.selectList(getNameSpace(),"getByPartType1",mbPartAttr);
    }
    public List<MbPartAttr> getByPartTypeContrast(MbPartAttr mbPartAttr){
        return super.selectList(getNameSpace(),"getByPartTypeContrast",mbPartAttr);
    }

    /**
     * This method corresponds to the database table MB_PART_ATTR
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}