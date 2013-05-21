package middleware.stockExchangeOnewayCall;

import java.io.*;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Client extends ReplyPOA {

	static String req = "";
	public Client() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			System.out.println(" [CLIENT]\tStock request txt required!");
			System.exit(1);
		}

		File f = new File("ServerIOR");
		// check if file exists
		if(! f.exists()) {
			System.out.println("File ServerIOR does not exist.");
			System.exit(-1);
		}
		
		// get stock arg
		for (int i=0; i<args.length; i++) {
			req += (args[i] + " ");
		}
		req = req.substring(0, req.length() - 1);
		
		// init the ORB
		ORB orb = ORB.init(args, null);
		
		// get Request object from ServerIOR file
		BufferedReader br = new BufferedReader(new FileReader(f));
		org.omg.CORBA.Object obj = orb.string_to_object(br.readLine());
		br.close();
		Request request = RequestHelper.narrow(obj);
		
		// register Client into ClientIOR file
		POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		poa.the_POAManager().activate();
		Client client = new Client();
		obj = poa.servant_to_reference(client);
		PrintWriter pw = new PrintWriter(new FileWriter("ClientIOR"));
		pw.println(orb.object_to_string(obj));
		pw.flush();
		pw.close();
		
		request.requestByTab(req);
		
		orb.run();
		
		System.out.println(" [CLIENT]\tWaiting for reply!\n");
	}

	@Override
	public void replyWithQuote(double quote) {
		if( quote != -1 ) {
	        System.out.println(" [CLIENT]\t\tName/ID\t\t\tQuote");
	        System.out.println(" [CLIENT]\t\t"+req+"\t\t"+quote);
	    } else {
	    	System.out.println(" [CLIENT]\t\tInvalid input!!");
	    }
		System.exit(1);
	}
}
