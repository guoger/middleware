package client;

import java.io.File;

import demoRequest.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//File file = new File("./TestRepo/Annotated.class");
		File file = new File("./bin/de/tu_berlin/kbs/mwk/test/HelloWorld.class");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist!");
			System.exit(1);
		}
		// System.out.println(file.getName());
		HelloWorldDemo demo = new HelloWorldDemo(file);
		//EchoDemo demo = new EchoDemo(file);
		//AnnotatedDemo demo = new AnnotatedDemo(file);
		demo.start();
	}

}
