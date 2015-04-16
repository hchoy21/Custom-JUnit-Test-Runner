package JUnit.tests.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
		Method[] methods = testCase.getMethods();
		ArrayList<Method> methodList = new ArrayList<Method>(Arrays.asList(methods));


		// if tester has decided they want to randomize
		if(testCase.isAnnotationPresent(Randomize.class)){


			randomizeMethods(methodList);
		}
//		if(testCase.isAnnotationPresent(IgnorePassed.class)){
//
//			methods = runignorePassedTest(testCase, methodList);
//		}


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
		}

		System.out.println("passed tests: " + passed);
		System.out.println("failed tests: " + failed);
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
			Runtime runtime = Runtime.getRuntime();
			float totalMemory = runtime.totalMemory()/1000;
			float freeMemory = runtime.freeMemory()/1000;
			float threshHold = (freeMemory/totalMemory) * 100;
			System.out.println(freeMemory/totalMemory);
			if(memoryTest.threshHold()<threshHold){
				m.invoke(obj);
				passed++;
				return true;
			}
			else{
				failed++;
				return false;
			}
		}
		catch (Exception e) {
			failed++;
			System.out.println("out of memory");
			return false;
			//dont run 
		}
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

	/*
	 * ignores test methods that passed in the last build
	 * stored in a xml file. The xml file will reset if the boolean reset is set to true when the annotation is called.
	 */
//	public static Method[] runignorePassedTest(Class <?> c, ArrayList<Method> m) throws FileNotFoundException, UnsupportedEncodingException{
//		Annotation annotation = c.getAnnotation(IgnorePassed.class);
//		IgnorePassed ignore = (IgnorePassed) annotation;
//		
//		//creates a file if it does not exist
//		File file = new File("state.xml");
//		if(!file.exists()){
//			PrintWriter writer = new PrintWriter("state.txt", "UTF-8");
//		}
//		
//		BufferedReader reader = new BufferedReader(new FileReader(file));
//		if(ignore.reset()==false){
//			
//		}
//		else{
//			PrintWriter writer = new PrintWriter("state.txt", "UTF-8");
//			writer.close();
//		}
//		
//	}

	public static boolean randomizeMethods(ArrayList<Method> m){
		if(!m.isEmpty()){
			Collections.shuffle(m);
			return true;
		}else{
			System.out.println("empty method list");
			return false;
		}
	}

}
