package com.dcits.ensemble.om.oms.action.interceptor;

import com.dcits.dynamic.web.util.action.ActionResultWrite;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class OmsExceptionInterceptor implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//System.out.println("step into OmsExceptionInterceptor 1");
		try {
			 if(ex instanceof MultipartException){
				 return fileUploadErrorHandle(request,response,handler,ex);
			 }
			  PrintWriter printWriter=response.getWriter();
			//System.out.println("step into OmsExceptionInterceptor 2");
			  ActionResultWrite.setErrorResult(printWriter,ex.getMessage());
			  
			//System.out.println("step into OmsExceptionInterceptor 3");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private ModelAndView  fileUploadErrorHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("errInfo",ex.getMessage());
		return new ModelAndView("/help/error",result);
	}

}
