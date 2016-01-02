package tmcore.users;

import java.util.HashMap;

public class UserManager {

  protected int latestUID;

  protected int latestGID;

  private static UserManager instance;

  protected static User currentUser;

  public static final String DEFAULT_CONFIG_NAME = "umgr.tmdb";

  private HashMap<Integer, User> users;

  private HashMap<Integer, Group> groups;

  UserManager() {
    instance    = null;
    latestUID   = 0;
    latestGID   = 0;
    currentUser = null;
    users       = new HashMap<>();
    groups      = new HashMap<>();
  }

  public static UserManager instance() {
    if(instance == null) instance = new UserManager();
    
    return instance;
  }

  public User getUser(int userID) {
    if(users.isEmpty()) {
      System.out.println("No users in database");
      return null;
    } else if(!users.containsKey(userID)) {
      System.out.println("Invalid userID");
      return null;
    }
    
    return users.get(userID);
  }

  public User newUser(String userName) {
    if(users.size() == Integer.MAX_VALUE) {
      System.out.println("Database is full of users");
      return null;
    }
    
    User u = new User(userName, ++latestUID);
    
    return u;
  }

  public void removeUser(int userID) {
  }

  public Group getGroup(int groupID) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public Group newGroup(String groupName, int[] users) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void removeGroup(int groupID) {
  }

  public User currentUser() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public char[] login(String username, char[] password) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void logout() {
  }
}
