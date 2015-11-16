package tmcore.users;

class SuperUserClass extends UserClass {

    protected SuperUserClass() {
        super("SuperUserClass");
    }
    
    @Override
    public String getLabel() {
        return "SuperUserClass";
    }
}
