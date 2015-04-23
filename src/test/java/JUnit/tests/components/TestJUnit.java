package JUnit.tests.components;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class TestJUnit {

	Object ctrObj;
	Method[] method;
	Object ctrObj2;
	Method[] method2;
	CustomTestRunner obj = new CustomTestRunner();
	CustomTestRunner obj2 = new CustomTestRunner();
	CustomTestRunner obj3 = new CustomTestRunner();
	
	@Before
	public void setup() throws Exception{
		ctrObj = Class.forName("JUnit.tests.components.stub.TestCasePass").newInstance();
		method = Class.forName("JUnit.tests.components.stub.TestCasePass").getMethods();
		ctrObj2 = Class.forName("JUnit.tests.components.stub.TestCaseFail").newInstance();
		method2 = Class.forName("JUnit.tests.components.stub.TestCaseFail").getMethods();
		obj.initializeRunner("JUnit.tests.components.stub.TestCasePass");
		obj2.initializeRunner("JUnit.tests.components.stub.TestCaseFail");
		obj3.initializeRunner("JUnit.tests.components.stub.TestCaseIgnorePassResetFalse");
	}

	@Test
	public void testInitializeRunnerResultCreated() throws Exception{
	
		File file = new File("State.JUnit.tests.components.stub.TestCasePass.txt");
		assertTrue("The number of passed objects should be equal to the number of times the test has passed", file.exists());
	}
	
	@Test
	public void testInitializeRunnerPassedNumber() throws Exception{

		assertTrue("The number of passed objects should be equal to the number of times the test has passed", obj.passed == obj.numberOfTests);
	}
	
	@Test
	public void testInitializeRunnerFailedNumber() throws Exception{

		assertTrue("The number of passed objects should be equal to the number of times the test has passed", obj.failed == 0);
	}
	
	@Test
	public void testInitializeRunnerFail() throws Exception{

		assertTrue("This test should not pass with at least one test case failing", obj2.failed != 0);
	}

	@Test
	public void testInitializeRunnerPass() throws Exception{

		assertTrue("This test should pass with every test case passing", obj.failed == 0);
	}
	
	@Test
	public void CPURestrictionLimitTestPassed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(CPULimitTest.class)){

				assertTrue("Passes the set memory limit", obj.runCPULimitTest(m, ctrObj));
			}

		}

	}
	
	@Test
	public void CPULimitTestFailed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : method2){
			// run tests on marked annotations
			if(m.isAnnotationPresent(CPULimitTest.class)){
				assertFalse("Fails the set memory Limit", obj2.runCPULimitTest(m, ctrObj2));
			}

		}

	}

	@Test
	public void ampleMemoryRunTestFailed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : method2){
			// run tests on marked annotations
			if(m.isAnnotationPresent(AmpleMemory.class)){

				assertFalse("There is suppose to be not enough memory in the JVM to run this method", obj2.runAmpleMemoryTest(m, ctrObj2));
			}

		}

	}

	@Test
	public void ampleMemoryRunTestPassed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : method){
			// run tests on marked annotations
			if(m.isAnnotationPresent(AmpleMemory.class)){

				assertTrue("There is enough memory in the JVM", obj.runAmpleMemoryTest(m, ctrObj));
			}

		}

	}
	
	@Test
	public void IgnorePassedTestResetTrue() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IOException{
		CustomTestRunner.runIgnorePassedTest(Class.forName("JUnit.tests.components.stub.TestCaseIgnorePassResetTrue"), new ArrayList<Method>());
		File file = new File("State."+ Class.forName("JUnit.tests.components.stub.TestCaseIgnorePassResetTrue").getName() + ".txt");
		assertTrue("Reset true should create a new file on the testcaseignorepassed stub", file.exists());
	}

	@Test
	public void IgnorePassedTestResetFalse() throws NoSuchMethodException, SecurityException, ClassNotFoundException, IOException{
		Class<?> c = Class.forName("JUnit.tests.components.stub.TestCaseIgnorePassResetFalse");
		ArrayList<Method> methods = CustomTestRunner.runIgnorePassedTest(c, new ArrayList<Method>(Arrays.asList(c.getMethods())));
		assertTrue("Reset false should return the same method list as previous without using randomize", 
				Arrays.asList(c.getMethods()).equals(methods));
	}
	
	//tests if randomize actually work, the number of methods after randomizing a subset should be different.
	@Test (timeout = 1000)
	public void testRandomize(){
		ArrayList<Method> mList = null;
		do{
		mList = CustomTestRunner.randomizeMethods(new ArrayList<Method>(Arrays.asList(method)));
		}while(mList.size()==method.length);
	}
	
	//does not test null but empty arraylist, since randomize cannot be used separately.
	@Test
	public void testRandomizeEmptyMethodList(){
		ArrayList<Method> mList = CustomTestRunner.randomizeMethods(new ArrayList<Method>());
		assertTrue("Returns null if there are no methods in method list", mList == null);
	}
	
	@Test
	public void testRandomizeNullMethodList(){
		ArrayList<Method> mList = CustomTestRunner.randomizeMethods(new ArrayList<Method>());
		assertTrue("Returns null if arraylist is null", mList == null);
	}

	@Test
	public void ExpectedCallTestFailed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		for(Method m : method2){

			if(m.isAnnotationPresent(ExpectedCalls.class)){
				assertFalse("Methods were not called as minimumly set by user", obj2.runExpectedCallsTest(m, ctrObj2));
			}
		}
	}

	@Test
	public void ExpectedCallTestPassed() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		for(Method m : method){

			if(m.isAnnotationPresent(ExpectedCalls.class)){
				assertTrue("Expected Calls", obj.runExpectedCallsTest(m, ctrObj));
			}
		}

	}

}