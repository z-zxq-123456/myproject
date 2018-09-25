package com.dcits.galaxy.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.SessionCallback;
import org.springframework.jms.support.JmsUtils;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.util.Assert;

import com.dcits.galaxy.jms.exception.JmsRecieveException;
import com.dcits.galaxy.jms.exception.JmsSendException;

public class SyncJmsTemplate extends JMSTemplate {
	
	private Destination replyDestination = null;
	
	public Message sendSync(MessageCreator messageCreator) throws JmsException {
		return sendSync(getDefaultDestination(), messageCreator);
	}
	
	public Message sendSync(final Destination sendTo, final MessageCreator messageCreator) throws JmsException {
		return sendSync(sendTo, resolveReplyTo(null), messageCreator);
	}
	
	public Message sendSync(final Destination sendTo, final Destination replyTo,final MessageCreator messageCreator) throws JmsException {
		Assert.notNull(messageCreator, "MessageCreator must not be null");
		Assert.notNull(sendTo, "sendTo must not be null");
		Assert.notNull(replyTo, "reply must not be null");
		
		return execute(new SessionCallback<Message>() {
			@Override
			public Message doInJms(Session session) throws JMSException {
				Assert.notNull(messageCreator, "MessageCreator must not be null");
				
				Message message = messageCreator.createMessage(session);
				message.setJMSReplyTo(replyTo);
				
				try {
					doSend(sendTo, session, message);
				} catch (Exception e) {
					throw new JmsSendException(e);
				}
				try {
					String correlationID = message.getJMSCorrelationID();
					if(correlationID == null){
						correlationID = message.getJMSMessageID();
					}
					return doReceive(session, replyTo, "JMSCorrelationID = '" + correlationID + "'");
				} catch (Exception e) {
					throw new JmsRecieveException(e);
				}
			}
		}, true);
	}
	
	public <T> T convertAndSendSync(final Object message) throws JmsException {
		Destination sendTo = getDefaultDestination();
		Assert.notNull(sendTo, "sendTo must not be null");
		
		return convertAndSendSync(sendTo, message);
	}
	
	public <T> T convertAndSendSync(Destination sendTo, final Object message) throws JmsException {
		Destination replyTo = resolveReplyTo(null);
		Assert.notNull(replyTo, "reply must not be null");
		
		return convertAndSendSync(sendTo, replyTo, message);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T convertAndSendSync(Destination sendTo, Destination replyTo, final Object message) throws JmsException {
		Message reply = sendSync(sendTo, replyTo, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MessageConverter converter = getMessageConverter();
				if (converter == null) {
					throw new IllegalStateException("No 'messageConverter' specified. Check configuration of JmsTemplate.");
				}
				return converter.toMessage(message, session);
			}
		});
		
		return (T) doConvertFromMessage(reply);
	}
	
	protected Destination resolveReplyTo(Destination replyTo) {
		return replyTo == null ? this.replyDestination : replyTo;
	}
	
	protected Destination resolveSendTo(Destination dest) {
		return dest == null ? this.getDefaultDestination() : dest;
	}
	
	protected Message createMessage(MessageCreator messageCreator, Session session) throws JMSException{
		Message message = messageCreator.createMessage(session);
		message.setJMSReplyTo(replyDestination);
		if(message.getJMSCorrelationID() == null){
			message.setJMSCorrelationID(message.getJMSMessageID());
		}
		return message;
	}
	
	protected void doSend(Destination sendTo, Session session, Message message) throws JMSException {
		MessageProducer producer = createProducer(session, sendTo);
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Sending created message: " + message);
			}
			doSend(producer, message);
			// Check commit - avoid commit call within a JTA transaction.
			if (session.getTransacted() && isSessionLocallyTransacted(session)) {
				// Transacted session created by this template -> commit.
				JmsUtils.commitIfNecessary(session);
			}
		} finally {
			JmsUtils.closeMessageProducer(producer);
		}
	}

	public Destination getReplyDestination() {
		return replyDestination;
	}

	public void setReplyDestination(Destination replyDestination) {
		this.replyDestination = replyDestination;
	}
}
