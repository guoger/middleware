package test;

import javax.jms.*;
import javax.naming.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class PubSub {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public PubSub() {
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		Context ctx = new InitialContext();
		TopicConnectionFactory factory =
				(TopicConnectionFactory) ctx.lookup("topicConnectionFactory");
		TopicConnection conn = factory.createTopicConnection();
		conn.start();
		TopicSession topicSession = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = (Topic) ctx.lookup("MyTopic");
		TopicPublisher topicPublisher = topicSession.createPublisher(topic);
		TextMessage msg = topicSession.createTextMessage();
		msg.setText("First Message!");
		topicPublisher.publish(msg);
		*/
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		TopicConnection topicConn = connectionFactory.createTopicConnection();
		topicConn.start();
		
		TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = topicSession.createTopic("adidas AG");
		TopicPublisher topicPublisher = topicSession.createPublisher(topic);
		TextMessage msg = topicSession.createTextMessage();
		msg.setText("First!");
		msg.setFloatProperty("quote", (float) 11.2);
		topicPublisher.publish(msg);
		System.out.println("Publish message!");
	}

}
