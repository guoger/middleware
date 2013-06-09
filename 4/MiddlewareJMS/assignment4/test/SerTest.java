package test;

import java.io.*;

import stocks.DAX;

public class SerTest {

	public SerTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float quote;
		DAX dax = new DAX();
		dax.establish();
		System.out.println(dax.daxCompanies.elementAt(0).getStockQuote());
		try {
			FileOutputStream fileOut =
					new FileOutputStream("DAX.der");
			ObjectOutputStream out =
					new ObjectOutputStream(fileOut);
			out.writeObject(dax);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			FileInputStream fileIn =
					new FileInputStream("DAX.der");
			ObjectInputStream in =
					new ObjectInputStream(fileIn);
			dax = (DAX) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		quote = dax.getValueByName("adidas AG");
		System.out.println("adidas AG quote price is: "+quote);
		System.out.printf("%.1f", quote);
		
	}

}
