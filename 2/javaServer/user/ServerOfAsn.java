package user;

import java.net.*;
import java.io.*;
import java.util.Random;
import java.lang.Object;

import testasn1.TestQuestion;

import com.turkcelltech.jac.*;
import com.chaosinmotion.asn1.*;

public class ServerOfAsn {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(" [SERVER] Starting on port 5000\n");
		ServerSocket server = new ServerSocket(5000);
		while (true) {
			Socket client = server.accept();
			Connection c = new Connection(client);
			c.start();
		}
	}
}
