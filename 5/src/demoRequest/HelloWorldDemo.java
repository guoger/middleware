package demoRequest;

import java.io.File;

import util.ParamList;
import util.Parameter;

import client.Request;

public class HelloWorldDemo extends Request {

	public HelloWorldDemo(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected ParamList[] formParams() {
		// TODO Auto-generated method stub
		ParamList pl = new ParamList("number");
		/*
		Class<?> paramType = float.class;
		Object paramVal = (float) 1.0;
		Parameter parameter = new Parameter(paramType, paramVal);
		pl.insert(parameter);
		paramType = String.class;
		paramVal = "OK";
		parameter = new Parameter(paramType, paramVal);
		pl.insert(parameter);
		*/
		return new ParamList[]{pl};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
