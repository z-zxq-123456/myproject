package com.dcits.galaxy.jms.server;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.jms.JMSContext;

public abstract class AbstractDispathcer implements MessageListener, InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDispathcer.class);

	private MessageConverter converter = null;

	private JmsTemplate jmsTemplate;
	
	private Destination replyDestination;
	
	private boolean enableReply = true;

	@Override
	public void onMessage(final Message message) {
		try {
			JMSContext.getContext().setProperties(message);
			MessageCreator replyMessageCreator = null;
			
			Destination replyTo = getReplyTo(message);
			String correlationID = getCorrelationID(message);
			
			boolean needReply = replyTo != null;
			try {
				replyMessageCreator = handle(message, needReply);
			} catch (Exception e) {
				onServiceException(message, needReply, e);
			}
			
			if (needReply && replyMessageCreator != null) {
				try {
					sendMessage(replyTo, replyMessageCreator, correlationID);
				} catch (Exception e) {
					onSendException(replyMessageCreator, e);
				}
			}
		} finally {
			JMSContext.removeContext();
		}
	}

	protected MessageCreator handle(Message message, boolean needReply) throws JMSException {
		Object msg = converter.fromMessage(message);
		
		final Object replyMessage = doService(msg);
		
		if(!needReply){
			return null;
		}
		
		return new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return converter.toMessage(replyMessage, session);
			}
		};
	}

	protected void onServiceException(Message message, boolean needReply, Exception e) {
		logger.warn("when processing message " + JSON.toJSONString(message) + ", " + e.getMessage(), e);
	}

	protected void onSendException(Object message, Exception e) {
		logger.warn("when send message " + JSON.toJSONString(message) + ", " + e.getMessage(), e);
	}

	protected void sendMessage(final Destination replyTo, final Object replyMessage, final String correlationID) {
		MessageCreator createor = null;
		if (replyMessage instanceof MessageCreator) {
			createor = new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					Message message = ((MessageCreator) replyMessage).createMessage(session);
					if (correlationID != null && message.getJMSCorrelationID() == null) {
						message.setJMSCorrelationID(correlationID);
					}
					return message;
				}
			};
		} else {
			createor = new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					Message message = converter.toMessage(replyMessage, session);
					if (correlationID != null) {
						message.setJMSCorrelationID(correlationID);
					}
					return message;
				}
			};
		}
		jmsTemplate.send(replyTo, createor);
	}
	
	private Destination getReplyTo(Message message) {
		if(!enableReply){
			return null;
		}
		
		Destination replyTo = null;
		try {
			replyTo = message.getJMSReplyTo();
			if(replyTo == null){
				replyTo = replyDestination;
			}
		} catch (JMSException e) {
			logger.warn("can't get replyTo for message: " + JSON.toJSONString(message), e);
		}

		return replyTo;
	}

	private String getCorrelationID(Message message) {
		try {
			String correlationID = message.getJMSCorrelationID();
			if(correlationID != null){
				return message.getJMSCorrelationID();
			}
			return message.getJMSMessageID();
		} catch (JMSException e) {
			logger.warn("can't get correlationID for message: " + JSON.toJSONString(message), e);
		}

		return null;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (converter == null) {
			converter = new SimpleMessageConverter();
		}
	}

	abstract protected Object doService(Object message);
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public MessageConverter getConverter() {
		return converter;
	}

	public void setConverter(MessageConverter converter) {
		this.converter = converter;
	}
	
	public boolean isEnableReply() {
		return enableReply;
	}

	public void setEnableReply(boolean enableReply) {
		this.enableReply = enableReply;
	}

	public Destination getReplyDestination() {
		return replyDestination;
	}

	public void setReplyDestination(Destination replyDestination) {
		this.replyDestination = replyDestination;
	}
}
