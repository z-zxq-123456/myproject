package com.dcits.ensemble.om.oms.action.shshpoc;

import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import com.dcits.ensemble.om.oms.action.utils.ActionResultWrite;
import com.dcits.dynamic.web.util.action.ActionResultWrite;
import com.dcits.ensemble.om.oms.module.shshpoc.EcmAppmoniIndex;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.CommonServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.IService;


/**
 * EcmAppmoniIndexAction* 
 * @author tangxlf
 * @createdate 2016-07-27
 */

@Controller
public class EcmAppmoniIndexAction {
	
	private int max_req_no = 0;

	@Resource
	PkServiceUtil serviceUtil;
    
    @Resource
	ParamComboxService  paramComboxService;
    
    @Resource
    IService  omsBaseService;
    
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("saveAppmoniIndex")
    public void  save(HttpServletRequest request, PrintWriter printWriter,  EcmAppmoniIndex moniIndex)  {
		try {
			int moniIndexId=serviceUtil.getMaxReqID(max_req_no,"ECM_APPMONI_INDEX","APP_INDEX_ID");
			moniIndex.setAppIndexId(moniIndexId);
			omsBaseService.insert(moniIndex);
			ActionResultWrite.setsuccessfulResult(printWriter);
		} catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	}
    }
    
    @RequestMapping("updateAppmoniIndex")
	public void update(HttpServletRequest request, PrintWriter printWriter, EcmAppmoniIndex moniIndex) {
		try {
			log.info(""+moniIndex);
			omsBaseService.updateByPrimaryKey(moniIndex);
			ActionResultWrite.setsuccessfulResult(printWriter);
		} catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }		  
    
    @RequestMapping("deleteAppmoniIndex")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmAppmoniIndex moniIndex) {
		try {
			omsBaseService.deleteByPrimaryKey(moniIndex);
			ActionResultWrite.setsuccessfulResult(printWriter);
		} catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    @RequestMapping("findAppmoniIndex")
    public void find(HttpServletRequest request, PrintWriter printWriter ,EcmAppmoniIndex moniIndex) {
		try {
				log.info("" + moniIndex);
				List<EcmAppmoniIndex> dataList =omsBaseService.findListByCond(moniIndex);
				ActionResultWrite.setReadResult(printWriter,magicList(dataList));
		 } catch (Exception e) {
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
    private List<EcmAppmoniIndex> magicList(List<EcmAppmoniIndex> list){
		for(EcmAppmoniIndex moniIndex:list){		
			moniIndex.setAppIndexIsviewName(paramComboxService.getParaName(moniIndex.getAppIndexIsview()));
		}
		return list;
	}
}

