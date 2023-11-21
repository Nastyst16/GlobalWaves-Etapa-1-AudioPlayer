package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;
import main.Type;
import main.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Shuffle implements Command {
    private String command;
    private String user;
    private int timestamp;
    private int seed;
    private String message;


    public Shuffle(String command, String user, int timestamp, int seed) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.seed = seed;
    }


    public void settingShuffle(User currentUser) {
        Type currentType = currentUser.getCurrentType();

        if (currentType != null && currentUser.getTypeLoaded() == 2) {

            if (currentUser.isShuffle() == false) {
                this.message = "Shuffle function activated successfully.";
                currentUser.setShuffle(true);

                ArrayList<Integer> originalIndices = new ArrayList<>();
                for (int i = 0; i < currentUser.getCurrentPlaylist().getSongList().size(); i++) {
                    originalIndices.add(i);
                }
                currentUser.getShuffledIndices().addAll(originalIndices);
                Random rand = new Random(this.seed);
                Collections.shuffle(currentUser.getShuffledIndices(), rand);

            } else {
                this.message = "Shuffle function deactivated successfully.";
                currentUser.setShuffle(false);

                while (!currentUser.getShuffledIndices().isEmpty()) {
                    currentUser.getShuffledIndices().removeFirst();
                }


            }
            currentUser.setShuffleSeed(this.seed);

        } else if (currentType != null && currentUser.getTypeLoaded() != 2) {
            this.message = "The loaded source is not a playlist.";
        } else if (currentType == null) {
            this.message = "Please load a source before using the shuffle function.";
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

    public int getSeed() {
        return seed;
    }

    @JsonIgnore
    public void setSeed(int seed) {
        this.seed = seed;
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
