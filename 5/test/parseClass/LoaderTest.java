package parseClass;

import java.io.File;
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
			// System.out.println(float.class);
			// System.out.println(float.class+" "+Float.class);
			Object param1 = "4";
			Object param2 = "2";
			Class[] paa = new Class[2]; // Class does not have public constructor
			ArrayList<Class<?>> pab = new ArrayList<Class<?>>();
			paa[0] = param1.getClass();
			paa[1] = param2.getClass();
			pab.add(param1.getClass());
			pab.add(param2.getClass());
			
			ArrayList<Object> parameters = new ArrayList<Object>();
			parameters.add(param1);
			parameters.add(param2);
			
			Class<?> cls = Class.forName("parseClass.HelloWorld");
			// System.out.println(cls.getCanonicalName());
			Method[] methods = cls.getDeclaredMethods();
			
			// static methods
			Method tempMtd = cls.getDeclaredMethod("foo", null);
			tempMtd.invoke(null, null);
			
			tempMtd = cls.getDeclaredMethod("withPar", paa);
			tempMtd.invoke(null, "1", "2");
			
			// parameter test
			Object param = (float)5.0;
			Class<?> paramCls = param.getClass();
			System.out.println("Float type is "+paramCls);
			paramCls = float.class;
			System.out.println("float type is "+paramCls);
			
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
			/*
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
			*/
			
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
			
			// modifier
			tempMtd = cls.getDeclaredMethod("foo");
			int modifier = tempMtd.getModifiers();
			if (Modifier.isStatic(modifier)) {
				System.out.println("foo is static");
			}
			System.out.println("foo's modifier is "+modifier);
			
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
		
		String[] sp = "String ok,float 1".split(",");
		for (String s : sp) {
			System.out.println(s);
		}
		System.out.println("Length of aaaa is "+"aaaa".length());
		System.out.println("abcd".substring(0, 3));
		
		File file = new File("./serverbin");
		System.out.println("file exist: "+file.exists());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.separator);
		System.out.println(file.separatorChar);
		System.out.println(File.pathSeparator);
		String[] list = file.list();
		//for (String s : list)
		//	System.out.println(s);
		
	}
}
