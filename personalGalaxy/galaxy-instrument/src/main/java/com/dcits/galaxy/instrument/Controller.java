package com.dcits.galaxy.instrument;

import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Galaxy 插桩控制器
 * @author yin.weicai
 */
public final class Controller implements ClassFileTransformer {
	
	public final static String Instrument_Properties_File = "instrument.properties.file";
	public final static String Instrument_Properties = "instrument.properties";

	@Override
	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		
		loadTransformer();
		ClassFileTransformer transformer = TransformerRegistry.get(className);
		if(transformer != null ){
			return transformer.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
		}
		return classfileBuffer;
	}
	
	public static void loadTransformer() {
		if( TransformerRegistry.isRegister() ){
			return;
		}
		String fileName = System.getProperty(Instrument_Properties_File, Instrument_Properties);
		Enumeration<URL> urls = null;
		try {
			urls = TransformerRegistry.getContextLoader().getResources( fileName );
		} catch (Exception e) {
			// ignored
		}
		
		try {
			InputStream inStream = null;
			while( urls != null && urls.hasMoreElements() ){
				try {
					URL url = urls.nextElement();
					inStream = url.openStream();
					Properties pros = new Properties();
					pros.load(inStream);
					Set<String> keySet = pros.stringPropertyNames();
					Iterator<String> it = keySet.iterator();
					while(it.hasNext()){
						String key = it.next();
						String value = pros.getProperty(key);
						System.out.println("Transformer for " + key);
						String targetClass = key.replace(".", "/");
						TransformerRegistry.register(targetClass, value);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					if( inStream != null ){
						try {
							inStream.close();
						} catch (IOException e) {
							
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
