package com.dcits.galaxy.core.loggerx;


import com.dcits.galaxy.core.instrument.ClassPoolManager;
import com.dcits.galaxy.core.instrument.Transformer;
import javassist.*;

import java.security.ProtectionDomain;

public class ThreadPoolExecutorTransformer extends Transformer {

    public final static String TargetClassName = "java.util.concurrent.ThreadPoolExecutor";

    @Override
    public String getTargetClassName() {
        return TargetClassName;
    }

	@Override
	public byte[] doTransform(ClassLoader loader, String className,Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain,byte[] classfileBuffer) {
		CtClass ctClass = null;
		try {
			ClassPool classPool = ClassPoolManager.getClassPool(loader);
			ctClass = classPool.get(TargetClassName);
			
			if( ctClass.isFrozen() ){
				return null;
			}

			String runnableWrapperClassFieldSrc = ""
			        + "public java.lang.Class runnableWrapperClass = "
			        + "  Thread.currentThread().getContextClassLoader().loadClass(\"com.dcits.galaxy.core.loggerx.Slf4jRunnableWrapper\");";
			CtField runnableWrapperClassField = CtField.make(runnableWrapperClassFieldSrc, ctClass);
			ctClass.addField(runnableWrapperClassField);


			CtField setTargetMethodField = CtField.make("public java.lang.reflect.Method setTargetMethod = null; ", ctClass);
			ctClass.addField(setTargetMethodField);


			String wrapperMethodSrc = ""
			        + " public java.lang.reflect.Method getSetTargetMethod(){"
			        + "   if( setTargetMethod == null){"
			        + "     this.setTargetMethod = this.runnableWrapperClass.getDeclaredMethod(\"setTarget\", new Class[]{ java.lang.Runnable.class});"
			        + "   } "
			        + "   return this.setTargetMethod; "
			        + " } ";
			CtMethod getWrapperMethod = CtNewMethod.make(wrapperMethodSrc, ctClass);
			ctClass.addMethod(getWrapperMethod);

			CtMethod method = ctClass.getDeclaredMethod("execute");
			String before = ""
			        + " Runnable runnableWrapper = (Runnable)runnableWrapperClass.newInstance();"
			        + " Object[] target = new Object[]{$1};"
			        + " getSetTargetMethod().invoke(runnableWrapper, target);"
			        + " $1 = runnableWrapper; ";
			method.insertBefore(before);
			ctClass.toBytecode();
			return ctClass.toBytecode();
		} catch (Throwable e) {
			e.printStackTrace();
		}finally{
			if( ctClass != null ){
				ctClass.detach();
			}
		} 
		return null;
	}

}
