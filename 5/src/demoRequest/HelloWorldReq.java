package demoRequest;

import java.io.File;

import util.ParamList;
import util.Parameter;

import client.Request;

public class HelloWorldReq extends Request {

	public HelloWorldReq(File file, Object obj) {
		super(file, obj);
		// TODO Auto-generated constructor stub
	}

	/**
	 * For HelloWorld.class
	 * invoke helloWorld() method.
	 */
	@Override
	protected ParamList[] formParams() {
		ParamList helloWorld = new ParamList("helloWorld");
		return new ParamList[]{helloWorld};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
