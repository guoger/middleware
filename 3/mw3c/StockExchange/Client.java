package StockExchange;

import java.io.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Client
{      
    public static void main( String args[] )
    {
        if( args.length != 1 )
        {
            System.out.println( "Usage: java StockExchange.Client <name/ID>" );
            System.exit( 1 );
        }

        try
        {
            String nameORid = args[0];

            // initialize the ORB.
            ORB orb_client = ORB.init( args, null );
            
            File f = new File( "Server_IOR_file");            
            BufferedReader br = new BufferedReader( new FileReader( f ));
            // get Quoter object reference from server
            org.omg.CORBA.Object obj = orb_client.string_to_object( br.readLine() );
            br.close();
            // and narrow it
            Quoter myQuoter = QuoterHelper.narrow( obj );
            
		    POA poa = POAHelper.narrow(orb_client.resolve_initial_references("RootPOA"));
		    poa.the_POAManager().activate();            
            // create a ResponderImpl object
            ResponderImpl myResponder = new ResponderImpl();
            // create the object reference
            org.omg.CORBA.Object o = poa.servant_to_reference(myResponder);
            PrintWriter ps = new PrintWriter(new FileOutputStream(new File( "Client_IOR_file" )));
            ps.println( orb_client.object_to_string( o ) );
            ps.close();
            
            // if nameORid starts with "DE" (capitals), it means we search for ID
            // else we search for Name...
            if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E'))
                // invoke the operation by ID and print the stock Quote
                myQuoter.getQuoteByID(nameORid);
            else
                // invoke the operation by Name and print the stock Quote                    
                myQuoter.getQuoteByName(nameORid);
		    orb_client.run();                                
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }
}

