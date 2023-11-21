package main;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Podcast implements Type{
    private String name;
    private String owner;
    private List<Episode> episodes;
    private int duration;
    private int lastRemainingEpisode;
//    private int currentSecond;
    private int secondsGone;


    public Podcast() {

    }

    public Podcast(String name, String owner, List<Episode> episodes) {
        this.name = name;
        this.owner = owner;
        this.episodes = episodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

//    setting episode one by one;
    public void setEpisodeOneByOne(Episode episode) {
        this.episodes.add(episode);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

//    public Episode getCurrentEpisode() {
//        return currentEpisode;
//    }
//
//    public void setCurrentEpisode(Episode currentEpisode) {
//        this.currentEpisode = currentEpisode;
//    }

//    public int getCurrentSecond() {
//        return currentSecond;
//    }
//
//    public void setCurrentSecond(int currentSecond) {
//        this.currentSecond = currentSecond;
//    }

    public void addEpisode(Episode episode) {
        this.episodes.add(episode);
    }

    public int getLastRemainingEpisode() {
        return lastRemainingEpisode;
    }

    public void setLastRemainingEpisode(int lastRemainingEpisode) {
        this.lastRemainingEpisode = lastRemainingEpisode;
    }

    public int getSecondsGone() {
        return this.secondsGone;
    }

    public void setSecondsGone(int secondsGone) {
        this.secondsGone = secondsGone;
    }


    @Override
    public void execute() {

    }
}
