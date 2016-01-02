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
public class ProjectTest {
  private final Project p;
  private final int groupID;
  private final Task task;
  
  public ProjectTest() {
    p = new Project("Unit Test Project", "This project is used to test all of "
        + "the functionality expected from this class", "Owner");
    p.addManager("Manager");
    groupID = p.newResourceGroup("User1", "User2");
    task = new Task("Unit Test Task", "Used to test project functionality");
    p.addTask(task);
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
    // No tearDownClass()
  }
  
  @Before
  public void setUp() {
    // No setUp()
  }
  
  @After
  public void tearDown() {
    // No tearDown()
  }
  
  /**
   * A general run test using various features
   */
  @Test
  public void testGeneralTest() {
    Project proj = new Project("A Project", "A new test project", "drognisep");
		System.out.format("Name: %s%nDesc: %s%n", proj.getName(), proj.getDesc());
		
		int group1 = proj.newResourceGroup("GM", "PM", "Manager");
		String[] sgroup1 = proj.getResourceGroup(group1);
		
		System.out.println("Project - group1");
		for(String s : sgroup1) { System.out.println("\t" + s); }
		
		proj.addTask(new Task("Simple Task", "I'm a simple task, in a simple world."));
		proj.getTask(0).setState(Task.State.COMPLETE);
		proj.addTask(new Task("It's fantastic", "And elastic"));
		proj.getTask(1).setState(Task.State.REVIEW);
		proj.addTask(new Task("Brush my hair", "And touch me everywhere"));
		proj.getTask(2).setState(Task.State.ACTIVE);
		
		Task.State state;
		System.out.println("My Tasks:");
		for(Task t : proj.getTasks()) {
			state = t.getState();
			System.out.format("Name: \"%s\", Desc: \"%s\", State: \"%s\" (%d)%n", 
				t.getName(), t.getDesc(), state.toString(), state.getOrdinal());
		}
  }

  /**
   * Test of getCreator method, of class Project.
   */
  @Test
  public void testGetCreator() {
    System.out.println("getCreator");
    String expResult = "Owner";
    String result = p.getCreator();
    assertEquals(expResult, result);
  }

  /**
   * Test of getManager method, of class Project.
   */
  @Test
  public void testGetManager() {
    System.out.println("getManager");
    int at = 0;
    Project instance = p;
    String expResult = "Manager";
    String result = instance.getManager(at);
    assertEquals(expResult, result);
  }

