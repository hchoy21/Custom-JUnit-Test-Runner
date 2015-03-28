package JUnit.tests.components.stub;

import java.util.Random;

public class BubbleSortStub {

	static int maxArraySize = 10;
	int[] array;
	Random rand = new Random();
	String errorMessage;

	public BubbleSortStub(){
		array = new int[maxArraySize];
	}

	public void fillArray(){
		for(int i=0;i<array.length;i++){
			array[i] = rand.nextInt(20 + 1);
		}
	}

	/*
	 * sorts array from highest to lowest
	 */
	public void sortArray(){
		if(array != null){
			int temp;
			for(int i=0;i<array.length;i++){
				for(int j=0;j<array.length;j++){
					if(array[i]>array[j]){
						temp = j;
						array[j] = array[i];
						array[i] = temp;
					}
				}
			}
		}
		else{
			errorMessage = "Array is null! Cannot sort.";
		}
	}

	public void printArray(){
		if(array != null)
			for(int i=0;i<array.length;i++){
				System.out.print(array[i] + " ");
			}
		else errorMessage = "Array is null! Cannot print.";
	}

	public void deleteAllElements(){
		array = new int[maxArraySize];
	}
	
	public boolean isArrayEmpty(){
		for(int i=0;i<array.length;i++){
			if(array[i] != 0){
				return false;
			}
		}
		
		return true;
	}
	
	public void insertElement(int a, int index){
		if(index >= maxArraySize){
			errorMessage = "Index is greater than array size.";
		}
		else if(array == null){
			errorMessage = "array is null! cannot insert an element.";
		}
		else array[index] = a;
	}
	
	public void deleteElement(int index){
		if(index >= maxArraySize){
			errorMessage = "Index is greater than array size.";
		}
		else if(array == null){
			errorMessage = "array is null! cannot insert an element.";
		}
		else array[index] = 0;
	}
	
}
