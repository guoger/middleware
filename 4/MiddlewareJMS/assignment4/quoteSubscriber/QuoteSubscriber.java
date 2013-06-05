package quoteSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
		SubListener listener = new SubListener(s);
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
	String subTopic, temp;
	TextMessage textMsg;
	String[] msgContent;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
	Date currentTime = new Date();
	
	public SubListener(StockIdentifier s) {
		subTopic = s.getValue();
	}
	// Override interface MessageListener to unblocking receive message
	@Override
	public void onMessage(Message msg) {
		if(msg instanceof TextMessage) {
			textMsg = (TextMessage) msg;
			try {
				temp = textMsg.getText();
				//System.out.println(temp);
				msgContent = parseMessage(temp);
				//System.out.println(msgContent[0]);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" [Subscriber] "+subTopic
					+"\n\t\tQuote: "+msgContent[0]+" | "+msgContent[1]);
		}
	}
	
	@SuppressWarnings("deprecation")
	public String[] parseMessage(String msg) {
		try {
			msgContent = msg.split(":");
			//System.out.println(msgContent[0]+"  "+msgContent[1]);
			currentTime = dateFormat.parse(msgContent[1]);
			msgContent[1] = currentTime.toGMTString();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msgContent;
	}
	
}