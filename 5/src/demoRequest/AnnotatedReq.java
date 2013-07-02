package demoRequest;

import java.io.File;

import util.ParamList;

import client.Request;

public class AnnotatedReq extends Request {

	public AnnotatedReq(File file) {
		super(file);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ParamList[] formParams() {
		// TODO Auto-generated method stub
		return new ParamList[]{};
	}
	
}
