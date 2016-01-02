/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmcore.tasks;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Doug
 */
public class TaskTest {
  
  public TaskTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of getName method, of class Task.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    Task instance = new Task();
    String expResult = "";
    String result = instance.getName();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setName method, of class Task.
   */
  @Test
  public void testSetName() {
    System.out.println("setName");
    String name = "";
    Task instance = new Task();
    instance.setName(name);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getDesc method, of class Task.
   */
  @Test
  public void testGetDesc() {
    System.out.println("getDesc");
    Task instance = new Task();
    String expResult = "";
    String result = instance.getDesc();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setDesc method, of class Task.
   */
  @Test
  public void testSetDesc() {
    System.out.println("setDesc");
    String desc = "";
    Task instance = new Task();
    instance.setDesc(desc);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of setState method, of class Task.
   */
  @Test
  public void testSetState() {
    System.out.println("setState");
    Task.State s = null;
    Task instance = new Task();
    instance.setState(s);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of getState method, of class Task.
   */
  @Test
  public void testGetState() {
    System.out.println("getState");
    Task instance = new Task();
    Task.State expResult = null;
    Task.State result = instance.getState();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of equals method, of class Task.
   */
  @Test
  public void testEquals_Object() {
    System.out.println("equals");
    Object o = null;
    Task instance = new Task();
    boolean expResult = false;
    boolean result = instance.equals(o);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of equals method, of class Task.
   */
  @Test
  public void testEquals_Task() {
    System.out.println("equals");
    Task t = null;
    Task instance = new Task();
    boolean expResult = false;
    boolean result = instance.equals(t);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of hashCode method, of class Task.
   */
  @Test
  public void testHashCode() {
    System.out.println("hashCode");
    Task instance = new Task();
    int expResult = 0;
    int result = instance.hashCode();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
  
}
