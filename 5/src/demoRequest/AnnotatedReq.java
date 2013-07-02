package demoRequest;

import java.io.File;

import util.ParamList;

import client.Request;

public class AnnotatedReq extends Request {


	public AnnotatedReq(File file, Object obj) {
		super(file, obj);
		// TODO Auto-generated constructor stub
	}

	/**
	 * For Annotated.class
	 * Methods labeled with InvokeThis will be automatically invoked
	 * No user specified method, ParamList[] length is 0.
	 */
	@Override
	protected ParamList[] formParams() {
		// TODO Auto-generated method stub
		return new ParamList[]{};
	}
	
}
