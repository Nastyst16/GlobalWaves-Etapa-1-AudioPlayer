package main;

import java.util.ArrayList;

public class SearchBar {
    private String command;
    private String user;
    private int timestamp;
    private String message;
    private ArrayList<String> results;

    public SearchBar(String user, int timestamp, String message, ArrayList<String> results) {
        this.user = user;
        this.timestamp = timestamp;
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


}
