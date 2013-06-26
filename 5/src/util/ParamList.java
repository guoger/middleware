package util;

import java.util.*;

@SuppressWarnings("serial")
public class ParamList extends ArrayList<Parameter> implements
		java.io.Serializable {
	public final String mtdName;
	
	public ParamList(String mtdName) {
		this.mtdName = mtdName;
	}

	/**
	 * Parse parameter list, return a ParamTypes which is an ArrayList
	 * containing all parameters type according to the order in method.
	 * 
	 * @return paramTypes;
	 */
	public ParamTypes parseTypes() {
		ParamTypes paramTypes = new ParamTypes();
		for (Parameter p : this) {
			paramTypes.add(p.getType());
		}
		return paramTypes;
	}

	/**
	 * Parse parameter list, return a ParamVals which is an ArrayList containing
	 * all parameters value according to the order in method.
	 * 
	 * @return paramVals
	 */
	public ParamVals parseVals() {
		ParamVals paramVals = new ParamVals();
		for (Parameter p : this) {
			paramVals.add(p.getVal());
		}
		return paramVals;
	}
	
	/**
	 * Insert a parameter using type class and value object
	 * @param type
	 * @param val
	 */
	public void insert(Class<?> type, Object val) {
		Parameter newParam = new Parameter(type, val);
		this.add(newParam);
	}
	
	/**
	 * Insert a parameter to list
	 * @param param
	 */
	public void insert(Parameter param) {
		this.add(param);
	}
}
