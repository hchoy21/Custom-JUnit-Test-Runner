package JUnit.tests.components.stub;

import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.CPULimitTest;
import JUnit.tests.components.IgnorePassed;


//this testcase is for the junit
@IgnorePassed (reset = true)
public class TestCaseFail {
	
	@AmpleMemory (threshHold=100)
	@CPULimitTest (limit=0)
	public void testRandomNumberFail(){
		for(int i=0;i<1000;i++){
			Object[] a = new Object[200];
		}
	}
	
}
