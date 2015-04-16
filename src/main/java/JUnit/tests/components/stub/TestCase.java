package JUnit.tests.components.stub;


import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.Randomize;


//this is the original testcase for the custom test runner
@Randomize
//@IgnorePassed
public class TestCase {

	public TestCase(){

	}
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		int a = 3;
		int b = 5;
		int total = a-b;
//		System.out.println("this should print 1 times");
	}
	
	@ExpectedCalls (numOfMethodCalls=0)
	public void testAddandPrintCalls(){
		int a = 3;
		int b = 5;
		int total = a+b;
//		System.out.println("this should print 2 times");
	}
	
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){
//		System.out.println("this should print 3 times");
	}

	@AmpleMemory
	@CPULimitTest
	public void testRandomNumberPass(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
	
	@AmpleMemory (threshHold = 100)
	@CPULimitTest (limit=1)
	public void testRandomNumberFail(){
		for(int i=0;i<1000;i++){
			Object[] a = new Object[2000];
		}
	}
	
}
