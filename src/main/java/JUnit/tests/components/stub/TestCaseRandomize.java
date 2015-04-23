package JUnit.tests.components.stub;

import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.Randomize;


//this testcase is for junit
@Randomize
public class TestCaseRandomize {	

	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		int a = 3;
		int b = 5;
		int total = a-b;

	}

	@CPULimitTest (limit=100)
	public void testRandomNumberPass(){
		int a = 3;
		int b = 5;
		int total = a+b;
	}

}

