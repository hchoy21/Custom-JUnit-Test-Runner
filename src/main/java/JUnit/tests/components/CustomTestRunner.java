package JUnit.tests.components;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import JUnit.tests.components.stub.TestCase;


public class CustomTestRunner {
	
	static int passed = 0, failed = 0;
	
	public static void main(String[] args) throws Exception{
		
		Class<TestCase> testCase = TestCase.class;
		
		// get the list of methods from the test case
		Method[] methods = Class.forName("JUnit.tests.components.stub.TestCase").getMethods();
		ArrayList<Method> methodList = new ArrayList<Method>(Arrays.asList(methods));
		
		
		// if tester has decided they want to randomize
		if(testCase.isAnnotationPresent(Randomize.class)){
			
			
			randomizeMethods(methodList);
		}
		if(testCase.isAnnotationPresent(IgnorePassed.class)){
			
			
		}
		
		
		// process method annotations
		for(Method m : methodList){
			
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
			
			// check test annotation against memory (kilobyte)
			if(memoryTest.max_memory_allowed() < usedMemory){
				passed++;
				
				return true;
			}else{
				failed++;
				
				return false;
			}
			
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
		
		return false;
		
	}
	
	public static boolean runAmpleMemoryTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(AmpleMemory.class);
		AmpleMemory memoryTest = (AmpleMemory) annotation;

		try{
			m.invoke(obj);
			passed++;
			return true;
		}catch(Exception e){
			e.printStackTrace();

		}
		catch (OutOfMemoryError E) {
			failed++;
			return false;
			//dont run 
		}
		return false;
	}

	
	public static boolean runExpectedCallsTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls expectedCallsTest = (ExpectedCalls) annotation;
		int calls = expectedCallsTest.numOfMethodCalls();
		int count = 0;
		
		try{
			
			if(calls > 0){
				for(int i = 0; i < calls; i++){
					m.invoke(obj);
					count++;
				}
				System.out.println("yes");
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(count);
		if(calls == count){
			return true;
		}
		else return false;
	}
	
	public static void runignorePassedTest(Method m, Object obj){
		
	}
	
	public static boolean randomizeMethods(ArrayList<Method> m){
		if(!m.isEmpty()){
			System.out.println("contains items");
			Collections.shuffle(m);
			System.out.println("Methods Randomized");
			return true;
		}else{
			System.out.println("empty");
			return false;
		}
	}

}
