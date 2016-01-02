package tmcore.tasks;

import java.util.ArrayList;

public class Project {
	private String name, description;
	private final ArrayList<Task> tasks;
	private final ArrayList<String> managers;
	private final ArrayList<String[]> resourceGroups;
	private final String creator;
	
	public Project() {
		tasks = new ArrayList<>();
		managers = new ArrayList<>();
		resourceGroups = new ArrayList<>();
		
		name = description = creator = "<None>";
	}
	
	public Project(String name, String description, String creator) {
		tasks = new ArrayList<>();
		managers = new ArrayList<>();
		resourceGroups = new ArrayList<>();
    
		this.name = (name == null ? "<None>" : name);
		this.description = (description == null ? "<None>" : description);
    this.creator = (creator == null ? "<None>" : creator);
	}
	
	public String getCreator() { return creator; }
	
	public String getManager(int at) { return managers.get(at); }
	public String[] getManagers() {
		Object[] objs = managers.toArray();
		String[] manAry = new String[objs.length];
		for(int i = 0; i < objs.length; ++i) { manAry[i] = (String)objs[i]; }
		return manAry;
	}
	public void addManager(String man) { managers.add(man); }
	public void rmvManager(String man) { managers.remove(man); }
	
	public Task getTask(int at) { return tasks.get(at); }
	public Task[] getTasks() {
		Object[] objs = tasks.toArray();
		Task[] taskAry = new Task[objs.length];
		for(int i = 0; i < objs.length; ++i) { taskAry[i] = (Task)objs[i]; }
		return taskAry;
	}
	public void addTask(Task t) { tasks.add(t); }
	public void rmvTask(Task t) { tasks.remove(t); }
	
	public String getName() { return name; }
  public void setName(String name) { this.name = name; }
	public String getDesc() { return description; }
  public void setDesc(String desc) { this.description = desc; }
	
	public int getResourceGroupCount() { return resourceGroups.size(); }
	public String[] getResourceGroup(int groupIdx) {
		if(groupIdx >= getResourceGroupCount() || groupIdx < 0) throw new
			IllegalArgumentException("Invalid group index");
		
		return resourceGroups.get(groupIdx);
	}
	public int newResourceGroup(String... names) {
		if(names == null) throw new NullPointerException("Null names");
		resourceGroups.add(names);
		return resourceGroups.size() - 1;
	}
  public void delResourceGroup(int groupIdx) {
    if(groupIdx >= getResourceGroupCount() || groupIdx < 0) throw new
			IllegalArgumentException("Invalid group index");
    
    resourceGroups.remove(groupIdx);
  }
	public void addResource(int groupIdx, String name) {
		if(groupIdx >= getResourceGroupCount() || groupIdx < 0) throw new
			IllegalArgumentException("Invalid group index");
		
		if(name == null) throw new NullPointerException("Null name");
		
		String[] group = resourceGroups.get(groupIdx);
		String[] newgrp = new String[group.length + 1];
		
		System.arraycopy(group, 0, newgrp, 0, group.length);
		newgrp[group.length] = name;
		
		resourceGroups.set(groupIdx, newgrp);
	}
	public void rmvResource(int groupIdx, String name) {
		if(groupIdx >= getResourceGroupCount() || groupIdx < 0) throw new
			IllegalArgumentException("Invalid group index");
		
		if(name == null) throw new NullPointerException("Null name");
		
		String[] group = resourceGroups.get(groupIdx);
		String[] newgrp = new String[group.length - 1];
    boolean nameFound = false;
		
		int i = 0, j = 0, target = 0;
    for(; i < group.length; ++i) {
      if(group[i].equals(name)) {
        nameFound = true;
        target = i;
        break;
      }
    }
    
    if(nameFound) {
      for(i = 0; i < group.length; ++i) {
        if(i != target) newgrp[j++] = group[i];
      }
    }
//		int limit = group.length - 1;
//		for(int i = 0; i < group.length; ++i) {
//			if(group[i].equals(name)) {
//        if(nameFound)
//      }
//			else {
//				if(i == limit && j == limit) return;
//				newgrp[j++] = group[i];
//			}
//		}
    
    resourceGroups.set(groupIdx, newgrp);
	}
}