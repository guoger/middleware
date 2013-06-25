package cldServ;

public class ParseParam {
	public static Object parse(String type, String param) {
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
}
