package JUnit.tests.components;

import org.junit.BeforeClass;

import JUnit.tests.components.stub.CalculatorStub;


public class CalculatorStubTest {

	CalculatorStub cal;
	
	@BeforeClass
	@IgnorePassed (num = 5, passed = { false })
	public void setUp(){
		//instantiate prehand
		cal = new CalculatorStub();
	}
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtractCalls(){
		cal.subtract(5, 2);
	}
	
	@ExpectedCalls (numOfMethodCalls=2)
	public void testAddandPrintCalls(){
		cal.add(3, 4);
		cal.printResult();
	}
	
	@ExpectedCalls (numOfMethodCalls=3)
	public void testIgnorePassedTests(){
		
	}
	
	@MemoryTest (max_memory_allowed=1000)
	public void testRandomNumberPass(){
		cal.randomNumber();
		cal.printRandomNumber();
	}
	
	@MemoryTest (max_memory_allowed=1)
	public void testRandomNumberFail(){
		cal.randomNumber();
		cal.printRandomNumber();
	}
	
}
