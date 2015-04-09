package JUnit.tests.components.stub;

import JUnit.tests.components.ExpectedCalls;


//this testcase is for the junit
public class TestCaseFail {

	@ExpectedCalls (numOfMethodCalls=0)
	public void testAddandPrintCalls(){
		int a = 3;
		int b = 5;
		int total = a+b;
		System.out.println("this should print 2 times");
	}
	
}
