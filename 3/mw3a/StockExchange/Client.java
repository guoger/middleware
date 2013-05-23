package StockExchange;

import java.io.*;
import org.omg.CORBA.*;

public class Client
{
    public static void start ( String args[] ) throws Exception {
        if( args.length != 1 )
        {
            System.out.println( "Usage: java StockExchange.Client <name/ID>" );
            System.exit( 1 );
        }

        File f = new File( "IOR_file");

        String nameORid = args[0];

        // initialize the ORB.
        ORB orb = ORB.init( args, null );

        BufferedReader br = new BufferedReader( new FileReader( f ));
        // get object reference from file
        org.omg.CORBA.Object obj = orb.string_to_object( br.readLine() );
        br.close();

        // and narrow it
        Quoter myQuoter = QuoterHelper.narrow( obj );
        
        // if nameORid starts with "DE" (capitals), it means we search for ID
        // else we search for Name...
        if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E')) {
            // invoke the operation by ID and print the stock Quote
            float x = myQuoter.getQuoteByID(nameORid);
            if (x == -1)
                //System.out.println("ID not found :(");
                throw new Exception("ID not found :(");
            else
                System.out.println("ID:" + nameORid + "\tQuote: " + x);                
        }
        else {
            // invoke the operation by Name and print the stock Quote                    
            float x = myQuoter.getQuoteByName(nameORid);                
            if (x == -1)
                //System.out.println("Name not found :(");
                throw new Exception("Name not found :C");
            else
                System.out.println("Name:" + nameORid + "\tQuote: " + x);
        }
    }
    public static void main( String args[] )
    {
        try {
            Client.start( args );
        } catch( Exception ex ) {
            ex.printStackTrace();
        }
    }
}

