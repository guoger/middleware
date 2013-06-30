package parseClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotatedTest {
	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class<?> claz = Class.forName("de.tu_berlin.kbs.mwk.test.Annotated");
		Method[] methods = claz.getDeclaredMethods();
		for (Method m : methods) {
			Annotation[] annos = m.getDeclaredAnnotations();
			for (Annotation a : annos) {
				// System.out.println(a.toString());
				System.out.println(a.toString());
				if (a.toString().endsWith("InvokeThis")) {
					System.out.println(a + " has annotation Invoke");
				}
			}
			System.out.println(m.invoke(null, null));
		}
	}
}
