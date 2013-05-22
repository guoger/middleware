package StockExchange;

import java.io.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;


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
            File f = new File( "IOR_file" );
            String nameORid = args[0];
            
            // initialize the ORB.
            ORB orb = ORB.init( args, null );
            
            BufferedReader br = new BufferedReader( new FileReader( f ));
            // get object reference from command-line argument file
            org.omg.CORBA.Object obj = orb.string_to_object( br.readLine() );
            br.close();

            Quoter myQuoter = QuoterHelper.narrow( obj );
         
            // Argument
            NVList argList = orb.create_list (1);   // create the argument list 
            Any argument = orb.create_any ();       // create an ANY object for the argument value
            argument.insert_string (nameORid);      // insert the argument value
            // insert the ANY object to the argument list and wrap it in a NamedValue 
            NamedValue nvArg = argList.add_value ("symbol", argument, org.omg.CORBA.ARG_IN.value);
            
            // Return Value
            Any result = orb.create_any ();         // create an ANY object for the return value 
            result.insert_float (0);                // insert a float value
            // Wrap the ANY object in a NamedValue
            NamedValue resultVal = orb.create_named_value ("result", result, org.omg.CORBA.ARG_OUT.value);

            // if nameORid starts with "DE" (capitals), it means we search for ID
            // else we search for Name...
            if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E')) {
                // Create the method request
                Request thisReq = obj._create_request (null, "getQuoteByID", argList, resultVal);
                thisReq.invoke ();                      // invoke the request
                result = thisReq.result().value ();     // get the result
                float x = result.extract_float ();      // extract the value
                if (x == -1)
                    System.out.println("ID not found :(");
                else
                    System.out.println("ID:" + nameORid + "\tQuote: " + x);                
            }
            else {
                Request thisReq = obj._create_request (null, "getQuoteByName", argList, resultVal);
                thisReq.invoke ();
                result = thisReq.result().value();
                float x = result.extract_float ();                                
                if (x == -1)
                    System.out.println("Name not found :(");
                else
                    System.out.println("Name:" + nameORid + "\tQuote: " + x);
            }

        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
    }
}

