package main.Commands.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Status implements Command {
    private String command;
    private String user;
    private int timestamp;
    private Map<String, Object> stats;
    private int remainingTime;


    public Status(String command, String user, int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.stats = new LinkedHashMap<>();
    }



    public void settingStats(User currentUser, Type currentType) {
        if (currentType instanceof Song)
            currentType = (Song) currentType;
        else if (currentType instanceof Podcast) {
            currentType = (Podcast) currentType;
            int index = ((Podcast) currentType).getLastRemainingEpisode();

//            currentType is the Episode now;
            currentType = ((Podcast) currentType).getEpisodes().get(index);
        }





        currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone()); // foarte importanta linia asta





        if (currentUser.getRemainingTime() < 0 && currentUser.getRepeatStatus() == 0) {
            currentUser.setRemainingTime(0);
        }


        this.remainingTime = currentUser.getRemainingTime();


        currentUser.setCurrentType(currentType);

        if (currentUser.getRemainingTime() == 0) {
            currentUser.setCurrentType(new Song(""));
            currentUser.setPaused(true);
        }

//

        stats.put("name", currentUser.getCurrentType().getName());
        stats.put("remainedTime", currentUser.getRemainingTime());
        stats.put("repeat", currentUser.getRepeatString());
        stats.put("shuffle", currentUser.isShuffle());
        stats.put("paused", currentUser.isPaused());
    }



    public void settingNoType(User currentUser) {
        currentUser.setPaused(true);
        currentUser.setTypeLoaded(-1);
        currentUser.setTypeSelected(-1);


        stats.put("name", "");
        stats.put("remainedTime", 0);
        stats.put("repeat", currentUser.getRepeatString());
        stats.put("shuffle", currentUser.isShuffle());
        stats.put("paused", currentUser.isPaused());
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

    public Map<String, Object> getStats() {
        return stats;
    }

    public void setStats(Map<String, Object> stats) {
        this.stats = stats;
    }

    public int getRemainingTime() {
        return remainingTime;
    }
    @JsonIgnore
    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    @Override
    public void execute() {

    }
}
