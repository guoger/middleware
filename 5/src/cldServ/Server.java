package cldServ;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

	public Server() {
		
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(5000);
		System.out.println(" [SERVER] Starting on port 5000\n");
		while (true) {
			Socket newClt = server.accept();
			System.out.println("new client "+newClt.getInetAddress());
			Jobs job = new Jobs(newClt);
			job.start();
		}
	}

}
