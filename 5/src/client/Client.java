package client;

import java.io.File;

import demoRequest.*;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("./TestRepo/HelloWorld.java");
		//File file = new File("./clientpool/HelloWorld.class");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist!");
			System.exit(1);
		}
		// System.out.println(file.getName());
		HelloWorldDemo demo = new HelloWorldDemo(file);
		//EchoDemo demo = new EchoDemo(file);
		demo.start();
	}

}
