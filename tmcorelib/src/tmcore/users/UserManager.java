package tmcore.users;

public interface UserManager {

    public long getNewID();

    public long getUser(long id);

    public long getGroup(long id);

    public long[] getUserGroups(long userID);

    public String[] getUserGroupNames(long userID);
}
