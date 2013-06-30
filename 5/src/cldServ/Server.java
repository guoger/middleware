package cldServ;

import java.io.File;
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
		File repo = new File("./ServRepo/de/tu_berlin/kbs/mwk/test");
		if (repo.exists() && repo.isDirectory()) {
			System.out.println(" [SERVER] Server repo: "+repo.getAbsolutePath());
		} else {
			System.out.println(" [SERVER] Create repo: "+repo.getAbsolutePath());
			if (!repo.mkdirs()) {
				System.out.println(" [SERVER] Create repo failed!");
				System.exit(1);
			}
		}
		
		ServerSocket server = new ServerSocket(5000);
		System.out.println(" [SERVER] Starting on port 5000\n");
		
		while (true) {
			Socket newClt = server.accept();
			System.out.println(" [SERVER] New client: "+
			newClt.getInetAddress()+":"+newClt.getPort());
			Jobs job = new Jobs(repo, newClt);
			job.start();
		}
	}

}
