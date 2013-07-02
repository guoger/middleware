package client;

import java.io.File;

import de.tu_berlin.kbs.mwk.test.Algebra;
import demoRequest.*;

/**
 * Choose source code/byte code file and start client. Three demos are provided in ISIS
 * NOTICE: HelloWorldReq, AnnotatedReq and EchoReq all inherit from Request abstract class.
 */
public class Client {

	public static void annotated() {
		File file = new File("./TestRepo/Annotated.class");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist: "+file);
			System.exit(1);
		}
		AnnotatedReq annoReq = new AnnotatedReq(file, null);
		annoReq.start();
	}
	
	public static void helloWorld() {
		File file = new File("./TestRepo/HelloWorld.class");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist: "+file);
			System.exit(1);
		}
		HelloWorldReq helloWorldReq = new HelloWorldReq(file, null);
		helloWorldReq.start();
	}
	
	public static void echo() {
		File file = new File("./TestRepo/Echo.class");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist: "+file);
			System.exit(1);
		}
		EchoReq echoReq = new EchoReq(file, null);
		echoReq.start();
	}
	
	public static void algebra() {
		File file = new File("./src/de/tu_berlin/kbs/mwk/test/Algebra.java");
		if (!file.exists()) {
			System.out.println(" [CLIENT] File does not exist: "+file);
			System.exit(1);
		}
		
		Object algebra = new Algebra(4);
		
		AlgebraReq alReq = new AlgebraReq(file, algebra);
		alReq.start();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//echo();
		//helloWorld();
		//annotated();
		algebra();
	}

}
