package main.Commands.Player;

import main.Command;
import main.Playlist;
import main.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowPlaylists implements Command {
    private String command;
    private String user;
    private int timestamp;
    private ArrayList<Playlist> result;

    public ShowPlaylists(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.result = new ArrayList<>();

    }

    public void copyPlaylists(User currentUser, ArrayList<Playlist> copyList) {

        for (int i = 0; i < currentUser.getPlayListList().size(); i++) {
            copyList.add(new Playlist(currentUser.getPlayListList().get(i)));
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

    public ArrayList<Playlist> getResult() {
        return result;
    }

    public void setResult(ArrayList<Playlist> result) {
        this.result = result;
    }

    @Override
    public void execute() {

    }
}
