package com.dcits.galaxy.jms.adapter;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.MessageCreator;

import com.dcits.galaxy.adapter.Adapter;
import com.dcits.galaxy.jms.SyncJmsTemplate;
import com.dcits.galaxy.jms.exception.JmsRecieveException;

public class JmsAdapter implements Adapter<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(JmsAdapter.class);
	
	@Resource
	private SyncJmsTemplate jmsTemplate;
	
	private Destination send = null;
	
	private Destination reply = null;
	
	private long timeout = 0;
	
	@Override
	public String execute(final String req) {
		if(logger.isDebugEnabled()){
			logger.debug("send request to jms: " + req);
		}

		Message message = jmsTemplate.sendSync(send, reply, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(req);
			}
		});
		
		try {
			return ((TextMessage) message).getText();
		} catch (JMSException e) {
			throw new JmsRecieveException(e);
		}
//		return jmsTemplate.execute(new SessionCallback<String>() {
//			@Override
//			public String doInJms(Session session) throws JMSException {
//				MessageProducer producer = createProducer(session, send);
//				String correlationID = null;
//				try {
//					TextMessage request = session.createTextMessage(req);
//					correlationID = request.getJMSMessageID();
//					request.setJMSCorrelationID(correlationID);
//					request.setJMSReplyTo(reply);
//				
//					producer.send(request, jmsTemplate.getDeliveryMode(), jmsTemplate.getPriority(), timeout);
//				} catch(Throwable t){
//					throw new BeforeSendException("消息发送失败：", t);
//				} finally {
//					JmsUtils.closeMessageProducer(producer);
//				}
//				
//				StringBuilder selectorBuilder = new StringBuilder().append("JMSCorrelationID = '").append(correlationID).append("'");
//				String replyText = null;
//				
//				MessageFuture future = new MessageFuture();
//				
//				MessageConsumer consumer = null;
//				try {
//					consumer = session.createConsumer(reply, selectorBuilder.toString());
//					consumer.setMessageListener(future);
//					TextMessage response = (TextMessage) future.get(timeout, TimeUnit.MILLISECONDS);
//					if(response == null){
//						throw new AfterSendException("can't receive response. wait timeout:" + timeout);
//					}
//					replyText = response.getText();
//				} catch (Throwable t) {
//					throw new AfterSendException("接收返回消息失败：", t);
//				} finally {
//					JmsUtils.closeMessageConsumer(consumer);
//				}
//				
//				return replyText;
//			}
//		}, true);
	}
	
//	protected MessageProducer createProducer(Session session, Destination destination) throws JMSException {
//		MessageProducer producer = session.createProducer(destination);
//		if (!jmsTemplate.isMessageIdEnabled()) {
//			producer.setDisableMessageID(true);
//		}
//		if (!jmsTemplate.isMessageTimestampEnabled()) {
//			producer.setDisableMessageTimestamp(true);
//		}
//		return producer;
//	}

	public SyncJmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(SyncJmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
