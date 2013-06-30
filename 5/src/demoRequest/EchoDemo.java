package demoRequest;

import java.io.File;

import util.ParamList;
import util.Parameter;

import client.Request;

public class EchoDemo extends Request {

	public EchoDemo(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ParamList[] formParams() {
		// TODO Auto-generated method stub
		ParamList pl = new ParamList("echo");
		Class<?> paramType = String.class;
		Object paramVal = "Hello";
		Parameter parameter = new Parameter(paramType, paramVal);
		pl.insert(parameter);
		return new ParamList[]{pl};
	}

}
