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
	String name, description;
	State state;
	
	public enum State {
		NONE("<None>", 0),
		CREATED("New", 1),
		AVAILABLE("Available", 2),
		ACTIVE("In Progress", 3),
		REVIEW("Under Review", 4),
		ACCEPTED("Accepted", 5),
		COMPLETE("Completed", 6);
		
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
	
	public Task(String name, String description, Task.State state) {
		this();
		this.name = name;
		this.description = description;
    this.state = state;
	}
	
	public String getName() { return name; }
  public void setName(String name) { this.name = name; }
	public String getDesc() { return description; }
  public void setDesc(String desc) { this.description = desc; }
	public void setState(Task.State s) { state = s; }
	public Task.State getState() { return state; }
  
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