package stocks;

public class StockQuote {

	private float stockQuote;
	
	public StockQuote() {
		this.stockQuote = 0;
	}
	
	public StockQuote(float stockQuote) {
		this.stockQuote = stockQuote;
	}
	
	public float getQuote() {
		return this.stockQuote;
	}
	
	public void setQuote(float stockQuote) {
		this.stockQuote = stockQuote;
	}

}
