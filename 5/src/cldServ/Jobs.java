/*
 * Deal with socket
 */

package cldServ;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import util.*;

public class Jobs extends Thread {
	Class<?> usrClaz;
	Object usrObj = null;
	Socket socket;
	static final String dirPath = "/Users/guoger/workspace/middleware/5/serverbin";
	boolean dupClaz = false;
	ArrayList<ParamList> mtdList = new ArrayList<ParamList>();
	Program usrProg;
	String fileName;
	String clsName;

	public Jobs(Socket socket) {
		this.socket = socket;
	}

	void recvData(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		int dataType;
		ParamList parList;
		while (true) {
			dataType = ois.readInt();
			if (dataType == ParamList.CODE && !dupClaz) {
				dupClaz = true;
				fileName = (String) ois.readUTF();
				clsName = fileName.substring(0, fileName.length()-6);
				long clsSize = ois.readLong();
				FileOutputStream fos = new FileOutputStream(dirPath + "/"
						+ fileName);
				BufferedOutputStream bos = new BufferedOutputStream(fos);

				for (int i = 0; i < clsSize; i++) {
					bos.write(ois.read());
				}
				bos.close();
				fos.close();
			} else if (dataType == ParamList.PARAMLIST) {
				parList = (ParamList) ois.readObject();
				mtdList.add(parList);
			} else if (dataType == ParamList.TERMINATE) {
				return;
			} else {

			}
		}
	}

	void formProgram() throws MalformedURLException, ClassNotFoundException,
			SecurityException, NoSuchMethodException, InstantiationException,
			IllegalAccessException {
		URL url = new URL("file://"+dirPath+"/");
		URL[] urls = new URL[] { url };
		URLClassLoader clsLoader = URLClassLoader.newInstance(urls);
		usrClaz = clsLoader.loadClass(clsName);
		usrProg = new Program(usrClaz, usrObj);
		
		for (ParamList pl : mtdList) {
			usrProg.retrieveMtds(pl);
		}
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ReturnVal ret = null;

		try {
			ois = new ObjectInputStream(new BufferedInputStream(
					socket.getInputStream()));
			// Receive all the data needed for loading class and retrieving
			// methods.
			recvData(ois);
			// Try to form the program and retrieve the methods that will be invoked
			// later
			formProgram();
			// Try to form the program and retrieve the methods that will be invoked
			// later
			ret = usrProg.execute();
			
			System.out.println(ret);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
