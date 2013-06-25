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

	void retrieveMtds(String mtdName, ParamList parList)
			throws SecurityException, NoSuchMethodException {
		// ParamTypes parTypes = parList.convertToTypes();
		int index = 0;
		Class<?>[] parTypes = new Class<?>[parList.size()];
		for (Object o : parList) {
			parTypes[index] = o.getClass();
			index++;
		}
		Method usrMtd = usrClaz.getMethod(mtdName, parTypes);
		this.put(usrMtd, parList);
		System.out.println(this);
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
			Program prog = new Program(cls);
			ParamList parList1 = new ParamList();
			prog.retrieveMtds("foo", parList1);
			prog.retrieveMtds("bar", parList1);
			ParamList parList2 = new ParamList();
			String a = "a";
			String b = "b";
			parList2.add(a);
			parList2.add(b);
			prog.retrieveMtds("withPar", parList2);
			ParamList parList3 = new ParamList();
			prog.retrieveMtds("number", parList3);
			
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
		}

	}

}