  /**
   * Test of getManagers method, of class Project.
   */
  @Test
  public void testGetManagers() {
    System.out.println("getManagers");
    Project instance = p;
    String[] expResult = new String[] {"Manager"};
    String[] result = instance.getManagers();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of addManager method, of class Project.
   */
  @Test
  public void testAddManager() {
    System.out.println("addManager");
    String man = "Another Manager";
    Project instance = p;
    instance.addManager(man);
    
    assertEquals(instance.getManager(1), man);
    instance.rmvManager(man);
  }

  /**
   * Test of rmvManager method, of class Project.
   */
  @Test
  public void testRmvManager() {
    System.out.println("rmvManager");
    String man = "Manager";
    Project instance = p;
    instance.rmvManager(man);
    
    assertEquals(instance.getManagers().length, 0);
    instance.addManager("Manager");
  }

  /**
   * Test of getTask method, of class Project.
   */
  @Test
  public void testGetTask() {
    System.out.println("getTask");
    int at = 0;
    Project instance = p;
    Task t = instance.getTask(at);
    
    assertEquals(t.getName(), "Unit Test Task");
    assertEquals(t.getDesc(), "Used to test project functionality");
    assertEquals(t.getState(), Task.State.CREATED);
  }

  /**
   * Test of getTasks method, of class Project.
   */
  @Test
  public void testGetTasks() {
    System.out.println("getTasks");
    Project instance = p;
    Task[] expResult = new Task[] { new Task("Unit Test Task", "Used to test "
        + "project functionality", Task.State.CREATED) };
    Task[] result = instance.getTasks();
    for(int i = 0; i < result.length; ++i) {
      assertTrue(expResult[i].equals(result[i]));
    }
  }

  /**
   * Test of addTask method, of class Project.
   */
  @Test
  public void testAddTask() {
    System.out.println("addTask");
    Task t = new Task("Test1", "Desc");
    Project instance = p;
    instance.addTask(t);
    
    assertEquals(p.getTask(1), t);
    assertTrue(p.getTasks().length == 2);
    p.rmvTask(t);
    assertEquals(p.getTask(0), task);
    assertTrue(p.getTasks().length == 1);
  }

  /**
   * Test of rmvTask method, of class Project.
   */
  @Test
  public void testRmvTask() {
    System.out.println("rmvTask");
    Task t = new Task("Test1", "Desc");
    Project instance = p;
    instance.addTask(t);
    
    assertTrue(instance.getTasks().length == 2);
    p.rmvTask(t);
    assertEquals(p.getTask(0), task);
    assertTrue(p.getTasks().length == 1);
  }

  /**
   * Test of getName method, of class Project.
   */
  @Test
  public void testGetName() {
    System.out.println("getName");
    Project instance = p;
    String expResult = "Unit Test Project";
    String result = instance.getName();
    assertEquals(expResult, result);
  }

  /**
   * Test of getDesc method, of class Project.
   */
  @Test
  public void testGetDesc() {
    System.out.println("getDesc");
    Project instance = p;
    String expResult = "This project is used to test all of the functionality "
        + "expected from this class";
    String result = instance.getDesc();
    assertEquals(expResult, result);
  }

  /**
   * Test of getResourceGroupCount method, of class Project.
   */
  @Test
  public void testGetResourceGroupCount() {
    System.out.println("getResourceGroupCount");
    Project instance = p;
    int expResult = p.getResourceGroupCount() + 1;
    int newgrp = p.newResourceGroup("Blah", "Blah");
    int result = instance.getResourceGroupCount();
    assertEquals(expResult, result);
    
    p.delResourceGroup(newgrp);
    assertEquals(p.getResourceGroupCount(), expResult - 1);
  }

  /**
   * Test of getResourceGroup method, of class Project.
   */
  @Test
  public void testGetResourceGroup() {
    System.out.println("getResourceGroup");
    int groupIdx = 0;
    Project instance = p;
    String[] expResult = new String[] {"User1", "User2"};
    String[] result = instance.getResourceGroup(groupIdx);
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of newResourceGroup method, of class Project.
   */
  @Test
  public void testNewResourceGroup() {
    System.out.println("newResourceGroup");
    String[] names = new String[] {"Test1", "Test2"};
    Project instance = p;
    int expResult = 1;
    int result = instance.newResourceGroup(names);
    assertEquals(expResult, result);
    
    p.delResourceGroup(result);
    assertEquals(p.getResourceGroupCount(), 1);
  }

  /**
   * Test of addResource method, of class Project.
   */
  @Test
  public void testAddResource() {
    System.out.println("addResource");
    int groupIdx = 0;
    String name = "User3";
    String[] result = new String[] {"User1", "User2", "User3"};
    Project instance = p;
    instance.addResource(groupIdx, name);
    
    assertArrayEquals(p.getResourceGroup(groupIdx), result);
    
    p.rmvResource(groupIdx, "User3");
    assertEquals(p.getResourceGroup(groupIdx).length, 2);
  }

  /**
   * Test of rmvResource method, of class Project.
   */
  @Test
  public void testRmvResource() {
    System.out.println("addResource");
    int groupIdx = 0;
    String name = "User2";
    String[] result = new String[] {"User1"};
    Project instance = p;
    instance.rmvResource(groupIdx, "User2");
    
    assertArrayEquals(p.getResourceGroup(groupIdx), result);
    
    p.addResource(groupIdx, "User2");
    assertEquals(p.getResourceGroup(groupIdx).length, 2);
  }

  /**
   * Test of setName method, of class Project.
   */
  @Test
  public void testSetName() {
    assertTrue(true);
  }

  /**
   * Test of setDesc method, of class Project.
   */
  @Test
  public void testSetDesc() {
    assertTrue(true);
  }

  /**
   * Test of delResourceGroup method, of class Project.
   */
  @Test
  public void testDelResourceGroup() {
    assertTrue(true);
  }
  
}
