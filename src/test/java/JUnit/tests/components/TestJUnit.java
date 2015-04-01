package JUnit.tests.components;

import java.lang.reflect.Method;




import org.junit.Assert;
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
				
				Assert.assertEquals(false, CustomTestRunner.runMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	@Test
	public void memoryRestrictionLimitTestPassed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(MemoryTest.class)){
				
				Assert.assertEquals(true, CustomTestRunner.runMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	
}
