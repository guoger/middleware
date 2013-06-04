package stocks;

public class StockID {
	
	private String stockID;
	
	public StockID() {
		this.stockID = "";
	}
	
	public StockID(String stockID) {
		this.stockID = stockID;
	}
	
	public String getID() {
		return this.stockID;
	}
	
	public void setID(String stockID) {
		this.stockID = stockID;
	}

}
