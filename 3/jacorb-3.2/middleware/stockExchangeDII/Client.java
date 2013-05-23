package middleware.stockExchangeDII;

import java.io.*;

import org.omg.CORBA.*;
// import org.omg.CosNaming.*;

public class Client {
	
	static String req = "";
	static double quote;
	public Client() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length <= 1) {
			System.out.println(" [CLIENT]\tIOR file and stock request txt required!");
			System.exit(1);
		}
		
		try {
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
			// RequestQuote reqQuote = RequestQuoteHelper.narrow(obj);
			
			// Input Argument
			NVList argList = orb.create_list (1);
			Any argument = orb.create_any ();
			argument.insert_string(req);      // insert the argument value
			NamedValue nvArg = argList.add_value("symbol", argument, org.omg.CORBA.ARG_IN.value);

			// Return Value
			Any result = orb.create_any ();
			result.insert_double(0);
			NamedValue resultVal = orb.create_named_value("result", result, org.omg.CORBA.ARG_OUT.value);
			
			// Create the request
			Request thisReq = obj._create_request(null, "reqQuote", argList, resultVal);
			thisReq.invoke();
			result = thisReq.result().value();
			quote = result.extract_double();
			
			// invoke request operation
			if( quote != -1 ) {
				System.out.println(" [CLIENT]\t\tName/ID\t\t\tQuote");
				System.out.println(" [CLIENT]\t\t"+req+"\t\t"+quote);
			} else {
				System.out.println(" [CLIENT]\t\tInvalid input!!");
			}
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
