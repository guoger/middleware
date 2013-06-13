package quotePub;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import stocks.*;

/**
 * General quote publisher
 * <p>Initialize stock market DAX from DAX.in file and instantiate companies to Company objects
 * <p>Refresh every company stock quote over time independently, range from 5 to 15 seconds
 * <p>Publish quote to JMS
 * <p>User can use command add/delete to create/remove a stock
 */
public class QuotePublisherA implements MessageListener {

	// args
	static int i;
	static int daxSize;
	static DAX dax;
	static QuoteRefresh quoteRefresh;
	static Company company;
	
	
	// PublishSession
	static TopicSession stockPublishSession = null;
	static QueueSession stockInitSession = null;
	static TopicConnection topicConn;
	static QueueConnection queueConn;
	
	// For request/reply
	static String request = "client.request";
	private static MessageProducer replyProducer;
	
	// Using default url of JMS, which is localhost
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	// Create 2 vector to hold quote Refreshers and Companies;
	private static Vector<QuoteRefresh> quoteRefreshThreads = new Vector<QuoteRefresh>();
	private static Vector<Company> daxCompanies;
	
	/**
	 * Initialize DAX and JMS, instantiate Company, create request/reply queue and setup message listener
	 */
	public QuotePublisherA() {
		dax = new DAX();
		daxCompanies = dax.establish();
		daxSize = daxCompanies.size();
		try {
			initializeJMS();
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
		/*
		 * For request/reply
		 */
		try {
			Destination requestQueue = stockInitSession.createQueue(request);
			
			// Setup a message producer to respond to message from clients
			// Reply destination will depend on correlation id part of request
			// Declared in the beginning for onMessage method to call
			replyProducer = stockInitSession.createProducer(null);
			replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			// Setup a message consumer to consume messages off of request queue
			MessageConsumer requestConsumer = stockInitSession.createConsumer(requestQueue);
			requestConsumer.setMessageListener(this);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Add a new stock
	 * Throw StockException if the stock already exist
	 */
	private static void addNewStock(String name, String id, float quote) throws StockException {
		String tempName, tempID;
		for (QuoteRefresh c : quoteRefreshThreads) {
			tempName = c.company.name;
			tempID = c.company.id;
			if (tempName.equals(name) || tempID.equals(id)) {
				throw new StockException("Stock already exist!");
			}
		}
		Company newCompany = dax.addCompany(name, id, quote);
		quoteRefresh = new QuoteRefresh(newCompany, stockPublishSession);
		quoteRefreshThreads.addElement(quoteRefresh);
		quoteRefresh.start();
	}
	
	/**
	 * To remove stock
	 * Throw StockException if cannot find stock
	 */
	@SuppressWarnings("deprecation")
	private static void removeStock(String i) throws StockException {
		for (QuoteRefresh c : quoteRefreshThreads) {
			String s1 = c.company.name;
			String s2 = c.company.id;
			if (s1.equals(i) || s2.equals(i)) {
				c.stop();
				quoteRefreshThreads.removeElement(c);
				System.out.println(" [Subscriber] Remove stock successfully!");
				return;
			}
		}
		throw new StockException("Cannot find stock!");
	}
	
	/**
	 * <p>1. Create a new ActiveMQConnectionFactory from url, which is set to ActiveMQConnection.DEFAULT_BROKER_URL
	 * <p>2. Use the ConnectionFactory to create a new TopicConnection and a new QueueConnection
	 * <p>3. Use Connections to create TopicSession for publishing and QueueSession for initialization
	 */
	private static void initializeJMS() throws Exception {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		topicConn = connectionFactory.createTopicConnection();
		queueConn = connectionFactory.createQueueConnection();
		topicConn.start();
		queueConn.start();
		stockPublishSession = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		stockInitSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	/**
	 * Method registered in JMS, fired when a new message is available
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage response = stockInitSession.createTextMessage();
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				String messageText = txtMsg.getText();
				response.setText(responseMsg(messageText));
			}
			
			// Set the correlation ID from the received message to be the correlation ID
			// of the response message. this lets the client identify which message this is
			// a response to
			response.setJMSCorrelationID(message.getJMSCorrelationID());
			response.setJMSType("Init");
			// Send the response to the Destination specified by the JMSReplyTo field of the received message
			// this is presumably a temporary queue created by the client
			this.replyProducer.send(message.getJMSReplyTo(), response);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Traverse daxCompanies to find corresponding company to setup respond message
	 */
	private String responseMsg(String txtMsg) {
		StockIdentifier s;
		if (txtMsg.startsWith("DE")) {
			s = new StockID(txtMsg);
		} else {
			s = new StockName(txtMsg);
		}
		for (Company c : daxCompanies) {
			if (c.match(s)) {
				return c.getState();
			}
		}
		// No stock found, return "0", indicating invalid stock
		return "0";
	}

	public static void main(String[] args) {
		QuotePublisherA qa = new QuotePublisherA();
		// System.out.println(" [Publisher]\tPlease input command:");
		String command = "";
		String userStockName, userStockID, userStockIdentifier;
		String userStockQuoteTemp = "";
		float userStockQuote;
		Console console = System.console();
		if (console == null) {
			System.err.println("No console found, entering autonomy mode.");
			// System.exit(1);
		} else {
			dax.printDAX();
			System.out.println("\n");
			while (true) {
				command = console.readLine("Enter 'new' to create stock, 'delete' to remove stock or 'exit' to quit: ");
				
				if (command.equals("new")) {
					// System.out.println("To create a new stock, please input info......");
					userStockName = console.readLine("Enter stock name: ");
					userStockID = console.readLine("Enter stock ID: ");
					userStockQuoteTemp = console.readLine("Enter stock price: ");
					userStockQuote = Float.parseFloat(userStockQuoteTemp);
					try {
						addNewStock(userStockName, userStockID, userStockQuote);
					} catch (StockException e) {
						System.out.println(" [Publisher] "+e.getError());
					}
				} else if (command.equals("delete")) {
					userStockIdentifier = console.readLine("To delete a stock, please input stock name or ID: ");
					try {
						removeStock(userStockIdentifier);
					} catch (StockException e) {
						System.out.println(" [Subscriber] "+e.getError());
					}
				} else if (command.equals("exit")) {
					break;
				}
			}
			
			System.out.println("Closing TopicSession...");
			try {
				stockPublishSession.close();
				stockInitSession.close();
				queueConn.close();
				topicConn.close();
			} catch (JMSException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	
}


/**
 * Every thread will hold a stock. Change the quote randomly (5 to 15 sec)
 * And then publish it to respective topic.
 * Time stamp included
 */
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
		publishQuoteByName.publishContent(tempQuote, companyToPublish.getState());
		publishQuoteByID.publishContent(tempQuote, companyToPublish.getState());
	}
	
	
}
