package JUnit.tests.components.stub;

import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.IgnorePassed;

@IgnorePassed
public class TestCaseIgnorePassResetFalse {
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){

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
	public void testRandomNumberFail(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}
}
