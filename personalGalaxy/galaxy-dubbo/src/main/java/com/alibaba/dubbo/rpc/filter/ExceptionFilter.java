/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.rpc.filter;

import java.lang.reflect.Method;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * ExceptionInvokerFilter
 * <p>
 * 功能：
 * <ol>
 * <li>不期望的异常打ERROR日志（Provider端）<br>
 *     不期望的日志即是，没有的接口上声明的Unchecked异常。
 * <li>异常不在API包中，则Wrap一层RuntimeException。<br>
 *     RPC对于第一层异常会直接序列化传输(Cause异常会String化)，避免异常在Client出不能反序列化问题。
 * </ol>
 * 
 * @author william.liangf
 * @author ding.lid
 */
@Activate(group = Constants.PROVIDER)
public class ExceptionFilter implements Filter {

    private final Logger logger;
    
    public ExceptionFilter() {
        this(LoggerFactory.getLogger(ExceptionFilter.class));
    }
    
    public ExceptionFilter(Logger logger) {
        this.logger = logger;
    }
    
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    	 try {
			   com.alibaba.dubbo.rpc.Result result = invoker.invoke(invocation);
			   if (result.hasException() && com.alibaba.dubbo.rpc.service.GenericService.class != invoker.getInterface()) {
			     try {
			       Throwable exception = result.getException();
//			       // 如果平台异常，直接抛出
			       if (exception instanceof com.dcits.galaxy.base.exception.GalaxyException ) {
			         return result;
			       }
			       if (! (exception instanceof RuntimeException) && (exception instanceof Exception)) {
			         return result;
			       }
			       try {
			         java.lang.reflect.Method method = invoker.getInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
			         Class[] exceptionClassses = method.getExceptionTypes();
//			         for (Class exceptionClass : exceptionClassses) {
			         if ( exceptionClassses != null ){
			           for( int i=0 ; i < exceptionClassses.length; i++) {
			             Class exceptionClass = exceptionClassses[i];
			             if ( exception.getClass().equals(exceptionClass) ) {
			               return result;
			             }
			           }
			         }
			       } catch (NoSuchMethodException e) {
			         return result;
			       }
			       logger.error("Got unchecked and undeclared exception which called by "
			         + com.alibaba.dubbo.rpc.RpcContext.getContext().getRemoteHost()
			         + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
			         + ", exception: " + exception.getClass().getName() + ": " + exception.getMessage(), exception);
			       String serviceFile = com.alibaba.dubbo.common.utils.ReflectUtils.getCodeBase(invoker.getInterface());
			       String exceptionFile = com.alibaba.dubbo.common.utils.ReflectUtils.getCodeBase(exception.getClass());
			       if (serviceFile == null || exceptionFile == null || serviceFile.equals(exceptionFile)){
			         return result;
			       }
			       String className = exception.getClass().getName();
			       if (className.startsWith("java.") || className.startsWith("javax.")) {
			         return result;
			       }
//			       // 是Dubbo本身的异常，直接抛出
			       if (exception instanceof com.alibaba.dubbo.rpc.RpcException) {
			         return result;
			       }
//			       // 否则，包装成RuntimeException抛给客户端
			       return new com.alibaba.dubbo.rpc.RpcResult(new RuntimeException(
			          com.alibaba.dubbo.common.utils.StringUtils.toString(exception)));
			     } catch (Throwable e) {
			       logger.warn("Fail to ExceptionFilter when called by " + com.alibaba.dubbo.rpc.RpcContext.getContext().getRemoteHost()
			         + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
			         + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
			       return result;
			     }
			   }
			   return result;
			 } catch (RuntimeException e) {
			    logger.debug("Got unchecked and undeclared exception which called by " + com.alibaba.dubbo.rpc.RpcContext.getContext().getRemoteHost()
			      + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName()
			      + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
			    throw e;
			 }
    }

}