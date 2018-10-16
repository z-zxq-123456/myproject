package com.dcits.ensemble.om.oms.common;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 文件上传插件* 
 * @author tangxlf
 * @date 2015-08-17
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {
	
	private  final Logger log = LoggerFactory.getLogger(this.getClass());
	//private  HttpServletRequest request;
	
	  public CustomMultipartResolver() {}
	  
	  public CustomMultipartResolver(ServletContext servletContext)
	  { 
		  super(servletContext);	   
	      setServletContext(servletContext);
	  }
	  
//	  protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
//		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
//		upload.setSizeMax(-1);
//		log.info("CustomMultipartResolver newFileUpload  request="+request);
//		if(request != null){
//			HttpSession session = request.getSession();
//			FileUploadProgressListener listener = new FileUploadProgressListener(session);
//			upload.setProgressListener(listener);
//		}
//	    return upload;
//	}
//	
//	public MultipartHttpServletRequest resolveMultipart(final HttpServletRequest request) {
//		    Assert.notNull(request, "Request must not be null");
//		    //MultipartHttpServletRequest multRequest = null;
//		   // try{
//		    log.info("CustomMultipartResolver resolveMultipart request="+request);
//		    this.request = request;
//		    return super.resolveMultipart(request);
//		   // }catch(Exception ex){
//		    //	setFail(request,ex);
//		    //	multRequest = new DefaultMultipartHttpServletRequest(request);
//		    //}
//		   // return multRequest;
//	}
	
	
	 protected MultipartParsingResult parseRequest(HttpServletRequest request)
			    throws MultipartException
			  {
			    String encoding = determineEncoding(request);
			    FileUpload fileUpload = prepareFileUpload(encoding);
			    if(request != null){
					HttpSession session = request.getSession();
					log.info("session ="+session  );					
					FileUploadProgressListener listener = new FileUploadProgressListener(session);
					fileUpload.setProgressListener(listener);
				}
			    try
			    {
			      List<FileItem> fileItems = ((ServletFileUpload)fileUpload).parseRequest(request);
			      return parseFileItems(fileItems, encoding);
			    }
			    catch (FileUploadBase.SizeLimitExceededException ex)
			    {
			      throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
			    }
			    catch (FileUploadException ex)
			    {
			      throw new MultipartException("Could not parse multipart servlet request", ex);
			    }
			  }
}
