package com.dcits.galaxy.jms;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;

import com.alibaba.dubbo.common.json.JSON;
import org.springframework.jms.support.JmsUtils;

public class JMSContext {

	private static ThreadLocal<JMSContext> LOCAL = new ThreadLocal<JMSContext>() {
		protected JMSContext initialValue() {
			return new JMSContext();
		};
	};

	private Map<String, Object> properties = new HashMap<>();

	private JMSContext() {
	};
	
	public static JMSContext getContext(){
		return LOCAL.get();
	}
	
	public static void removeContext(){
		LOCAL.remove();
	}
	
	public void cleanProperties(){
		properties.clear();
	}

	public void setProperties(Map<String, Object> properties) {
		properties.putAll(properties);
	}

	public void setProperties(Message message) {
		try {
			@SuppressWarnings("unchecked")
			Enumeration<String> names = message.getPropertyNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				properties.put(name, message.getObjectProperty(name));
			}
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
	}
	
	public Map<String, Object> getProperties(){
		return properties;
	}
	
	public void setProperty(String name, Object value){
		properties.put(name, value);
	}
	
	public Object getProperty(String name){
		return properties.get(name);
	}

	public static void main(String[]args){
		final ThreadLocal<Map> threadLocal = new ThreadLocal<Map>(){
			@Override
			protected Map<Object, Object> initialValue() {
				Map map = new HashMap();
				map.put("init","int1");
				return map;
			}
		};
		Map map = threadLocal.get();
		map.put("k1","v1");

		try {
			System.out.println(JSON.json(map));
		}catch (Exception e){}

		new Thread(new Runnable() {
			@Override
			public void run() {
				try{

					Map map = threadLocal.get();
					System.out.println(map.get("init"));
				}catch (Exception e){}
			}
		}).start();

	}
}
