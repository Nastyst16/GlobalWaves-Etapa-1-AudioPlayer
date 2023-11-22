package main.Commands.Player;

import main.Command;
import main.User;

public class Backward implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;




    public Backward(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setBackward(User currentUser) {

        if (currentUser.getTypeLoaded() != 1) {
            this.message = "The loaded source is not a podcast.";
            return;
        }

        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before returning to the previous track.";
            return;
        }




        currentUser.getCurrentType().setSecondsGone(currentUser.getCurrentType().getSecondsGone() - 90);
        this.message = "Rewound successfully.";

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
