package stocks;

@SuppressWarnings("serial")
public class StockException extends Exception {

	String mistake = "";
	public StockException() {
		super();
		mistake = "Unknown error";
	}
	
	public StockException(String err) {
		super(err);
		mistake = err;
	}
	
	public String getError() {
		return mistake;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
