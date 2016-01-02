package tmcore.communication;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;

public class CommunicationManager implements java.io.Serializable{
  private static final long serialVersionUID = -5301094609626301353L;

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

    CommunicationManager() {
    }

    public void register(SystemListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public CommunicationManager getInstance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ConnectionStatus getStatus(ConnectionType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void connect(InetSocketAddress adrs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void connect(InetSocketAddress adrs, ConnectionType type) {
      throw new UnsupportedOperationException("Not supported yet.");
    }

    public InetSocketAddress getAddress(ConnectionType type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void postUDP(Notification note) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void postTCP(Notification note) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
