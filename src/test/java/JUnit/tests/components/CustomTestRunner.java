package JUnit.tests.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import JUnit.tests.components.stub.CalculatorStub;


public class CustomTestRunner {
	
	public static void main(String[] args) throws Exception{
		
		
		int passed = 0, failed = 0;
		
		
		for(Method m : Class.forName("JUnit.tests.components.CalculatorStubTest").getMethods()){
			
			Object calObj = Class.forName("JUnit.tests.components.CalculatorStubTest").newInstance();
			
			if(m.isAnnotationPresent(MemoryTest.class)){
				System.out.println("line 22");
				Annotation annotation = m.getAnnotation(MemoryTest.class);
				MemoryTest memoryTest = (MemoryTest) annotation;
				
				
				try{
					Runtime runtime = Runtime.getRuntime();
					long totalMemory = runtime.totalMemory();
					m.invoke(calObj);
					
					long freeMemory = runtime.freeMemory();
					
					long usedMemory = (totalMemory - freeMemory) / 1024;
					
					System.out.println("used mem : " + usedMemory);
					// check memory test annotation against runtime memory
					// in kb
					if(memoryTest.max_memory_allowed() > usedMemory){
						System.out.println("test");
						passed++;
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
		}
		
		System.out.println("passed tests: " + passed);
		
	}

}
