package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AmpleMemory {
<<<<<<< HEAD
	
	//default is 25 percent if unchanged
	float threshHold() default 25;
	
=======
>>>>>>> ef748c500ada562cf1343a447f6994e143fe7a14
}
