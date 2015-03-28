package JUnit.tests.components;

import org.junit.BeforeClass;

import JUnit.tests.components.stub.BubbleSortStub;


public class BubbleSortStubTest {

	BubbleSortStub bubble;

	@BeforeClass
	@IgnorePassed(num = 5, passed = { false, false, false, false, false})
	@RandomizedTest
	public void setUp(){
		//instantiate prehand
		bubble = new BubbleSortStub();
	}

	@MemoryTest (max_memory_allowed=1000)
	public void testMemory(){
		bubble.fillArray();
	}

	@ExpectedCalls (numOfMethodCalls = 4)
	public void testDoStuff(){
		bubble.deleteAllElements();
		bubble.fillArray();
		bubble.sortArray();
		bubble.printArray();
	}

	@ExpectedCalls (numOfMethodCalls = 1)
	public void testDeleteWithCalls(){
		bubble.deleteElement(2);
	}

	@MemoryTest(max_memory_allowed=1)
	public void anotherTest(){
		bubble.deleteAllElements();	
	}
	
}