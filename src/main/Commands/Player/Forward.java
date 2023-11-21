package main.Commands.Player;

import main.Command;
import main.User;

public class Forward implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;




    public Forward(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setForward(User currentUser) {

        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before attempting to forward.";
            return;
        }

        if (currentUser.getTypeLoaded() != 1) {
            this.message = "The loaded source is not a podcast.";
            return;
        }

        currentUser.getCurrentType().setSecondsGone(currentUser.getCurrentType().getSecondsGone() + 90);
        this.message = "Skipped forward successfully.";
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
