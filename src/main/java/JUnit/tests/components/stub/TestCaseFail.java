package JUnit.tests.components.stub;

import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.ExpectedCalls;


//this testcase is for the junit
//@IgnorePassed
public class TestCaseFail {
	
	@ExpectedCalls (numOfMethodCalls=0)
	public void testAddandPrintCalls(){
		int a = 3;
		int b = 5;
		int total = a+b;
		System.out.println("this should print 2 times");
	}
	
	@AmpleMemory (threshHold=100)
	@CPULimitTest (limit=0)
	public void testRandomNumberFail(){
		for(int i=0;i<1000;i++){
			Object[] a = new Object[200];
		}
	}
	
}
