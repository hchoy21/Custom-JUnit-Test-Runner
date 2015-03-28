package JUnit.tests.components;

import java.lang.reflect.Method;


public class CustomTestRunner {
	
	public static void main(String[] args) throws Exception{
		
		
		int passed = 0, failed = 0;
		
		Object obj = Class.forName(args[0]).newInstance();
		
		for(Method m: Class.forName(args[0]).getMethods()){
			
		}
		
		
	}

}
