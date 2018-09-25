package com.dcits.galaxy.jms.support;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

public class ConvertMessageCreator implements MessageCreator {
	
	private static MessageConverter converter = new SimpleMessageConverter();

	private final Object message;
	
	public ConvertMessageCreator(Object message){
		this.message = message;
	}
	
	@Override
	public Message createMessage(Session session) throws JMSException {
		return converter.toMessage(message, session);
	}

}
