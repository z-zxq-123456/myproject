package com.dcits.galaxy.dev.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * MyBatis的DAO、mapper及modle的自动生成器
 * 
 * @author xuecy
 * 
 */
public class MybatisGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		final boolean overwrite = true;

		try {
			File configFile = new File(
					"src/main/java/com/dcits/galaxy/dev/tools/generator.xml");
			System.out.println("config fiel is in : "
					+ configFile.getAbsoluteFile());
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration configuration;
			configuration = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator mybatisGenerator = new MyBatisGenerator(
					configuration, callback, warnings);
			mybatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("OK");
	}

}
