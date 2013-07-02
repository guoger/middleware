package demoRequest;

import java.io.File;

import util.ParamList;
import util.Parameter;

import client.Request;

public class EchoReq extends Request {

	public EchoReq(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ParamList[] formParams() {
		// TODO Auto-generated method stub
		ParamList echo = new ParamList("echo");
		Class<?> paramType = String.class;
		Object paramVal = "Hello TU-Berlin!";
		Parameter parameter = new Parameter(paramType, paramVal);
		echo.insert(parameter);
		return new ParamList[]{echo};
	}

}
