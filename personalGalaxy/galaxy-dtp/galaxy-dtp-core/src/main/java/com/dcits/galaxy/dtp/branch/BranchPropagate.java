package com.dcits.galaxy.dtp.branch;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BranchPropagate {
	Propagation value() default Propagation.NEW;
	
	public static enum Propagation{
		NEW,CONTEXT,NONE
	}
}