/*
 * Consume a socket from Server and process the request of a single user
 */

package cldServ;

import java.lang.reflect.InvocationTargetException;

public class Jobs extends Thread {
	Class<?> claz;

	public Jobs() {

	}

	void loadClass() {

	}

	ParamList formParamList(String[] usrParams) {
		String[] paramUnit;
		ParamList paramList = new ParamList();
		for (String s : usrParams) {
			paramUnit = s.split(" ");
			Object obj = ParseParam.parseValue(paramUnit[0], paramUnit[1]);
			paramList.add(obj);
		}
		return paramList;
	}
	
	ParamTypes formParamTypes(String[] usrParams) throws TypeException {
		String[] paramUnit;
		ParamTypes paramTypes = new ParamTypes();
		for (String s : usrParams) {
			paramUnit = s.split(" ");
			Class<?> cls = ParseParam.parseType(paramUnit[0]);
			paramTypes.add(cls);
		}
		return paramTypes;
	}

	String[] parseParams(String usrInputParam) {
		return usrInputParam.split(",");
	}

	@Override
	public void run() {
		String input = "withPar(String 1,String 2)";
		String input1 = input.substring(0, input.length()-1);
		String[] a = input1.split("\\(");
		String mtdName = a[0];
		
		try {
			ParamList paramList = formParamList(parseParams(a[1]));
			ParamTypes paramTypes = formParamTypes(parseParams(a[1]));
			Class<?> cls = Class.forName("parseClass.HelloWorld");
			Program prog = new Program(cls);
			prog.retrieveMtds(mtdName, paramList, paramTypes);
			prog.execute();
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
		} catch (TypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jobs jobs = new Jobs();
		jobs.start();
	}

}
