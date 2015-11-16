package tmcore.users;

class UserClass {

    private String label;

    private java.util.ArrayList<String> userActions;

    UserClass(String userClass) {
    }

    public String getLabel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String addAction(String action) throws UserSecurityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String rmvAction(String action) throws UserSecurityException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public boolean hasAction(String action) {
      throw new UnsupportedOperationException("Not supported yet.");
    }
}
