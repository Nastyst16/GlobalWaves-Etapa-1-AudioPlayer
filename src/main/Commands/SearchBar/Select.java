package main.Commands.SearchBar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.Command;

public class Select implements Command {
    private String command;
    private String user;
    private int timestamp;
    private int itemNumber;
    private String selectedName;
    private String message;


    public Select(String command, String user, int timestamp, int itemNumber) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
        this.itemNumber = itemNumber;
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

    public int getItemNumber() {
        return itemNumber;
    }

    @JsonIgnore
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    @Override
    public void execute() {

    }
}
