package com.dcits.ensemble.om.oms.action.middleware;

import com.dcits.dynamic.web.util.action.ActionResultWrite;

import com.dcits.ensemble.om.oms.manager.db.DBSqlResultInfo;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.ensemble.om.oms.action.utils.OMSPassWordBase64;
import com.dcits.ensemble.om.oms.manager.db.DBContainer;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareDbint;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.middleware.MiddlewareDBService;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * DbIntantInfoAction* 
 * @author WangbinAf
 * @date 2016-02-23
 */
@Controller
public class DbIntantInfoAction {
	
	
	
	
	@Resource
	IService omsBaseService;
	@Resource
	ParamComboxService paramComboxService;
	@Resource
	DBContainer dbContainer;
	@Resource
	MiddlewareDBService middlewareDBService;
	
	@RequestMapping("saveDbIntantInfo")
	public void save(HttpServletRequest request, PrintWriter printWriter,EcmMidwareDbint midwareDbint) {
		try {
			middlewareDBService.save(midwareDbint);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	@RequestMapping("updateDbIntantInfo")
	public void update(HttpServletRequest request, PrintWriter printWriter,EcmMidwareDbint midwareDbint) {
		try {
			middlewareDBService.update(midwareDbint);
			ActionResultWrite.setsuccessfulResult(printWriter);
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
	}
	
	
	@RequestMapping("deleteDbIntantInfo")
	public void delete(HttpServletRequest request, PrintWriter printWriter,EcmMidwareDbint midwareDbint) {
		   try {
			   middlewareDBService.delete(midwareDbint);
			   ActionResultWrite.setsuccessfulResult(printWriter);
		   }catch (Exception e){
			   e.printStackTrace();
			   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		   }
	}
	
	@RequestMapping("findDbIntantInfo")
	public void find(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareDbint midwareDbint) {
		try {
			int pageNo = ServletRequestUtils.getIntParameter(request, "page", 1);
			int pageSize = ServletRequestUtils.getIntParameter(request, "rows",10);
			PageData<EcmMidwareDbint> pageData =omsBaseService.findPageByCond(midwareDbint,pageNo,pageSize);
			ActionResultWrite.setPageReadResult(printWriter, magicList(pageData.getList()), pageData.getTotalNum());
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }
	@RequestMapping("findDbIntByMidwareId")
	public void findDbIntByMidwareId(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareDbint midwareDbint) {
		try {
			//ActionResultWrite.setReadResult(printWriter,middlewareDBService.monitorDbInt(midwareDbint));
			ActionResultWrite.setReadResult(printWriter, middlewareDBService.monitorDbInt(midwareDbint));
		}catch (Exception e){
			e.printStackTrace();
			ActionResultWrite.setErrorResult(printWriter,e.getMessage());
		}
    }
	@RequestMapping("queryDbInt")
	public void queryDbInt(HttpServletRequest request, PrintWriter printWriter ,EcmMidwareDbint midwareDbint,String sql) {
       try {
		   middlewareDBService.queryDbInt(midwareDbint,sql).toString();
		   ActionResultWrite.setReadResult(printWriter, middlewareDBService.queryDbInt(midwareDbint, sql));
	   }catch (Exception e){
		   e.printStackTrace();
		   ActionResultWrite.setErrorResult(printWriter,e.getMessage());
	   }
    }
    private List<EcmMidwareDbint> magicList(List<EcmMidwareDbint> list) {
		for (EcmMidwareDbint midwareDbint : list) {
			midwareDbint.setDbTypeName(paramComboxService.getParaName(midwareDbint.getDbType()));
			if (!midwareDbint.getDbintUserPwd().equals("")){
				try {
					String str = new String(OMSPassWordBase64.decode(midwareDbint.getDbintUserPwd()), "iso8859-1");
					midwareDbint.setDbintUserPwd(str);
				} catch (UnsupportedEncodingException e) {
					throw new GalaxyException(e.getMessage());
				}
			}
		}
		return list;
	}

}