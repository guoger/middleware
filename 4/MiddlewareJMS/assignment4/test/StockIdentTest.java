package test;

import stocks.StockIdentifier;
import stocks.StockName;

public class StockIdentTest {

	public StockIdentTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StockName s = new StockName("test");
		System.out.println("StockName has name "+s.getName());
		StockIdentifier p = new StockName("test2");
		System.out.println("StockIdentifier has value "+p.getValue());
		System.out.println("StockIdentifier has type "+p.getType());
	}

}
