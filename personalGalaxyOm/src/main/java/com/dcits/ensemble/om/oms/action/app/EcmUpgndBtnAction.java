package com.dcits.ensemble.om.oms.action.app;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.alibaba.fastjson.JSON;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.manager.app.UpgNodeCache;
import com.dcits.ensemble.om.oms.module.app.EcmUpgflowNode;
import com.dcits.ensemble.om.oms.module.app.EcmUpgndBtn;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;

/**
 * EcmUpgndBtnAction* 
 * @author LuoLang
 * @createdate 2016-02-29
 */

@Controller
public class EcmUpgndBtnAction {
	
	private int max_req_no = 0;
	
    @Resource
    PkServiceUtil  serviceUtil;
    
    @Resource
	ParamComboxService  paramComboxService;
    
    @Resource
    IService  omsBaseService;
    
	@Resource
	UpgNodeCache upgNodeCache;
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveEcmUpgndBtn")
    public void  save(HttpServletRequest request, PrintWriter printWriter,  EcmUpgndBtn  upgndBtn)  {
		try {
			int upgndBtnId=serviceUtil.getMaxReqID(max_req_no,"ECM_UPGND_BTN","UPGND_BTN_ID");
			upgndBtn.setUpgndBtnId(upgndBtnId);
			omsBaseService.insert(upgndBtn);
			//ActionResultWrite.setsuccessfulResult(printWriter);
			HashMap result = new HashMap();
			result.put("success", "success");
			result.put("id", upgndBtnId);
			String jsonStr = JSON.toJSONString(result);
			printWriter.print(jsonStr);
			printWriter.flush();
			printWriter.close();
			upgNodeCache.removeNodeMap();
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }
    
    @RequestMapping("updateEcmUpgndBtn")
	public void update(HttpServletRequest request, PrintWriter printWriter, EcmUpgndBtn  upgndBtn) {
    	log.info("" + upgndBtn);
		try {
			omsBaseService.updateByPrimaryKey(upgndBtn);
			ActionResultWrite.setsuccessfulResult(printWriter);
			upgNodeCache.removeNodeMap();
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }		  
    
    @RequestMapping("deleteEcmUpgndBtn")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmUpgndBtn  upgndBtn) {
		try {
			omsBaseService.deleteByPrimaryKey(upgndBtn);
			ActionResultWrite.setsuccessfulResult(printWriter);
			upgNodeCache.removeNodeMap();
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    @RequestMapping("findEcmUpgndBtn")
    public void find(HttpServletRequest request, PrintWriter printWriter ,EcmUpgndBtn  upgndBtn) {
    	log.info("" + upgndBtn);
		try {
			List<EcmUpgndBtn> dataList =omsBaseService.findListByCond(upgndBtn);
			ActionResultWrite.setReadResult(printWriter,magicList(dataList));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    private List<EcmUpgndBtn> magicList(List<EcmUpgndBtn> list){
		for(EcmUpgndBtn  upgndBtn:list){
			EcmUpgflowNode upgflowNode1 = new EcmUpgflowNode();
			upgflowNode1.setUpgflowNodeId(upgndBtn.getUpgndBtnDirnodeid());
			EcmUpgflowNode upgflowNode2 = omsBaseService.selectByPrimaryKey(upgflowNode1);
			upgndBtn.setUpgndBtnDirnodename(upgflowNode2.getUpgflowNodeName());
			upgndBtn.setUpgndBtnIsviewName(paramComboxService.getParaName(upgndBtn.getUpgndBtnIsview()));
		}
		return list;
	}
}

