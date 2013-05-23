package middleware.stockExchangeSII;

import java.io.*;
import java.util.*;
import org.omg.CORBA.*;

public class Client {

	static String req = "";
	public Client() {
		// TODO Auto-generated constructor stub
	}

    public static void start( String[] args ) throws Exception {
        File f = new File(args[0]);
        // check if file exists
        if(! f.exists()) {
            System.out.println(" [CLIENT]\tFile " + args[0] + " does not exist.");
            System.exit(-1);
        }
        // check if args[0] points to a directory
        if( f.isDirectory() )
        {
            System.out.println(" [CLIENT]\tFile " + args[0] + " is a directory.");
            System.exit( -1 );
        }
        
        // get stock arg
        for (int i=1; i<args.length; i++) {
            req += (args[i] + " ");
        }
        req = req.substring(0, req.length() - 1);
        
        // init the ORB
        ORB orb = ORB.init(args, null);
        
        // get object from IOR file
        BufferedReader br = new BufferedReader(new FileReader(f));
        org.omg.CORBA.Object obj = orb.string_to_object(br.readLine());
        
        br.close();
        
        // narrow it to RequestQuote
        RequestQuote reqQuote = RequestQuoteHelper.narrow(obj);
        
        // invoke request operation
        double quote = reqQuote.reqQuote(req);
        if( quote != -1 ) {
            System.out.println(" [CLIENT]\t\tName/ID\t\t\tQuote");
            System.out.println(" [CLIENT]\t\t"+req+"\t\t"+quote);
        } else {
            throw new Exception("[CLIENT]\t\tInvalid input!!");
        }
    }

	public static void main(String[] args) {
		if (args.length <= 1) {
			System.out.println(" [CLIENT]\tIOR file and stock request txt required!");
			System.exit(1);
		}
		
		try {
            Client.start(args);
		} catch(Exception ex) {
			System.out.println(ex);
		}

	}

}
