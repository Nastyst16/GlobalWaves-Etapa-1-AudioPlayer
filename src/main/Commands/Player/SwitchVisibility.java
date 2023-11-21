package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;
import main.Playlist;
import main.User;

import java.util.ArrayList;

public class SwitchVisibility implements Command {
    private String command;
    private String user;
    private int timestamp;
    private int id;
    private String message;




    public SwitchVisibility(String command, String user, int timestamp, int id) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.id = id;
    }

    public void setVisibility(User currentUser) {

        if (this.id > currentUser.getPlayListList().size()) {
            this.message = "The specified playlist ID is too high.";
            return;
        }

        if (currentUser.getPlayListList().get(this.id - 1).getVisibility().equals("public")) {
            currentUser.getPlayListList().get(this.id - 1).setVisibility("private");
            this.message = "Visibility status updated successfully to private.";
        } else {
            currentUser.getPlayListList().get(this.id - 1).setVisibility("public");
            this.message = "Visibility status updated successfully to public.";
        }

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

    public int getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void execute() {

    }
}
