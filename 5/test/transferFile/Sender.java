package transferFile;

import java.net.*;
import java.io.*;

import util.ParamList;
import util.Parameter;

public class Sender {
	public static void main(String[] args) throws IOException {
		File file = new File("temp");
		System.out.println(file.getName());
		// create socket
		Socket sock = new Socket("localhost", 13267);
		OutputStream os = sock.getOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(os);
		
		// prepare parameters
		Parameter parameter = null;
		Class<?> paramType = null;
		Object paramVal = null;

		ParamList foo = new ParamList("withPar");
		
		paramType = float.class;
		paramVal = (float) 1.0;
		parameter = new Parameter(paramType, paramVal);
		foo.insert(parameter);
		paramType = String.class;
		paramVal = "OK";
		parameter = new Parameter(paramType, paramVal);
		foo.insert(parameter);
		
		oos.writeInt(Receiver.PARAMLIST);
		oos.writeObject(foo);
		
		oos.writeInt(Receiver.PARAMLIST);
		oos.writeObject(foo);
		
		oos.writeInt(Receiver.TERMINATE);
		
		oos.close();
		os.close();
		sock.close();
		// send an object

	}
}
