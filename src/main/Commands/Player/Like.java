package main.Commands.Player;

import main.Command;

public class Like implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;


    public Like(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setMessageIfLiked(boolean like) {
        if (like)
            setMessage("Like registered successfully.");
        else
            setMessage("Unlike registered successfully.");
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
