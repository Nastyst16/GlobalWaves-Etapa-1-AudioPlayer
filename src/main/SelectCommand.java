package main;

public class SelectCommand {
    private String user;
    private int timestamp;
    private String message;

    public SelectCommand(String user, int timestamp, String message) {
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
