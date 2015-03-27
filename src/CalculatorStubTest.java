import org.junit.BeforeClass;

import stub.CalculatorStub;


public class CalculatorStubTest {

	
	@BeforeClass
	@IgnorePassed (num = 5)
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
