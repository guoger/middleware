package util;

/**
 * Thrown if received file is neither .java nor .class
 *
 */
public class UnknownFileTypeException extends Exception {
	public String fileName;
	
	public UnknownFileTypeException(String fileName) {
		super(fileName);
		this.fileName = fileName;
	}
	
	
}
