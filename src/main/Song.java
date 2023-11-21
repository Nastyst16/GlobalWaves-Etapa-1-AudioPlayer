package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Song implements Type{
    private String name;
    private int duration;
    private String album;
    private List<String> tags;
    private String lyrics;
    private String genre;
    private int releaseYear;
    private String artist;
    private int secondsGone;
    private int numberOfLikes;


    public Song() {}

    public Song(String name) {
        this.name = name;
    }

    public Song(String name, int duration, String album, List<String> tags, String lyrics, String genre, int releaseYear, String artist) {
        this.name = name;
        this.duration = duration;
        this.album = album;
        this.tags = tags;
        this.lyrics = lyrics;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.artist = artist;
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

    @JsonIgnore
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    @JsonIgnore
    public void setAlbum(String album) {
        this.album = album;
    }

    public List<String> getTags() {
        return tags;
    }

    @JsonIgnore
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getLyrics() {
        return lyrics;
    }

    @JsonIgnore
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getGenre() {
        return genre;
    }

    @JsonIgnore
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    @JsonIgnore
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    @JsonIgnore
    public void setArtist(String artist) {
        this.artist = artist;
    }


    @Override
    public int getSecondsGone() {
        return secondsGone;
    }

    public void setSecondsGone(int secondsGone) {
        this.secondsGone = secondsGone;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    @Override
    public void execute() {

    }

}
