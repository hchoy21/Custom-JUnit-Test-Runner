package JUnit.tests.components;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import JUnit.tests.components.stub.TestCase;

public class TestJUnit {
	
	Class<TestCase>	ctrObj;
	Method[] method;
	
	@Before
	public void setup(){
		ctrObj = TestCase.class;
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
	
	@Test 
	public void RandomizeFailed(){
//		CustomTestRunner.methodList = null;
		ArrayList<Method> mList = new ArrayList<Method>(Arrays.asList(method));
		
		
		//empty the list of methods
		mList.clear();
		assertFalse("randomize", CustomTestRunner.randomizeMethods(mList));
		
	}
	
	@Test
	public void RandomizePassed(){
		ArrayList<Method> mList = new ArrayList<Method>(Arrays.asList(method));

		assertTrue("randomize", CustomTestRunner.randomizeMethods(mList));
	}
	
	@Test 
	public void ExpectedCallTestFailed(){
		
		for(Method m : method){
			
			if(m.isAnnotationPresent(ExpectedCalls.class)){
				
				assertFalse("Expected Calls", CustomTestRunner.runExpectedCallsTest(m, ctrObj));
			}
		}
	}
	
	@Test
	public void ExpectedCallTestPassed(){
		for(Method m : method){
			
			if(m.isAnnotationPresent(ExpectedCalls.class)){
				
				assertTrue("Expected Calls", CustomTestRunner.runExpectedCallsTest(m, ctrObj));
			}
		}
		
	}
	
}
