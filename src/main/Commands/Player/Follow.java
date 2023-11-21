package main.Commands.Player;

import main.Command;
import main.Playlist;
import main.User;

import java.util.ArrayList;

public class Follow implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;




    public Follow(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setFollow(User currentUser, ArrayList<Playlist> everyPlaylist) {

        if (currentUser.getCurrentSelect() == null) {
            this.message = "Please select a source before following or unfollowing.";
            return;
        }

        if (currentUser.getTypeSelected() != 2) {
            this.message = "The selected source is not a playlist.";
            return;
        }

        currentUser.getFollowedPlaylists().remove(currentUser.getSelectedPlaylist());
        int indexPlaylist = everyPlaylist.indexOf(currentUser.getSelectedPlaylist());

        if (currentUser.getFollowedPlaylists().contains(currentUser.getSelectedPlaylist())) {

            everyPlaylist.get(indexPlaylist).decrementFollowers();

        } else {
            everyPlaylist.get(indexPlaylist).incrementFollowers();
        }

//        int index = currentUser.getFollowedPlaylists().indexOf()



        this.message = "Playlist followed successfully.";
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
