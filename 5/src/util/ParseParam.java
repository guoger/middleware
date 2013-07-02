package util;

import server.TypeException;

public class ParseParam {
	public static Object parseValue(String type, String param) {
		if (type.equals("String")) {
			return param;
		} else if (type.equals("Float") || type.equals("float")) {
			return Float.parseFloat(param);
		} else if (type.equals("Integer") || type.equals("int")) {
			return Integer.parseInt(param);
		} else if (type.equals("Short") || type.equals("short")) {
			return Short.parseShort(param);
		} else if (type.equals("Long") || type.equals("long")) {
			return Long.parseLong(param);
		} else if (type.equals("Double") || type.equals("double")) {
			return Double.parseDouble(param);
		} else if (type.equals("Boolean") || type.equals("boolean")) {
			return Boolean.parseBoolean(param);
		} else {
			return null;
		}
	}
	
	public static Class<?> parseType(String type) throws TypeException {
		if (type.equals("String")) {
			return String.class;
		} else if (type.equals("Float")) {
			return Float.class;
		} else if (type.equals("float")) {
			return float.class;
		} else if (type.equals("Integer")) {
			return Integer.class;
		} else if (type.equals("int")) {
			return int.class;
		} else if (type.equals("Short")) {
			return Short.class;
		} else if (type.equals("short")) {
			return short.class;
		} else if (type.equals("Long")) {
			return Long.class;
		} else if (type.equals("long")) {
			return long.class;
		} else if (type.equals("Double")) {
			return Double.class;
		} else if (type.equals("double")) {
			return double.class;
		} else if (type.equals("Boolean")) {
			return Boolean.class;
		} else if (type.equals("boolean")) {
			return boolean.class;
		} else {
			throw new TypeException(type);
		}
	}
}
