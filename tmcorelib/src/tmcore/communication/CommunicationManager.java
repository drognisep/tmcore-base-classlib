package tmcore.communication;

public class CommunicationManager {

    protected ConnectionStatus connectStatus;

    protected InetSocketAddress ip;

    protected Queue<Notification> queue;

    protected ConnectionStatus status;

    protected ArrayList<SystemListener> registry;

    private static CommunicationManager instance;

    protected MulticastSocket mcConnection;

    protected DatagramSocket dgConnection;

    protected Socket skConnection;

    protected ConnectionStatus mcConnectionStatus;

    protected ConnectionStatus dgConnectionStatus;

    protected ConnectionStatus skConnectionStatus;

    protected CommunicationManager() {
    }

    public SystemListener register(SystemListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CommunicationManager getInstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ConnectionStatus getStatus() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CommunicationManager connect(InetSocketAddress adrs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public InetSocketAddress getAddress() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Notification postUDP(Notification note) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Notification postTCP(Notification note) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
