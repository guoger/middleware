package quoteSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jms.*;
import javax.naming.*;

import stocks.*;

//@SuppressWarnings("serial")
public class QuoteSubscriber implements MessageListener, java.io.Serializable {
	transient public String subject;
	
	transient public TopicSession topicSession;
	transient Topic topic;
	transient TopicSubscriber topicSubscriber;
	transient TextMessage message;
	// String stockQuoteText;
	StockIdentifier s;
	// transient SubListener listener;
	
	
	transient String subTopic;
	transient String[] msgContent;
	transient SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
	Date currentTime;
	float currentQuote;
	
	
	// Construct object by StockName and TopicSession
	public QuoteSubscriber() {
		// this.s = si;
		// this.subject = s.getValue();
		// this.topicSession = topicSession;
		// listener = new SubListener(s);
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
			System.out.println(" [Subscriber]\tInitialization failed!");
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
		// message = topicSession.createTextMessage();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMessage(Message msg) {
		TextMessage textMsg;
		String temp;
		
		// System.out.println("Received something!"+msg);
		if(msg instanceof TextMessage) {
			textMsg = (TextMessage) msg;
			try {
				temp = textMsg.getText();
				System.out.println("Correct msg!"+"\n"+temp);
				//System.out.println(temp);
				parseMessage(temp);
				//System.out.println(msgContent[0]);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(" [Subscriber] "+s.getValue()+"\n\t\t");
			System.out.printf("%.2f", currentQuote);
			System.out.println("\t\t| "+currentTime);
		}
	}
	
	public void parseMessage(String msg) {
		try {
			msgContent = msg.split(":");
			//System.out.println(msgContent[0]+"  "+msgContent[1]);
			currentTime = dateFormat.parse(msgContent[1]);
			currentQuote = Float.parseFloat(msgContent[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


/*
 * SubListener
 */
/*
@SuppressWarnings("serial")
class SubListener implements MessageListener, java.io.Serializable {
	transient String subTopic, temp;
	transient TextMessage textMsg;
	transient String[] msgContent;
	transient SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
	Date currentTime = new Date();
	float currentQuote;
	
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
				parseMessage(temp);
				//System.out.println(msgContent[0]);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(" [Subscriber] "+subTopic+"\n\t\t");
			System.out.printf("%.2f", currentQuote);
			System.out.println("\t\t| "+currentTime);
		}
	}
	
	public void parseMessage(String msg) {
		try {
			msgContent = msg.split(":");
			//System.out.println(msgContent[0]+"  "+msgContent[1]);
			currentTime = dateFormat.parse(msgContent[1]);
			currentQuote = Float.parseFloat(msgContent[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
*/