package com.dcits.galaxy.instrument;

import java.lang.instrument.Instrumentation;

/**
 * Galaxy Instrment 插桩启动类
 * @author yin.weicai
 */
public class PreBootstrap {
	

	public static void premain(String agentArgs, Instrumentation inst){
		
		Controller cftController = new Controller();
		inst.addTransformer(cftController);
	}

	
}
