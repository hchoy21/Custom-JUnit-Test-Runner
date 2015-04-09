package JUnit.tests.components.stub;


import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.IgnorePassed;
import JUnit.tests.components.MemoryLimitTest;
import JUnit.tests.components.Randomize;

@Randomize
public class TestCase {

	SampleMethods sm;

	public TestCase(){
		sm = new SampleMethods();
	}
	
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		sm.subtract(5, 2);
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=2)
	public void testAddandPrintCalls(){
		sm.add(3, 4);
		sm.printResult();
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){
		
	}
	
//	@IgnorePassed (num = 5, passed = { false })
	@MemoryLimitTest (max_memory_allowed=1000)
	public void testRandomNumberPass(){
			sm.randomNumber();
			sm.printRandomNumber();
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@AmpleMemory
	@MemoryLimitTest (max_memory_allowed=1)
	public void testRandomNumberFail(){
		sm.randomNumber();
		sm.printRandomNumber();
	}
	
}
