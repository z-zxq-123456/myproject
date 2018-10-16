package com.dcits.ensemble.om.oms.common;

import com.dcits.ensemble.om.oms.constants.SysConfigConstants;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;


/**
 * 文件上传进度监听器* 
 * @author tangxlf
 * @date 2015-08-17
 */
public class FileUploadProgressListener implements ProgressListener{
	//private  final Logger log = LoggerFactory.getLogger(this.getClass());
	
    private HttpSession session;
    
   
	public FileUploadProgressListener(HttpSession session){
		this.session = session;
	}
	@Override
	public void update(long arg0, long arg1, int arg2) {
		//log.info("FileUploadProgressListener SysConfigConstants.progressName="+arg0+" session="+session);
		    
			session.setAttribute(SysConfigConstants.progressName,Math.round(arg0/1024) );
			session.setAttribute(SysConfigConstants.fileTotalSize,Math.round( arg1/1024));
		
	}
}
