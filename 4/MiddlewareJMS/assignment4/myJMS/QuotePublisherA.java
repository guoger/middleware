package myJMS;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import stocks.*;


public class QuotePublisherA {

	// args
	static int i;
	static int daxSize;
	static DAX dax;
	static QuoteRefresh quoteRefresh;
	
	// Using default url of JMS, which is localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	// Create 2 vector to hold quote Refreshers and Companies;
	public static Vector<QuoteRefresh> quoteRefreshThreads = new Vector<QuoteRefresh>();
	public static Vector<Company> daxCompanies;
	
	// Constructor of QuotePublisherA
	public QuotePublisherA() {
		dax = new DAX();
		daxCompanies = dax.establish();
		daxSize = daxCompanies.size();
		TopicSession stockPublishSession = null;
		/*
		 * Initialize TopicConnectionFactory, TopicConnection and TopicSession
		 * A valid TopicSession should be available after successful execution
		 * of Initialization.
		 */
		try {
			stockPublishSession = initializeJMS();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(" Initialization failed!!");
			e.printStackTrace();
		}
		for (i=0; i<daxSize; i++) {
			quoteRefresh = new QuoteRefresh(daxCompanies.elementAt(i), stockPublishSession);
			quoteRefreshThreads.addElement(quoteRefresh);
			quoteRefresh.start();
		}
	}
	
	
	/*
	 * A static method initializing JMS and return a valid TopicSession instance.
	 * NOTICE: will lost control of TopicConnection, which means TopicConnection.close()
	 * cannot be invoked!!! Be very careful when using this method!!!
	 * 
	 * Need to be improved later.
	 */
	public static TopicSession initializeJMS() throws Exception {
		/*
		 * JMS Pub/Sub Initialization, create session used by all topic publisher.
		 */
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		TopicConnection topicConn = connectionFactory.createTopicConnection();
		topicConn.start();
		TopicSession topicSess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		return topicSess;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuotePublisherA qa = new QuotePublisherA();
	}
	
}



class QuoteRefresh extends Thread {
	Company company;
	static Random randGen;
	QuotePublisher publishQuoteByName = null;
	QuotePublisher publishQuoteByID = null;
	TopicSession topicSession = null;
	SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMddHHmmssz");
	String currentTime;
	
	public QuoteRefresh(Company company, TopicSession topicSess) {
		this.company = company;
		this.topicSession = topicSess;
		randGen = new Random();
		randGen.setSeed(System.currentTimeMillis());
		// Create topic by Name and ID separately
		publishQuoteByName =
				new QuotePublisher(company.stockName, this.topicSession);
		publishQuoteByID =
				new QuotePublisher(company.stockID, this.topicSession);
	}
	
	@Override
	public void run() {
		int refreshInterval;
		while(true) {
			// Generate random number between 5 and 15 as sleep seconds
			refreshInterval = (randGen.nextInt(11) + 5) * 1000;
			// changeQuote() is a method implement in company class
			company.changeQuote();
			// Publish the quote to JMS platform
			try {
				publishQuoteToJMS(company);
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(refreshInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void publishQuoteToJMS(Company companyToPublish) throws JMSException {
		float tempQuote = companyToPublish.stockQuote.getQuote();
		System.out.println("\t"+companyToPublish.stockName.getName()+"\n\t"+
				companyToPublish.stockID.getID()+"\t"+
				tempQuote);
		currentTime = timeStamp.format(Calendar.getInstance().getTime());
		publishQuoteByName.publishContent(Float.toString(tempQuote)+":"+currentTime);
		publishQuoteByID.publishContent(Float.toString(tempQuote)+":"+currentTime);
	}
	
	
}
