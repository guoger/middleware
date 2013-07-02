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

import javax.tools.*;

import util.*;

public class Jobs extends Thread {
	Class<?> usrClaz;
	Object usrObj = null;
	Socket socket;
	final String repoDir;
	ArrayList<ParamList> mtdList = new ArrayList<ParamList>();
	// Program usrProg;
	String fileName;
	String clsName;
	String clazPath;

	public Jobs(File repo, Socket socket) {
		repoDir = repo.getAbsolutePath();
		clazPath = repoDir.split("/de")[0];
		this.socket = socket;
	}

	void recvData(ObjectInputStream ois) throws IOException,
			ClassNotFoundException, UnknownFileTypeException, CompilationException {
		int dataType;
		ParamList parList;
		while (true) {
			dataType = ois.readInt();
			if (dataType == ParamList.CODE) {
				fileName = (String) ois.readUTF();
				if (fileName.endsWith(".class")) {
					recvBytecode(ois);
				} else if (fileName.endsWith(".java")) {
					recvSrccode(ois);
				} else {
					throw new UnknownFileTypeException(fileName);
				}
			} else if (dataType == ParamList.PARAMLIST) {
				parList = (ParamList) ois.readObject();
				mtdList.add(parList);
			} else if (dataType == ParamList.OBJECT) {
				usrObj = (Object) ois.readObject();
			} else if (dataType == ParamList.TERMINATE) {
				return;
			} else {

			}
		}
	}

	Program formProgram() throws MalformedURLException, ClassNotFoundException {
		URL url = new URL("file://"+clazPath+File.separator);
		URL[] urls = new URL[] { url };
		URLClassLoader clsLoader = URLClassLoader.newInstance(urls);
		usrClaz = clsLoader.loadClass("de.tu_berlin.kbs.mwk.test."+clsName);
		Program usrProg = new Program(usrClaz, usrObj);
		
		for (ParamList pl : mtdList) {
			try {
				usrProg.retrieveMtds(pl);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				System.out.println(" [SERVER] No such method: \n"+pl);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		usrProg.retrieveAntdMtd("Invoke");
		
		return usrProg;
	}
	
	private void recvBytecode(ObjectInputStream ois) throws IOException {
		clsName = fileName.split(".class")[0];
		// clsName = fileName.substring(0, fileName.length()-6);
		long clsSize = ois.readLong();
		FileOutputStream fos = new FileOutputStream(repoDir+File.separator+fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		for (int i = 0; i < clsSize; i++) {
			bos.write(ois.read());
		}
		bos.close();
		fos.close();
	}

	private void recvSrccode(ObjectInputStream ois) throws IOException, CompilationException {
		System.out.println(" [SERVER] Receive java Source code: "+fileName);
		clsName = fileName.split(".java")[0];
		// clsName = fileName.substring(0, fileName.length()-6);
		long clsSize = ois.readLong();
		FileOutputStream fos = new FileOutputStream(repoDir+File.separator+fileName);
		BufferedOutputStream bos = new BufferedOutputStream(fos);

		for (int i = 0; i < clsSize; i++) {
			bos.write(ois.read());
		}
		bos.close();
		fos.close();
		
		System.out.println(" [SERVER] Compiling...");
		if (compile() == 0) {
			System.out.println(" [SERVER]    ...successfully!\n");
		} else {
			System.out.print(" [SERVER]    ...failed!\n");
			throw new CompilationException(fileName);
		}
	}
	
	private int compile() {
		String fileToCompile = repoDir+File.separator+fileName;
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		return compiler.run(null, null, null, fileToCompile);
	}

	@Override
	public void run() {
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		ReturnVal ret = null;
		int retFlag = ReturnVal.TERMINATE;

		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));
			oos.flush();
			ois = new ObjectInputStream(new BufferedInputStream(
					socket.getInputStream()));
		} catch (IOException e) {
			return;
		}
		
		if (ois == null || oos == null)
			return;
		
		// Receive all the data needed for loading class and retrieving
		// methods.
		try {
			recvData(ois);
			retFlag = ReturnVal.TRANSFER_OK;
			System.out.println(" [SERVER] Data received successfully!");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		} catch (UnknownFileTypeException e) {
			System.out.println(" [SERVER] Unkown file type: "+e.fileName);
			retFlag = ReturnVal.FILE_TYPE_ERR;
		} catch (CompilationException e) {
			retFlag = ReturnVal.COMPILATION_ERR;
		} finally {
			try {
				oos.writeInt(retFlag);
				oos.flush();
				retFlag = ReturnVal.TERMINATE;
				if (retFlag == ReturnVal.COMPILATION_ERR || retFlag == ReturnVal.FILE_TYPE_ERR) {
					oos.close();
					ois.close();
					socket.close();
					return;
				}
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		}
		
		// Try to form the program and retrieve the methods that will be invoked
		// later
		Program prog = null;
		try {
			prog = formProgram();
			// System.out.println(prog);
			retFlag = ReturnVal.LOAD_OK;
			System.out.println(" [SERVER] Load "
			+prog.usrClaz.getCanonicalName()+" successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			retFlag = ReturnVal.LOAD_ERR;
		} finally {
			try {
				oos.writeInt(retFlag);
				oos.flush();
				retFlag = ReturnVal.TERMINATE;
				if (retFlag == ReturnVal.LOAD_ERR) {
					oos.close();
					ois.close();
					socket.close();
					return;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		}
		
		
		ret = prog.execute();
		System.out.println(ret);
		try {
			oos.writeInt(ReturnVal.RESULT);
			oos.writeObject(ret);
			oos.writeInt(ReturnVal.TERMINATE);
			System.out.println(" [SERVER] Terminate: "
			+socket.getInetAddress()+":"+socket.getPort()+"\n");
			oos.flush();
			oos.close();
			ois.close();
			socket.close();
		} catch (IOException e) {
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
