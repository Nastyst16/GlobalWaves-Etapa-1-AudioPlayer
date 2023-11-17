package main;

public class SelectCommand implements Command{
    private String command;
    private String user;
    private int timestamp;
    private int itemNumber;
    private String message;


    public SelectCommand(String command, String user, int timestamp, int itemNumber) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.itemNumber = itemNumber;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void execute() {

    }
}
