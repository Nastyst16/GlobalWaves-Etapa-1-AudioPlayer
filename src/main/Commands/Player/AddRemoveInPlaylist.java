package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;
import main.Song;
import main.User;

public class AddRemoveInPlaylist implements Command {
    private String command;
    private String user;
    private int timestamp;
    private int playlistId;
    private String message;


    public AddRemoveInPlaylist(String command, String user, int timestamp, int playlistId) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.playlistId = playlistId;
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

    public int getPlaylistId() {
        return playlistId;
    }

    @JsonIgnore
    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(User currentUser, int index) {
        if (currentUser.getTypeLoaded() == 2 ||
            currentUser.getTypeLoaded() == 1) {
            this.message = "The loaded source is not a song.";
            return;
        }

        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before adding to or removing from the playlist.";
        } else {
            if (index > currentUser.getPlayListList().size()) {
                this.message = "The specified playlist does not exist.";
            } else {
                if (currentUser.getTypeSelected() == 2 ||
                    currentUser.getTypeSelected() == 1)
                    this.message = "The loaded source is not a song.";
                else
                    this.message = currentUser.getPlayListList().get(index - 1).addRemoveSong((Song)currentUser.getCurrentType());
                int x =0;
            }
        }
    }

    @Override
    public void execute() {

    }
}
