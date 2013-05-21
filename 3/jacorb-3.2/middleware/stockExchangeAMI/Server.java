package middleware.stockExchangeAMI;

import java.io.FileWriter;
import java.io.PrintWriter;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.*;

public class Server {

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// test input parameter
		if (args.length != 1) {
			System.out.println("IOR file required!\n");
			System.exit(1);
		}
		
		try {
			// init ORB
			ORB orb = ORB.init(args, null);
			// init POA
			POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			poa.the_POAManager().activate();
			
			// create a RequestQuoteImpl object
			RequestQuoteImpl req = new RequestQuoteImpl();
			// create the object reference
			org.omg.CORBA.Object obj = poa.servant_to_reference(req);
			
			PrintWriter pw = new PrintWriter(new FileWriter(args[0]));
			pw.println(orb.object_to_string(obj));
			pw.flush();
			pw.close();
			
			
			// wait for requests
			orb.run();
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
	}
}
