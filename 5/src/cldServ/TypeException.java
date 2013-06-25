package cldServ;

public class TypeException extends Exception {
	String errType;
	
	public TypeException() {
		super();
		this.errType = "Unknown Type";
	}
	
	public TypeException(String type) {
		super(type);
		this.errType = type;
	}
	
	public String getErr() {
		return this.errType;
	}

}
