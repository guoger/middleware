package stocks;

public abstract class StockIdentifier {

	private String value;
	
	public StockIdentifier() {
		this.value = "";
	}
	
	public StockIdentifier(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public abstract String getType();

}
