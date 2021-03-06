package StockExchange;

import java.io.*;
import java.util.Vector;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class Server
{
    static ORB orb_server;
    
    public static void main(String[] args) throws Exception
    {
        //init ORB
        orb_server = ORB.init( args, null );

        //init POA
        POA poa = POAHelper.narrow( orb_server.resolve_initial_references( "RootPOA" ));
        poa.the_POAManager().activate();

        // create a QuoterImpl object
        QuoterImpl myQuoter = new QuoterImpl();

        // create the object reference
        org.omg.CORBA.Object o = poa.servant_to_reference(myQuoter);

        PrintWriter ps = new PrintWriter(new FileOutputStream(new File( "Server_IOR_file" )));
        ps.println( orb_server.object_to_string( o ) );
        ps.close();
        
        orb_server.run();
    }
}
