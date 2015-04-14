package JUnit.tests.components;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AmpleMemory {
	
	float threshHold() default 0;
	
	//default to this if threshhold is null
	float defaultThreshHold() default 25;
}
