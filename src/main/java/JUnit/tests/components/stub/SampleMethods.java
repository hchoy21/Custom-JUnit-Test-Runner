package JUnit.tests.components.stub;

import java.util.Random;

public class SampleMethods {

	int big;
	int small;
	int result;
	int random;
	Random rand;
	
	public SampleMethods(){
		
		big = 5;
		small = 3;
		random = 0;
		result = 0;
		rand = new Random();
		
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
		random = rand.nextInt(10);
	}
	
	public void printRandomNumber(){
		System.out.println(random);
	}
	
}