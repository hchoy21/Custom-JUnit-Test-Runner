package JUnit.tests.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
			if(m.isAnnotationPresent(MemoryLimitTest.class)){
				
				assertFalse("Fails the set memory Limit", CustomTestRunner.runMemoryLimitTest(m, ctrObj));
			}
							
		}
		
	}
	
	@Test
	public void memoryRestrictionLimitTestPassed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(MemoryLimitTest.class)){
				
				assertTrue("Passes the set memory limit", CustomTestRunner.runMemoryLimitTest(m, ctrObj));
			}
							
		}
		
	}
	
	// TODO: JUnit testing on the CustomTestRunner
	@Test
	public void ampleMemoryRunTestFailed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(AmpleMemory.class)){
				
				assertFalse("There is not enough memory in the JVM to run this method", CustomTestRunner.runAmpleMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	@Test
	public void ampleMemoryRunTestPassed(){
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(AmpleMemory.class)){
				
				assertTrue("There is enough memory in the JVM", CustomTestRunner.runAmpleMemoryTest(m, ctrObj));
			}
							
		}
		
	}
	
	@Test (expected=RuntimeException.class)
	public void IgnorePassedTestFailed(){
	
		
	}
	
	@Test (expected=RuntimeException.class)
	public void IgnorePassedTestPassed(){
		
		
	}
	
	@Test (expected=RuntimeException.class)
	public void RandomizeFailed(){
		
		
	}
	
	@Test (expected=RuntimeException.class)
	public void RandomizePassed(){
		
	}
	
	@Test (expected=RuntimeException.class)
	public void ExpectedCallTestFailed(){
		
		
	}
	
	@Test (expected=RuntimeException.class)
	public void ExpectedCallTestPassed(){
		
		
	}
	
}
