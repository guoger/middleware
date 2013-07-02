package util;

/**
 * Be thrown if compilation failed.
 *
 */
public class CompilationException extends Exception {
	String fileName;
	
	public CompilationException(String fileName) {
		this.fileName = fileName;
	}
}
