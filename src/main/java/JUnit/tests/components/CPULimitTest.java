package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author Hendrik Choy, Nathan Nguyen
 * @param double limit
 * @notes Test CPU system load (percentage). Default limit is 75%
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CPULimitTest {
	
	double limit() default 75;
	
}