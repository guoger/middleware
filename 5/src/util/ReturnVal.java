package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A map of <Method name, return value>, containing execution time.
 * 
 */
@SuppressWarnings("serial")
public class ReturnVal extends HashMap<String, Object> implements
		java.io.Serializable {
	public static final transient int TRANSFER_OK = 1;
	public static final transient int LOAD_OK = 2;
	public static final transient int RESULT = 3;

	public static final transient int FILE_TYPE_ERR = -1;
	public static final transient int COMPILATION_ERR = -2;
	public static final transient int LOAD_ERR = -3;

	public static final transient int TERMINATE = 0;

	public long time;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, Object>> iter = this.entrySet().iterator();
		while (iter.hasNext()) {
			java.util.Map.Entry<String, Object> entry = iter.next();
			sb.append(entry.getValue());
			sb.append("\t");
			sb.append(entry.getKey());
			sb.append("\n");
		}
		sb.append("Total time: ");
		sb.append(time);
		sb.append("\n");
		return sb.toString();
	}
}
