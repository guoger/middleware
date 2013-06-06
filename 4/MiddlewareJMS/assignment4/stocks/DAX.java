package stocks;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.Random;

@SuppressWarnings("serial")
public class DAX implements java.io.Serializable {
	transient String temp, stockName, stockID;
	transient int i;
	transient Float quote;
	transient int maxCom = 100;
	transient Company company;
	transient private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyyMMddHHmmssz");
	transient String tempTime;
	
	// Create a Vector to hold all DAX companies
	public static Vector<Company> daxCompanies = null;
	public static int daxSize;
	// Random generator
	transient static Random randomGenerator;

	public DAX() {
		// Use time as seed
		randomGenerator = new Random();
		randomGenerator.setSeed(System.currentTimeMillis());
	}
	
	public Vector<Company> establish() {
		System.out.println(" [DAX]\tEstablishing DAX......\n");
		daxCompanies = new Vector<Company>();
		// The timeStamp of initialization
		tempTime = timeStamp.format(Calendar.getInstance().getTime());
		// Initialize DAX market using DAX.in file
		try {
	        BufferedReader in = new BufferedReader(new FileReader("DAX.in"));
	        while ((temp = in.readLine()) != null) {
		        stockName = temp;
		        stockID = in.readLine();
		        quote = Float.parseFloat(in.readLine());
		        company = new Company(stockName, stockID, quote, tempTime);
		        daxCompanies.addElement(company);
		        System.out.println(" [DAX]\t"+stockName+" "+stockID+"\n\t"
		        		+quote+" | "+tempTime);
	        }
	        in.close();
	    } catch(Exception ex) {
	    	ex.printStackTrace();
	    }
		System.out.println("\n [DAX]\t......Establishment complete!\n");
		daxSize = daxCompanies.size();
		return daxCompanies;
	}
	
	public Company addCompany(String stockName, String stockID, float stockQuote) {
		tempTime = timeStamp.format(Calendar.getInstance().getTime());
		company = new Company(new StockName(stockName), new StockID(stockID), new StockQuote(stockQuote), new StockTime(tempTime));
		daxCompanies.addElement(company);
		return company;
	}
	
	public int removeCompany(String i) {
		for (Company c : daxCompanies) {
			if (c.name.equals(i) || c.id.equals(i)) {
				daxCompanies.removeElement(c);
				return 0;
			}
		}
		return 1;
	}
	
	public void printDAX() {
		System.out.println("\n [DAX]\tPrinting out DAX:\n");
		System.out.println("*****************************");
		for (Company s : daxCompanies) {
			System.out.println(" [DAX]\t"+s.name+" "+s.id+"\n\t"
	        		+s.quote+" | "+s.time);
		}
		System.out.println("*****************************");
	}
	
	/*
	 * Following methods are used in old version. It's just fine to IGNORE THIS PART.
	 * They are used to traverse the whole DAX market and try to return a specific company
	 * matching a certain StockName or StockID.
	 * 
	 * *****************Your Are Safe To IGNORE Following part******************
	 * 
	 */
	
	// Get Value by stock Name, e.g. adidas AG
	public float getValueByName(String name) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (name.equals(daxCompanies.elementAt(i).stockName.getName())) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}

	// Get Value by stock ID, e.g. DE000A1EWWW0
	public double getValueByID(String id) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (id.equals(daxCompanies.elementAt(i).stockID.getID())) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}
	
	// Change stock quote in a range of -0.2 and +0.2 percent, randomly
	// Return new stock quote.
	public float changeQuoteByName(String name) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (name.equals(daxCompanies.elementAt(i).stockName.getName())) {
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
			if (id.equals(daxCompanies.elementAt(i).stockID.getID())) {
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
