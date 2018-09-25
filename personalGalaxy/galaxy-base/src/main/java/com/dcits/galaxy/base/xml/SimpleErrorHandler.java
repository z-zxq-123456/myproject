package com.dcits.galaxy.base.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleErrorHandler.class);

	private Logger logger;
	
			
	public SimpleErrorHandler() {
		this(LOGGER);
	}

	public SimpleErrorHandler(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		logger.info(e.getMessage(), e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		throw e;
	}

}
