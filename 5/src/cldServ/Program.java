/*
 * Program.java
 * consume a Class and do actual things
 */

package cldServ;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Iterator;
import util.*;

@SuppressWarnings("serial")
public class Program extends HashMap<Method, ParamVals> {
	Class<?> usrClaz;
	Object usrObj = null;

	public Program(Class<?> claz) {
		this.usrClaz = claz;
	}

	public Program(Class<?> claz, Object obj) {
		this.usrClaz = claz;
		this.usrObj = obj;
	}

	void retrieveMtds(ParamList parList)
			throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException {
		// ParamTypes parTypes = parList.convertToTypes();
		int index = 0;
		Class<?>[] paramTypesArray = new Class<?>[parList.size()];
		for (Class<?> cls : parList.parseTypes()) {
			paramTypesArray[index] = cls;
			index++;
		}
		// retrieve corresponding method using method name and parameters type
		// list
		String mtdName = parList.mtdName;
		Method usrMtd = usrClaz.getMethod(mtdName, paramTypesArray);
		this.put(usrMtd, parList.parseVals());
		// Check whether the method is static, which indicates the necessity of
		// instantiation.
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
		Iterator<java.util.Map.Entry<Method, ParamVals>> iter = this.entrySet()
				.iterator();
		while (iter.hasNext()) {
			java.util.Map.Entry<Method, ParamVals> entry = iter.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

	}

}
