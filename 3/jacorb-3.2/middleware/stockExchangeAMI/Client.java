package middleware.stockExchangeAMI;

import java.io.*;

import org.omg.CORBA.ORB;

public class Client {
	static String req = "";
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length <= 1) {
			System.out.println(" [CLIENT]\tIOR file and stock request txt required!");
			System.exit(1);
		}
		
		try {
			File f = new File(args[0]);
			// check if file exists
			if(! f.exists()) {
				System.out.println("File " + args[0] + " does not exist.");
				System.exit(-1);
			}
			// check if args[0] points to a directory
			if( f.isDirectory() )
            {
                System.out.println("File " + args[0] + " is a directory.");
                System.exit( -1 );
            }
			
			// get stock arg
			for (int i=1; i<args.length; i++) {
				req += (args[i] + " ");
			}
			req = req.substring(0, req.length() - 1);
			
			System.out.println(" [CLIENT]\tMessage is "+req);
			// init the ORB
			ORB orb = ORB.init(args, null);
			
			// get object from IOR file
			BufferedReader br = new BufferedReader(new FileReader(f));
			org.omg.CORBA.Object obj = orb.string_to_object(br.readLine());
			
			br.close();
			
			// narrow it to RequestQuote
			RequestQuote reqQuote = RequestQuoteHelper.narrow(obj);
			AMI_RequestQuoteHandler reqHandler =
					new AMI_RequestQuoteHandlerImpl()._this(orb);
			
			System.out.println(" [CLIENT]\tSending AMI request...");
			((_RequestQuoteStub)reqQuote).sendc_getQuote(reqHandler, req);
			System.out.println
				(" [CLIENT]\t...done! Waiting for reply, but you can do blah!");
			
			try {
				Thread.sleep(10000);
			} catch(Exception ex) {
			}
			
		}
		catch( Exception ex ) {
			System.err.println( ex );
		}

	}

}
