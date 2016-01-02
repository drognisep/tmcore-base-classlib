package tmcore.users;

import java.util.ArrayList;

public class Group {

  protected ArrayList<Integer> _users;

  protected int groupID;

  protected int leaderID;

  protected String groupName;

  Group(String name, int groupID) {
  }

  Group(String name, int groupID, int leaderID) {
  }

  Group() {
  }

  public boolean add(int userID) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void remove(int userID) {
  }

  public int[] getUserIDs() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setLeader(int userID) {
  }

  public int getLeaderID() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public String getGroupName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setGroupName(String groupName) {
  }
}
