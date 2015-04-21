package JUnit.tests.components;

import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;


public class CustomTestRunner {

	static int passed = 0, failed = 0;
	static OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
			.getOperatingSystemMXBean();

//	public static void main(String[] args) throws Exception{

		//		Class<TestCase> testCase = TestCase.class;
		//		
		//		// get the list of methods from the test case
		//		Method[] methods = Class.forName("JUnit.tests.components.stub.TestCase").getMethods();
		//		ArrayList<Method> methodList = new ArrayList<Method>(Arrays.asList(methods));
		//		
		//		
		//		// if tester has decided they want to randomize
		//		if(testCase.isAnnotationPresent(Randomize.class)){
		//			
		//			randomizeMethods(methodList);
		//		}
		////		if(testCase.isAnnotationPresent(IgnorePassed.class)){
		////
		////			methods = runignorePassedTest(testCase, methodList);
		////		}
		////
		////		if(testCase.isAnnotationPresent(IgnorePassed.class)){
		////				
		////		}
		//		
		//		// process method annotations
		//		for(Method m : methodList){
		//			
		//			Object obj = Class.forName("JUnit.tests.components.stub.TestCase").newInstance();
		//			
		//			
		//			// check each test annotation
		//			if(m.isAnnotationPresent(CPULimitTest.class)){
		//				
		//				runCPULimitTest(m, obj);
		//				
		//			}
		//			if(m.isAnnotationPresent(AmpleMemory.class)){
		//				
		//				runAmpleMemoryTest(m, obj);
		//				
		//			}
		//			if(m.isAnnotationPresent(ExpectedCalls.class)){
		//				
		//				runExpectedCallsTest(m, obj);
		//				
		//			}
		//		}
		//		
		//		System.out.println("passed tests: " + passed);
		//		System.out.println("failed tests: " + failed);
//	}

	//TODO: default value is false? figure logic
	public static boolean runCPULimitTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Annotation annotation = m.getAnnotation(CPULimitTest.class);
		CPULimitTest cputest = (CPULimitTest) annotation;
		double load;


		do{
			m.invoke(obj);
			load = ((com.sun.management.OperatingSystemMXBean) mbean)
					.getProcessCpuLoad();
		}while(load==-1);

		System.out.println("method: " + m.getName() + "cpu load: " + load*100 + "%");

		// check test annotation against memory (kilobyte)
		if(cputest.limit() >= load * 100){
			passed++;
			return true;
		}else{
			failed++;
			return false;
		}


	}

	public static boolean runAmpleMemoryTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Annotation annotation = m.getAnnotation(AmpleMemory.class);
		AmpleMemory memoryTest = (AmpleMemory) annotation;


		Runtime runtime = Runtime.getRuntime();
		float totalMemory = runtime.totalMemory()/1000;
		float freeMemory = runtime.freeMemory()/1000;
		float threshHold = (freeMemory/totalMemory) * 100;
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


	public static boolean runExpectedCallsTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls expectedCallsTest = (ExpectedCalls) annotation;
		int calls = expectedCallsTest.numOfMethodCalls();
		int count = 0;


		if(calls > 0){
			for(int i = 0; i < calls; i++){
				m.invoke(obj);
				count++;
			}
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
