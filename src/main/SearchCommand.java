package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchCommand implements Command{
    private String command;
    private String user;
    private int timestamp;
    private String type;
    private Map<String, Object> filters;
    private String message;
    private ArrayList<String> results;


    public SearchCommand(String command, String user, int timestamp,
                         String type, Map<String, Object> filters) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.type = type;
        this.filters = filters;

//        initialize the results array
        this.results = new ArrayList<>();
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResults(ArrayList<String> results) {
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public void searchingBySongType(ArrayList<Song> songs) {
        String songPrefix = (String)(filters.get("name"));
        String album = (String)(filters.get("album"));
        String lyrics = (String)(filters.get("lyrics"));
        String genre = (String)(filters.get("genre"));
        List<String> tags = (List<String>)(filters.get("tags"));
        int releaseYear = 0;
        if (filters.get("releaseYear") instanceof Integer) {
            releaseYear = (int)filters.get("releaseYear");
        }

        String artist = (String)(filters.get("artist"));

        ArrayList<String> results = new ArrayList<>();


        for (Song song : songs) {
            if (songPrefix != null && song.getName().startsWith(songPrefix)) {
                results.add(song.getName());
            } else if (album != null && song.getAlbum().equals(album)) {
                results.add(song.getName());
            } else if (lyrics != null && song.getLyrics().contains(lyrics)) {
                results.add(song.getName());
            } else if (genre != null && song.getGenre().equalsIgnoreCase(genre)) {
                results.add(song.getName());
            } else if (tags != null && song.getTags().containsAll(tags)) {
                results.add(song.getName());
            } else if (releaseYear != 0 && song.getReleaseYear() == releaseYear) {
                results.add(song.getName());
            } else if (artist != null && song.getArtist().equals(artist)) {
                results.add(song.getName());
            }

//            maximum size of 5
            if (results.size() == 5)
                break;
        }

        this.setResults(results);
        this.setMessage("Search returned " + results.size() + " results");
    }

//    public void searchingByAlbumName(ArrayList<S>)



    @Override
    public void execute() {

    }
}
