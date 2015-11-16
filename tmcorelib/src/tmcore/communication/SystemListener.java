package tmcore.communication;

public interface SystemListener {

    public String getSubSystemID();

    public Notification post(Notification note);

    public long getOwner();
}
