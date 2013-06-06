package stocks;

@SuppressWarnings("serial")
public class StockID extends StockIdentifier {
	
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

	public String getType() {
		return "ID";
	}

	@Override
	public String getValue() {
		return this.stockID;
	}

}
