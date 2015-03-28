package JUnit.tests.components.stub;

import java.util.Random;

public class CalculatorStub {

	int big;
	int small;
	int result;
	int random;
	
	public CalculatorStub(){
		
		big = 5;
		small = 3;
		random = 0;
		result = 0;
		
	}
	
	public void add(int a, int b){
		result = a + b;
	}
	
	public void subtract(int a, int b){
		result = a - b;
	}

	public void printResult(){
		System.out.println(result);
	}
	
	public void randomNumber(){
		random = 5;
	}
	
	public void printRandomNumber(){
		System.out.println(random);
	}
	
}
