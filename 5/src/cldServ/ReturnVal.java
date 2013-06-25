package cldServ;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReturnVal extends HashMap<Method, Object> {
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<Method, Object>> iter = this.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<Method, Object> entry = iter.next();
			sb.append(entry.getKey());
			sb.append("=");
			sb.append(entry.getValue());
			sb.append("\n");
		}
		return sb.toString();
	}
}
