package util;

public class CompilationException extends Exception {
	String fileName;
	
	public CompilationException(String fileName) {
		this.fileName = fileName;
	}
}
