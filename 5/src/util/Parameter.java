package util;

/**
 * A tuple of <TYPE.class, VALUE.object>
 *
 */
@SuppressWarnings("serial")
public class Parameter implements java.io.Serializable {
	public final Class<?> typeClaz;
	public final Object paramVal;

	public Parameter(Class<?> cls, Object obj) {
		typeClaz = cls;
		paramVal = obj;
	}
	
	public Class<?> getType() {
		return typeClaz;
	}
	
	public Object getVal() {
		return paramVal;
	}

	public String toString() {
		return "(" + typeClaz + " => " + paramVal + ")";
	}
}
