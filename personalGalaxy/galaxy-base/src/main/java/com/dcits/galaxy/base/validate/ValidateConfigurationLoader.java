package com.dcits.galaxy.base.validate;

import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcits.galaxy.base.exception.GalaxyException;
import com.dcits.galaxy.base.xml.DocumentUtils;

public class ValidateConfigurationLoader {

	private static final Logger logger = LoggerFactory.getLogger(ValidateConfigurationLoader.class);

	private Resource[] locations = null;
	
	public void loadConfiguration() {
		if (locations == null) {
			throw new GalaxyException("locations can't be null");
		}

		for (Resource location : locations) {
			if (!location.exists() || !location.isReadable()) {
				throw new GalaxyException("can't read hint xml file:" + location.toString());
			}
			Map<String, V> validators = parseXml(location);
			Validator.getInstance().addValidators(validators);
		}
	}

	private Map<String, V> parseXml(Resource location) {
		if(logger.isDebugEnabled()){
			logger.debug("parse service mapping configuration " + location.getDescription());
		}
		
		Map<String, V> validators = new HashMap<>();
		DocumentBuilder builder = null;
		try {
			builder = DocumentUtils.createDocumentBuilder(DocumentUtils.VALIDATION_DTD, logger);
		} catch (ParserConfigurationException e) {
			throw new GalaxyException(e);
		}
		
		try {
			Document doc = builder.parse(location.getInputStream());
			Element root = doc.getDocumentElement();
			String className = root.getAttribute("class");
			
			NodeList nodes = root.getChildNodes();
			int size = nodes.getLength();
			for (int i = 0; i < size; i++) {
				Node e = nodes.item(i);
				if (e instanceof Element) {
					V v = parseValidate((Element) e);
					String fieldName = ((Element) e).getAttribute("name");
					validators.put(className + "." + fieldName, v);
				}
			}
		} catch (Exception e) {
			throw new GalaxyException("parse " + location.getDescription() + " exception. please check file settings.", e);
		}
		
		return validators;
	}

	private V parseValidate(Element e) {
		JSONObject json = new JSONObject();
		NamedNodeMap map = e.getAttributes();
		for(int i =0;i<map.getLength();i++){
			Node node = map.item(i);
			json.put(node.getNodeName(), node.getNodeValue());
		}
		ValidateBuilder builder = JSON.toJavaObject(json, ValidateBuilder.class);
		return builder.build();
	}

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}
}
