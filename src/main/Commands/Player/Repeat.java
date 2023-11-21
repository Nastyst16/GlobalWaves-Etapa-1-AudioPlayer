package main.Commands.Player;

import main.Command;
import main.Type;
import main.User;

public class Repeat implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;

    public Repeat(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }



    public int setRepeatMessage(User currentUser, int repeatStatus, int typeLoaded) {
        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before setting the repeat status.";
            return -1;
        }

        if (typeLoaded == 0 || typeLoaded == 1) {
            if (repeatStatus == 0) {
                this.message = "Repeat mode changed to repeat once.";
                repeatStatus = 1;
                currentUser.setRepeatString("Repeat Once");
            } else if (repeatStatus == 1) {
                this.message = "Repeat mode changed to repeat infinite.";
                repeatStatus = 2;
                currentUser.setRepeatString("Repeat Infinite");
            } else if (repeatStatus == 2) {
                this.message = "Repeat mode changed to no repeat.";
                repeatStatus = 0;
                currentUser.setRepeatString("No Repeat");
            }
        } else {
            if (repeatStatus == 0) {
                this.message = "Repeat mode changed to repeat all.";
                repeatStatus = 1;
                currentUser.setRepeatString("Repeat All");
            } else if (repeatStatus == 1) {
                this.message = "Repeat mode changed to repeat current song.";
                repeatStatus = 2;
                currentUser.setRepeatString("Repeat Current Song");
            } else if (repeatStatus == 2) {
                this.message = "Repeat mode changed to no repeat.";
                repeatStatus = 0;
                currentUser.setRepeatString("No Repeat");
            }
        }

        return repeatStatus;
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
