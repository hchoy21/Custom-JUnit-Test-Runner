package JUnit.tests.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class CustomTestRunner {
	
	static int passed = 0, failed = 0;
	
	public static void main(String[] args) throws Exception{
		
		for(Method m : Class.forName("JUnit.tests.components.stub.TestCase").getMethods()){
			
			Object obj = Class.forName("JUnit.tests.components.stub.TestCase").newInstance();
			
			
			// check each test annotation
			if(m.isAnnotationPresent(MemoryLimitTest.class)){
				
				runMemoryLimitTest(m, obj);
				
			}
			if(m.isAnnotationPresent(AmpleMemory.class)){
				
				runAmpleMemoryTest(m, obj);
				
			}
			if(m.isAnnotationPresent(ExpectedCalls.class)){
				
				runExpectedCallsTest(m, obj);
				
			}
			if(m.isAnnotationPresent(IgnorePassed.class)){
				runignorePassedTest(m, obj);
			}
		}
		
		System.out.println("passed tests: " + passed);
		
	}
	
	//TODO: default value is false? figure logic
	public static boolean runMemoryLimitTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(MemoryLimitTest.class);
		MemoryLimitTest memoryTest = (MemoryLimitTest) annotation;
		
		try{
			
			Runtime runtime = Runtime.getRuntime();
			long totalMemory = runtime.totalMemory();
			m.invoke(obj);
			
			long freeMemory = runtime.freeMemory();
			long usedMemory = (totalMemory - freeMemory) / 1024;
			
			System.out.println("used mem : " + usedMemory);
			
			// check test annotation against memory (kilobyte)
			if(memoryTest.max_memory_allowed() < usedMemory){
				System.out.println("memory limit test passed");
				passed++;
				
				return true;
			}else{
				System.out.println("memory limit test failed");
				failed++;
				
				return false;
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		System.out.println("passed tests: " + passed);
		return false;
		
	}
	
	public static boolean runAmpleMemoryTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(AmpleMemory.class);
		AmpleMemory memoryTest = (AmpleMemory) annotation;

		try{
			System.out.println(m.invoke(obj));
			System.out.println("ample memory test passed");
			passed++;
			return true;
		}catch(Exception e){
			e.printStackTrace();

		}
		catch (OutOfMemoryError E) {
			System.out.println("ample memory test failed");
			failed++;
			return false;
			//dont run 
		}
		System.out.println("passed tests: " + passed);
		return false;
	}

	
	public static void runExpectedCallsTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls expectedCallsTest = (ExpectedCalls) annotation;
		
		try{
			int calls = expectedCallsTest.numOfMethodCalls();
			
			for(int i = 0; i < calls; i++){
				m.invoke(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void runignorePassedTest(Method m, Object obj){
		
	}
	
	public void Randomize(){
		
	}

}
