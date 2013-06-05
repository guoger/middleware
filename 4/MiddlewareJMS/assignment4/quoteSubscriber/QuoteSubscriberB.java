package quoteSubscriber;

import java.util.*;
import java.io.*;

import javax.jms.*;
import javax.naming.*;

import stocks.*;

public class QuoteSubscriberB {

	// Vector to hold QuoteSubscriber threads-like objects
	Vector<QuoteSubscriber> subscribeList = new Vector<QuoteSubscriber>();
	StockName tempStockName;
	StockID tempStockID;
	StockIdentifier stockIdentifier;
	Company tempCompany;
	TopicSession stockSubscribeSession;
	QuoteSubscriber quoteSubscriber;
	public QuoteSubscriberB() {
		String temp;
		// Using myJMS initialization to initialize JMS
		try {
			stockSubscribeSession = myJMS.QuotePublisherA.initializeJMS();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// Read subscribe list from file SubscribeList.in
		try {
			BufferedReader in =
					new BufferedReader(new FileReader("SubscribeList.in"));
			/*
			 * Reading from SubscribeList.in file line by line until null found
			 * Determine whether it is a stock name or ID, using it to create new
			 * QuoteSubscriber instance.
			 */
			while((temp = in.readLine()) != null) {
				if(temp.startsWith("DE")) {
					stockIdentifier = new StockID(temp);
					quoteSubscriber =
							new QuoteSubscriber(stockIdentifier, stockSubscribeSession);
				} else {
					stockIdentifier = new StockName(temp);
					quoteSubscriber =
							new QuoteSubscriber(stockIdentifier, stockSubscribeSession);
				}
				// Insert and start a new QuoteSubscriber thread
				subscribeList.addElement(quoteSubscriber);
				quoteSubscriber.start();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find SubscribeList.in, please make sure" +
					" the file is accessable and try again!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Read file failed!");
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuoteSubscriberB qb = new QuoteSubscriberB();

	}

}
