package com.dcits.ensemble.om.oms.service.middleware;

import com.dcits.galaxy.base.util.DateUtils;
import com.dcits.ensemble.om.oms.common.CommonPathHelp;
import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import com.dcits.ensemble.om.oms.module.middleware.EcmMidwareVer;
import com.dcits.ensemble.om.oms.module.utils.PageData;
import com.dcits.ensemble.om.oms.service.sys.ParamComboxService;
import com.dcits.dynamic.web.util.dao.PkServiceUtil;
import com.dcits.ensemble.om.oms.service.utils.FileOperService;
import com.dcits.ensemble.om.oms.service.utils.IService;
import com.dcits.galaxy.base.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * EcmAppInfoAction* 
 * @author LuoLang
 * @createdate 2016-2-18
 */
@Service
public class MiddlewareVerService {	
	@Resource
	IService omsBaseService;
	@Resource
	PkServiceUtil  serviceUtil;
	@Resource
	FileOperService fileOperService;
	@Resource
	ParamComboxService paramComboxService;
	
	private int max_midware_ver_id = 0;//最大中间件版本ID
	
	
	/**
     * 应用版本的上传及记录保存
     * @param EcmMidwareVer  midwarVer       中间件版本对象	
     * @param int    userId           用户ID 		     
     */  
	public void save(EcmMidwareVer  midwarVer,String userId ) {
		int midwareVerId = serviceUtil.getMaxReqID(max_midware_ver_id,"ECM_MIDWARE_VER","MIDWARE_VER_ID");		
		midwarVer.setMidwareVerId(midwareVerId);
		midwarVer.setMidwareVerUserid(userId);
		midwarVer.setMidwareVerDate(DateUtils.getDate());
		midwarVerSave(midwarVer);
		omsBaseService.insert(midwarVer);	
	}
    //中间价版本上传
	private void midwarVerSave(EcmMidwareVer midwarVer ) {
		String fileUrl=createFileUrl(midwarVer);
	    fileOperService.singleFileUpload(midwarVer.getSourceFile(),CommonPathHelp.createTotalPath(fileUrl,paramComboxService));
		midwarVer.setMidwareVerPath(fileUrl);
	}
	 /**
		 * 生成中间件文件路径
		 * @param  EcmMidwareVer  midwarVer   中间件对象
		 * @return String  中间件文件路径
	     */	
	private String createFileUrl(EcmMidwareVer midwarVer) {
        String saveUrl = paramComboxService.getParaRemark1(SysConfigConstants.MIDDLEWARE_VER_SAVE_URL);
        if (StringUtils.isNotEmpty(saveUrl))
            System.out.print("saveUrl:" + saveUrl);
        saveUrl = CommonPathHelp.handlePath(saveUrl);//如果最后已"/"结束,去掉
        if (StringUtils.isNotEmpty(saveUrl))
            System.out.print("saveUrl:" + saveUrl);
        String midwarTypeName = paramComboxService.getParaName(midwarVer.getMidwareType());
        if (StringUtils.isNotEmpty(midwarTypeName))
            System.out.print("midwarTypeName:" + midwarTypeName);
        if(midwarVer.getSourceFile()!=null) {
            System.out.print(saveUrl + "/" + midwarTypeName + "/" +
                    midwarVer.getMidwareVerNo() + "/" + midwarVer.getSourceFile().getOriginalFilename());
            return saveUrl + "/" + midwarTypeName + "/" +
                    midwarVer.getMidwareVerNo() + "/" + midwarVer.getSourceFile().getOriginalFilename();
        }
        else
        {
            System.out.print(saveUrl + "/" + midwarTypeName + "/" +
                    midwarVer.getMidwareVerNo() );
            return saveUrl + "/" + midwarTypeName + "/" +
                    midwarVer.getMidwareVerNo() ;
        }
    }
	
	/**
	 * 从版本库里面删除中间件版本
	 * @param  EcmMidwareVer  midwarVer   中间件版本对象
     */	
	public void delete(EcmMidwareVer midwarVer) {
		 midwarVer=omsBaseService.selectByPrimaryKey(midwarVer);
		 fileOperService.deleteFile(CommonPathHelp.createTotalPath(midwarVer.getMidwareVerPath(),paramComboxService));		
		 omsBaseService.deleteByPrimaryKey(midwarVer);
    }
	/**
	 * 从数据库获取中间件版本信息，并分页处理
	 * @param   int pageNo  页号
	 * @param   int pageSize  页面大小
	 * @param   EcmMidwareVer  midwarVer  中间件版本对象
	 * @return  以pagaData模板封装的中间件版本信息
     */	
	public PageData<EcmMidwareVer> findList( int pageNo,int pageSize,EcmMidwareVer midwarVer) {
        PageData<EcmMidwareVer> pageData =omsBaseService.findPageByCond(midwarVer,pageNo,pageSize);
        magicList(pageData.getList());
        return pageData;
	}
	private List<EcmMidwareVer> magicList(List<EcmMidwareVer> list){
		for(EcmMidwareVer para:list){
			para.setMidwareTypeName(paramComboxService.getParaName(para.getMidwareType()));
			para.setMidwareVerPath(CommonPathHelp.createTotalPath(para.getMidwareVerPath(),paramComboxService));
		}
		return list;
	}
	
}