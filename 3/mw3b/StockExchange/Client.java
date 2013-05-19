package StockExchange;

import java.io.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;


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
            
            //akis
            //org.omg.CORBA.Object ncRef = orb.resolve_initial_references ("RootPOA");             

            BufferedReader br = new BufferedReader( new FileReader( f ));
            // get object reference from command-line argument file
            org.omg.CORBA.Object obj = orb.string_to_object( br.readLine() );
            br.close();

            // Get a reference to the object 
            //org.omg.CORBA.Object ncRef = orb.resolve_initial_references ("NameService"); 
            //NamingContext nc = NamingContextHelper.narrow (ncRef); 
            //NameComponent nComp = new NameComponent ("NASDAQ", ""); 
            //NameComponent [] path = {nComp}; 
            //org.omg.CORBA.Object obj = nc.resolve (path);             
            
            // and narrow it to HelloWorld.GoodDay
            // if this fails, a BAD_PARAM will be thrown
            Quoter myQuoter = QuoterHelper.narrow( obj );
            
            // from gsraj.tripod
            // Now make a dynamic call to the get_price method.  The first step is 
            // to build the argument list. In this case, there's a single String 
            // argument to the method, so create an NVList of length 1.  Next 
            // create an Any object to hold the value of the argument and insert 
            // the desired value.  Finally, wrap the Any object with a NamedValue 
            // and insert it into the NVList, specifying that it is an input 
            // parameter.            
            NVList argList = orb.create_list (1); 
            Any argument = orb.create_any (); 
            argument.insert_string (nameORid/*"something"*/); 
            NamedValue nvArg = argList.add_value ("symbol", argument, org.omg.CORBA.ARG_IN.value);
            
            // Create an Any object to hold the return value of the method and 
            // wrap it in a NamedValue 
            Any result = orb.create_any (); 
            result.insert_float (0); 
            NamedValue resultVal = orb.create_named_value ("result", result, org.omg.CORBA.ARG_OUT.value);                              

            // Create the method request using the default context, the name of 
            // the method, the NVList argument list, and the NamedValue for the 
            // result.  Then invoke the method by calling invoke () on the Request. 
            //Request thisReq = obj._create_request (null, "getQuoteByName", argList, resultVal); 
            //thisReq.invoke (); 
            
            // Get the return value from the Request object and output results. 
            //result = thisReq.result().value (); 
            //System.out.println ("getQuoteByName() returned: " + result.extract_float ()); 
            
            // if args[1] starts with "DE" (capitals), it means we search for ID
            // else we search for Name...
            if ((nameORid.charAt(0) == 'D') && (nameORid.charAt(1) == 'E')) {
                Request thisReq = obj._create_request (null, "getQuoteByID", argList, resultVal);
                thisReq.invoke ();
                result = thisReq.result().value ();
                float x = result.extract_float (); 
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

