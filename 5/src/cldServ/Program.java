/*
 * Program.java
 * consume a Class and do actual things
 */

package cldServ;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import util.*;

@SuppressWarnings("serial")
public class Program extends HashMap<Method, ParamVals> {
	Class<?> usrClaz;
	Object usrObj = null;
	JavaCompiler jc = null;

	public Program(Class<?> claz, Object obj) {
		this.usrClaz = claz;
		this.usrObj = obj;
	}
	
	public Program(File javaFile) throws IOException, ClassNotFoundException {
		jc = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = 
				jc.getStandardFileManager(null, null, null);
		Iterable fileObjects = sjfm.getJavaFileObjects(javaFile);
		String[] options = new String[]{"-d", "/Users/guoger/workspace/middleware/5/serverbin"};
		jc.getTask(null, null, null, Arrays.asList(options), null, fileObjects).call();
		sjfm.close();
		System.out.println("Compile successfully!");
		URL[] urls = new URL[]{ new URL("file:/Users/guoger/workspace/middleware/5/serverbin/")};
		URLClassLoader ucl = new URLClassLoader(urls);
		usrClaz = ucl.loadClass("HelloWorld");
		System.out.println("Class HelloWorld has been successfully loaded");
	}

	/**
	 * Retrieve a method using a ParamList
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
	 * @param annotation
	 */
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

	/**
	 * Execute all methods demanded in this program.
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public ReturnVal execute() throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		ReturnVal retVal = new ReturnVal();
		Object retObj = null;
		for (Method mtd : this.keySet()) {
			retObj = mtd.invoke(usrObj, this.get(mtd).toArray());
			retVal.put(mtd, retObj);
		}
		// System.out.println("Is instantiated? "+(usrObj != null));
		return retVal;
	}

	/**
	 * Override toString method to print neatly.
	 */
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
		Parameter parameter = null;
		Class<?> paramType = null;
		Object paramVal = null;
		File javaFile = new File("/Users/guoger/workspace/middleware/5/serverbin/HelloWorld.java");
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
			
			// Compile source code
			Program progBar = new Program(javaFile);
			progBar.retrieveMtds(foo);
			progBar.execute();
			
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
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
