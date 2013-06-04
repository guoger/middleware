package stocks;

import java.util.*;

public class Company {
	public String name;
	public String id;
	public float quote;
	
	/* For a new version of company, stockName and stockID are no longer
	 * Strings. Respectively they are Object StockName and StockID, but the
	 * String types are still used somewhere. Will be modified later.
	 */
	public StockName stockName;
	public StockID stockID;
	public StockQuote stockQuote;
	
	static Random randGen = new Random();
	
	public Company() {
		// for old version
		this.name = "";
		this.id = "";
		this.quote = (float)0;
		
		// for new version
		stockName = new StockName();
		stockID = new StockID();
		stockQuote = new StockQuote();
	}
	
	public Company(String stockName, String stockID, float quote) {
		// for old version
		this.name = stockName;
		this.id = stockID;
		this.quote = quote;
		
		// for new version
		this.stockName = new StockName(stockName);
		this.stockID = new StockID(stockID);
		this.stockQuote = new StockQuote(quote);
	}

	public float changeQuote() {
		float changePercent =
				(float)((randGen.nextInt(41)-20))/(float)1000;
		float newQuote = (this.stockQuote.getQuote()*((float)1 + changePercent));
		
		// for old version
		this.quote = newQuote;
		
		// for new version
		this.stockQuote.setQuote(newQuote);
		
		return newQuote;
	}
	
	public static String getStockName(Company company) {
		return company.stockName.getName();
	}
	
	public static String getStockID(Company company) {
		return company.stockID.getID();
	}
	
	public static float getStockQuote(Company company) {
		return company.stockQuote.getQuote();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
