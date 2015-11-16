package tmcore.users;

public class UserManager {

    protected long latestUID;

    protected long latestGID;

    protected java.util.ArrayList<User> users;

    protected java.util.ArrayList<Group> groups;

    private static UserManager instance;

    protected static User currentUser;

    public static final String DEFAULT_CONFIG_NAME = "auth.tmc";

    protected UserManager() {
    }

    public static UserManager instance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getUser(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String newUser(String userName, String userClass) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long removeUser(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getGroup(long groupID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long[] newGroup(String groupName, long[] users) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long removeGroup(long groupID) {
        throw new UnsupportedOperationException("Not supported yet.");
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
