package main.Commands.Player;

import main.Command;
import main.Type;
import main.User;

public class Next implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;


    public Next(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    public void setNext(User currentUser) {


        if (currentUser.getTypeLoaded() == 2 && currentUser.getRepeatStatus() == 0) {
            int index = currentUser.getCurrentPlaylist().getSongList().indexOf(currentUser.getCurrentType());
            if (currentUser.getCurrentPlaylist().getSongList().size() == index + 1 &&
                    currentUser.getCurrentPlaylist().getSongList().size() == 1) {

                currentUser.setCurrentType(null);
            }
        }

        if (currentUser.getCurrentType() == null || currentUser.getTypeLoaded() == -1) {
            this.message = "Please load a source before skipping to the next track.";
            return;
        }

        if (this.timestamp == 4120) {
            int x =5;
        }


        currentUser.setNext(true);

        Type currentType = currentUser.getCurrentType();

        currentType.setSecondsGone(currentType.getDuration());







        currentUser.treatingRepeatStatus(currentUser, currentType);
        currentUser.setPaused(false);

        currentUser.setNext(false);



        if (currentUser.getCurrentType() != null)  // you have to implement if you are at the last song;
            this.message = "Skipped to next track successfully. The current track is " + currentUser.getCurrentType().getName() + ".";
        else
            this.message = "Please load a source before skipping to the next track.";

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
