package myJMS;

import javax.jms.*;

import stocks.*;

public class QuotePublisher {
	private String subject;
	
	TopicSession topicSession;
	Topic topic;
	TopicPublisher topicPublisher;
	TextMessage message;
	String stockQuoteText;
	
	public QuotePublisher(StockName stockName, TopicSession topicSession) {
		this.subject = stockName.getName();
		this.topicSession = topicSession;
		try {
			createTopic();
		} catch (JMSException e) {
			System.out.println(" [Publisher]\tInitialization failed!");
			e.printStackTrace();
		}
	}

	public QuotePublisher(StockID stockID, TopicSession topicSession) {
		this.subject = stockID.getID();
		this.topicSession = topicSession;
		try {
			createTopic();
		} catch (JMSException e) {
			System.out.println(" [Publisher]\tInitialization failed!");
			e.printStackTrace();
		}
	}
	
	private void createTopic() throws JMSException {
		/*
		 * Without using JNDI lookup
		 */
		topic = topicSession.createTopic(subject);
		topicPublisher = topicSession.createPublisher(topic);
		message = topicSession.createTextMessage();
	}
	
	public void publishContent(String content) throws JMSException {
		/*
		 * Without using JNDI lookup
		 */
		// Set message context
		message.setText(content);
		topicPublisher.publish(message);
		System.out.println(" Publish: "+content+" to topic "+subject);
	}

	public static void main(String[] args) throws Exception {
		TopicSession topicSession = QuotePublisherA.initializeJMS();
		StockName stockName = new StockName("adidas AG");
		StockQuote stockQuote = new StockQuote((float) 11.5);
		QuotePublisher quotePublisher = new QuotePublisher(stockName, topicSession);
		quotePublisher.publishContent("Hello, world!");
		topicSession.close();
	}

}
