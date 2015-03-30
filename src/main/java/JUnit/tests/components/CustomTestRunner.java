package JUnit.tests.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import JUnit.tests.components.stub.SampleMethods;


public class CustomTestRunner {
	
	static int passed = 0, failed = 0;
	
	public static void main(String[] args) throws Exception{
		
		
		
		for(Method m : Class.forName("JUnit.tests.components.CalculatorStubTest").getMethods()){
			
			Object calObj = Class.forName("JUnit.tests.components.CalculatorStubTest").newInstance();
			
			
			// check each test annotation
			if(m.isAnnotationPresent(MemoryTest.class)){
				
				runMemoryTest(m, calObj);
				
			}else if(m.isAnnotationPresent(ExpectedCalls.class)){
				
				runExpectedCallsTest(m, calObj);
				
			}
		}
		
		System.out.println("passed tests: " + passed);
		
	}
	
	static void runMemoryTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(MemoryTest.class);
		MemoryTest memoryTest = (MemoryTest) annotation;
		
		try{
			
			Runtime runtime = Runtime.getRuntime();
			long totalMemory = runtime.totalMemory();
			m.invoke(obj);
			
			long freeMemory = runtime.freeMemory();
			long usedMemory = (totalMemory - freeMemory) / 1024;
			
			System.out.println("used mem : " + usedMemory);
			
			// check test annotation against memory (kilobyte)
			if(memoryTest.max_memory_allowed() > usedMemory){
				System.out.println("test");
				
				passed++;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	static void runExpectedCallsTest(Method m, Object obj){
		//TODO: Hendrik
	}
	

}
