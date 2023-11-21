package main.Commands.Player;

import main.Command;

public class Load implements Command {
    String command;
    String user;
    int timestamp;
    String message;

    public Load(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.message = "You can't load an empty audio collection!";
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
