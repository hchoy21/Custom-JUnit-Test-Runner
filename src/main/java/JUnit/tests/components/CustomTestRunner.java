package JUnit.tests.components;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;


public class CustomTestRunner {

	HashMap<String, Boolean> testMethods = new HashMap<String, Boolean>();
	@SuppressWarnings("restriction")
	final OperatingSystemMXBean mbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
	.getOperatingSystemMXBean();
	ArrayList<Method> methodList;
	ArrayList<String> ignoreList;
	Method[] methods;
	Class<?> testFile;
	Annotation annotation;
	IgnorePassed ignore;
	boolean isIgnorePassedPresent;
	int passed = 0, failed = 0, numberOfTests = 0;

	public CustomTestRunner(String className) throws Exception{
		testFile = Class.forName(className);
		initializeRunner();
	}

	public boolean initializeRunner() throws Exception{

		isIgnorePassedPresent = false;

		// get the list of methods from the test case
		methods = testFile.getMethods();
		methodList = new ArrayList<Method>(Arrays.asList(methods));
		ignoreList = new ArrayList<String>();
		
		// if tester has decided they want to randomize
		if(testFile.isAnnotationPresent(Randomize.class))
			methodList = randomizeMethods(methodList);

		if(testFile.isAnnotationPresent(IgnorePassed.class)){
			isIgnorePassedPresent = true;
			methodList = runIgnorePassedTest(testFile, methodList);
			annotation = testFile.getAnnotation(IgnorePassed.class);
			ignore = (IgnorePassed) annotation;
		}
		// process method annotations
		for(Method m : methodList){

			Object obj = testFile.newInstance();
			boolean test;
			// check each test annotation
			if(m.isAnnotationPresent(CPULimitTest.class)){
				test = runCPULimitTest(m, obj);
				testMethods.put(m.getName() + " CPULimitTest", test);
				ignoreList.add(m.getName());
				numberOfTests++;
			}
			if(m.isAnnotationPresent(AmpleMemory.class)){
				test = runAmpleMemoryTest(m, obj);
				testMethods.put(m.getName() + " AmpleMemoryTest", test);
				ignoreList.add(m.getName());
				numberOfTests++;
			}
			if(m.isAnnotationPresent(ExpectedCalls.class)){
				test = runExpectedCallsTest(m, obj);
				testMethods.put(m.getName() + "ExpectedCallsTest", test);
				ignoreList.add(m.getName());
				numberOfTests++;
			}
		}	
		
		createResultsFile();
		
		if(isIgnorePassedPresent){
			saveIgnoredPassResults();
		}
		
		if(failed == 0)
			return true;
		else return false;
	}

	private void createResultsFile() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter writerResult;
		//creates a new test result file, or overwrites it if it exists
		writerResult = new PrintWriter("Results." + testFile.getName() + ".txt", "UTF-8");

		writerResult.println("TEST RESULTS FOR " + testFile.getName() + "\n\n");
		writerResult.println("--------------------------------------------------------\n");
		Iterator<Entry<String, Boolean>> it = testMethods.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Boolean> pair = it.next();
			if((Boolean) pair.getValue()){
				writerResult.println("\t" + pair.getKey() + " = " + "passed.\n");
			}
			else{
				writerResult.println("\t" + pair.getKey() + " = " + "failed.\n");
			}
			it.remove(); // avoids a ConcurrentModificationException
		}
		writerResult.println("\n\t\t tests passed: " + passed + "\n\t\t tests failed: " + failed);
		writerResult.println("\n--------------------------------------------------------");
		writerResult.flush();
		writerResult.close();
	}

	public File saveIgnoredPassResults() throws IOException{
		File file = new File("State." + testFile.getName() + ".txt");
		if(isIgnorePassedPresent){
			//creates a file if it does not exist
			//creates a new state file for ignorepassed if it is used
			if(ignore.reset()){
				PrintWriter writerState = new PrintWriter("State." + testFile.getName() + ".txt", "UTF-8");
				writerState.close();
			}
			BufferedWriter bufWriterState = new BufferedWriter(new FileWriter(file, true));
			for(int i=0; i<ignoreList.size(); i++)
				bufWriterState.write(ignoreList.get(i) + "\n");
			bufWriterState.flush();
			bufWriterState.close();
		}
		return file;	
	}

	@SuppressWarnings("restriction")
	public boolean runCPULimitTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Annotation annotation = m.getAnnotation(CPULimitTest.class);
		CPULimitTest cputest = (CPULimitTest) annotation;
		double load;


		do{
			m.invoke(obj);
			load = ((com.sun.management.OperatingSystemMXBean) mbean)
					.getProcessCpuLoad();
		}while(load==-1);

		// check test annotation against memory (kilobyte)
		if(cputest.limit() >= load * 100){
			passed++;
			return true;
		}else{
			failed++;
			return false;
		}


	}

	public boolean runAmpleMemoryTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
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


	public boolean runExpectedCallsTest(Method m, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Annotation annotation = m.getAnnotation(ExpectedCalls.class);
		ExpectedCalls expectedCallsTest = (ExpectedCalls) annotation;
		int calls = expectedCallsTest.numOfMethodCalls();

		if(calls > 0){
			for(int i = 0; i < calls; i++){
				m.invoke(obj);
			}
			passed++;
			return true;
		}
		else{
			failed++;
			m.invoke(obj);		//invoke at least once before failing
			return false;
		}
	}

	/*
	 * ignores test methods that passed in the last build
	 * stored in a xml file. The xml file will reset if the boolean reset is set to true when the annotation is called.
	 */
	public ArrayList<Method> runIgnorePassedTest(Class <?> c, ArrayList<Method> m) throws IOException, NoSuchMethodException, SecurityException{

		//creates a file if it does not exist
		File file = new File("State." + c.getName() + ".txt");
		if(!file.exists()){
			PrintWriter writer = new PrintWriter("State." + c.getName() + ".txt", "UTF-8");
			writer.close();
		}

		//if tester does not want to reset, read from file
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String methodName;
		while((methodName = reader.readLine())!= null){
			if(m.contains(c.getMethod(methodName))){
				m.remove(c.getMethod(methodName));
			}
		}
		reader.close();

		return m;

	}

	public ArrayList<Method> randomizeMethods(ArrayList<Method> m){
		if(!m.isEmpty()){
			Random r = new Random();
			int subset = r.nextInt(m.size());
			Collections.shuffle(m);
			for(int i=subset; i < m.size(); i++){
				m.remove(i);
			}
			return m;
		}else{
			return null;
		}
	}

}
