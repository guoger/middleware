package transferFile;

import java.net.*;
import java.io.*;

import util.ParamList;

public class Receiver {
	public static final int SOURCECODE = 1;
	public static final int BYTECODE = 2;
	public static final int PARAMLIST = 3;

	public static void main(String[] args) throws IOException {
		int filesize = 6022386; // filesize temporary hardcoded

		long start = System.currentTimeMillis();
		int bytesRead;
		int current = 0;
		long length = 0;

		// localhost for testing
		ServerSocket servsock = new ServerSocket(13267);
		System.out.println("Waiting...");
		Socket socket = servsock.accept();

		InputStream is = socket.getInputStream();
		//BufferedInputStream bis = new BufferedInputStream(is);
		//DataInputStream dis = new DataInputStream(bis);
		ObjectInputStream ois = new ObjectInputStream(is);

		try {
			ParamList parList = (ParamList) ois.readObject();
			ois.close();
			is.close();
			socket.close();
			System.out.println(parList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 // receive file byte [] mybytearray = new byte [filesize];
		 InputStream is = sock.getInputStream(); FileOutputStream fos = new
		 FileOutputStream("temp-copy"); BufferedOutputStream bos = new
		 BufferedOutputStream(fos); bytesRead =
		 is.read(mybytearray,0,mybytearray.length); current = bytesRead;
		 
		 // thanks to A. C‡diz for the bug fix do { bytesRead =
		 is.read(mybytearray, current, (mybytearray.length-current));
		 if(bytesRead >= 0) current += bytesRead; } while(bytesRead > -1);
		 
		 bos.write(mybytearray, 0 , current); bos.flush(); long end =
		 System.currentTimeMillis(); System.out.println(end-start);
		 bos.close(); sock.close();
		 */
		// receive an object

	}
}
