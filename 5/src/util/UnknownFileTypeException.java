package util;

public class UnknownFileTypeException extends Exception {
	public String fileName;
	
	public UnknownFileTypeException(String fileName) {
		super(fileName);
		this.fileName = fileName;
	}
	
	
}
