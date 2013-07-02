/*
 * Program.java
 * consume a Class and do actual things
 */

package server;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import util.*;
import de.tu_berlin.kbs.reflect.*;

/**
 * A program is a map of <Method, ParamVals> in which ParamVals is used to
 * invoke corresponding method.
 * 
 */
@SuppressWarnings("serial")
public class Program extends HashMap<Method, ParamVals> {
	Class<?> usrClaz;
	Object usrObj = null;
	JavaCompiler jc = null;

	public Program(Class<?> claz, Object obj) {
		this.usrClaz = claz;
		this.usrObj = obj;
	}

	/**
	 * Retrieve a method using a ParamList
	 * 
	 * @param parList
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	void retrieveMtds(ParamList parList) throws SecurityException,
			NoSuchMethodException, InstantiationException,
			IllegalAccessException {
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

	/**
	 * Retrieve a method that has an annotation containing a specific string
	 * 
	 * @param annotation
	 */
	void retrieveAntdMtd(String annotation) {
		Method[] allMtd = usrClaz.getMethods();
		for (Method m : allMtd) {
			Annotation[] allAnno = m.getAnnotations();
			for (Annotation a : allAnno) {
				if (a.toString().contains("InvokeThis")) {
					this.put(m, null);
				}
			}
		}
	}

	/**
	 * Execute all methods demanded in this program.
	 * 
	 * @return
	 */
	public ReturnVal execute() {
		ReturnVal retVal = new ReturnVal();
		Object retObj = null;
		Object[] param = null;

		long before = System.currentTimeMillis();
		for (Method mtd : this.keySet()) {
			try {
				if (this.get(mtd) == null) {
					param = null;
				} else {
					param = this.get(mtd).toArray();
				}
				retObj = mtd.invoke(usrObj, param);
			} catch (IllegalArgumentException e) {
				retObj = e;
			} catch (IllegalAccessException e) {
				retObj = e;
			} catch (InvocationTargetException e) {
				retObj = e;
			} finally {
				retVal.put(mtd.toString(), retObj);
			}
		}
		long after = System.currentTimeMillis();
		retVal.time = after - before;
		// System.out.println("Is instantiated? "+(usrObj != null));
		return retVal;
	}

	/**
	 * Override toString method to print neatly.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Class: " + usrClaz.getCanonicalName());
		sb.append("\nMethods to invoke:\n");
		Iterator<java.util.Map.Entry<Method, ParamVals>> iter = this.entrySet()
				.iterator();
		int index = 1;
		while (iter.hasNext()) {
			sb.append(index + " ");
			index++;
			java.util.Map.Entry<Method, ParamVals> entry = iter.next();
			sb.append(entry.getKey());
			sb.append(" <= ");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Parameter parameter = null;
		Class<?> paramType = null;
		Object paramVal = null;
		File javaFile = new File(
				"/Users/guoger/workspace/middleware/5/serverbin/HelloWorld.java");
		try {
			// Prepare parameters
			ParamList foo = new ParamList("withPar");
			paramType = float.class;
			paramVal = (float) 1.0;
			parameter = new Parameter(paramType, paramVal);
			foo.insert(parameter);
			paramType = String.class;
			paramVal = "OK";
			parameter = new Parameter(paramType, paramVal);
			foo.insert(parameter);

			// Load bytecode
			Class<?> cls = Class.forName("parseClass.HelloWorld");
			Program progFoo = new Program(cls, null);
			progFoo.retrieveMtds(foo);
			progFoo.execute();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
