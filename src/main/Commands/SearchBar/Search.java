package main.Commands.SearchBar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;
import main.Playlist;
import main.Podcast;
import main.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Search implements Command {
    private String command;
    private String user;
    private int timestamp;
    private String type;
    private Map<String, Object> filters;
    private String message;
    private ArrayList<String> results;


    public Search(String command, String user, int timestamp,
                  String type, Map<String, Object> filters) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.type = type;
        this.filters = filters;
        this.setMessage("Please conduct a search before making a selection");

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

    @JsonIgnore
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
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
//        converting to lowerCase
        if (lyrics != null)
            lyrics = lyrics.toLowerCase();

        String genre = (String)(filters.get("genre"));
        List<String> tags = (List<String>)(filters.get("tags"));
        String releaseYear = (String)(filters.get("releaseYear"));
        String operator = null;
        int targetYear = 0;

        if (releaseYear != null) {
//              operator can be <, >, or =
            operator = releaseYear.substring(0, 1);
//              target year is the year that the user wants to compare to
            targetYear = Integer.parseInt(releaseYear.substring(1));
        }


        String artist = (String)(filters.get("artist"));

        ArrayList<String> results = new ArrayList<>();

        int a = 5;


        for (Song song : songs) {
            String songLyrics = song.getLyrics().toLowerCase();
//            if the song matches the filters, add it to the results
            if ((songPrefix == null || song.getName().startsWith(songPrefix)) &&
                    (album == null || song.getAlbum().equals(album)) &&
                    (lyrics == null || songLyrics.contains(lyrics)) &&
                    (genre == null || song.getGenre().equalsIgnoreCase(genre)) &&
                    (tags == null || song.getTags().containsAll(tags)) &&
                    (artist == null || song.getArtist().equals(artist)) &&
                    (releaseYear == null || (operator.equals("<") && song.getReleaseYear() < targetYear) ||
                    (operator.equals(">") && song.getReleaseYear() > targetYear) ||
                    (operator.equals("=") && song.getReleaseYear() == targetYear)))
                results.add(song.getName());

//            maximum size of 5
            if (results.size() == 5)
                break;
        }

        this.setResults(results);
        this.setMessage("Search returned " + results.size() + " results");
    }

    public void searchingByPodcastType(ArrayList<Podcast> podcasts) {
        String podcastPrefix = (String)(filters.get("name"));
        String owner = (String)(filters.get("owner"));


        for (Podcast podcast : podcasts) {
            if ((podcastPrefix == null || podcast.getName().startsWith(podcastPrefix)) &&
                (owner == null || podcast.getOwner().equals(owner)))
                results.add(podcast.getName());

            if (results.size() == 5)
                break;
        }


        this.setResults(results);
        this.setMessage("Search returned " + results.size() + " results");
    }

    public void searchingByPlaylistType(ArrayList<Playlist> everyPlaylist) {
        String owner = (String) (filters.get("owner"));
        String name = (String) (filters.get("name"));

        for (Playlist playlist : everyPlaylist) {
            if (playlist.getVisibility().equals("private") && !playlist.getUser().equals(user))
                continue;
            if ((owner == null || playlist.getUser().equals(owner)) &&
                (name == null || playlist.getName().equals(name))) {
                results.add(playlist.getName());
            }

            if (results.size() == 5)
                break;
        }

        this.setResults(results);
        this.setMessage("Search returned " + results.size() + " results");
    }


    @Override
    public void execute() {

    }
}
