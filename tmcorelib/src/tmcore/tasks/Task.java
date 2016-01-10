/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmcore.tasks;

import java.util.Objects;

/**
 *
 * @author Doug
 */
public class Task {
  long ID;
	private String name, description;
	private State state;
	
	public enum State {
		NONE("<None>", 0),
		CREATED("New", 1),
    DELEGATED("Delegated", 2),
		AVAILABLE("Available", 3),
		ACTIVE("In Progress", 4),
		REVIEW("Under Review", 5),
		ACCEPTED("Accepted", 6),
		COMPLETE("Completed", 7);
		
		private final String strRep;
		private final int ordinal;
		State(String str, int ord) { strRep = str; ordinal = ord; }
		
		public int getOrdinal() { return ordinal; }
		
		public boolean equals(Task.State s) { return s.getOrdinal() == ordinal; }
		
		@Override
		public String toString() { return strRep; }
	}
	
	public Task() {
		name = description = "<None>";
		state = State.NONE;
	}
  
  public Task(String name, String description) {
    this(name, description, Task.State.CREATED);
  }
	
  /**
   * Initializes the task with its basic information.
   * @param name
   * @param description
   * @param state 
   */
	public Task(String name, String description, Task.State state) {
		this();
		this.name = name;
		this.description = description;
    this.state = state;
	}
	
	public String getName() { return name; }
  
  /**
   * Sets the task's name.
   * @param name the new name for the task
   */
  public void setName(String name) { this.name = name; }
  
  /**
   * 
   * @return the tasks's description
   */
	public String getDesc() { return description; }
  
  /**
   * Sets the task's description to that set by <code>desc</code>.
   * @param desc 
   */
  public void setDesc(String desc) { this.description = desc; }
  
  /**
   * Sets the state to that specified by <code>s</code>.
   * @param s 
   */
	public void setState(Task.State s) { state = s; }
	
  /**
   * 
   * @return the current state
   */
  public Task.State getState() { return state; }
  
  /**
   * @return the ID
   */
  public long getID() {
    return ID;
  }
  
  @Override
  public boolean equals(Object o) {
    if(o == null) return false;
    if(o instanceof Task) {
      return equals((Task)o);
    }
    return false;
  }
  
  public boolean equals(Task t) {
    if(t == null) return false;
    
    return t.hashCode() == this.hashCode();
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 11 * hash + Objects.hashCode(this.name);
    hash = 11 * hash + Objects.hashCode(this.description);
    hash = 11 * hash + Objects.hashCode(this.state);
    return hash;
  }
}