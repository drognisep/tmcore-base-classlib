package tmcore.users;

public class Group {

    protected java.util.ArrayList<Long> users;

    protected long groupID;

    protected long leaderID;

    protected String groupName;

    Group(String name, long groupID) {
    }

    Group(String name, long groupID, long leaderID) {
    }

    protected Group() {
    }

    public long add(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long remove(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long[] users() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long get(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long setLeader(long userID) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public User getLeader() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getGroupName() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String setGroupName(String groupName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected ArrayList<Long> users;
}
