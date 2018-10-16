package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.app.EcmAppIntant;
import com.dcits.ensemble.om.oms.module.utils.PageData;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EcmAppIntantConfigAction* 
 * @author LuoLang
 * @date 2015-09-1
 */
@Controller
public class EcmAppIntantConfigAction {
	
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	IService  omsBaseService;
	@Resource
	ParamComboxService  paramComboxService;
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	Map<Integer, String> model = new HashMap<Integer, String>();
	
	void setMap() {
		model.put(0, "一");
	    model.put(1, "二");
	    model.put(2, "三");
	    model.put(3, "四");
	    model.put(4, "五");
	    model.put(5, "六");
	    model.put(6, "七");
	    model.put(7, "八");
	    model.put(8, "九");
	    model.put(9, "十");
	}
	
	private String getAppChNumber(int number) {
		if(model.isEmpty()){
			setMap();
		}
		return model.get(number);  
	}
	
	@RequestMapping("autoGetAppNumber")
	public void autoGet(HttpServletRequest request, PrintWriter printWriter) {
		try {
			Map<String,String> number = new HashMap<String,String>();
			List<EcmAppIntant> infoList = omsBaseService.findListByCond(new EcmAppIntant(),
					"findCount", new HashMap<String, Object>());

			for(EcmAppIntant intant:infoList) {
				number.put(""+intant.getAppId(), getAppChNumber(intant.getAppNumber()));
			}
			number.put("" + 0, getAppChNumber(0));
			number.put("success", "success");
			ActionResultWrite.setReadResult(printWriter, number);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
		
	@RequestMapping("saveEcmAppIntantConfig")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		try {
			int appIntantId=serviceUtil.getMaxReqID(0,"ecm_app_intant","app_intant_id"); //获取当前列表数据最大ID
			appIntant.setAppIntantStatus(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY);//实例状态初始化
			appIntant.setAppIntantId(appIntantId);
			omsBaseService.insert(appIntant);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("updateEcmAppIntantConfig")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		try {
			if(isUpdateIntantSerIp(appIntant)){
				if(isAppIntantIsDeploy(appIntant)){
					omsBaseService.updateByPrimaryKey(appIntant);
				}else{
					throw new GalaxyException("该实例已经部署，禁止修改该实例服务器信息!");
				}
			} else {
				omsBaseService.updateByPrimaryKey(appIntant);
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("deleteEcmAppIntantConfig")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmAppIntant appIntant) {
		try {
			if(isAppIntantIsDeploy(appIntant)) {
				omsBaseService.deleteByPrimaryKey(appIntant);
			}else {
				ActionResultWrite.setErrorResult(printWriter, "该实例已经部署，禁止删除该实例信息！");
				throw new GalaxyException("该实例已经部署，禁止删除该实例信息！");
			}
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}

	}
	


	@RequestMapping("findEcmAppIntantConfig")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmAppIntant appIntant) {
		try {
			//System.out.println("hello world!");
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			log.info("pageNo ="+pageNo +"pageSize ="+ pageSize);
			Map<String,Object> appIntantConfig = new HashMap<String,Object>();
			appIntantConfig.put("appId",appIntant.getAppId());
			appIntantConfig.put("serId",appIntant.getSerId());
			PageData<EcmAppIntant> pageData =omsBaseService.findPageByCond(appIntant,"findIntantList",pageNo,pageSize,appIntantConfig);
			List<EcmAppIntant> ecmAppIntant=pageData.getList();
//		ActionResultWrite.setPageReadResult(printWriter,pageData.getList(),pageData.getTotalNum());
			ActionResultWrite.setReadResult(printWriter,ecmAppIntant);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }
	//判断实例是否已经部署。
	private boolean isAppIntantIsDeploy(EcmAppIntant  intant){		
		EcmAppIntant  intantTmp=omsBaseService.selectByPrimaryKey(intant);     	
    		if(!intantTmp.getAppIntantStatus().equals(SysConfigConstants.APP_INTANTSTATUS_NODEPLOY)){
    				return false;
    		}
    	return true;
	}
	
	//判断是否修改了实例服务器 修改  false 未修改
		private boolean isUpdateIntantSerIp(EcmAppIntant  intant){		
			EcmAppIntant  oldIntant= omsBaseService.selectByPrimaryKey(intant);
			if(intant.getSerId()!=(oldIntant.getSerId())){
				return true;
			}
			return false;
	}
}