package main.Commands.Player;

import main.Command;
import main.Type;
import main.User;

public class Prev implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String message;


    public Prev(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }


    public void setPrev(User currentUser, Type currentType) {

        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before returning to the previous track.";
            return;
        }

//        if it's a song
        if (currentUser.getTypeLoaded() == 0) {
            currentType.setSecondsGone(0);
        } else if (currentUser.getTypeLoaded() == 2) {
//            prev for playlists

            if (currentType.getSecondsGone() > 0) {
                currentType.setSecondsGone(0);
            } else if (currentType.getSecondsGone() == 0) {
                if (currentUser.isShuffle()) { // aici e buba

//                    int index = currentUser.getShuffledIndices().getFirst();
//                    if (currentUser.getCurrentPlaylist().getSongList().get)


                    int prevIndex = currentUser.getCurrentPlaylist().getSongList().indexOf(currentType);
                    prevIndex = currentUser.getShuffledIndices().indexOf(prevIndex) - 1;

                    if (prevIndex == -1) {
                        currentType.setSecondsGone(0);
                    } else {
                        prevIndex = currentUser.getShuffledIndices().get(prevIndex);
                        currentType = currentUser.getCurrentPlaylist().getSongList().get(prevIndex);
                        currentType.setSecondsGone(0);
                    }


                } else {
//                    if it's the first song of the playlist
                    if (currentUser.getCurrentPlaylist().getSongList().indexOf(currentType) == 0) {
                        currentType.setSecondsGone(0);
                    } else {
                        int prevIndex = currentUser.getCurrentPlaylist().getSongList().indexOf(currentType) - 1;
                        currentType = currentUser.getCurrentPlaylist().getSongList().get(prevIndex);
                        currentType.setSecondsGone(0);
                    }
                }
            }
        } else if (currentUser.getTypeLoaded() == 1) {
            if (currentUser.getCurrentPodcast().getLastRemainingEpisode() == 0) {
                currentType.setSecondsGone(0);
            } else {
                int prevIndex = currentUser.getCurrentPodcast().getLastRemainingEpisode() - 1;
                currentType = currentUser.getCurrentPodcast().getEpisodes().get(prevIndex);
            }
        }

        currentUser.setCurrentType(currentType);
        currentUser.setPaused(false);

        if (currentUser.getCurrentType() != null) {
            this.message = "Returned to previous track successfully. The current track is " + currentType.getName() + ".";
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

    @Override
    public void execute() {

    }
}
