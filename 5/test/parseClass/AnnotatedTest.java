package parseClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotatedTest {
	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> claz = Class.forName("parseClass.HelloAnno");
		Method[] methods = claz.getDeclaredMethods();
		for (Method m : methods) {
			Annotation[] annos = m.getDeclaredAnnotations();
			for (Annotation a : annos) {
				System.out.println(a);
			}
			System.out.println(m);
		}
	}
}
