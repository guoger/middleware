package quoteSubscriber;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import javax.jms.*;
import javax.naming.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import stocks.*;

public class QuoteSubscriberB {

	// Vector to hold QuoteSubscriber threads-like objects
	static Vector<QuoteSubscriber> watchList = new Vector<QuoteSubscriber>();
	StockName tempStockName;
	StockID tempStockID;
	static StockIdentifier stockIdentifier;
	Company tempCompany;
	
	// JMS tools
	static TopicSession stockSubscribeSession;
	static QuoteSubscriber quoteSubscriber;
	static TopicConnection topicConn;
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public QuoteSubscriberB() {

	}
	
	/*
	 * Initialize JMS, get TopicSession
	 */
	public static TopicSession initializeJMS() throws Exception {
		/*
		 * JMS Pub/Sub Initialization, create session used by all topic subscriber.
		 */
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		topicConn = connectionFactory.createTopicConnection();
		topicConn.start();
		TopicSession topicSess = topicConn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		return topicSess;
	}
	
	/*
	 * Load subscribe list from SubscribeList.in file
	 */
	private void loadFromIn(String fileName) throws IOException, JMSException {
		System.out.println(" [Subscriber]\tLoading "+fileName+"......");
		String temp;
		BufferedReader in =
				new BufferedReader(new FileReader(fileName));
		/*
		 * Reading from SubscribeList.in file line by line until null found
		 * Determine whether it is a stock name or ID, using it to create new
		 * QuoteSubscriber instance.
		 */
		while((temp = in.readLine()) != null) {
			if(temp.startsWith("DE")) {
				stockIdentifier = new StockID(temp);
			} else {
				stockIdentifier = new StockName(temp);
			}
			quoteSubscriber =
					new QuoteSubscriber(stockIdentifier, stockSubscribeSession);
			// Insert and start a new QuoteSubscriber thread
			watchList.addElement(quoteSubscriber);
			quoteSubscriber.setup();
		}
		System.out.println(" [Subscriber]\t......Load "+fileName+" successfully!");
		in.close();
	}
	
	/*
	 * Load watch list from serialized object file
	 */
	@SuppressWarnings("unchecked")
	private void loadFromSer(String fileName) throws IOException, ClassNotFoundException, JMSException {
		System.out.println(" [Subscriber]\tLoading "+fileName+"......");
		FileInputStream fileIn =
				new FileInputStream(fileName);
		ObjectInputStream in =
				new ObjectInputStream(fileIn);
		watchList = (Vector<QuoteSubscriber>) in.readObject();
		for (QuoteSubscriber q : watchList) {
			// q.dateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
			q.topicSession = stockSubscribeSession;
			// System.out.println(q.topicSubscriber);
			q.setup();
			// System.out.println(q.topicSubscriber);
		}
		System.out.println(" [Subscriber]\t......load "+fileName+" successfully!");
		in.close();
		fileIn.close();
	}
	
	/*
	 * Use stockIdentifier to subscribe a new stock
	 */
	private static void subscribeNewStock(String s) throws JMSException, StockException {
		// Use s to create stock identifier
		StockIdentifier si;
		if (s.startsWith("DE")) {
			si = new StockID(s);
		} else {
			si  = new StockName(s);
		}
		// Determine whether stock already exists in watch list
		for (QuoteSubscriber q : watchList) {
			if (si.equals(q.s)) {
				throw new StockException("Stock exists!");
			}
		}
		// Create new StockSubscriber
		quoteSubscriber =
				new QuoteSubscriber(si, stockSubscribeSession);
		// Insert and start a new QuoteSubscriber thread
		watchList.addElement(quoteSubscriber);
		quoteSubscriber.setup();
		System.out.println(" [Subscriber]\tSubscribed "+s);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuoteSubscriberB qb = new QuoteSubscriberB();
		// Using myJMS initialization to initialize JMS
		//System.out.println("I'm QuoteSubscriberB constructor!!");
		try {
			stockSubscribeSession = initializeJMS();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String fileName;
		String command = "";
		String userStockIdentifier;
		
		Console console = System.console();
		if (console == null) {
			System.err.println(" [Subscriber]\tNo console found!");
			System.exit(1);
		}
		
		System.out.print("\n");
		
		
		/*
		 * First step, reading console command to load from .in/.ser file
		 */
		while (true) {
			fileName = console.readLine("Enter file name to load watch list: ");
			if (fileName.endsWith(".in")) {
				try {
					qb.loadFromIn(fileName);
					break;
				} catch (IOException e) {
					System.out.println("Load file failed! Please try again!");
					//e.printStackTrace();
				} catch (JMSException e) {
					System.out.println("JMS setup failed!");
					e.printStackTrace();
				}
			} else if (fileName.endsWith(".ser")) {
				try {
					qb.loadFromSer(fileName);
					break;
				} catch (IOException e) {
					System.out.println("Load file failed! Please try again!");
					//e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("Class error, please check the file and try again!");
					//e.printStackTrace();
				} catch (JMSException e) {
					System.out.println("JMS setup failed!");
					e.printStackTrace();
				}
			} else {
				System.out.println("Please enter a valid file name, it should end with .ser or .in");
			}
		}
		
		/*
		 * Reading command to add/delete stock
		 * or exit to quit program
		 */
		while (true) {
			command = console.readLine("Enter 'new' to subscribe a new stock, 'delete' to unsubscribe " +
					"an exist stock, 'exit' to quite.\n>> ");
			if (command.equals("new")) {
				userStockIdentifier = console.readLine("Enter name or ID: ");
				try {
					subscribeNewStock(userStockIdentifier);
				} catch (JMSException e) {
					System.out.println(" [Subscriber]\tFailed to subscribe a new stock");
					e.printStackTrace();
				} catch (StockException e) {
					System.out.println(" [Subscriber]\tException: "+e.getError());
				}
			} else if (command.equals("delete")) {
				userStockIdentifier = console.readLine("Enter name or ID: ");
			} else if (command.equals("exit")) {
				break;
			} else {
				System.out.println("Invalid command!");
			}
		}
		
		// Close topic session and connection
		try {
			stockSubscribeSession.close();
			topicConn.close();
		} catch (JMSException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("TopicSession closed!");
		// Serialize object into file
		try {
			System.out.println("Serializing object...");
			FileOutputStream fileOut =
					new FileOutputStream("watchList.ser");
			ObjectOutputStream out =
					new ObjectOutputStream(fileOut);
			out.writeObject(watchList);
			out.close();
			fileOut.close();
			System.out.println("\t...Object serialized!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nBye!");
		// Exit program
		System.exit(0);
		
	}

}
