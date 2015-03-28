package JUnit.tests.components;

import org.junit.Before;
import org.junit.BeforeClass;

import JUnit.tests.components.stub.CalculatorStub;


public class CalculatorStubTest {

	CalculatorStub cal;

	public CalculatorStubTest(){
		cal = new CalculatorStub();
	}
	
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		cal.subtract(5, 2);
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=2)
	public void testAddandPrintCalls(){
		cal.add(3, 4);
		cal.printResult();
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){
		
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@MemoryTest (max_memory_allowed=1000)
	public void testRandomNumberPass(){
		for(int i = 0; i < 10; i++){
			cal.randomNumber();
			cal.printRandomNumber();
		}
	}
	
	@IgnorePassed (num = 5, passed = { false })
	@MemoryTest (max_memory_allowed=1)
	public void testRandomNumberFail(){
		cal.randomNumber();
		cal.printRandomNumber();
	}
	
}
