package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import util.*;

public abstract class Request extends Thread {
	
	File file;
	
	public Request(File file) {
		this.file = file;
	}
	
	/** 
	 * User need to implement this abstract method to form params
	 */
	abstract ParamList[] formParams();
	
	/**
	 * Send ParamLists and bytecode/sourcecode to server
	 * Meanwhile, client will wait for reply, which is a serialized ReturnVal
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void sendTo() throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 5000);
		ObjectOutputStream oos = new ObjectOutputStream
				(new BufferedOutputStream(socket.getOutputStream()));
		// For socket input, client will only receive a serialized ReturnVal
		ObjectInputStream dis = new ObjectInputStream
				(new BufferedInputStream(socket.getInputStream()));
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		long length = file.length();
		
		
		// Send file
		oos.writeInt(ParamList.CODE);
		oos.writeUTF(file.getName());
		oos.writeLong(length);
		
		for (int i = 0; i < length; i++) {
			oos.write(bis.read());
		}
		
		// Send parameters
		for (ParamList pl : formParams()) {
			oos.writeInt(ParamList.PARAMLIST);
			oos.writeObject(pl);
		}
		
		// Send terminate
		oos.writeInt(ParamList.TERMINATE);
	}
}
