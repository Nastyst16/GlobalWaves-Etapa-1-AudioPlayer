package main.Commands.Types;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class Playlist {

    private final String name;
    private final String user;
    private final ArrayList<Song> songList;
    private final ArrayList<String> songs;
    private String visibility;
    private int followers;


    /**
     * Playlist constructor
     * @param playlist contains every field of a playlist
     *                 so we can copy them
     */
    public Playlist(final Playlist playlist) {
        this.name = playlist.getName();
        this.user = playlist.getUser();
        this.songList = playlist.getSongList();
        this.songs = playlist.getSongs();
        this.visibility = playlist.getVisibility();
        this.followers = playlist.getFollowers();
    }
    public Playlist(final String name, final String user) {
        this.name = name;
        this.user = user;
        songList = new ArrayList<>();
        songs = new ArrayList<>();
        visibility = "public";
        followers = 0;
    }

    /**
     * Adds or removes a song from the playlist
     * @param currentSong the song to be added or removed
     * @return a message to be displayed
     */
    public String addRemoveSong(final Song currentSong) {

        String message;
        if (songList.contains(currentSong)) {
            songList.remove(currentSong);
            songs.remove(currentSong.getName());
            message = "Successfully removed from playlist.";
        } else {
            songList.add(currentSong);
            songs.add(currentSong.getName());
            message = "Successfully added to playlist.";
        }
        return message;
    }

    /**
     * Gets the name of the playlist
     * @return the name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the user
     * @return the user
     */
    @JsonIgnore
    public String getUser() {
        return user;
    }

    /**
     * Gets the songs in the playlist
     * @return the songs in the playlist
     */
    @JsonIgnore
    public ArrayList<Song> getSongList() {
        return songList;
    }

    /**
     * Gets the songs in the playlist
     * @return the songs in the playlist
     */
    public ArrayList<String> getSongs() {
        return songs;
    }

    /**
     * Gets the visibility of the playlist
     * @return the visibility of the playlist
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility of the playlist
     * @param visibility the visibility of the playlist
     */
    public void setVisibility(final String visibility) {
        this.visibility = visibility;
    }

    /**
     * Gets the number of followers
     * @return the number of followers
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Sets the number of followers
     * @param followers the number of followers
     */
    public void setFollowers(final int followers) {
        this.followers = followers;
    }

    /**
     * Increments the number of followers
     */
    public void incrementFollowers() {
        this.followers++;
    }

    /**
     * Decrements the number of followers
     */
    public void decrementFollowers() {
        this.followers--;
    }
}
