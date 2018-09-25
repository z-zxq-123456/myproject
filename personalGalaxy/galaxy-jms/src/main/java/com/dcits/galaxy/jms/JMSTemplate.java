package com.dcits.galaxy.jms;

import java.util.Map;
import java.util.Map.Entry;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class JMSTemplate extends org.springframework.jms.core.JmsTemplate {
	
	@Override
	protected void doSend(MessageProducer producer, Message message) throws JMSException {
		try {
			injectProperties(message);
			super.doSend(producer, message);
		} finally {
			JMSContext.removeContext();
		}
	}
	
	protected void injectProperties(Message message) throws JMSException {
		Map<String, Object> properties = JMSContext.getContext().getProperties();
		for(Entry<String, Object> entry : properties.entrySet()){
			message.setObjectProperty(entry.getKey(), entry.getValue());
		}
	}
	
	@Override
	protected Message doReceive(Session session, Destination destination, String messageSelector) throws JMSException {
		Message message = super.doReceive(session, destination, messageSelector);
		JMSContext.getContext().setProperties(message);
		return message;
	}
}
