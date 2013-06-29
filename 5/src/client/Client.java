package client;

import java.io.File;

import demoRequest.HelloWorldDemo;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("./clientpool/HelloWorld.java");
		// System.out.println(file.getName());
		HelloWorldDemo hw = new HelloWorldDemo(file);
		hw.start();
	}

}
