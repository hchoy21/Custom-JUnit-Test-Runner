package JUnit.tests.components;

import org.junit.BeforeClass;

import JUnit.tests.components.stub.CalculatorStub;


public class CalculatorStubTest {

	
	@BeforeClass
	@IgnorePassed (num = 5, passed = { false })
	public void setUp(){
		//instantiate prehand
		CalculatorStub cal = new CalculatorStub();
	}
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testAdd(){
		
	}
	
	@ExpectedCalls (numOfMethodCalls=1)
	public void testSubtract(){
		
	}
	
	@ExpectedCalls (numOfMethodCalls=2)
	public void testAddandPrint(){
		
	}
	
}
