package stocks;

import java.util.*;

public class Company {
	public String name;
	public String id;
	public float quote;
	public String time;
	
	/* For a new version of company, stockName and stockID are no longer
	 * Strings. Respectively they are Object StockName and StockID, but the
	 * String types are still used somewhere. Will be modified later.
	 */
	public StockName stockName;
	public StockID stockID;
	public StockQuote stockQuote;
	public StockTime stockTime;
	
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
	
	public Company(String stockName, String stockID, float quote, String stockTime) {
		// for old version
		this.name = stockName;
		this.id = stockID;
		this.quote = quote;
		this.time = stockTime;
		
		// for new version
		this.stockName = new StockName(stockName);
		this.stockID = new StockID(stockID);
		this.stockQuote = new StockQuote(quote);
		this.stockTime = new StockTime(stockTime);
	}

	public void setQuote(float newQuote) {
		// for old version
		this.quote = newQuote;
		
		// for new version
		this.stockQuote.setQuote(newQuote);
	}
	
	public void setTime(String time) {
		this.stockTime.setTime(time);
	}
	
	public String getStockName() {
		return this.stockName.getName();
	}
	
	public String getStockID() {
		return this.stockID.getID();
	}
	
	public float getStockQuote() {
		return this.stockQuote.getQuote();
	}
	
	public String getStockTime() {
		return this.stockTime.getTime();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
