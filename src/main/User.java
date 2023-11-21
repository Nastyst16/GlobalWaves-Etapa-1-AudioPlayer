package main;

import com.fasterxml.jackson.annotation.JsonIgnore;

import main.Commands.SearchBar.Search;
import main.Commands.SearchBar.Select;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private String username;
    private int age;
    private String city;
    private int loadMade;
    private boolean paused;

    private int timestampAtLoading;
    private int timestampAtPlaying;
    private int timestampAtPausing;
    private int secondsGone;
    private int repeatStatus;
    private int typeFoundBySearch;
    private int typeSelected;
    private int typeLoaded;
    private boolean shuffle;
    private int duration; /// i think you should remove this with all the getter and setter
    private int remainingTime;
    private String repeatString;
    private int timestampAtStatus;


    private Search currentSearch;
    private ArrayList<Song> everySong;
    private ArrayList<Podcast> everyPodcast;


    private Type currentType;
    private int prevTimestamp;
    private Podcast currentPodcast;
    private Playlist currentPlaylist;
    private ArrayList<Integer> shuffledIndices;
//    private ArrayList<Song> currentPlaylistSongsShuffled;
    private String selectedName;
    private int shuffleSeed;


//    private Song currentSong;
//    private Podcast currentPodcast;
    private Select currentSelect;
    private ArrayList<Playlist> playListList;
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> followedPlaylists;
    private ArrayList<String> likedPlaylists;
    private ArrayList<Podcast> podcastsPlayed;
    private Playlist selectedPlaylist;


    public User(String username, int age, String city, ArrayList<Song> eveySong, ArrayList<Podcast> everyPodcast) {
        this.username = username;
        this.age = age;
        this.city = city;
        likedSongs = new ArrayList<>();
        likedPlaylists = new ArrayList<>();
        podcastsPlayed = new ArrayList<>();
        followedPlaylists = new ArrayList<>();

        loadMade = 0;
        paused = false;
        timestampAtLoading = 0;
        timestampAtPlaying = 0;
        timestampAtPausing = 0;
        secondsGone = 0;
        repeatStatus = -1;
        typeFoundBySearch = -1;
        typeSelected = -1;
        typeLoaded = -1;
        shuffle = false;
//        currentSong = null;
        currentType = null;

        currentSelect = null;
        currentSearch = null;
        playListList = new ArrayList<>();

        this.shuffledIndices = new ArrayList<>();
//        this.currentPlaylistSongsShuffled = new ArrayList<>();


//        copy the songs and the podcasts

        this.everySong = new ArrayList<>();
        for (Song song : eveySong) {
            Song copySong = new Song();
            copySong.setName(song.getName());
            copySong.setDuration(song.getDuration());
            copySong.setAlbum(song.getAlbum());
            copySong.setTags(song.getTags());
            copySong.setLyrics(song.getLyrics());
            copySong.setGenre(song.getGenre());
            copySong.setReleaseYear(song.getReleaseYear());
            copySong.setArtist(song.getArtist());
            copySong.setSecondsGone(song.getSecondsGone());
            copySong.setNumberOfLikes(song.getNumberOfLikes());

            this.everySong.add(copySong);
        }

        this.everyPodcast = new ArrayList<>();
        for (Podcast podcast : everyPodcast) {
            Podcast copyPodcast = new Podcast();

            copyPodcast.setName();
        }

        this.followedPlaylists = new ArrayList<>();

    }

    public void addPlaylistToList(Playlist playList) {
        playListList.add(playList);
    }

    public boolean setLikedSongs(Song song) {
        if (this.likedSongs.contains(song)) {
            this.likedSongs.remove(song);
            song.setNumberOfLikes(song.getNumberOfLikes() - 1);
            return false;
        }
        else {
            this.likedSongs.add(song);
            song.setNumberOfLikes(song.getNumberOfLikes() + 1);
            return true;
        }
    }

    public boolean setLikedPlaylist() {
        if (this.likedPlaylists.contains(this.selectedName)) {
            likedPlaylists.remove(this.selectedName);
            return false;
        } else {
            likedPlaylists.add(this.selectedName);
            return true;
        }
    }

    public void treatingRepeatStatus(User currentUser, Type currentType) {


        currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone());

        if (currentUser.getTypeLoaded() == 0 || currentUser.getTypeLoaded() == 1) {

            if (currentUser.getRepeatStatus() == 1 && currentUser.getRemainingTime() < 0) {
                currentUser.setRepeatStatus(0);
                currentUser.setRepeatString("No Repeat");
                currentType.setSecondsGone(currentType.getSecondsGone() - currentType.getDuration());
                currentUser.setRemainingTime((currentType.getDuration()) - currentType.getSecondsGone());
            } else if (currentUser.getRepeatStatus() == 2) {
                while (currentUser.getRemainingTime() < 0) {

                    currentType.setSecondsGone(currentType.getSecondsGone() - currentType.getDuration());
                    currentUser.setRemainingTime((currentType.getDuration()) - currentType.getSecondsGone());

                }
            }

        } else if (currentUser.getTypeLoaded() == 2) {

            if (currentUser.getRepeatStatus() == 1 && currentUser.getRemainingTime() < 0) {

//                if the last song is playing
                if (currentUser.getCurrentPlaylist().getSongList().getLast().getName().equals(currentUser.getSelectedName())) {
                    currentType = currentUser.getCurrentPlaylist().getSongList().getFirst();

                    currentType.setSecondsGone(currentType.getDuration() + currentUser.getRemainingTime());
                }
            }

        }




        if (currentUser.getTypeLoaded() == 1) {

//            comutam in episodul urmator pana cand este nevoie
            while (currentUser.getRemainingTime() <= 0) {
                Podcast podcast = currentUser.getCurrentPodcast();

                currentUser.getCurrentPodcast().setLastRemainingEpisode(currentUser.getCurrentPodcast().getLastRemainingEpisode() + 1);
                int indexEpisode = currentUser.getCurrentPodcast().getLastRemainingEpisode();

                Episode newEpisode = currentUser.getCurrentPodcast().getEpisodes().get(indexEpisode);

                currentType = newEpisode;
                currentType.setSecondsGone(Math.abs(currentUser.getRemainingTime()));

                currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone());

                int debug = 5;
            }

        }


        if (currentUser.getTypeLoaded() == 2) {

//            commuting the next song in playlist
            while (currentUser.getRemainingTime() <= 0) {
                Playlist playlist = currentUser.getCurrentPlaylist();

                int indexSong = playlist.getSongList().indexOf((Song) (currentType));





                Song newSong = null;
                if (currentUser.isShuffle()) {

                    int nextShuffledIndex = currentUser.getShuffledIndices().indexOf(indexSong) + 1;
//
                    if (nextShuffledIndex == currentUser.getShuffledIndices().size()) {
//                        nextShuffledIndex = currentUser.getShuffledIndices().getFirst();

//                        end of playlist;
                        currentUser.setCurrentType(null);
                        currentUser.setTypeLoaded(-1);
                        currentUser.setShuffle(false);
                        return;
                    }
//
                    nextShuffledIndex = currentUser.getShuffledIndices().get(nextShuffledIndex);
//
////
//
//
                    newSong = currentUser.getCurrentPlaylist().getSongList().get(nextShuffledIndex);
                    currentType = newSong;

//                if repeat current song we wont change the currentType
                } else if (currentUser.getRepeatStatus() != 2) {
                    if (playlist.getSongList().size() - 1 > indexSong) {

                        currentType.setSecondsGone(0);

                        newSong = playlist.getSongList().get(indexSong + 1);
                        currentType = newSong;
                    }
                    else {
                        break;
                    }
                }

//




                currentType.setSecondsGone(Math.abs(currentUser.getRemainingTime()));

                currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone());

            }
            if (currentUser.getRepeatStatus() == 1 && currentUser.getCurrentPlaylist().getSongList().getLast().getName().equals(currentType.getName())) {

                int secsGone = currentType.getSecondsGone() - currentType.getDuration();

                currentType = currentUser.getCurrentPlaylist().getSongList().getFirst();
                currentType.setSecondsGone(secsGone);
                currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone());


                int r = 5;
            }

        }




        this.currentType = currentType;
        currentUser.setCurrentType(currentType);
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




    public int getLoadMade() {
        return loadMade;
    }

    @JsonIgnore
    public void setLoadMade(int loadMade) {
        this.loadMade = loadMade;
    }

    public boolean isPaused() {
        return paused;
    }

    @JsonIgnore
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getTimestampAtLoading() {
        return timestampAtLoading;
    }

    @JsonIgnore
    public void setTimestampAtLoading(int timestampAtLoading) {
        this.timestampAtLoading = timestampAtLoading;
    }

    public int getTimestampAtPlaying() {
        return timestampAtPlaying;
    }

    @JsonIgnore
    public void setTimestampAtPlaying(int timestampAtPlaying) {
        this.timestampAtPlaying = timestampAtPlaying;
    }

    public int getTimestampAtPausing() {
        return timestampAtPausing;
    }

    @JsonIgnore
    public void setTimestampAtPausing(int timestampAtPausing) {
        this.timestampAtPausing = timestampAtPausing;
    }

    public int getSecondsGone() {
        return secondsGone;
    }

    @JsonIgnore
    public void setSecondsGone(int secondsGone) {
        this.secondsGone = secondsGone;
    }

    public int getRepeatStatus() {
        return repeatStatus;
    }

    @JsonIgnore
    public void setRepeatStatus(int repeatStatus) {
        this.repeatStatus = repeatStatus;
    }

    public int getTypeFoundBySearch() {
        return typeFoundBySearch;
    }

    @JsonIgnore
    public void setTypeFoundBySearch(int typeFoundBySearch) {
        this.typeFoundBySearch = typeFoundBySearch;
    }

    public int getTypeSelected() {
        return typeSelected;
    }

    @JsonIgnore
    public void setTypeSelected(int typeSelected) {
        this.typeSelected = typeSelected;
    }

    public int getTypeLoaded() {
        return typeLoaded;
    }

    @JsonIgnore
    public void setTypeLoaded(int typeLoaded) {
        this.typeLoaded = typeLoaded;
    }

