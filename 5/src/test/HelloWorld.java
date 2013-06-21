package test;

public class HelloWorld {
	@Invoke(str = "HEIHEI")
	public void print() {
		System.out.println("Hello world!");
	}
	
	@Invoke(str = "HAHA")
	public static void printIt() {
		System.out.println("With annotation");
	}
}
