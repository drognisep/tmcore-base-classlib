package tmcore.users;

public class UserSecurityException extends Exception {

    public UserSecurityException(String message) {
        super(message);
    }

    public UserSecurityException() {
        super();
    }
}
