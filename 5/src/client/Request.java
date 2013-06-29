package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import util.*;

public abstract class Request extends Thread {
	
	File file;
	
	Socket socket;
	
	public Request(File file) {
		this.file = file;
	}
	
	/** 
	 * User need to implement this abstract method to form params
	 */
	protected abstract ParamList[] formParams();
	
	/**
	 * Send ParamLists and bytecode/sourcecode to server
	 * Meanwhile, client will wait for reply, which is a serialized ReturnVal
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void sendTo(ObjectOutputStream oos) throws UnknownHostException, IOException {
		
		// For socket input, client will only receive a serialized ReturnVal
		// ObjectInputStream dis = new ObjectInputStream
		//		(new BufferedInputStream(socket.getInputStream()));
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		long length = file.length();
		
		// Send file
		oos.writeInt(ParamList.CODE);
		oos.writeUTF(file.getName());
		oos.writeLong(length);
		oos.flush();
		for (int i = 0; i < length; i++) {
			oos.write(bis.read());
		}
		oos.flush();
		
		// Send parameters
		for (ParamList pl : formParams()) {
			oos.writeInt(ParamList.PARAMLIST);
			oos.writeObject(pl);
		}
		
		// Send terminate
		oos.writeInt(ParamList.TERMINATE);
		oos.flush();
		bis.close();
		fis.close();
	}
	
	public void recv(ObjectInputStream ois) throws IOException, ClassNotFoundException {
		int flag;
		ReturnVal reply;
		while (true) {
			flag = ois.readInt();
			switch (flag) {
			case ReturnVal.RESULT:
				System.out.println(" [CLIENT] Return value: ");
				reply = (ReturnVal) ois.readObject();
				System.out.println(reply);
				break;
			case ReturnVal.LOAD_OK:
				System.out.println(" [CLIENT] Load class successfully!");
				break;
			case ReturnVal.TRANSFER_OK:
				System.out.println(" [CLIENT] Data transfered successfully!");
				break;
			case ReturnVal.COMPILATION_ERR:
				System.out.println(" [CLIENT] Compilation failed!");
				return;
			case ReturnVal.LOAD_ERR:
				System.out.println(" [CLIENT] Load class failed!");
				return;
			case ReturnVal.FILE_TYPE_ERR:
				System.out.println(" [CLIENT] File type error!");
				return;
			case ReturnVal.TERMINATE:
				System.out.println(" [CLIENT] Terminate");
				return;
			default:
				System.out.println(" [CLIENT] Unknown message");
				return;
			}
		}
	}
	
	@Override
	public void run() {
		ObjectOutputStream oos;
		ObjectInputStream ois;
		try {
			socket = new Socket("localhost", 5000);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			oos.flush();
			sendTo(oos);
			recv(ois);
			oos.close();
			ois.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
