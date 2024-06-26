package main.Commands.Player;

import main.Command;
import main.User;

public class Forward implements Command {
    private final String command;
    private final String user;
    private final int timestamp;
    private String message;
    private static final int SECONDS_TO_FORWARD = 90;


    /**
     * Constructor
     * @param command the command
     * @param user the user
     * @param timestamp the timestamp
     */
    public Forward(final String command, final String user, final int timestamp) {
        this.command = command;
        this.user = user;
        this.timestamp = timestamp;
    }

    /**
     * Skips forward 90 seconds
     * @param currentUser the current user
     */
    public void setForward(final User currentUser) {
        if (currentUser.getCurrentType() == null) {
            this.message = "Please load a source before attempting to forward.";
            return;
        }
        if (currentUser.getTypeLoaded() != 1) {
            this.message = "The loaded source is not a podcast.";
            return;
        }

        currentUser.getCurrentType().setSecondsGone(currentUser.
                getCurrentType().getSecondsGone() + SECONDS_TO_FORWARD);
        this.message = "Skipped forward successfully.";
    }

    /**
     * Getter for the message
     * @return the message
     */
    public String getCommand() {
        return command;
    }

    /**
     * Getter for the user
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Getter for the timestamp
     * @return the timestamp
     */
    public int getTimestamp() {
        return timestamp;
    }

    /**
     * Getter for the message
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter for the message
     * @param message the message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Executes the command
     */
    @Override
    public void execute() {

    }
}
