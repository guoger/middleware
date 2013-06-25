package parseClass;

public class HelloWorld {
	
	@Invoke(str = "HEIHEI")
	public static void foo() {
		System.out.println("foo");
	}
	
	@Invoke(str = "HAHA")
	public static void bar() {
		System.out.println("bar");
		foo();
	}
	
	public static void withPar(String a, String b) {
		System.out.println("OK, integer is "+a+" and "+b);
	}
	
	public static float number() {
		return 1;
	}
	
}
