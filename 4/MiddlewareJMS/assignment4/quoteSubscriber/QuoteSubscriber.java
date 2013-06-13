package quoteSubscriber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

import stocks.*;

@SuppressWarnings("serial")
public class QuoteSubscriber implements MessageListener, java.io.Serializable {
	transient public String subject;
	transient public TopicSession topicSession;
	transient public QueueSession queueSession;
	transient public Topic topic;
	protected transient TopicSubscriber topicSubscriber;
	transient TextMessage message;
	public StockIdentifier s;
	
	transient static String request = "client.request";
	
	transient String subTopic;
	transient String[] msgContent;
	public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
	public Date currentTime;
	public float currentQuote;
	
	public transient boolean print = true;
	
	public transient boolean init = false;
	
	
	/**
	 * Accept StockIdentifier and a valid TopicSession
	 */
	public QuoteSubscriber(StockIdentifier si, TopicSession topicSession, QueueSession queueSession) {
		this.s = si;
		this.subject = s.getValue();
		this.topicSession = topicSession;
		this.queueSession = queueSession;
	}

	public StockIdentifier getStockIdentifier() {
		return s;
	}
	
	/**
	 * <p>Use session to setup subscriber
	 * <p>Bind this instance to JMS
	 * <p>Every time a message is available, onMessage() method will be called
	 * <p>And print entry info
	 */
	public void setup() throws JMSException {
		this.subject = s.getValue();
		// System.out.println(subject);
		try {
			// System.out.println(" [Subscriber] Fast initialize: "+this.subject);
			// fastInit();
			createTopic();
		} catch (JMSException e) {
			System.out.println(" [Subscriber] Initialization failed!");
			e.printStackTrace();
		}
		topicSubscriber.setMessageListener(this);
		/*
		System.out.print("  "+s.getValue()+"\t\t");
		System.out.printf("%.2f", currentQuote);
		System.out.print("\t|");
		if (currentTime == null) {
			System.out.print("NA");
		} else {
			System.out.print(currentTime);
		}
		System.out.print("\n");
		*/
	}
	
	/**
	 * Fast initialization
	 */
	public void fastInit() throws JMSException {
		Destination dest = queueSession.createQueue(request);
		// Create producer to producer request
		MessageProducer requestProducer = queueSession.createProducer(dest);
		requestProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		// Create consumer to consume response with temporary queue
		Destination tempDest = queueSession.createTemporaryQueue();
		MessageConsumer responseConsumer = queueSession.createConsumer(tempDest);
		
		// bind listener to JMS
		responseConsumer.setMessageListener(this);
		// Create message to send
		TextMessage tempTxt = queueSession.createTextMessage();
		tempTxt.setText(s.getValue());
		tempTxt.setJMSReplyTo(tempDest);
		// Set a correlation ID
		String correlationID = this.createRandomString();
		tempTxt.setJMSCorrelationID(correlationID);
		requestProducer.send(tempTxt);
	}
	
	private String createRandomString() {
        Random random = new Random(System.currentTimeMillis());
        long randomLong = random.nextLong();
        return Long.toHexString(randomLong);
    }
	
	/**
	 * Use topicSession to create topic and topicSubscriber
	 */
	public void createTopic() throws JMSException {
		topic = topicSession.createTopic(subject);
		topicSubscriber = topicSession.createSubscriber(topic);
	}
	
	/**
	 * onMessage, the method registered in JMS, is called when a message is available
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message msg) {
		TextMessage textMsg;
		String temp;
		// System.out.println(msg);
		if(msg instanceof TextMessage) {
			textMsg = (TextMessage) msg;
			try {
				temp = textMsg.getText();
				parseMessage(temp);
			} catch (JMSException e) {
				e.printStackTrace();
			}
			if (print) {
				System.out.print("\n [Subscriber] "+s.getValue()+"\n\t\t");
				System.out.printf("%.2f", currentQuote);
				System.out.println("\t\t| "+currentTime);
			}
		}
		try {
			if (msg.getJMSType() != null && msg.getJMSType().equals("Init")) {
				// System.out.println(msg);
				init = true;
				print = false;
			}
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Parse String to Date
	 */
	public void parseMessage(String msg) {
		try {
			//System.out.println(msg);
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
