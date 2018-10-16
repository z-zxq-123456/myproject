package com.dcits.ensemble.om.oms.service.sys;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.dao.utils.OMSIDao;
import com.dcits.ensemble.om.oms.module.sys.EcmParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数service * 
 * @author tangxlf
 * @date 2015-07-20
 */
@Service
public class ParamComboxService {
	    private  final Logger log = LoggerFactory.getLogger(this.getClass());
	    private static final String FIND_DETAIL_SQLID ="findDetailList";
	    private static Map<String,ParaCombox> paraMap = new HashMap<String,ParaCombox>();


		@Resource
		private OMSIDao omsBaseDao;
		
		private static final int DEFAULT = 0;
		private static final int ISSET = 1;
		//private static final int ISDEL = 2;		
		
		
		
		private synchronized  ParaCombox  getParaObj(String parentCode) {
			ParaCombox combox = paraMap.get(parentCode);
			if(combox==null){
				combox = new ParaCombox();				
				Map<String,Object> para = new HashMap<String,Object>();
				para.put("paraParentId", parentCode);				
				List<EcmParam> result = new ArrayList<EcmParam>();
			    try {
					result = omsBaseDao.findListByCond(new EcmParam(),"findCombList",para);
				} catch (Exception e) {					
					log.error("数据库查询出错信息："+e);
				}
				combox.setComboxList(result);
				Map<String,EcmParam> subParaMap = new HashMap<String,EcmParam>();
				for(int i=0;i<result.size();i++){
					subParaMap.put(result.get(i).getParaCode(),result.get(i));
				}
				combox.setComboxMap(subParaMap);
				if(subParaMap.size()>0){				
					paraMap.put(parentCode,combox);
				}
				return combox;			
			}else{
				return combox;
			}		
		}

		
		
	    public  List<EcmParam>  getParaColls(String parentCode,String isDefault){
	    	ParaCombox combox =   getParaObj(parentCode);
	    	List<EcmParam> result = combox.getComboxList();
	    	if(isDefault!=null&&isDefault.equals("true")&&combox.defalutstatus!=ISSET){
				  EcmParam info = new EcmParam();
				  info.setParaCode("");
				  info.setParaName("--请选择--");				
				  result.add(0,info);
				  combox.defalutstatus=ISSET;
			}else if((isDefault==null||isDefault.equals("")||isDefault.equals("false"))&&combox.defalutstatus==ISSET){
				  result.remove(0);
				  combox.defalutstatus=DEFAULT;
			}
			return result;
	    }
		
		public  String  getParaName(String paraCode){			    
			    if(paraCode==null||paraCode.trim().equals("")){
			    	return "";
			    }
			    ParaCombox combox =  getParaObj(paraCode.substring(0,4));
			    Map<String,EcmParam> subParaMap = combox.getComboxMap();
				return subParaMap.get(paraCode).getParaName();		
		}
		
		public  String  getParaNameByCode(String paraCode){	
			EcmParam param = new EcmParam();
			param.setParaCode(paraCode);
			return omsBaseDao.selectByPrimaryKey(param).getParaName();	
	    }
		
		public  String  getParaRemark1(String paraCode){	
		    if(paraCode==null||paraCode.trim().equals("")){
		    	return "";
		    }
		    ParaCombox combox =   getParaObj(paraCode.substring(0,4));
		    Map<String,EcmParam> subParaMap = combox.getComboxMap();
			return subParaMap.get(paraCode).getRemark1();
	    }
		
		public  String  getParaRemark2(String paraCode){	
		    if(paraCode==null||paraCode.trim().equals("")){
		    	return "";
		    }
		    ParaCombox combox =   getParaObj(paraCode.substring(0,4));
		    Map<String,EcmParam> subParaMap = combox.getComboxMap();
			return subParaMap.get(paraCode).getRemark2();		
	    }
		
		public void deleteCacheComboxData(String paraCode){
			paraMap.remove(paraCode.substring(0,4));
		}
		
		private class ParaCombox {
			List<EcmParam> comboxList;
			Map<String,EcmParam>  comboxMap;
			int  defalutstatus = DEFAULT;
			
		
			public List<EcmParam> getComboxList() {
				return comboxList;
			}
			public void setComboxList(List<EcmParam> comboxList) {
				this.comboxList = comboxList;
			}
			public Map<String,EcmParam> getComboxMap() {
				return comboxMap;
			}
			public void setComboxMap(Map<String,EcmParam> comboxMap) {
				this.comboxMap = comboxMap;
			}
			
		}


		public   List<EcmParam>  getParaDetailList(String paraName) {
				Map<String,Object> para = new HashMap<String,Object>();
				para.put("paraParentId", SysConfigConstants.SERVICECODE_MAPPING_CLASSNAME);
				para.put("isValidate", SysConfigConstants.STATUS_OK);
				para.put("paraName", paraName);
				return omsBaseDao.findListByCond(new EcmParam(),FIND_DETAIL_SQLID,para);
		}
}
