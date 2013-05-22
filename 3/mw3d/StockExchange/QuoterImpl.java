package StockExchange;

import org.omg.CORBA.*;

import java.io.*;
import java.util.Vector;
import java.util.Enumeration;


public class QuoterImpl
    extends QuoterPOA
{
    private Vector StockCompanies = new Vector();    

    public QuoterImpl()
    {
        try 
        {
            FileReader fr = new FileReader("DAX.in");
            BufferedReader in = new BufferedReader(fr);
            
            for (int i = 0; i < 30; i++) {
                String name = in.readLine();
                String id = in.readLine();
                float quote = Float.parseFloat( in.readLine() );
                Company newCompany = new Company(name, id, quote);
                StockCompanies.addElement(newCompany);
            }
            
            in.close();
            fr.close();
            
            for (int i = 0; i < 30; i++) {
                Company curCompany = (Company) StockCompanies.elementAt(i);
                curCompany.printCompany();
            }
            System.out.println("QuoterImpl executed!");            
        }
        catch(Exception ex)
        {
            ex.printStackTrace();            
        }
    }

    public float getQuoteByName (String stockName)
    {
        System.out.println("QuoteByName:Called for "+stockName);
        // Traverse entire list, printing them all out
        for (Enumeration e = StockCompanies.elements(); e.hasMoreElements();)
        {
	        Company curCompany = (Company) e.nextElement();
	        String compName = (String) curCompany.getName();
	        if (stockName.equals(compName))
	            return curCompany.getQuote();
        }
        System.out.println("QuoteByName:Error-not found!");        
        return -1;
    }

    public float getQuoteByID (String stockID)
    {
        System.out.println("QuoteByID:Called for "+stockID);
        // Traverse entire list, printing them all out
        for (Enumeration e = StockCompanies.elements(); e.hasMoreElements();)
        {
	        Company curCompany = (Company) e.nextElement();
	        String compID = (String) curCompany.getID();
	        if (stockID.equals(compID))
	            return curCompany.getQuote();
        }
        System.out.println("QuoteByID:Error-not found!");        
        return -1;
    }
}
