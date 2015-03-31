package JUnit.tests.components;

import java.lang.reflect.Method;



import org.junit.Assert;
import org.junit.Test;

public class TestJUnit {
	
	// TODO: JUnit testing on the CustomTestRunner
	@Test
	public void memoryRestrictionLimitTestPass() throws SecurityException, ClassNotFoundException, 
														InstantiationException, IllegalAccessException{
		for (Method m : Class.forName("JUnit.tests.components.TestCase").getMethods()) {

			Object obj = Class.forName("JUnit.tests.components.TestCase")
					.newInstance();
			
			Assert.assertEquals(true, CustomTestRunner.runMemoryTest(m, obj));
			
		}
	}
	
//	@Test
//	public void memoryRestrictionLimitTestFail(){
//		
//	}
}
