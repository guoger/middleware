package client;

import java.io.File;

import demoRequest.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file1 = new File("./TestRepo/HelloWorld.class");
		if (!file1.exists()) {
			System.out.println(" [CLIENT] File does not exist!");
			System.exit(1);
		}
		HelloWorldReq helloWorldReq = new HelloWorldReq(file1);
		helloWorldReq.start();
		
		File file2 = new File("./TestRepo/Annotated.class");
		if (!file2.exists()) {
			System.out.println(" [CLIENT] File does not exist!");
			System.exit(1);
		}
		AnnotatedReq annoReq = new AnnotatedReq(file2);
		annoReq.start();
		
		File file3 = new File("./TestRepo/Echo.class");
		if (!file3.exists()) {
			System.out.println(" [CLIENT] File does not exist!");
			System.exit(1);
		}
		EchoReq echoReq = new EchoReq(file3);
		echoReq.start();
		
	}

}
