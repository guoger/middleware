package test;

import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;


public class LoaderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class cls = Class.forName("test.HelloWorld");
			Object obj = cls.newInstance();
			Method[] methods = cls.getDeclaredMethods();
			for (Method m : methods) {
				Annotation[] annos = m.getAnnotations();
				for (Annotation a : annos) {
					if (a instanceof Invoke) {
						m.invoke(obj, null);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
