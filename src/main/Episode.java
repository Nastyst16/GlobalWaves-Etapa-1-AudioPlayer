package main;

import fileio.input.EpisodeInput;

public class Episode implements Type{
    private String name;
    private int duration;
    private String description;
    private int secondsGone;


    public Episode(EpisodeInput episodeInput) {
        this.name = episodeInput.getName();
        this.duration = episodeInput.getDuration();
        this.description = episodeInput.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSecondsGone() {
        return secondsGone;
    }

    public void setSecondsGone(int secondsGone) {
        this.secondsGone = secondsGone;
    }

    @Override
    public void execute() {

    }
}
