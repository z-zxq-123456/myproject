package com.dcits.galaxy.core.instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 字节码转换器接口.
 * 注：目前不支持转换链（即：多个转换器前后衔接构成责任链）。
 * @author yin.weicai
 */
public abstract class Transformer implements ClassFileTransformer {
	
	@Override
	public final byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		
		try {
			byte[] byteArray = doTransform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
			if( byteArray != null && byteArray.length > 0 ){
				return byteArray;
			}
		} catch (Throwable e) {
			System.out.println(e.fillInStackTrace());
		}
		return classfileBuffer;
	}
	
	public abstract String getTargetClassName();
	
	public abstract byte[] doTransform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer);
}
