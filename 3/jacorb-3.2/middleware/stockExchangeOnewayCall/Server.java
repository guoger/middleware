package middleware.stockExchangeOnewayCall;

import java.io.*;

import middleware.getQuote.GetQuote;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server extends RequestPOA {
	double quote;
	static ORB orb;
	public Server() {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// init ORB
		orb = ORB.init(args, null);
		// init POA
		POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		poa.the_POAManager().activate();
		
		// create a RequestQuoteImpl object
		Server server = new Server();
		// create the object reference
		org.omg.CORBA.Object obj = poa.servant_to_reference(server);
					
		PrintWriter pw = new PrintWriter(new FileWriter("ServerIOR"));
		pw.println(orb.object_to_string(obj));
		pw.flush();
		pw.close();
		
		// wait for requests
		orb.run();
		System.out.println(" [SERVER]\tWaiting for caller!");
	}

	@Override
	public void requestByTab(String tab) {
		// TODO Auto-generated method stub
		GetQuote getQuote = new GetQuote();
		if (tab.startsWith("DE")) {
		    System.out.println(" [SERVER]\tGet quote by ID");
			quote = getQuote.getValueByID(tab);
			System.out.println(" [SERVER]\tValue is "+quote);
		} else {
		    System.out.println(" [SERVER]\tGet quote by Name");
			quote = getQuote.getValueByName(tab);
			System.out.println(" [SERVER]\tValue is "+quote);
		}
		/*
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		File f = new File("ClientIOR");
		// check if file exists
		if(! f.exists()) {
			System.out.println("File ServerIOR does not exist.");
			System.exit(-1);
		}
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			org.omg.CORBA.Object obj = orb.string_to_object(br.readLine());
			br.close();
			Reply reply = ReplyHelper.narrow(obj);
			
			reply.replyWithQuote(quote);
			
		} catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
