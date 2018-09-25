package com.dcits.galaxy.jms;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.alibaba.fastjson.JSON;
import com.dcits.galaxy.jms.support.ConvertMessageCreator;

public class Sender {
	
	@Resource
	private JmsTemplate jmsTemplate;
	
	private String destName;
	
	public void send1(){
		jmsTemplate.convertAndSend(destName, "");
	}
	public static void main(String[] args) {
		sendByJmsTemplate();
	}

	public static void send() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://119.253.84.71:61616");
		try {
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			TextMessage message = session.createTextMessage("hello world");
//			message.setBooleanProperty("allow", false);
//			message.setJMSReplyTo(session.createTemporaryQueue());

			MessageProducer producer = session.createProducer(session.createQueue("queue.dest"));
			producer.send(message);
			
			producer.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public static void sendByJmsTemplate(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/client.xml");
		SyncJmsTemplate jms = context.getBean(SyncJmsTemplate.class);
		Message message = jms.sendSync(new ConvertMessageCreator("world"));
		System.out.println(JSON.toJSONString(message));
	}

}
