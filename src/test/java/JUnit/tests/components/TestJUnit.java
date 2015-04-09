package JUnit.tests.components;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
 
public class TestJUnit {
       
	//2s are fails, regular is passed
        Object ctrObj;
        Method[] method;
        Object ctrObj2;
        Method[] method2;
        
        @Before
        public void setup() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
                ctrObj = Class.forName("JUnit.tests.components.stub.TestCasePass").newInstance();
                method = Class.forName("JUnit.tests.components.stub.TestCasePass").getMethods();
                ctrObj2 = Class.forName("JUnit.tests.components.stub.TestCaseFail").newInstance();
                method2 = Class.forName("JUnit.tests.components.stub.TestCaseFail").getMethods();
        }
       
       
//      // TODO: JUnit testing on the CustomTestRunner
//      @Test
//      public void memoryRestrictionLimitTestFailed(){
//              for(Method m : method){
//                      // run tests on marked annotations
//                      if(m.isAnnotationPresent(MemoryLimitTest.class)){
//                              assertFalse("Fails the set memory Limit", CustomTestRunner.runMemoryLimitTest(m, ctrObj));
//                      }
//                                                     
//              }
//             
//      }
//     
//      @Test
//      public void memoryRestrictionLimitTestPassed(){
//              for(Method m : method){
//                      // run tests on marked annotations
//                      if(m.isAnnotationPresent(MemoryLimitTest.class)){
//                             
//                              assertTrue("Passes the set memory limit", CustomTestRunner.runMemoryLimitTest(m, ctrObj));
//                      }
//                                                     
//              }
//             
//      }
       
        // TODO: JUnit testing on the CustomTestRunner
        @Test
        public void ampleMemoryRunTestFailed(){
                for(Method m : method2){
                        // run tests on marked annotations
                        if(m.isAnnotationPresent(AmpleMemory.class)){
                               
                                assertFalse("There is not enough memory in the JVM to run this method", CustomTestRunner.runAmpleMemoryTest(m, ctrObj2));
                        }
                                                       
                }
               
        }
       
        @Test
        public void ampleMemoryRunTestPassed(){
                for(Method m : method){
                        // run tests on marked annotations
                        if(m.isAnnotationPresent(AmpleMemory.class)){
                               
                                assertTrue("There is enough memory in the JVM", CustomTestRunner.runAmpleMemoryTest(m, ctrObj));
                        }
                                                       
                }
               
        }
       
        @Test (expected=RuntimeException.class)
        public void IgnorePassedTestFailed(){
               
        }
       
        @Test (expected=RuntimeException.class)
        public void IgnorePassedTestPassed(){
               
               
        }
       
        @Test
        public void RandomizeFailed(){
//              CustomTestRunner.methodList = null;
                ArrayList<Method> mList = new ArrayList<Method>(Arrays.asList(method2));
               
               
                //empty the list of methods
                mList.clear();
                assertFalse("randomize", CustomTestRunner.randomizeMethods(mList));
               
        }
       
        @Test
        public void RandomizePassed(){
                ArrayList<Method> mList = new ArrayList<Method>(Arrays.asList(method));
 
                assertTrue("randomize", CustomTestRunner.randomizeMethods(mList));
        }
       
        @Test
        public void ExpectedCallTestFailed(){
               
                for(Method m : method2){
                       
                        if(m.isAnnotationPresent(ExpectedCalls.class)){
                                assertFalse("Methods were not called as minimumly set by user", CustomTestRunner.runExpectedCallsTest(m, ctrObj2));
                        }
                }
        }
       
        @Test
        public void ExpectedCallTestPassed(){
                for(Method m : method){
                       
                        if(m.isAnnotationPresent(ExpectedCalls.class)){
                                assertTrue("Expected Calls", CustomTestRunner.runExpectedCallsTest(m, ctrObj));
                        }
                }
               
        }
       
}