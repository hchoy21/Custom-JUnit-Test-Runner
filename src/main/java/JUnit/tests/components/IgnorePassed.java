package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Hendrik Choy, Nathan Nguyen
 * @params boolean reset
 * @notes class level annotation. This checks which unit tests have previously passed or failed, 
 * and ignores the previously passed tests while running the previously failed ones. 
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface IgnorePassed {
	boolean reset() default false;
}
