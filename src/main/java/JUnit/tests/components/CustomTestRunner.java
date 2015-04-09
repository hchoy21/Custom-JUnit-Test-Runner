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

			}else if(m.isAnnotationPresent(AmpleMemory.class)){

				runAmpleMemoryTest(m, obj);

			}else if(m.isAnnotationPresent(ExpectedCalls.class)){

				runExpectedCallsTest(m, obj);

			}
			else if(m.isAnnotationPresent(IgnorePassed.class)){

				runIgnorePassedTest(m, obj);

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
				System.out.println("memory test passed");
				passed++;

				return true;
			}else{
				System.out.println("memory test failed");
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
			System.out.println("exception occurs: " + e.getMessage());
			failed++;
			return false;
		}
		catch (OutOfMemoryError E) {
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

	public static void runIgnorePassedTest(Method m, Object obj){
		//TODO: Hendrik
	}

	public void Randomize(){

	}

}
