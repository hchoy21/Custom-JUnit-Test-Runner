package JUnit.tests.components.stub;

import java.util.Random;

public class CalculatorStub {

	int big;
	int small;
	int result;
	int random;
	Random rand = new Random();
	
	public CalculatorStub(){
		
		big = 5;
		small = 3;
		
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
		random = rand.nextInt();
	}
	
	public void printRandomNumber(){
		System.out.println(random);
	}
	
}
