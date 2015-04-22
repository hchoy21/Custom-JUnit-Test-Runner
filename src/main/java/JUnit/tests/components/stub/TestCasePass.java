package JUnit.tests.components.stub;


import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.Randomize;


//this testcase is for junit
@Randomize
//@IgnorePassed
public class TestCasePass {	

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

	@CPULimitTest (limit=1000)
	public void testRandomNumberPass(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@CPULimitTest (limit=100)
	public void testRandomNumberPass1(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	
	@AmpleMemory
	@CPULimitTest 
	public void testRandomNumberFail(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@AmpleMemory
	@CPULimitTest (limit = 60)
	public void testRandomNumberFail1(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@AmpleMemory (threshHold = 50)
	@CPULimitTest 
	public void testRandomNumberFail2(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@AmpleMemory (threshHold = 50)
	@CPULimitTest (limit = 60)
	public void testRandomNumberFail3(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
}
