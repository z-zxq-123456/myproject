package com.dcits.galaxy.dal;

import java.util.List;

import org.springframework.transaction.HeuristicCompletionException;
import org.springframework.transaction.UnexpectedRollbackException;

import com.dcits.galaxy.dal.mybatis.exception.MultipleCauseException;

import junit.framework.TestCase;

public class ExceptionTest extends TestCase {

	public void testCommit(){
		MultipleCauseException ex = new MultipleCauseException();
		ex.add(new RuntimeException("12233242"));
//		ex.add(new RuntimeException("1223"));
		List<Throwable> causes = ex.getCauses();
		if (!causes.isEmpty()) {
			if(causes.size() == 1){
				throw new HeuristicCompletionException(HeuristicCompletionException.STATE_UNKNOWN, causes.get(0));
			} else {
				throw new HeuristicCompletionException(HeuristicCompletionException.STATE_UNKNOWN, ex);
			}
		}
	}
	
	public void testRollback(){
		try {
			MultipleCauseException ex = new MultipleCauseException();
			ex.add(new RuntimeException("12233242"));
			ex.add(new RuntimeException("1223"));
			List<Throwable> causes = ex.getCauses();
			if (!causes.isEmpty()) {
				if(causes.size() == 1){
					throw new UnexpectedRollbackException("one or more error on rolling back the transaction", causes.get(0));
				} else {
					throw new UnexpectedRollbackException("one or more error on rolling back the transaction", ex);
				}
			}
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
