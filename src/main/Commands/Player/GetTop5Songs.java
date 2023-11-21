package main.Commands.Player;

import main.Command;
import main.Playlist;
import main.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GetTop5Songs implements Command {
    private String command;
    private int timestamp;
    private ArrayList<String> result;

    public GetTop5Songs(String command, int timestamp) {
        this.command = command;
        this.timestamp = timestamp;
        result = new ArrayList<>();
    }

    public void searchTop5Songs(ArrayList<Song> everySong) {

        ArrayList<Song> sortedSong = new ArrayList<>(everySong);

        Collections.sort(sortedSong, Comparator.comparingInt(Song::getNumberOfLikes).reversed());

        int i = 0;
        while (i < 5 && i < sortedSong.size()) {
            result.add(sortedSong.get(i).getName());
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
