package StockExchange;

import org.omg.CORBA.*;

import java.io.*;


public class ResponderImpl
    extends ResponderPOA
{

    public ResponderImpl()
    {
    }
    
    public void respondQuote (float quote)
    {
        if( quote != -1 ) {
            System.out.println("Quote: " + quote);
  	    } 
  	    else {
	    	System.out.println("Company not found :(");
	    }
		System.exit(1);
	}
}