//    public Song getCurrentSong() {
//        return currentSong;
//    }
//
//    @JsonIgnore
//    public void setCurrentSong(Song currentSong) {
//        this.currentSong = currentSong;
//    }

    public Select getCurrentSelect() {
        return currentSelect;
    }

    @JsonIgnore
    public void setCurrentSelect(Select currentSelect) {
        this.currentSelect = currentSelect;
    }

    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    @JsonIgnore
    public void setLikedSongs(ArrayList<Song> likedSongs) {
        this.likedSongs = likedSongs;
    }

    public boolean isShuffle() {
        return shuffle;
    }

    @JsonIgnore
    public void setShuffle(boolean shuffle) {
        this.shuffle = shuffle;
    }

    public Search getCurrentSearch() {
        return currentSearch;
    }

    @JsonIgnore
    public void setCurrentSearch(Search currentSearch) {
        this.currentSearch = currentSearch;
    }

    public ArrayList<Playlist> getPlayListList() {
        return playListList;
    }

    @JsonIgnore
    public void setPlayListList(ArrayList<Playlist> playListList) {
        this.playListList = playListList;
    }

//    public Podcast getCurrentPodcast() {
//        return currentPodcast;
//    }
//
//    public void setCurrentPodcast(Podcast currentPodcast) {
//        this.currentPodcast = currentPodcast;
//    }

    public ArrayList<String> getLikedPlaylists() {
        return likedPlaylists;
    }

    public void setLikedPlaylists(ArrayList<String> likedPlaylists) {
        this.likedPlaylists = likedPlaylists;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Type getCurrentType() {
        return currentType;
    }

    public void setCurrentType(Type currentType) {
        this.currentType = currentType;
    }

    public String getRepeatString() {
        return repeatString;
    }

    public void addPodcastPlayed(Podcast podcast) {
        this.podcastsPlayed.add(podcast);
    }

    public ArrayList<Podcast> getPodcastsPlayed() {
        return podcastsPlayed;
    }

    public void setPodcastsPlayed(ArrayList<Podcast> podcastsPlayed) {
        this.podcastsPlayed = podcastsPlayed;
    }

    public void setRepeatString(String repeatString) {
        this.repeatString = repeatString;
    }

    public int getTimestampAtStatus() {
        return timestampAtStatus;
    }

    public void setTimestampAtStatus(int timestampAtStatus) {
        this.timestampAtStatus = timestampAtStatus;
    }

    public int getPrevTimestamp() {
        return prevTimestamp;
    }

    public void setPrevTimestamp(int prevTimestamp) {
        this.prevTimestamp = prevTimestamp;
    }

    public Podcast getCurrentPodcast() {
        return currentPodcast;
    }

    public void setCurrentPodcast(Podcast currentPodcast) {
        this.currentPodcast = currentPodcast;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    public int getShuffleSeed() {
        return shuffleSeed;
    }

    public void setShuffleSeed(int shuffleSeed) {
        this.shuffleSeed = shuffleSeed;
    }

//    public ArrayList<Song> getCurrentPlaylistSongsShuffled() {
//        return currentPlaylistSongsShuffled;
//    }
//
//    public void setCurrentPlaylistSongsShuffled(ArrayList<Song> currentPlaylistSongsShuffled) {
//        this.currentPlaylistSongsShuffled = currentPlaylistSongsShuffled;
//    }


    public ArrayList<Integer> getShuffledIndices() {
        return shuffledIndices;
    }

    public void setShuffledIndices(ArrayList<Integer> shuffledIndices) {
        this.shuffledIndices = shuffledIndices;
    }

    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Playlist selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public ArrayList<Playlist> getFollowedPlaylists() {
        return followedPlaylists;
    }

    public void setFollowedPlaylists(ArrayList<Playlist> followedPlaylists) {
        this.followedPlaylists = followedPlaylists;
    }

    public ArrayList<Song> getEverySong() {
        return everySong;
    }

    public void setEverySong(ArrayList<Song> everySong) {
        this.everySong = everySong;
    }

    public ArrayList<Podcast> getEveryPodcast() {
        return everyPodcast;
    }

    public void setEveryPodcast(ArrayList<Podcast> everyPodcast) {
        this.everyPodcast = everyPodcast;
    }
}
