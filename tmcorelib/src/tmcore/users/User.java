package tmcore.users;

public class User {

  protected int id;

  protected String name;

  protected String userClass;

  User() {
  }

  User(String name, int userID) {
  }

  User(String name, int userID, String userClass) {
  }

  public int getID() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setUserName(String userName) {
  }

  public String getUserName() {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  public void setUserClass(String userClass) {
  }

  public String getUserClass() {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
