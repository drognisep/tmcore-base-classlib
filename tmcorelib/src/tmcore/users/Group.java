package tmcore.users;

import java.util.ArrayList;

public class Group {

    protected ArrayList<Long> users;

    protected long id;

    protected UserManager manager;

    protected String name;

    public Group(UserManager mgr, User[] users) {
    }

    protected Group() {
    }

    public User[] getUsers() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
