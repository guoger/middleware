package cldServ;

import java.util.*;

@SuppressWarnings("serial")
public class ParamList extends ArrayList<Object> {
	/**
	 * Convert parameter list to parameter type list to retrieve respective
	 * method.
	 */
	public ParamTypes convertToTypes() {
		ParamTypes parTypes = new ParamTypes();
		for (Object o : this) {
			parTypes.add(o.getClass());
		}
		return parTypes;
	}
}
