package demoRequest;

import java.io.File;

import util.ParamList;
import util.Parameter;

import client.Request;

public class AlgebraReq extends Request {

	public AlgebraReq(File file, Object obj) {
		super(file, obj);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ParamList[] formParams() {
		ParamList alge = new ParamList("add");
		Class<?> paramType = float.class;
		Object paramVal = (float) 2;
		Parameter parameter = new Parameter(paramType, paramVal);
		alge.insert(parameter);
		return new ParamList[]{alge};
	}

}
