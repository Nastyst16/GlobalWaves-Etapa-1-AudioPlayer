package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private String user;
    private ArrayList<Song> songList;
    private ArrayList<String> songs;
    private String visibility;
    private int followers;



    public Playlist(Playlist playlist) {
        this.name = playlist.getName();
        this.user = playlist.getUser();
        this.songList = playlist.getSongList();
        this.songs = playlist.getSongs();
        this.visibility = playlist.getVisibility();
        this.followers = playlist.getFollowers();
    }
    public Playlist(String name, String user) {
        this.name = name;
        this.user = user;
        songList = new ArrayList<>();
        songs = new ArrayList<>();
        visibility = "public";
        followers = 0;
    }

    public String addRemoveSong(Song currentSong) {

        String message;
        if (songList.contains(currentSong)) {
            songList.remove(currentSong);
            songs.remove(currentSong.getName());
            message = "Successfully removed from playlist.";
        }
        else {
            songList.add(currentSong);
            songs.add(currentSong.getName());
            message = "Successfully added to playlist.";
        }
        return message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Song> getSongList() {
        return songList;
    }

    @JsonIgnore
    public void setSongList(ArrayList<Song> songList) {
        this.songList = songList;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void incrementFollowers() {
        this.followers++;
    }

    public void decrementFollowers() {
        this.followers--;
    }
}
