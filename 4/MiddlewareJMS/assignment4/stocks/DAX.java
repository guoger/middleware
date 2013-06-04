package stocks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.Random;

public class DAX {
	String temp, stockName, stockID;
	int i;
	Float quote;
	int maxCom = 100;
	Company company;
	
	// Create a Vector to hold all DAX companies
	Vector<Company> daxCompanies = null;
	// Random generator
	static Random randomGenerator;

	public DAX() {
		// Use time as seed
		randomGenerator = new Random();
		randomGenerator.setSeed(System.currentTimeMillis());
	}
	
	public Vector<Company> establish() {
		daxCompanies = new Vector<Company>();
		// Initialize DAX market using DAX.in file
		try {
	        BufferedReader in = new BufferedReader(new FileReader("DAX.in"));
	        while ((temp = in.readLine()) != null) {
		        stockName = temp;
		        stockID = in.readLine();
		        quote = Float.parseFloat(in.readLine());
		        company = new Company(stockName, stockID, quote);
		        daxCompanies.addElement(company);
	        }
	        in.close();
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		return daxCompanies;
	}
	
	// Get Value by stock Name, e.g. adidas AG
	public float getValueByName(String name) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (name.equals(daxCompanies.elementAt(i).stockName)) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}

	// Get Value by stock ID, e.g. DE000A1EWWW0
	public double getValueByID(String id) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (id.equals(daxCompanies.elementAt(i).stockID)) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}
	
	// Change stock quote in a range of -0.2 and +0.2 percent, randomly
	// Return new stock quote.
	public float changeQuoteByName(String name) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (name.equals(daxCompanies.elementAt(i).stockName)) {
				float changePercent =
						(float)((randomGenerator.nextInt(41)-20))/(float)1000;
				float newQuote =
						(daxCompanies.elementAt(i).quote * ((float)1 + changePercent));
				daxCompanies.elementAt(i).quote = newQuote;
				return newQuote;
			}
		}
		return -1;
	}
	
	// Change stock quote in a range of -0.2 and +0.2 percent, randomly
	// Return new stock quote.
	public float changeQuoteByID(String id) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (id.equals(daxCompanies.elementAt(i).stockID)) {
				float changePercent =
						(float)((randomGenerator.nextInt(41)-20))/(float)1000;
				float newQuote =
						(daxCompanies.elementAt(i).quote * ((float)1 + changePercent));
				daxCompanies.elementAt(i).quote = newQuote;
				return newQuote;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		DAX dax = new DAX();
		System.out.println("adidas AG: " + dax.getValueByName("adidas AG"));
		System.out.println("new adidas AG: " + dax.changeQuoteByName("adidas AG"));
		System.out.println("adidas AG: " + dax.getValueByName("adidas AG"));
		System.out.println("new adidas AG: " + dax.changeQuoteByName("adidas AG"));
		System.out.println("adidas AG: " + dax.getValueByName("adidas AG"));
		System.out.println("new adidas AG: " + dax.changeQuoteByName("adidas AG"));
	}

}
