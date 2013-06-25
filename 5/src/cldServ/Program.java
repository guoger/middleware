/*
 * Program.java
 * consume a Class and do actual things
 */

package cldServ;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Program extends HashMap<Method, ParamList> {
	Class<?> usrClaz;
	Object usrObj = null;

	public Program(Class<?> claz) {
		this.usrClaz = claz;
	}

	public Program(Class<?> claz, Object obj) {
		this.usrClaz = claz;
		this.usrObj = obj;
	}

	void retrieveMtds(String mtdName, ParamList paramList, ParamTypes paramTypes)
			throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException {
		// ParamTypes parTypes = parList.convertToTypes();
		int index = 0;
		Class<?>[] paramTypesArray = new Class<?>[paramTypes.size()];
		for (Class<?> cls : paramTypes) {
			paramTypesArray[index] = cls;
			index++;
		}
		Method usrMtd = usrClaz.getMethod(mtdName, paramTypesArray);
		this.put(usrMtd, paramList);
		// Check whether the method is static, which indicates whether to
		// instantiate an object. If an object already exists, just ignore
		int mod = usrMtd.getModifiers();
		if (!Modifier.isStatic(mod) && this.usrObj == null) {
			this.usrObj = this.usrClaz.newInstance();
		}
	}

	void retrieveAntdMtd(String annotation) {
		Method[] allMtd = usrClaz.getMethods();
		for (Method m : allMtd) {
			Annotation[] allAnno = m.getDeclaredAnnotations();
			for (Annotation anno : allAnno) {
				if (anno.toString().contains(annotation)) {
					if (!this.containsKey(m)) {
						this.put(m, null);
					}
				}
			}
		}
	}

	public ReturnVal execute() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		ReturnVal retVal = new ReturnVal();
		Object tempObj = null;
		for (Method mtd : this.keySet()) {
			tempObj = mtd.invoke(usrObj, this.get(mtd).toArray());
			retVal.put(mtd, tempObj);
		}
		return retVal;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Invoke List:\n");
		Iterator<Map.Entry<Method, ParamList>> iter = this.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<Method, ParamList> entry = iter.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName("parseClass.HelloWorld");
			Method[] mtd = cls.getDeclaredMethods();
			for (Method m : mtd) {
				System.out.println(m);
			}
			Program prog = new Program(cls);
			ParamList parList1 = new ParamList();
			ParamTypes parTypes1 = new ParamTypes();
			prog.retrieveMtds("foo", parList1, parTypes1);
			prog.retrieveMtds("bar", parList1, parTypes1);
			ParamList parList2 = new ParamList();
			ParamTypes parTypes2 = new ParamTypes();
			float a = (float)2.0;
			String b = "b";
			parList2.add(a);
			parList2.add(b);
			parTypes2.add(float.class);
			parTypes2.add(b.getClass());
			
			prog.retrieveMtds("withPar", parList2, parTypes2);
			ParamList parList3 = new ParamList();
			//prog.retrieveMtds("number", parList3);

			// prog.retrieveAntdMtd("Invoke");
			ReturnVal ret = prog.execute();
			System.out.println(ret);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
