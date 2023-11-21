package main;

public interface Type {

    public int getSecondsGone();
    public void setSecondsGone(int secondsGone);

    public void execute();

    int getDuration();

    Object getName();
}
