package main.Commands.Player;

import main.Command;
import main.Song;
import main.User;

import java.util.ArrayList;

public class ShowPreferredSongs implements Command {
    private String command;
    private String user;
    private int timestamp;

    private ArrayList<String> result;

    public ShowPreferredSongs(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        result = new ArrayList<>();
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

    public ArrayList<String> getResult() {
        return result;
    }

    public void setResult(User user) {
        if (!user.getLikedSongs().isEmpty())
            for (Song song : user.getLikedSongs()) {
                this.result.add(song.getName());
            }
    }

    @Override
    public void execute() {

    }
}
