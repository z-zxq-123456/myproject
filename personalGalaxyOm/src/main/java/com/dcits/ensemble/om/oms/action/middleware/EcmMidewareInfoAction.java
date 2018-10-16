package com.dcits.ensemble.om.oms.action.middleware;

import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.mapper.PkList;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.common.DataUtil;
import com.dcits.ensemble.om.oms.module.middleware.*;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.manager.app.AppPathHandler;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.MiddlewareInfoService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmMidewareInfoAction* 
 * @author LuoLang
 * @date 2016-02-23
 */
@Controller
public class EcmMidewareInfoAction {

	private int max_req_no = 0;
	@Resource
	PkServiceUtil serviceUtil;

	
	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	AppPathHandler appPathHandler;
	@Resource
	MiddlewareInfoService middlewareInfoService;
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("saveEcmMidewareInfo")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareInfo midware) {
		try {
			int midewareId = serviceUtil.getMaxReqID(max_req_no,"ecm_midware_info","midware_id");
			midware.setMidwareId(midewareId);
			System.out.println("111" + midware);
			middlewareInfoService.save(midware);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}

	@RequestMapping("updateEcmMidewareInfo")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmMidwareInfo midware) {
		try {
			if(!isAllMidwareIsClean(midware)){
				if(!isUpdateMidwareInfo(midware)){
					middlewareInfoService.update(midware);
				}else{
					throw new GalaxyException("该集群下的已部署实例，中间件类型和路径不能改变！");
				}
			}else{
				middlewareInfoService.update(midware);
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("deleteEcmMidewareInfo")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmMidwareInfo midware) {
		try {
			if(isAllMidwareIsClean(midware)){
				if(midware.getIsDefault().equals(SysConfigConstants.IS_DEFAULT)) {
					middlewareInfoService.getMap().remove(midware.getMidwareType());
				}
				omsBaseService.deleteByPrimaryKey(midware);
			}else {
				throw new GalaxyException("请先删除该集群下的所有实例！");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("findEcmMidwareInfo")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareInfo midware) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",1000);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			PageData<EcmMidwareInfo> pageData =omsBaseService.findPageByCond(midware,pageNo,pageSize);
			ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList()), pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	private List<EcmMidwareInfo> magicList(List<EcmMidwareInfo> list) {
		for(EcmMidwareInfo midware:list){
			if(!DataUtil.isNull(midware.getKfkZksId())){
				EcmMidwareInfo  temp = new EcmMidwareInfo();
				EcmMidwareInfo  result = new EcmMidwareInfo();
				temp.setMidwareId(Integer.valueOf(midware.getKfkZksId()));
			    result=omsBaseService.selectByPrimaryKey(temp);
				midware.setKfkZksIdName(result.getMidwareName());
			}
			midware.setIsDefaultParaName(paramComboxService.getParaName(midware.getIsDefault()));
			midware.setMidwareTypeName(paramComboxService.getParaName(midware.getMidwareType()));
		}
		return list;
	}
	@RequestMapping("findKfkCombox")
	public void findKfkCombox(HttpServletRequest request, PrintWriter printWriter,EcmMidwareInfo midware ){
		int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
		int pageSize = ServletRequestUtils.getIntParameter(request, "rows",1000);
		log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
		PageData<EcmMidwareInfo> pageData =omsBaseService.findPageByCond(midware, pageNo, pageSize);
		List<PkList> pkLists = new ArrayList<>();
		for (int i=0;i<pageData.getList().size();i++){
			PkList pkList = new PkList();
			pkList.setPkDesc(pageData.getList().get(i).getMidwareName());
			pkList.setPkValue(pageData.getList().get(i).getMidwareId().toString());
			pkLists.add(pkList);
		}
		printWriter.print(JSON.toJSONString(pkLists));
		printWriter.flush();
		printWriter.close();

	}

	@RequestMapping("findMidwareCombox")
	public void findMiddlewareCombox(HttpServletRequest request, PrintWriter printWriter,String midwareType) {
		List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(midwareType);
        List<PkList> pkLists = new ArrayList<>();
        for (int i=0;i<infoList.size();i++){
            PkList pkList = new PkList();
            pkList.setPkDesc(infoList.get(i).getMidwareName());
            pkList.setPkValue(infoList.get(i).getMidwareId().toString());
            pkLists.add(pkList);
        }
        printWriter.print(JSON.toJSONString(pkLists));
        printWriter.flush();
        printWriter.close();

	}
	@RequestMapping("findMidwareDefaultCombox")
	public void findMiddlewareDefaultCombox(HttpServletRequest request, PrintWriter printWriter,String midwareType) {
		try {
			   List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(midwareType);
				List<EcmMidwareInfo> midwareInfoList = magicDefaultList(infoList);
				List<PkList> pkLists = new ArrayList<>();
				for (int i = 0; i < midwareInfoList.size(); i++) {
					PkList pkList = new PkList();
					pkList.setPkDesc(midwareInfoList.get(i).getMidwareName());
					pkList.setPkValue(midwareInfoList.get(i).getMidwareId().toString());
					//pkList.setSelected(true);
					pkLists.add(pkList);
				}
				printWriter.print(JSON.toJSONString(pkLists));
				printWriter.flush();
				printWriter.close();
		}catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter, "请先添加zookeeper集群！");
		}
	}
	//查找中间件集群是否已经有设置成默认
	@RequestMapping("checkIsHaveDefaultCombox")
	public void checkIsHaveDefaultCombox(HttpServletRequest request, PrintWriter printWriter,String midwareType) {
		try {
			Boolean  flag  = false;
			List<EcmMidwareInfo> infoList = middlewareInfoService.findMidwareList(midwareType);
			List<EcmMidwareInfo> midwareInfoList = magicDefaultList(infoList);
			for (int i = 0; i < midwareInfoList.size(); i++) {
                  if(midwareInfoList.get(i).getIsDefault().equals(SysConfigConstants.IS_DEFAULT)){
					  flag =true;
					  break;
				  }
			}
			if (flag ==true){
				ActionResultWrite.setErrorResult(printWriter, "已经有默认的集群存在，不能再将该集群设成默认！");
			}else{
				ActionResultWrite.setsuccessfulResult(printWriter);
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private List<EcmMidwareInfo> magicDefaultList(List<EcmMidwareInfo> list) {
		for (EcmMidwareInfo midwareInfo : list) {
			if(midwareInfo.getIsDefault().equals(SysConfigConstants.IS_DEFAULT)){
				int index = list.indexOf(midwareInfo);
				EcmMidwareInfo midwareInfoTmp =  list.get(0);
				list.set(0,midwareInfo);
				list.set(index, midwareInfoTmp);
			}
		}
		return list;
	}
	
	//判断该服务器是否已经部署集群 true 未部署  false 已部署  ,分别相应中间件的实例
	private boolean isAllMidwareIsClean(EcmMidwareInfo midware){
		if(midware.getMidwareType().equals(SysConfigConstants.REDIS_MIDWARE)) {
			Map<String ,Object> redisMap =new HashMap<String ,Object>();
			redisMap.put("midwareId", midware.getMidwareId());
			List<EcmMidwareRedisint> midwareInt =omsBaseService.findListByCond(new EcmMidwareRedisint(),"findDepList",redisMap);
			if(midwareInt.size()==0){
		    	return true;
		    }else {
				for(EcmMidwareRedisint  temp :midwareInt){
					if(!temp.getRedisintStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
						return  false;
					}
				}
			}
    	return true;
		}
		if(midware.getMidwareType().equals(SysConfigConstants.ZOOKEEPER_MIDWARE)) {
			Map<String ,Object> zkMap =new HashMap<String ,Object>();
			zkMap.put("midwareId",midware.getMidwareId());
			List<EcmMidwareZkint> midwareInt =omsBaseService.findListByCond(new EcmMidwareZkint(),"findByMid",zkMap);
			if(midwareInt.size()!=0){
			    	return false;
			}
	    	return true;
		}
		
		if(midware.getMidwareType().equals(SysConfigConstants.DB_MIDWARE)) {
			Map<String ,Object> dbMap =new HashMap<String ,Object>();
			dbMap.put("midwareId",midware.getMidwareId());
			List<EcmMidwareDbint> midwareInt =omsBaseService.findListByCond(new EcmMidwareDbint(),"findByMidwareId",dbMap);
			if(midwareInt.size()!=0){
		    	return false;
		   }
    	   return true;
	   }
		if(midware.getMidwareType().equals(SysConfigConstants.KAFKA_MIDWARE)) {
			Map<String ,Object> dbMap =new HashMap<String ,Object>();
			dbMap.put("midwareId",midware.getMidwareId());
			List<EcmMidwareKafkaint> midwareInt =omsBaseService.findListByCond(new EcmMidwareKafkaint(),"findList",dbMap);
			if(midwareInt.size()!=0){
				return false;
			}
			return true;
		}
		   return false;
    }
    //判断是否修改了类型和路径       false 未修改 ；true  修改  
	private boolean isUpdateMidwareInfo(EcmMidwareInfo midware){
		EcmMidwareInfo oldMidware=omsBaseService.selectByPrimaryKey(midware);
		if(!midware.getMidwareType().equals(oldMidware.getMidwareType())){
			return true;
		}
		if(!midware.getMidwarePath().equals(oldMidware.getMidwarePath())){
			return true;
		}
		return false;
  }	
}
