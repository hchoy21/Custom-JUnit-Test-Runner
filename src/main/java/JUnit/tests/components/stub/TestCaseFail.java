package JUnit.tests.components.stub;

import JUnit.tests.components.AmpleMemory;
import JUnit.tests.components.ExpectedCalls;
import JUnit.tests.components.IgnorePassed;
import JUnit.tests.components.Randomize;


//this testcase is for the junit
@Randomize
@IgnorePassed
public class TestCaseFail {

	public TestCaseFail(){

	}
	
	@ExpectedCalls (numOfMethodCalls=0)
	public void testAddandPrintCalls(){
		int a = 3;
		int b = 5;
		int total = a+b;
		System.out.println("this should print 2 times");
	}
	
	@AmpleMemory
	public void testOutOfMemory() throws InterruptedException{
		int v = 20;
		while(1==1){
			int[] memoryFill = new int[v];
			v = v * 20;
			Thread.sleep(50);
		}
	}
	
}
