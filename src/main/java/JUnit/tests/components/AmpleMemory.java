package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 
 * @author Hendrik Choy, Nathan Nguyen
 * @param float threshold
 * @notes Test JVM memory exceeds a certain threshold (percentage). Default threshold = 25%
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AmpleMemory {
	
	//default is 25 percent if unchanged
	float threshHold() default 25;
	
}
