package quoteSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jms.*;
import javax.naming.*;

import stocks.*;

@SuppressWarnings("serial")
public class QuoteSubscriber implements MessageListener, java.io.Serializable {
	transient public String subject;
	transient public TopicSession topicSession;
	transient Topic topic;
	transient TopicSubscriber topicSubscriber;
	transient TextMessage message;
	StockIdentifier s;
	
	
	transient String subTopic;
	transient String[] msgContent;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
	Date currentTime;
	float currentQuote;
	
	boolean print = false;
	
	
	/*
	 * Constructor, accept StockIdentifier and a valid TopicSession
	 */
	public QuoteSubscriber(StockIdentifier si, TopicSession topicSession) {
		this.s = si;
		this.subject = s.getValue();
		this.topicSession = topicSession;
	}

	public StockIdentifier getStockIdentifier() {
		return s;
	}
	
	/*
	 * Use session to setup subscriber
	 * Bind this instance to JMS
	 * Every time a message is available, onMessage() method will be called
	 * And print entry info
	 */
	public void setup() throws JMSException {
		this.subject = s.getValue();
		System.out.println(subject);
		try {
			createTopic();
		} catch (JMSException e) {
			System.out.println(" [Subscriber] Initialization failed!");
			e.printStackTrace();
		}
		topicSubscriber.setMessageListener(this);
		System.out.print("  "+s.getValue()+"\t\t");
		System.out.printf("%.2f", currentQuote);
		System.out.print("\t|");
		if (currentTime == null) {
			System.out.print("NA");
		} else {
			System.out.print(currentTime);
		}
		System.out.print("\n");
	}
	
	/*
	 * Use topicSession to create topic and topicSubscriber
	 */
	private void createTopic() throws JMSException {
		topic = topicSession.createTopic(subject);
		topicSubscriber = topicSession.createSubscriber(topic);
	}
	
	/*
	 * onMessage, the method registered in JMS, is called when a message is available
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {
		TextMessage textMsg;
		String temp;
		
		if(msg instanceof TextMessage) {
			textMsg = (TextMessage) msg;
			try {
				temp = textMsg.getText();
				parseMessage(temp);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if (print) {
				System.out.print(" [Subscriber] "+s.getValue()+"\n\t\t");
				System.out.printf("%.2f", currentQuote);
				System.out.println("\t\t| "+currentTime);
			}
		}
	}
	
	/*
	 * Parse String to Date
	 */
	public void parseMessage(String msg) {
		try {
			msgContent = msg.split(":");
			currentTime = dateFormat.parse(msgContent[1]);
			currentQuote = Float.parseFloat(msgContent[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}
