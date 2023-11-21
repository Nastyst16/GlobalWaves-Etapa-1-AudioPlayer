package main.Commands.Player;

import main.Command;
import main.Type;
import main.User;

public class Next implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;


    public Next(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setNext(User currentUser) {

        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before skipping to the next track.";
            return;
        }

        if (this.timestamp == 4120) {
            int x =5;
        }



        Type currentType = currentUser.getCurrentType();

        currentType.setSecondsGone(currentType.getDuration());

        currentUser.treatingRepeatStatus(currentUser, currentType);



        if (currentUser.getCurrentType() != null)  // you have to implement if you are at the last song;
        this.message = "Skipped to next track successfully. The current track is " + currentUser.getCurrentType().getName() + ".";

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
