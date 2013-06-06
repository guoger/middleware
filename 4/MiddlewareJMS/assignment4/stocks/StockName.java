package stocks;

@SuppressWarnings("serial")
public class StockName extends StockIdentifier {

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

	public String getType() {
		return "Name";
	}
	
	@Override
	public String getValue() {
		return this.stockName;
	}

}
