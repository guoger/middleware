package parseClass;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class LoaderTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p = "1";
		try {
			//Byte test = 5;
			//Object test1 = Integer.class.cast(test);
			Object test = "4";
			System.out.println(test.getClass());
			Class[] paa = new Class[2]; // Class does not have public constructor
			ArrayList<Class<?>> pab = new ArrayList<Class<?>>();
			paa[0] = test.getClass();
			paa[1] = test.getClass();
			pab.add(test.getClass());
			pab.add(test.getClass());
			
			ArrayList<Object> parameters = new ArrayList<Object>();
			parameters.add(test);
			parameters.add(test);
			
			Class<?> cls = Class.forName("parseClass.HelloWorld");
			System.out.println(cls.getCanonicalName());
			Method[] methods = cls.getDeclaredMethods();
			
			// static methods
			Method tempMtd = cls.getDeclaredMethod("foo", null);
			tempMtd.invoke(null, null);
			
			tempMtd = cls.getDeclaredMethod("withPar", paa);
			tempMtd.invoke(null, "1", "2");
			// converting arraylist to array will work
			Class[] tempClaz = new Class[pab.size()];
			int index = 0;
			for (Class c : pab) {
				tempClaz[index] = c;
				index++;
			}
			tempMtd = cls.getDeclaredMethod("withPar", tempClaz);
			tempMtd.invoke(null, parameters.toArray());
			
			//tempMtd = cls.getDeclaredMethod("withPar", pab.toArray());
			
			Object obj = cls.newInstance();
			// parameters
			Object pa = "5";
			Object[] paraList = null;
			Class[] paramTyArr = null;
			for (Method m : methods) {
				paramTyArr = m.getParameterTypes();
				index = 0;
				if (paramTyArr.length != 0) {
					paraList = new Object[paramTyArr.length];
					for (Class c : paramTyArr) {
						System.out.println("Parameter Type is: " + c);
						System.out.println("Is primitive? " + c.isPrimitive());
						paraList[index] = c.cast(pa);
						index++;
					}
					m.invoke(obj, paraList);
				}
			}
			
			// annotation
			for (Method m : methods) {
				Annotation[] annos = m.getAnnotations();
				for (Annotation a : annos) {
					// System.out.println(a.toString());
					if (a.toString().startsWith("@parseClass.Invoke")) {
						System.out.println(a+" has annotation Invoke");
					}
					if (a instanceof Invoke) {
						m.invoke(obj, null);
					}
				}
			}
			
			
			// return type
			tempMtd = cls.getDeclaredMethod("number", null);
			Object ret = tempMtd.invoke(obj, null);
			System.out.println(ret);
			System.out.println(ret.getClass());
			tempMtd = cls.getDeclaredMethod("foo", null);
			Type retType = tempMtd.getReturnType();
			if (retType.equals(void.class)) {
				System.out.println("void");
			}
			ret = tempMtd.invoke(obj, null);
			System.out.println(ret);
			//System.out.println(retType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
