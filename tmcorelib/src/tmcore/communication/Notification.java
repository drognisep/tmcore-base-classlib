package tmcore.communication;

public final class Notification {

    private long srcID;

    private long dstID;

    private MessageFlag flag;

    private String sysID;

    private byte[] payload;

    public Notification(byte[] data, long sourceID, long destID, MessageFlag flag, String subSysSource) {
    }

    public Notification(byte[] data, SystemListener source, long destID, MessageFlag flag) {
    }

    public Notification(String message, long sourceID, long destID, MessageFlag flag) {
    }

    public long getSource() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public long getTarget() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MessageFlag getFlag() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getSubSystemID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MessageFlag createReply(byte[] data, Notification original, MessageFlag flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
