package parseClass;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * An annotation definition also requires the meta-annotations
 * @Target and @Retention. @Target defines where you can apply this
 * annotation (a method or a field, for example). @Retention defines
 * whether the annotations are available in the source code (SOURCE),
 * in the class files (CLASS), or at run time (RUNTIME).
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
	
}
