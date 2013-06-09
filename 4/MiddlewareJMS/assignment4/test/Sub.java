package test;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sub {

	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	public Sub() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		TopicConnection topicConn = connectionFactory.createTopicConnection();
		topicConn.start();
		
		TopicSession topicSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = topicSession.createTopic("adidas AG");
		// TopicPublisher topicPublisher = topicSession.createPublisher(topic);
		TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);
		// TextMessage msg = topicSession.createTextMessage();
		// msg.setText("First!");
		
		// TopicListener topicListener = new TextListener();
		Listener listener = new Listener();
		topicSubscriber.setMessageListener(listener);
		System.out.println("I'm not blocking!");
	}

}

class Listener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		if(msg instanceof TextMessage) {
			TextMessage textMsg = (TextMessage) msg;
			try {
				System.out.println("Received message: "+textMsg.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
