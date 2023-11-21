package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;
import main.Playlist;
import main.Song;

import java.util.ArrayList;

public class CreatePlayList implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String playlistName;
    private String message;
    private Playlist playlist;


    public CreatePlayList(String command, String user, int timestamp, String playlistName, String message) {

        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.message = message;

        this.playlist = new Playlist(playlistName, user);
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

    public String getPlaylistName() {
        return playlistName;
    }

    @JsonIgnore
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    @JsonIgnore
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    //    public ArrayList<Song> getSongList() {
//        return songList;
//    }
//
//    @JsonIgnore
//    public void setSongList(ArrayList<Song> songList) {
//        this.songList = songList;
//    }

    @Override
    public void execute() {

    }
}
