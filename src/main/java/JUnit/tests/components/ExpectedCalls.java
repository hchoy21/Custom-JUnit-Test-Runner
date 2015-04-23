package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Hendrik Choy, Nathan Nguyen
 * @params int numOfMethodCalls
 * @notes Run method as many times as specified in the annotation parameter(int numOfMethodCalls)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExpectedCalls {

	int numOfMethodCalls();
	
	
}
