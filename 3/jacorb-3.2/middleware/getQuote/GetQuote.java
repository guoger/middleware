package middleware.getQuote;

import java.io.*;
import java.util.*;

import middleware.getQuote.Company;

public class GetQuote {
	String temp, stockName, stockID;
	int i;
	Double quote;
	int maxCom = 100;
	Company newCompany;
	static private Vector<Company> daxCompanies = new Vector<Company>();
	public GetQuote() {
    try {
        BufferedReader in = new BufferedReader(new FileReader("DAX.in"));
        while ((temp = in.readLine()) != null) {
        stockName = temp;
        stockID = in.readLine();
        quote = Double.parseDouble(in.readLine());
        newCompany = new Company(stockName, stockID, quote);
        daxCompanies.addElement(newCompany);
      }
      in.close();
    } catch(Exception ex) {
      ex.printStackTrace();
    }
	}
	
	public double getValueByName(String name) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (name.equals(daxCompanies.elementAt(i).stockName)) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}

	public double getValueByID(String id) {
		for (i=0; i<daxCompanies.size(); i++) {
			if (id.equals(daxCompanies.elementAt(i).stockID)) {
				return daxCompanies.elementAt(i).quote;
			}
		}
		return -1;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    GetQuote testCompany = new GetQuote();
    System.out.println("quote of adidas AG is " + testCompany.getValueByName("adidas AG"));
    System.out.println("quote of DE0005552004 is " + testCompany.getValueByID("DE0005552004"));
	}
}
