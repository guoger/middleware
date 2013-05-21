package middleware.getQuote;

public class Company {
	public String stockName;
	public String stockID;
	public Double quote;
	public Company() {
		// TODO Auto-generated constructor stub
		stockName = "";
		stockID = "";
		quote = (double)0;
	}
	
	public Company(String stockName, String stockID, Double quote) {
		this.stockName = stockName;
		this.stockID = stockID;
		this.quote = quote;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
