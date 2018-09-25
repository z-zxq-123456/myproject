package com.dcits.galaxy.base.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;

public class DocumentUtils {
	
	public static final int VALIDATION_NONE = 0;
	
	public static final int VALIDATION_DTD = 1;

	public static final int VALIDATION_XSD = 2;
	
	/**
	 * JAXP attribute used to configure the schema language for validation.
	 */
	private static final String SCHEMA_LANGUAGE_ATTRIBUTE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/**
	 * JAXP attribute value indicating the XSD schema language.
	 */
	private static final String XSD_SCHEMA_LANGUAGE = "http://www.w3.org/2001/XMLSchema";
	
	public static DocumentBuilder createDocumentBuilder(int validationMode) throws ParserConfigurationException {
		return createDocumentBuilder(validationMode, ClasspathEntityResolver.getInstance(), new SimpleErrorHandler());
	}
	
	public static DocumentBuilder createDocumentBuilder(int validationMode, Logger logger)
			throws ParserConfigurationException {
		return createDocumentBuilder(validationMode, ClasspathEntityResolver.getInstance(), new SimpleErrorHandler(logger));
	}
	
	public static DocumentBuilder createDocumentBuilder(int validationMode, EntityResolver resolver, ErrorHandler errorHandler) throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		
		if(validationMode != VALIDATION_NONE){
			factory.setValidating(true);
			factory.setIgnoringElementContentWhitespace(true);
			if (validationMode == VALIDATION_XSD) {
				try {
					factory.setAttribute(SCHEMA_LANGUAGE_ATTRIBUTE, XSD_SCHEMA_LANGUAGE);
				}
				catch (IllegalArgumentException ex) {
					ParserConfigurationException pcex = new ParserConfigurationException(
							"Unable to validate using XSD: Your JAXP provider [" + factory +
							"] does not support XML Schema. Are you running on Java 1.4 with Apache Crimson? " +
							"Upgrade to Apache Xerces (or Java 1.5) for full XSD support.");
					pcex.initCause(ex);
					throw pcex;
				}
			}
		}

		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		docBuilder.setEntityResolver(resolver);
		docBuilder.setErrorHandler(errorHandler);
		return docBuilder;
	}
}
