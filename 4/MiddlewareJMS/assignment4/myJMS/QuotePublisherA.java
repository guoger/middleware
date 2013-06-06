package myJMS;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

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
	static Company company;
	
	// PublishSession
	static TopicSession stockPublishSession = null;
	
	
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
		//stockPublishSession = null;
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
	 * Add a new stock
	 * First check whether the stock already exist, return 1 if it does, otherwise, create it
	 * and start refreshing and return 0;
	 */
	private static int addNewStock(String name, String id, float quote) {
		String tempName, tempID;
		for (QuoteRefresh c : quoteRefreshThreads) {
			tempName = c.company.name;
			tempID = c.company.id;
			if (tempName.equals(name) || tempID.equals(id)) {
				return 1;
			}
		}
		Company newCompany = dax.addCompany(name, id, quote);
		quoteRefresh = new QuoteRefresh(newCompany, stockPublishSession);
		quoteRefreshThreads.addElement(quoteRefresh);
		quoteRefresh.start();
		return 0;
	}
	
	@SuppressWarnings("deprecation")
	private static int removeStock(String i) {
		for (QuoteRefresh c : quoteRefreshThreads) {
			String s1 = c.company.name;
			String s2 = c.company.id;
			if (s1.equals(i) || s2.equals(i)) {
				c.stop();
				quoteRefreshThreads.removeElement(c);
				return dax.removeCompany(i);
			}
		}
		return 1;
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
		// System.out.println(" [Publisher]\tPlease input command:");
		String command = "";
		String userStockName, userStockID, userStockIdentifier;
		String userStockQuoteTemp = "";
		float userStockQuote;
		Console console = System.console();
		if (console == null) {
			System.err.println("No console found.");
			System.exit(1);
		}
		System.out.println("\n\n");
		while (true) {
			command = console.readLine("Enter 'new' to create stock, 'delete' to remove stock or 'exit' to quit: ");
			
			if (command.equals("new")) {
				// System.out.println("To create a new stock, please input info......");
				userStockName = console.readLine("Enter stock name: ");
				userStockID = console.readLine("Enter stock ID: ");
				userStockQuoteTemp = console.readLine("Enter stock price: ");
				userStockQuote = Float.parseFloat(userStockQuoteTemp);
				if (addNewStock(userStockName, userStockID, userStockQuote) == 1) {
					System.out.println(" [Publisher]\tStock already exists!");
				} else {
					System.out.println(" [Publisher]\tSuccessfully create stock: "+userStockName+" "+userStockID+
							"\n\t\t"+userStockQuote+"\n");
				}
			} else if (command.equals("delete")) {
				userStockIdentifier = console.readLine("To delete a stock, please input stock name or ID: ");
				if (removeStock(userStockIdentifier) == 1) {
					System.out.println(" [Publisher]\tStock does not exists!");
				} else {
					System.out.println(" [Publisher]\tSuccessfully delete stock!");
				}
			} else if (command.equals("exit")) {
				break;
			}
		}
		
		// Serializing DAX
		System.out.println(" [Publisher]\tStoring DAX......");
		try {
			FileOutputStream fileOut =
					new FileOutputStream("DAX.der");
			ObjectOutputStream out =
					new ObjectOutputStream(fileOut);
			out.writeObject(dax);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" [Publisher]\t......complete!");
		
		/*
		 * For debugging serialization.
		 * Read out DAX.der and print exit state
		 */
		try {
			FileInputStream fileIn =
					new FileInputStream("DAX.der");
			ObjectInputStream in =
					new ObjectInputStream(fileIn);
			dax = (DAX) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		dax.printDAX();
		System.out.println("\nBye!");
		System.exit(0);
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
			// Generate new quote price randomly
			float changePercent =
					(float)((randGen.nextInt(41)-20))/(float)1000;
			float newQuote = (this.company.stockQuote.getQuote()*((float)1 + changePercent));
			// Record current time
			currentTime = timeStamp.format(Calendar.getInstance().getTime());
			// update quote price
			company.setQuote(newQuote);
			// update time
			company.setTime(currentTime);
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
		//System.out.println("\t"+companyToPublish.stockName.getName()+"\n\t"+
		//		companyToPublish.stockID.getID()+"\t"+
		//		tempQuote);
		publishQuoteByName.publishContent(Float.toString(tempQuote)+":"
				+companyToPublish.getStockTime());
		publishQuoteByID.publishContent(Float.toString(tempQuote)+":"
				+companyToPublish.getStockTime());
	}
	
	
}
