package com.dcits.ensemble.om.pf.action.dataFactory;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcits.ensemble.om.pf.dao.dataFactory.*;
import com.dcits.ensemble.om.pf.module.dataFactory.*;
import com.dcits.ensemble.om.pf.util.ProdToMap;
import com.dcits.ensemble.om.pf.util.action.ActionResultWrite;
import com.dcits.ensemble.om.pf.zTreeModel.AttrModel;
import com.dcits.ensemble.om.pf.zTreeModel.PartModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyjw on 2015/10/9.
 */
@Controller
@RequestMapping("/partCon")
public class MbPartAttrAction {

    @Resource
    private MbAttrClassDao mbAttrClassDao;
    @Resource
    private MbAttrTypeDao mbAttrTypeDao;
    @Resource
    private MbAttrValueDao mbAttrValueDao;

    @Resource
    private MbPartClassDao mbPartClassDao;

    @Resource
    private MbPartTypeDao mbPartTypeDao;

    @Resource
    private MbPartAttrDao mbPartAttrDao;

    @RequestMapping("/getzTreeList")
    public void getzTreeList(HttpServletRequest request, PrintWriter printWriter){
        MbAttrClass mbAttrClass;
        List<MbAttrClass>  mbAttrClassList;
        if(request.getParameter("attrClass")== null)
        {
            mbAttrClassList=mbAttrClassDao.getAll();
        }
        else {
            mbAttrClassList=mbAttrClassDao.getByBmodule(request.getParameter("attrClass"));
        }
        List<AttrModel> attrModelList=new ArrayList<>();
        for(int i=0;i<mbAttrClassList.size();i++){
            mbAttrClass=mbAttrClassList.get(i);
            List<MbAttrType>  mbAttrTypeList;
            MbAttrType mbAttrType1 = new MbAttrType();
            mbAttrType1.setAttrClass(mbAttrClassList.get(i).getAttrClass());
            mbAttrType1.setBusiCategory("%"+request.getParameter("busiCategory")+"%");
            AttrModel attrModel = new AttrModel();
            attrModel.setId(mbAttrClass.getAttrClass());
            attrModel.setPId(mbAttrClass.getParentAttrClass());
            attrModel.setName(mbAttrClass.getAttrClassDesc());
            attrModelList.add(attrModel);
            MbAttrType mbAttrType;
            if(request.getParameter("busiCategory")== null){
                mbAttrTypeList=mbAttrTypeDao.getAttrKey(mbAttrClass);
            }else {
                 mbAttrTypeList=mbAttrTypeDao.getAttrKey11(mbAttrType1);
            }
            System.out.println(mbAttrTypeList);
            for(int j=0;j<mbAttrTypeList.size();j++){
                mbAttrType=mbAttrTypeList.get(j);
                AttrModel attrModel1=new AttrModel();
                attrModel1.setId(mbAttrType.getAttrKey());
                attrModel1.setPId(mbAttrType.getAttrClass());
                attrModel1.setName(mbAttrType.getAttrDesc());
                attrModel1.setSetValueFlag(mbAttrType.getSetValueFlag());
                attrModel1.setValueMethod(mbAttrType.getValueMethod());
                attrModelList.add(attrModel1);

            }
        }
        printWriter.print(JSON.toJSONString(attrModelList));
        printWriter.flush();
        printWriter.close();
    }
    //获取attrKey下所有的attrValue及描述
    @RequestMapping("/getValueList")
    public void getValueList( PrintWriter printWriter,String attrKey){
        MbAttrValue  mbAttrValue=new MbAttrValue();
        mbAttrValue.setAttrKey(attrKey);
        List<MbAttrValue> mbAttrValueList= mbAttrValueDao.getValueList(mbAttrValue);
        printWriter.print(JSON.toJSONString(mbAttrValueList));
        printWriter.flush();
        printWriter.close();
    }
    //获取attrKey下配置的表中的attrValue及描述
    @RequestMapping("/getRfValueList")
    public void getRfValueList( PrintWriter printWriter,String attrKey){
        MbAttrValue  mbAttrValue=new MbAttrValue();
        mbAttrValue.setAttrKey(attrKey);
        List<MbAttrValue> mbAttrValueList= mbAttrValueDao.getValueList(mbAttrValue);
        String str;
        List<MbAttrValue> mbAttrValueListRf;
        if(mbAttrValueList.size()>1){
           mbAttrValueListRf=mbAttrValueList;
        }else {
            String refColumns = mbAttrValueList.get(0).getRefColumns();
            int a = refColumns.indexOf(",");
            if (a > 0) {
                str = " " + refColumns.substring(0, a) + " ATTR_VALUE" + "," + refColumns.substring(a + 1, refColumns.length()) + " VALUE_DESC ";
            } else {
                str = " " + refColumns + " ATTR_VALUE";
            }
            mbAttrValue.setAttrDesc(str);
            mbAttrValue.setRefTable(mbAttrValueList.get(0).getRefTable());
            mbAttrValue.setRefCondition(mbAttrValueList.get(0).getRefCondition());
            mbAttrValueListRf = mbAttrValueDao.getRfValueList(mbAttrValue);
        }
        printWriter.print(JSON.toJSONString(mbAttrValueListRf));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getPartzTreeList")
    public void getPartzTreeList(HttpServletRequest request, PrintWriter printWriter){
        String str ;
        MbPartClass mbPartClass;
        List<MbPartClass> mbPartClassList ;

        if(request.getParameter("Bmodule")== null)
        {
            mbPartClassList=mbPartClassDao.getAll();
            str = null;
        }
        else {
            str ="%"+request.getParameter("Bmodule")+"%";
            mbPartClassList=mbPartTypeDao.getTreeByBmodule(request.getParameter("Bmodule"));
        }
        List<PartModel> partModelList=new ArrayList<>();
        for(int i=0;i<mbPartClassList.size();i++){
            mbPartClass=mbPartClassList.get(i);
            PartModel partModel = new PartModel();
            partModel.setId(mbPartClass.getPartClass());
            partModel.setPId(mbPartClass.getParentPartClass());
            partModel.setName(mbPartClass.getPartClassDesc());
            partModelList.add(partModel);
            MbPartType mbPartType=new MbPartType();
            mbPartType.setPartClass(mbPartClass.getPartClass());
            mbPartType.setBusiCategory(str);
            List<MbPartType> mbPartTypeList=mbPartTypeDao.getPartTypeByClass1(mbPartType);
            System.out.println(mbPartTypeList);
            for(int j=0;j<mbPartTypeList.size();j++){
                mbPartType=mbPartTypeList.get(j);
                PartModel partModel1=new PartModel();
                partModel1.setId(mbPartType.getPartType());
                partModel1.setPId(mbPartType.getPartClass());
                partModel1.setName(mbPartType.getPartDesc());
                partModelList.add(partModel1);
            }

        }
        System.out.println("----------" + partModelList.toString());
        printWriter.print(JSON.toJSONString(partModelList));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/insert")
    public void insert(PrintWriter printWriter,@RequestBody String jsonList){

        List<AttrModel> attrModelList = JSON.parseArray(jsonList,AttrModel.class);
        System.out.println("--partCon--insert-----" + attrModelList.toString());
        Map result=new HashMap();
        try {
            for (AttrModel attrModel : attrModelList) {
                MbPartAttr mbPartAttr = new MbPartAttr();
                mbPartAttr.setPartType(attrModel.getPartType());
                mbPartAttr.setAttrKey(attrModel.getAttrKey());

                mbPartAttr.setAttrValue(attrModel.getAttrValue());
                if ("I".equals(attrModel.getFlag())) {
                    ProdToMap.partCon(mbPartAttr,"Insert",attrModel.getReqNum());
                    //mbPartAttrDao.insert(mbPartAttr);
                } else if ("U".equals(attrModel.getFlag())) {
                    ProdToMap.partCon(mbPartAttr,"Modify",attrModel.getReqNum());
                    //mbPartAttrDao.updateByPrimaryKey(mbPartAttr);
                } else if ("D".equals(attrModel.getFlag())) {
                    ProdToMap.partCon(mbPartAttr,"Delete",attrModel.getReqNum());
                    //mbPartAttrDao.deleteByPrimaryKey(mbPartAttr);
                }

            }
            result.put("retStatus", "S");
            result.put("retMsg", "保存成功");
        } catch(Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", "保存失败");
            throw e;
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/copy")
    public void copy(PrintWriter printWriter,@RequestBody String jsonList){
        List<AttrModel> attrModelList = JSON.parseArray(jsonList,AttrModel.class);
        System.out.println("--partCon--copy-----" + attrModelList.toString());
        Map result=new HashMap();
        try {
            for (AttrModel attrModel : attrModelList) {
                MbPartAttr mbPartAttr = new MbPartAttr();
                mbPartAttr.setPartType(attrModel.getPartType());
                mbPartAttr.setAttrKey(attrModel.getAttrKey());
                mbPartAttr.setAttrValue(attrModel.getAttrValue());
                if (!"D".equals(attrModel.getFlag())) {
                    ProdToMap.partCon(mbPartAttr,"Insert",attrModel.getReqNum());
                }
            }
            result.put("retStatus", "S");
            result.put("retMsg", "复制成功");
        } catch(Exception e) {
            result.put("retStatus", "F");
            result.put("retMsg", "复制失败");
            throw e;
        }
        printWriter.print(JSON.toJSONString(result));
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartType")
    public void getByPartType(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbPartAttr mbPartAttr=new MbPartAttr();
        mbPartAttr.setPartType(request.getParameter("partType"));
        mbPartAttr.setAttrValue(request.getParameter("partClass"));
        List<MbPartAttr> mbPartAttrList =mbPartAttrDao.getByPartType(mbPartAttr);
        List<AttrModel> attrModelList=new ArrayList<>();
        for(int i=0;i<mbPartAttrList.size();i++){
            AttrModel attrModel=new AttrModel();
            MbPartAttr mbPartAttr1=mbPartAttrList.get(i);
            attrModel.setAttrKey(mbPartAttr1.getAttrKey());
            attrModel.setAttrValue(mbPartAttr1.getAttrValue());
            attrModel.setName((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getAttrDesc());
            attrModel.setValueMethod((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getValueMethod());
            attrModelList.add(attrModel);
        }
        System.out.println("--partCon--getByPartType-----" + attrModelList.toString());
        out.put("data",attrModelList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartTypeContrast")
    public void getByPartTypeContrast(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbPartAttr mbPartAttr=new MbPartAttr();
        mbPartAttr.setPartType(request.getParameter("partType"));
        mbPartAttr.setAttrValue(request.getParameter("partClass"));
        List<MbPartAttr> mbPartAttrList =mbPartAttrDao.getByPartTypeContrast(mbPartAttr);
        List<AttrModel> attrModelList=new ArrayList<>();
        for(int i=0;i<mbPartAttrList.size();i++){
            AttrModel attrModel=new AttrModel();
            MbPartAttr mbPartAttr1=mbPartAttrList.get(i);
            attrModel.setAttrKey(mbPartAttr1.getAttrKey());
            attrModel.setAttrValue(mbPartAttr1.getAttrValue());
            attrModel.setName((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getAttrDesc());
            attrModel.setValueMethod((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getValueMethod());
            attrModelList.add(attrModel);
        }
        System.out.println("--partCon--getByPartTypeContrast-----" + attrModelList.toString());
        out.put("data",attrModelList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartType1")
    public void getByPartType1(HttpServletRequest request, PrintWriter printWriter){
        JSONArray outArray = new JSONArray();
        JSONArray result=new JSONArray();
        JSONObject object = new JSONObject();
        MbPartAttr mbPartAttr=new MbPartAttr();
        mbPartAttr.setPartType(request.getParameter("partType"));
        List<MbPartAttr> mbPartAttrList =mbPartAttrDao.getByPartType(mbPartAttr);
        for(int j=0;j<mbPartAttrList.size();j++){
            MbAttrType mbAttrType = mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(),mbPartAttrList.get(j).getAttrKey());
            JSONObject data=new JSONObject();
            data.put("attrKey", mbPartAttrList.get(j).getAttrKey());
            data.put("attrValue", mbPartAttrList.get(j).getAttrValue());
            data.put("valueMethod", mbAttrType.getValueMethod());
            data.put("attrDesc", mbAttrType.getAttrDesc());
            data.put("setValueFlag", mbAttrType.getSetValueFlag());
            result.add(j, data);
        }
         object.put("data",result);
         outArray.add(object);
        System.out.println("---partAttr---getByPartType1----"+outArray.toString());
        printWriter.print(outArray.toJSONString());
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/getByPartAll")
    public void getByPartAll(HttpServletRequest request, PrintWriter printWriter){
        JSONObject out=new JSONObject();
        MbPartAttr mbPartAttr=new MbPartAttr();
        mbPartAttr.setPartType(request.getParameter("partType"));
        List<MbPartAttr> mbPartAttrList =mbPartAttrDao.getByPartType(mbPartAttr);
        List<AttrModel> attrModelList=new ArrayList<AttrModel>();
        for(int i=0;i<mbPartAttrList.size();i++){
            AttrModel attrModel=new AttrModel();
            MbPartAttr mbPartAttr1=mbPartAttrList.get(i);
            attrModel.setAttrKey(mbPartAttr1.getAttrKey());
            MbAttrValue mbAttrValue= mbAttrValueDao.selectByPrimaryKey(new MbAttrValue(), mbPartAttr1.getAttrKey(), mbPartAttr1.getAttrValue());
            if(mbAttrValue!=null){
                attrModel.setAttrValue(mbPartAttr1.getAttrValue() + "-" + mbAttrValue.getValueDesc());
            }else{
                if("Y".equals(mbPartAttr1.getAttrValue())){
                    attrModel.setAttrValue(mbPartAttr1.getAttrValue() + "-是");
                } else if("N".equals(mbPartAttr1.getAttrValue())){
                    attrModel.setAttrValue(mbPartAttr1.getAttrValue() + "-否");
                } else {
                    attrModel.setAttrValue(mbPartAttr1.getAttrValue());
                }
            }
            attrModel.setName((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getAttrDesc());
            attrModel.setValueMethod((mbAttrTypeDao.selectByPrimaryKey(new MbAttrType(), mbPartAttr1.getAttrKey())).getValueMethod());
            attrModelList.add(attrModel);
        }
        System.out.println("--partCon--getByPartAll-----" + attrModelList.toString());
        out.put("data",attrModelList);
        printWriter.print(out);
        printWriter.flush();
        printWriter.close();
    }
    @RequestMapping("/selectByCondition")
    public void getAll(HttpServletRequest request,PrintWriter printWriter){
        try {
            MbPartAttr partAttr = new MbPartAttr();
            partAttr.setPartType(request.getParameter("partType"));
            if(request.getParameter("attrKey").isEmpty()) {
                partAttr.setAttrKey(null);
            }else{
                partAttr.setAttrKey(request.getParameter("attrKey"));
            }
            List<MbPartAttr> info = mbPartAttrDao.selectByCondition(partAttr);
            List<MbPartAttr> infoList=new ArrayList<>();
            //字段数据汉化
            for(MbPartAttr mbPartAttr : info){
                String AttrKey=mbAttrTypeDao.getAttrDesc(mbPartAttr.getAttrKey());
                MbAttrValue attrValue=new MbAttrValue();
                attrValue.setAttrKey(mbPartAttr.getAttrKey());
                attrValue.setAttrValue(mbPartAttr.getAttrValue());
                String valueMethod=mbAttrTypeDao.getValueMethod(attrValue.getAttrKey());
                String attrValue1=mbAttrValueDao.getAttrValue(attrValue);
                String attrValueD=mbAttrValueDao.getAttrValueDesc(attrValue);
                if(null != valueMethod ) {
                    if ("FD".equals(valueMethod)) {
                        mbPartAttr.setAttrValue(attrValueD);
                    } else if(attrValue1 == null) {
                        mbPartAttr.setAttrValue("");  //null(null) ->""
                    }else if(attrValueD ==null){
                        mbPartAttr.setAttrValue(AttrKey+" ("+attrValue1+")");  //null(a) -> DESC(a)
                    }else{
                        mbPartAttr.setAttrValue(attrValueD+" ("+attrValue1+")");     //desc(a)
                    }
                }
                mbPartAttr.setAttrKey(AttrKey);
                infoList.add(mbPartAttr);
            }
            ActionResultWrite.setReadResult(printWriter, info);
        }catch(Exception e){
            ActionResultWrite.setErrorResult(printWriter,e.getMessage());
            throw e;
        }
    }
}
