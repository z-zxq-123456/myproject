package com.dcits.galaxy.base.extension;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dcits.galaxy.base.data.BeanResult;
import com.dcits.galaxy.base.data.Result;
import com.dcits.galaxy.base.data.Results;
import com.dcits.galaxy.base.exception.BusinessException;
import com.dcits.galaxy.base.exception.MultiBusinessException;
import com.dcits.galaxy.base.exception.WithoutAuthAndConfirmException;
import com.dcits.galaxy.base.exception.WithoutAuthorizationException;
import com.dcits.galaxy.base.exception.WithoutConfirmationException;

public class MultiExceptionTest {
	protected static final Logger logger = LoggerFactory.getLogger(MultiExceptionTest.class);
	public static void main(String[] args) {
	 MultiExceptionTest test =new MultiExceptionTest();
	 MultiBusinessException ex = new MultiBusinessException();
//	 try {
//		test.exceptionMthod1();
//	} catch (BusinessException e) {
//		ex.addCause(e);
////		throw new MultiBusinessException(e);
//	}
	 try {
		 test.exceptionMethod2();
		
	} catch (WithoutAuthAndConfirmException e) {
//		ex = new MultiBusinessException(e);
		ex.addCause(e);
//		throw new MultiBusinessException();
	}	 
	 try {
		test.exceptionMethod3();
	} catch (WithoutAuthorizationException e) {
//		ex = new MultiBusinessException(e);
		ex.addCause(e);
//		throw new MultiBusinessException("sssssssssssss");
	}
	 
	 try {
		test.exceptionMethod4();
	} catch (WithoutConfirmationException e) {
//		ex = new MultiBusinessException(e);
		ex.addCause(e);
	}
//	 try {
//		test.exceptionMthod5();
//	} catch (BusinessException e) {
//		ex.addCause(e);
//	}
	 
//	 try {
//		test.exceptionMthod6();
//	} catch (BusinessException e) {
//		 ex.addCause(e);
//	}
	 
	 try {
		test.exceptionMethod7();
	} catch (WithoutAuthAndConfirmException e) {
		ex.addCause(e);
	}
	 
	 try {
		test.exceptionMethod8();
	} catch (WithoutAuthAndConfirmException e) {
		ex.addCause(e);
	}
	 try {
		test.exceptionMethod9();
	} catch (BusinessException e) {
		ex.addCause(e);
	}
	 
//	 try {
//		test.exceptionMethod10();
//	} catch (BusinessException e) {
//		ex.addCause(e);
//	}
	

//	 System.out.println(ex.getRet().toString());
	 BeanResult result = new BeanResult(ex);
	 System.out.println(result.toString());
//	 throw new MultiBusinessException("MulitiBusinessException", ex);
//	 throw new MultiBusinessException(null, ex);
	}
	
	public void exceptionMthod1() throws BusinessException{
		logger.error("999999");
		throw new BusinessException("99999", " throw exceptionMethod1!");
	}
	
	public void exceptionMethod2() throws WithoutAuthAndConfirmException{
		System.out.println("exceptionMethod2");
		throw new WithoutAuthAndConfirmException("99998", "throw exceptionMethod2!");
	}
	
	public void exceptionMethod3() throws WithoutAuthorizationException{
		System.out.println("exceptonMethod3");
		throw new WithoutAuthorizationException("99997",  "throw exceptionMethod3!") ;
	}
	public void exceptionMethod4() throws WithoutConfirmationException{
		System.out.println("exceptionMethod4");
		throw new WithoutConfirmationException("99996", "throw exceptionMethod4!");
	}
	
	public void exceptionMthod5() throws BusinessException{
		logger.error("999995");
		throw new BusinessException("99995", " throw exceptionMethod5!");
	}
	
	public void exceptionMthod6() throws BusinessException{
		logger.error("999999");
		throw new BusinessException("99995", " throw exceptionMethod5!");
	}
	
	public void exceptionMethod7() throws WithoutAuthAndConfirmException {
		System.out.println("exceptionMethod7");
		throw new WithoutAuthAndConfirmException("99993","throw exceptionMethod7!");
	}
	
	public void exceptionMethod8() throws WithoutAuthAndConfirmException {
		System.out.println("exceptionMethod7");
		throw new WithoutAuthAndConfirmException("99993","throw exceptionMethod7!");
	}
	
	public void exceptionMethod9() throws BusinessException{
		List<Result> list = new ArrayList<>();
		Result r= new Result();
		r.setRetCode("999990");
		r.setRetMsg("sssss");
		Result r1= new Result();
		r1.setRetCode("999990");
		r1.setRetMsg("sssss");
		Result r2= new Result();
		r2.setRetCode("999990");
		r2.setRetMsg("sssss");
		list.add(r);
		list.add(r1);
		list.add(r2);
		Results rs = new Results(list);
		throw new BusinessException(rs);
	}
	
	public void exceptionMethod10() throws BusinessException{
		List<Result> list = new ArrayList<>();
		Result r= new Result();
		r.setRetCode("999990");
		r.setRetMsg("sssss");
		Result r1= new Result();
		r1.setRetCode("999991");
		r1.setRetMsg("sssss");
		Result r2= new Result();
		r2.setRetCode("999992");
		r2.setRetMsg("sssss");
		list.add(r);
		list.add(r1);
		list.add(r2);
		Results rs = new Results(list);
		throw new BusinessException(rs);
	}

}
