package tmcore.users;

class AdminUserClass extends UserClass {

    protected AdminUserClass() {
        super("AdminUserClass");
    }
    
    @Override
    public String getLabel() {
        return "AdminUserClass";
    }
}
