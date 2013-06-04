package stocks;

public class StockName {

	private String stockName;
	
	public StockName() {
		this.stockName = "";
	}
	
	public StockName(String stockName) {
		this.stockName = stockName;
	}
	
	public String getName() {
		return this.stockName;
	}
	
	public void setName(String stockName) {
		this.stockName = stockName;
	}

}
