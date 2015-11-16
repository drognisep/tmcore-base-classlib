package tmcore.users;

public class User {

    protected long id;

    protected String name;

    protected String userClass;

    User(String name, long userID) {
    }

    User(String name, long userID, String userClass) {
    }

    protected User() {
    }

    public long getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setUserName(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUserName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setUserClass(String userClass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getUserClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
