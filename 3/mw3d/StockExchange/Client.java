package StockExchange;

import java.io.*;
import org.omg.CORBA.*;

public class Client
{
    public static void main( String args[] )
    {
        if( args.length != 1 )
        {
            System.out.println( "Usage: java StockExchange.Client <name/ID>" );
            System.exit(1);
        }

        try
        {
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
			
			// create quoterhandler
			AMI_QuoterHandler quoterHandler = new AMI_QuoterHandlerImpl()._this(orb);
            
            // send request
            if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E'))
                ((_QuoterStub)myQuoter).sendc_getQuoteByID(quoterHandler, nameORid);           
            else
                ((_QuoterStub)myQuoter).sendc_getQuoteByName(quoterHandler, nameORid);
            
            System.out.println(" Sent AMI request! Waiting for reply...");
			
			try {
				Thread.sleep(10000);
			} catch(Exception ex) {
			}

        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }
}

