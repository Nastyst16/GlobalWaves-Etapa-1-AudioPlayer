package main;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class Podcast {
    private String name;
    private String owner;
    private List<Episode> episodes;


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
}
