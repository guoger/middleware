package quoteSubscriber;

import javax.jms.*;
import javax.naming.*;

import stocks.*;

public class QuoteSubscriber extends Thread {
	private String subject;
	
	TopicSession topicSession;
	Topic topic;
	TopicSubscriber topicSubscriber;
	TextMessage message;
	String stockQuoteText;
	StockIdentifier s;
	
	// Construct object by StockName and TopicSession
	public QuoteSubscriber(StockIdentifier si, TopicSession topicSession) {
		this.s = si;
		this.subject = s.getValue();
		this.topicSession = topicSession;
		try {
			createTopic();
		} catch (JMSException e) {
			System.out.println(" [Subscriber]\tInitialization failed!");
			e.printStackTrace();
		}
	}

	public StockIdentifier getStockIdentifier() {
		return s;
	}
	
	@Override
	public void run() {
		SubListener listener = new SubListener();
		try {
			topicSubscriber.setMessageListener(listener);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" [Subscriber]\tListening to stock: "+subject);
	}
	
	private void createTopic() throws JMSException {
		topic = topicSession.createTopic(subject);
		topicSubscriber = topicSession.createSubscriber(topic);
		message = topicSession.createTextMessage();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class SubListener implements MessageListener {
	// Override interface MessageListener to unblocking receive message
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