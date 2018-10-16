package com.dcits.ensemble.om.pf.dao.dataFactory;

import com.dcits.ensemble.om.pf.module.dataFactory.MbPartClass;
import com.dcits.orion.core.dao.BaseDao;
import com.dcits.ensemble.om.pf.module.dataFactory.MbPartType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhangyjw on 2015/09/28 10:26:21.
 */
@Repository
public class MbPartTypeDao extends BaseDao {

    //Mapper文件定义的NameSpace
    private final String DEFAULT_NAME_SPACE = "com.dcits.ensemble.om.pf.dao.dataFactory.MbPartTypeDao";
    public String  getForCheck(String partType){
        return super.selectOne(getNameSpace(), "getForCheck", partType);
    }
    public String  getPartTypeDesc(String partType){
        return super.selectOne(getNameSpace(), "getPartTypeDesc", partType);
    }
    public List<MbPartType> getAll(){
        return super.selectList(getNameSpace(), "getAll");
    }
    public List<MbPartType> getAllContrast(){
        return super.selectList(getNameSpace(), "getAllContrast");
    }


    public List<MbPartType> getAllN(){
        String isStandard="N";
        return super.selectList(getNameSpace(),"getAllN",isStandard);
    }
    public List<MbPartType> getPartTypeByClass(MbPartType mbPartType){
        mbPartType.setIsStandard("Y");
        return super.selectList(getNameSpace(),"getPartTypeByClass",mbPartType);
    }
    public List<MbPartType> getPartTypeByClass1(MbPartType mbPartType){
        mbPartType.setIsStandard("Y");
        return super.selectList(getNameSpace(),"getPartTypeByClass1",mbPartType);
    }

    public List<MbPartClass> getTreeByBmodule(String Bmodule) {
       MbPartType mbPartType =new MbPartType();
        String str = '%'+ Bmodule+'%';
        mbPartType.setBusiCategory(str);
        return super.selectList(getNameSpace(),"getTreeByBmodule",mbPartType);
    }

    public List<MbPartType> getPartKey(MbPartClass mbPartClass){
        return super.selectList(getNameSpace(), "getPartKey", mbPartClass);
    }

    public String getProcessMethod(String partType){
        return super.selectOne(getNameSpace(), "getProcessMethod", partType);
    }

    /**
     * This method corresponds to the database table MB_PART_TYPE
     */
    protected String getNameSpace() {
        return DEFAULT_NAME_SPACE;
    }
}