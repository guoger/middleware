package StockExchange;

import java.io.*;
import org.omg.CORBA.*;

public class Client
{
    public static void main( String args[] )
    {
        if( args.length != 2 )
        {
            System.out.println( "Usage: java StockExchange.Client <file> <name/ID>" );
            System.exit( 1 );
        }

        try
        {
            File f = new File( args[0] );

            //check if file exists
            if( ! f.exists() )
            {
                System.out.println("File " + args[0] + " does not exist.");
                System.exit( -1 );
            }

            //check if args[0] points to a directory
            if( f.isDirectory() )
            {
                System.out.println("File " + args[0] + " is a directory.");
                System.exit( -1 );
            }
            
            String nameORid = args[1];

            // initialize the ORB.
            ORB orb = ORB.init( args, null );

            BufferedReader br = new BufferedReader( new FileReader( f ));
            // get object reference from command-line argument file
            org.omg.CORBA.Object obj = orb.string_to_object( br.readLine() );
            br.close();

            // and narrow it to HelloWorld.GoodDay
            // if this fails, a BAD_PARAM will be thrown
            Quoter myQuoter = QuoterHelper.narrow( obj );
            
            // if args[1] starts with "DE" (capitals), it means we search for ID
            // else we search for Name...
            if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E')) {
                // invoke the operation by ID and print the stock Quote
                float x = myQuoter.getQuoteByID(nameORid);
                if (x == -1)
                    System.out.println("ID not found :(");
                else
                    System.out.println("ID:" + nameORid + "\tQuote: " + x);                
            }
            else {
                float x = myQuoter.getQuoteByName(nameORid);                
                if (x == -1)
                    System.out.println("Name not found :(");
                else
                    // invoke the operation by Name and print the stock Quote                    
                    System.out.println("Name:" + nameORid + "\tQuote: " + x);
            }

        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }
}

