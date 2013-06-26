public class HelloWorld {
	
	public HelloWorld() {
		
	}
	
	public static void foo() {
		System.out.println("foo");
	}
	
	public static void bar() {
		System.out.println("bar");
		foo();
	}
	
	public static void withPar(Float a, String b) {
		System.out.println("withPar(float "+a+", String "+b+")");
	}
	
	public static void withPar(String a, String b) {
		System.out.println("withPar(String "+a+", String "+b+")");
	}
	
	public void withPar(float a, String b) {
		System.out.println("withPar(float "+a+", String "+b+")");
	}
	
	public static float number() {
		return 1;
	}
	
}
