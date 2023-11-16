package main;

import java.util.ArrayList;

public class SearchCommand {
    private String user;
    private int timestamp;
    private String message;
    private ArrayList<String> results;

    public SearchCommand(String user, int timestamp, String message, ArrayList<String> results) {
        this.user = user;
        this.timestamp = timestamp;
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


}
