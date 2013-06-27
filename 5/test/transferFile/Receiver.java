package transferFile;

import java.net.*;
import java.io.*;

import util.ParamList;

public class Receiver {
	public static final int TERMINATE = -1;
	public static final int SOURCECODE = 1;
	public static final int BYTECODE = 2;
	public static final int PARAMLIST = 3;

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
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
		// BufferedInputStream bis = new BufferedInputStream(is);
		// DataInputStream dis = new DataInputStream(bis);
		ObjectInputStream ois = new ObjectInputStream(is);

		while (true) {
			int recvType = ois.readInt();
			if (recvType == SOURCECODE) {
				System.out.println("Receive a source code");
			} else if (recvType == PARAMLIST) {
				System.out.println("Receive a paramlist");
				ParamList parList = (ParamList) ois.readObject();
				System.out.println(parList);
			} else if (recvType == BYTECODE) {
				System.out.println("Receive a byte code");
			} else if (recvType == -1) {
				break;
			}
		}
		ois.close();
		is.close();
		socket.close();

	}
}
