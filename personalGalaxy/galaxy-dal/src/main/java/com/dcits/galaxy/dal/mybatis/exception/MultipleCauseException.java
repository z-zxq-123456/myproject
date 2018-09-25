package com.dcits.galaxy.dal.mybatis.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleCauseException extends DALException {
	
	private static final long serialVersionUID = 4775754339053133211L;
	
	private List<Throwable> causes = null;

    public MultipleCauseException(){
    	super("multiple cause exception.");
    }

    public MultipleCauseException(List<Throwable> causes) {
    	super("multiple cause exception.");
        if (!(causes == null || causes.isEmpty())) this.causes.addAll(causes);
    }

    public void add(Throwable e) {
    	 if (e == null){
    		 throw new IllegalArgumentException();
    	 }

		if (causes == null) {
			initCause(e);
			causes = new ArrayList<>();
		} else {
			addSuppressed(e);
		}
         
		if (e instanceof MultipleCauseException) {
			MultipleCauseException me = (MultipleCauseException) e;
			if(me.causes != null){
				causes.addAll(me.causes);
			}
		} else {
			causes.add(e);
		}
    }

    public List<Throwable> getCauses() {
    	if(causes == null){
    		return Collections.emptyList();
    	}
		return causes; 
    }
    
	public int size() {
		return (causes == null) ? 0 : causes.size();
	}

    @Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(MultipleCauseException.class.getSimpleName());
		if ((causes == null) || (causes.size() <= 0)) {
			str.append("[]");
		} else {
			str.append(causes);
		}
		return str.toString();
	}
}
