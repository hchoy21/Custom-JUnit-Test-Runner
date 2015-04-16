package JUnit.tests.components.stub;


import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.IgnorePassed;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.Randomize;


//this testcase is for junit
@Randomize
@IgnorePassed
public class TestCasePass {	
	
	public TestCasePass(){

	}
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		int a = 3;
		int b = 5;
		int total = a-b;
		System.out.println("this should print 1 times");
	}
	
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){
		System.out.println("this should print 3 times");
	}

	@CPULimitTest (max_memory_allowed=1000)
	public void testRandomNumberPass(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@AmpleMemory
	@CPULimitTest (max_memory_allowed=1)
	public void testRandomNumberFail(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
}
