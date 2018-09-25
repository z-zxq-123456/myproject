package com.dcits.galaxy.dtp.annotation;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.dcits.galaxy.core.spring.SpringApplicationContext;
import com.dcits.galaxy.dal.mybatis.idempotent.IdempotentContext;
import com.dcits.galaxy.dtp.DtpContext;
import com.dcits.galaxy.dtp.defaults.log.DefaultSubmitLog;
import com.dcits.galaxy.dtp.defaults.log.LogManagerHelper;
import com.dcits.galaxy.dtp.defaults.log.ServiceInfo;
import com.dcits.galaxy.dtp.defaults.log.ServiceType;
import com.dcits.galaxy.dtp.exception.DTPException;
import com.dcits.galaxy.dtp.log.SubmitLog;
import com.dcits.galaxy.dtp.sequence.LogIdSequence;
import com.dcits.galaxy.dtp.trunk.TrunkStatus;

@Aspect
public class SubmitLogAspect implements InitializingBean {

	private static Logger logger = LoggerFactory.getLogger(SubmitLogAspect.class);

	private LogIdSequence logIdSequence = null;

	private PlatformTransactionManager tm = null;
	private TransactionDefinition definition = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
	
	private Map<Method, ServiceType> serviceTypeMap = new ConcurrentHashMap<>();
	
	private ThreadLocal<Boolean> processing = new ThreadLocal<>();
	
	@Around("execution(@com.dcits.galaxy.dtp.annotation.SubmitLog public * *(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {

		if (!DtpContext.isInDtp())
			return jp.proceed();
		
		Boolean inLog = processing.get();
		
		if(Boolean.TRUE.equals(inLog))
			return jp.proceed();
		
		processing.set(Boolean.TRUE);
		
		try {
			TrunkStatus status = DtpContext.getStatus();
			
			if (TrunkStatus.prepare.equals(status)) {
				return handlePrepare(jp);
			} else if (TrunkStatus.confirm.equals(status)){
				return handleConfirm(jp);
			}
			
			throw new DTPException("can't execute submitLog when DTP status is:" + status.name());
		} finally {
			processing.remove();
		}
		
	}

	/**
	 * 对于prepare阶段的方法进行处理：
	 * 先新建submitLog，再尝试执行方法，如果执行成功，则写入submitLog
	 * 如果已在submitLog方法中，不再新开submitLog，也即submitLog不允许嵌套
	 * 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	private Object handlePrepare(ProceedingJoinPoint jp) throws Throwable {
		
		if(DtpContext.getLogid() != null) {
			return jp.proceed();
		}
		
		SubmitLog log = createSubmitLog(jp);

		DtpContext.setLogid(log.getLogId());
		// 设置幂等上下文，防止日志重复执行
		IdempotentContext.setIdempotentObj(log.getLogId());
		
		Object result = null;
		try {
			result = jp.proceed();
		} finally {
			DtpContext.setLogid(null);
			IdempotentContext.clear();
		}
		logger.debug("write submit log:{}", log.getLogId());
		LogManagerHelper.writeLog(log);
		
		return result;
	}

	private Object handleConfirm(ProceedingJoinPoint jp) throws Throwable {
		String logid = DtpContext.getLogid();
		
		if(logid == null) {
			throw new DTPException("can't get logid from context!");
		}
		
		PlatformTransactionManager tm = getTransactionManager();
		TransactionStatus transactionStatus = tm.getTransaction(definition);
		
		// 设置幂等上下文，防止日志重复执行
		IdempotentContext.setIdempotentObj(logid);
		

		Object result = null;
		try {
			result = jp.proceed();
			tm.commit(transactionStatus);
		} catch(Exception e){
			try {
				tm.rollback(transactionStatus);
			} catch (Exception e2) {
				logger.info("caught exception when rollback transaction, ignore it. cause: {}", e.getMessage());
			}
			throw e;
		}
		return result;
	}

	private SubmitLog createSubmitLog(JoinPoint jp) {
		MethodSignature signature = (MethodSignature) jp.getSignature();

		String serviceClass = jp.getTarget().getClass().getName();
		String methodName = signature.getName();
		Object[] args = jp.getArgs();
		Class<?>[] argsType = signature.getParameterTypes();

		ServiceInfo serviceInfo = new ServiceInfo();
		serviceInfo.setMethodName(methodName);
		serviceInfo.setArgsType(argsType);
		serviceInfo.setArgs(args);
		serviceInfo.setServiceClass(serviceClass);
		ServiceType serviceType = serviceTypeMap.get(signature.getMethod());
		if(serviceType == null) {
			Method method = signature.getMethod();
			serviceType = method.getAnnotation(com.dcits.galaxy.dtp.annotation.SubmitLog.class).value();
			serviceTypeMap.put(method, serviceType);
		}
		serviceInfo.setServiceType(serviceType);

		SubmitLog submitLog = new DefaultSubmitLog();

		LogIdSequence logIdSequence = getLogIdSequence();
		String logid = logIdSequence.nextLogid();
		Integer logIndex = DtpContext.nextLogIndex();

		submitLog.setLogId(logid);
		submitLog.setTxid(DtpContext.getTxid());
		submitLog.setBxid(DtpContext.getBxid());

		submitLog.setLogIndex(logIndex);
		submitLog.setContent(serviceInfo);

		return submitLog;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (logIdSequence == null)
			throw new DTPException("LogIdSequence must not null!");
	}

	public void setLogIdSequence(LogIdSequence logIdSequence) {
		this.logIdSequence = logIdSequence;
	}

	private LogIdSequence getLogIdSequence() {
		if (logIdSequence == null) {
			throw new DTPException("can't get instance of logIdSequence. ");
		}

		return logIdSequence;
	}
	
	private PlatformTransactionManager getTransactionManager(){
		if (tm != null)
			return tm;

		try {
			tm = SpringApplicationContext.getContext().getBean(PlatformTransactionManager.class);
		} catch (Exception e) {
			throw new DTPException("occur exception when get instance of PlatformTransactionManager, cause: ", e);
		}
		
		return tm;
	}
	
	public void setTransactionManager(PlatformTransactionManager tm) {
		this.tm = tm;
	}
}
