package quotePub;

import javax.jms.*;

import stocks.*;

public class QuotePublisher {
	private String subject;
	
	TopicSession topicSession;
	Topic topic;
	TopicPublisher topicPublisher;
	TextMessage message;
	String stockQuoteText;
	
	public QuotePublisher(StockIdentifier stockIdentifier, TopicSession topicSession) {
		this.subject = stockIdentifier.getValue();
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
	
	protected void publishContent(float quote, String content) throws JMSException {
		/*
		 * Without using JNDI lookup
		 */
		// Set message context
		message.setText(content);
		message.setFloatProperty("quote", quote);
		topicPublisher.publish(message);
		/*
		 * Print out quote change
		 * Just for debugging, might mess the console up
		 */
		// System.out.println(" Publish: "+content+" to topic "+subject);
	}

	public static void main(String[] args) throws Exception {
		
	}

}
