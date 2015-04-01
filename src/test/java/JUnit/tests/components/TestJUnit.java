package JUnit.tests.components;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import org.junit.Before;
import org.junit.Test;

public class TestJUnit {
	
	Class<CustomTestRunner>	ctrObj;
	Method[] method;
	
	@Before
	public void setup(){
		ctrObj = CustomTestRunner.class;
		method = ctrObj.getDeclaredMethods();
		
	}
	
	
	// TODO: JUnit testing on the CustomTestRunner
	@Test
	public void memoryRestrictionLimitTestFailed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(MemoryTest.class)){
				
				assertFalse("There is not enough memory", CustomTestRunner.runMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	@Test
	public void memoryRestrictionLimitTestPassed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(MemoryTest.class)){
				
				assertTrue("There is enough memory", CustomTestRunner.runMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	
}
