package JUnit.tests.components.stub;


import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.IgnorePassed;
import JUnit.tests.components.MemoryLimitTest;
import JUnit.tests.components.Randomize;

@Randomize
@IgnorePassed
public class TestCase {

	SampleMethods sm;

	public TestCase(){
		sm = new SampleMethods();
	}
	
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		sm.subtract(5, 2);
	}
	
	@ExpectedCalls (numOfMethodCalls=2)
	public void testAddandPrintCalls(){
		sm.add(3, 4);
		sm.printResult();
	}
	
<<<<<<< HEAD
	@IgnorePassed (num = 5, passed = { false })
=======
	@ExpectedCalls (numOfMethodCalls=3)
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
	public void testIgnorePassedTests(){
		
	}

	@MemoryLimitTest (max_memory_allowed=1000)
	public void testRandomNumberPass(){
			sm.randomNumber();
			sm.printRandomNumber();
	}
	
	@AmpleMemory
	@MemoryLimitTest (max_memory_allowed=1)
	public void testRandomNumberFail(){
		sm.randomNumber();
		sm.printRandomNumber();
	}
	
}
