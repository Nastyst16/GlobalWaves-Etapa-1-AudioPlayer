package main.Commands.Player;

import main.Command;
import main.Playlist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetTop5Playlists implements Command {
    private String command;
    private int timestamp;
    private ArrayList<String> result;

    public GetTop5Playlists(String command, int timestamp) {
        this.command = command;
        this.timestamp = timestamp;
        result = new ArrayList<>();
    }

    public void searchTop5Playlists(ArrayList<Playlist> everyPlaylists) {

        ArrayList<Playlist> sortedPlaylists = new ArrayList<>(everyPlaylists);

        Collections.sort(sortedPlaylists, Comparator.comparingInt(Playlist::getFollowers).reversed());

        int i = 0;
        while (i < 5 && i < sortedPlaylists.size()) {
            result.add(sortedPlaylists.get(i).getName());
            i++;
        }
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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

    public void setResult(ArrayList<String> result) {
        this.result = result;
    }

    public void execute() {

    }
}
