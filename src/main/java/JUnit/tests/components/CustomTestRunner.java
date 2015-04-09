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
<<<<<<< HEAD

		for(Method m : Class.forName("JUnit.tests.components.stub.TestCase").getMethods()){

=======
		
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
			
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
			Object obj = Class.forName("JUnit.tests.components.stub.TestCase").newInstance();


			// check each test annotation
			if(m.isAnnotationPresent(MemoryLimitTest.class)){

				runMemoryLimitTest(m, obj);
<<<<<<< HEAD

			}else if(m.isAnnotationPresent(AmpleMemory.class)){

=======
				
			}
			if(m.isAnnotationPresent(AmpleMemory.class)){
				
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
				runAmpleMemoryTest(m, obj);

			}else if(m.isAnnotationPresent(ExpectedCalls.class)){

				runExpectedCallsTest(m, obj);

			}
			else if(m.isAnnotationPresent(IgnorePassed.class)){

				runIgnorePassedTest(m, obj);

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
<<<<<<< HEAD

=======
		
		System.out.println("passed tests: " + passed);
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
		return false;

	}

	public static boolean runAmpleMemoryTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(AmpleMemory.class);
		AmpleMemory memoryTest = (AmpleMemory) annotation;

		try{
<<<<<<< HEAD
			m.invoke(obj);
=======
			System.out.println(m.invoke(obj));
			System.out.println("ample memory test passed");
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
			passed++;
			return true;
		}catch(Exception e){
			System.out.println("exception occurs: " + e.getMessage());
			failed++;
			return false;
		}
		catch (OutOfMemoryError E) {
			System.out.println("ample memory test failed");
			failed++;
			return false;
			//dont run 
		}
	}


	public static boolean runExpectedCallsTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls memoryTest = (ExpectedCalls) annotation;
		int i = 0;
		while(memoryTest.numOfMethodCalls()>i){
			try{
				m.invoke(obj);
				passed++;

				return true;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("passed tests: " + passed);
		return false;
	}

<<<<<<< HEAD
	public static void runIgnorePassedTest(Method m, Object obj){
		//TODO: Hendrik
	}

	public void Randomize(){

=======
	
	public static boolean runExpectedCallsTest(Method m, Object obj){
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls expectedCallsTest = (ExpectedCalls) annotation;
		
		try{
			int calls = expectedCallsTest.numOfMethodCalls();
			
			if(calls > 0){
				for(int i = 0; i < calls; i++){
					m.invoke(obj);
				}
				return true;
			}else
				return false;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
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
>>>>>>> 941283a56f304c6b0560d9cf49462bbdafe44ad2
	}

}
