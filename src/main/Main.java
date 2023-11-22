package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.input.*;
import main.Commands.Player.*;
import main.Commands.SearchBar.Search;
import main.Commands.SearchBar.Select;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    static final String LIBRARY_PATH = CheckerConstants.TESTS_PATH + "library/library.json";

    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().startsWith("library")) {
                continue;
            }

            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePathInput for input file
     * @param filePathOutput for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePathInput,
                              final String filePathOutput) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryInput library = objectMapper.readValue(new File(LIBRARY_PATH), LibraryInput.class);

        ArrayNode outputs = objectMapper.createArrayNode();

        // TODO add your implementation



//        reading songs
        ArrayList<SongInput> songInputs = library.getSongs();
        int songsCount = 0;

        ArrayList<Song> songs = new ArrayList<>();
//        storing songs
        for (SongInput songInput : songInputs) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));

            ++songsCount;
        }

//        reading Podcasts && Episodes
        ArrayList<PodcastInput> podcastInputs = library.getPodcasts();
        int podcastsCount = 0;

        ArrayList<Podcast> podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputs) {
            ArrayList<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
            ++podcastsCount;
        }



//        reading users
        ArrayList<UserInput> userInputs = library.getUsers();
        int userCount = 0;

        ArrayList<User> users = new ArrayList<>();

//        storing users
        for (UserInput userInput : userInputs) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity(), songs, podcasts));
            ++userCount;
        }






//        reading input test files

        ArrayList<SearchBar> searchBarInputs = objectMapper.readValue(new File(CheckerConstants.TESTS_PATH +
                                                filePathInput), new TypeReference<ArrayList<SearchBar>>() {} );

        ArrayList<Command> commands = new ArrayList<>();

//        every playlist from every user;
        ArrayList<Playlist> everyPlaylist = new ArrayList<>();

//        pana aici e bine

//        parsing the Json content into corresponding commands
//        if (filePathInput.equals("ref_test03_like_create_addRemove.json"))
        for (SearchBar searchBarInput : searchBarInputs) {

            String command = searchBarInput.getCommand();
            User currentUser = null;
            for (User user : users)
                if (user.getUsername().equals(searchBarInput.getUsername())) {
                    currentUser = user;
                    break;
                }


            if (searchBarInput.getTimestamp() == 5570) {
                int x = 5;
            }



            if (currentUser != null && currentUser.getCurrentType() != null && currentUser.isPaused() == false) {

                int newSecsGone = searchBarInput.getTimestamp() - currentUser.getPrevTimestamp();

                if (searchBarInput.getTimestamp() == 257) {
                    int k = 5;
                }

                Type currentType = currentUser.getCurrentType();

                currentUser.getCurrentType().setSecondsGone(currentUser.getCurrentType().getSecondsGone() + newSecsGone);

                currentUser.setRemainingTime(currentType.getDuration() - currentType.getSecondsGone());


            }

            if (searchBarInput.getTimestamp() == 5470) {
                int y = 6;
            }


            if (currentUser != null && currentUser.getCurrentType() != null)
                currentUser.treatingRepeatStatus(currentUser, currentUser.getCurrentType());

            if (currentUser != null)
                currentUser.setPrevTimestamp(searchBarInput.getTimestamp());

            if (command.equals("search")) {
//                adding the search command, and initializing
                commands.add(new Search(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp(), searchBarInput.getType(), searchBarInput.getFilters()));

//                if only type is songs:
                if (searchBarInput.getType().equals("song")) {
                    ((Search) (commands.getLast())).searchingBySongType(currentUser.getEverySong());
                    currentUser.setTypeFoundBySearch(0);
                }

//                if only type is podcasts:
                if (searchBarInput.getType().equals("podcast")) {
                    ((Search) (commands.getLast())).searchingByPodcastType(podcasts);
                    currentUser.setTypeFoundBySearch(1);
                }

//                if only type is playlist:
                if (searchBarInput.getType().equals("playlist")) {
                    ((Search)(commands.getLast())).searchingByPlaylistType(everyPlaylist);
                    currentUser.setTypeFoundBySearch(2);
                }

                currentUser.setCurrentSearch((Search) (commands.getLast()));
                currentUser.setTypeSelected(-1);


                currentUser.setCurrentType(null);


                currentUser.setTypeLoaded(-1);
                currentUser.setRepeatString("No Repeat");


            }

            if (command.equals("select")) {
//                if the last command was search
                if (currentUser.getCurrentSearch() != null) {
                    ArrayList<String> resultsPrevSearch = currentUser.getCurrentSearch().getResults();

                    commands.add(new Select(searchBarInput.getCommand(), searchBarInput.getUsername(),
                            searchBarInput.getTimestamp(), searchBarInput.getItemNumber()));

//                    getting index for setting the message
                    int index = searchBarInput.getItemNumber();


                    if (searchBarInput.getTimestamp() == 1710) {
                        int x = 5;
                    }


//                    make this be a method in select class
                    if (index > resultsPrevSearch.size()) {
                        ((Select) (commands.getLast())).setMessage("The selected ID is too high.");
                        currentUser.setCurrentSelect(null);
                        currentUser.setCurrentType(null);
                    } else {
                        String name = resultsPrevSearch.get(index - 1);
                        ((Select) (commands.getLast())).setMessage("Successfully selected " + name + ".");
                        ((Select) (commands.getLast())).setSelectedName(name);

                        if (currentUser.getTypeFoundBySearch() == 0)
                            currentUser.setTypeSelected(0);
                        else if (currentUser.getTypeFoundBySearch() == 1)
                            currentUser.setTypeSelected(1);
                        else if (currentUser.getTypeFoundBySearch() == 2) {
                            currentUser.setTypeSelected(2);


                            for (Playlist playlist : everyPlaylist) {
                                if (playlist.getName().equals(name)) {
                                    int indexPlaylist = everyPlaylist.indexOf(playlist);

                                    currentUser.setSelectedPlaylist(playlist);
                                    break;
                                }
                            }

                        }
                        currentUser.setCurrentSelect((Select) (commands.getLast()));
                    }



                } else {
                    commands.add(new Select(searchBarInput.getCommand(), searchBarInput.getUsername(),
                            searchBarInput.getTimestamp(), searchBarInput.getItemNumber()));
                    ((Select) (commands.getLast())).setMessage("Please conduct a search before making a selection.");
                }

                currentUser.setCurrentSearch(null);
                currentUser.setTypeFoundBySearch(-1);

            }
            if (command.equals("load")) {
//                if the last command was select
                if (currentUser.getCurrentSelect() != null) {
//                    if the last selection was succesfully we can do the load
//                    boolean selectSuccessful = currentSelect.getMessage().contains("Successfully");
                    boolean selectSuccessful = currentUser.getCurrentSelect().getMessage().contains("Successfully");


                    if (selectSuccessful) {
                        commands.add(new Load(searchBarInput.getCommand(), searchBarInput.getUsername(),
                                    searchBarInput.getTimestamp()));
                        ((Load)(commands.getLast())).setMessage("Playback loaded successfully.");

                        currentUser.setTimestampAtLoading(((Load)(commands.getLast())).getTimestamp());
                        currentUser.setTimestampAtPlaying(currentUser.getTimestampAtLoading());
                        currentUser.setLoadMade(1);
                        currentUser.setPaused(false);
                        currentUser.setRepeatStatus(0);
                        currentUser.setRepeatString("No Repeat");
                        currentUser.setSecondsGone(0);

                        if (currentUser.getTypeSelected() == 0) {
                            for (Song song : currentUser.getEverySong()) {
                                if (song.getName().equals(currentUser.getCurrentSelect().getSelectedName())) {
                                    currentUser.setCurrentType(song);

                                    currentUser.getCurrentType().setSecondsGone(0);

                                    currentUser.setTypeLoaded(0);
                                    break;
                                }
                            }
                        } else if (currentUser.getTypeSelected() == 1) {
                            for (Podcast podcast : podcasts) {
                                if (podcast.getName().equals(currentUser.getCurrentSelect().getSelectedName())) {
//                                    currentUser.setCurrentType(podcast);


                                    if (currentUser.getPodcastsPlayed().contains(podcast)) {
//                                        resume the episode where you left off
//                                        keep in mind that the remainingTime is not set
//                                        currentType also not initialized here

                                        int indexPodcast = currentUser.getPodcastsPlayed().indexOf(podcast);

                                        currentUser.setCurrentType(currentUser.getPodcastsPlayed().get(indexPodcast));


                                        int indexEpisode = ((Podcast)(currentUser.getCurrentType())).getLastRemainingEpisode();
                                        currentUser.setCurrentType(((Podcast)(currentUser.getCurrentType())).getEpisodes().get(indexEpisode));

//                                        currentUser.getCurrentType().setSecondsGone(currentUser.getSecondsGone() + 1);

                                        currentUser.setCurrentPodcast(podcast);

                                    } else {
//                                        adding to the currentUser the loaded podcast
                                        currentUser.addPodcastPlayed(podcast);
//                                        setting last episode watched to 0
                                        currentUser.getPodcastsPlayed().getLast().setLastRemainingEpisode(0);
//                                        setting the remaining second;
                                        currentUser.getPodcastsPlayed().getLast().getEpisodes().getLast().setSecondsGone(0);

                                        currentUser.setRemainingTime(podcast.getEpisodes().getLast().getDuration());

//                                        current type is Podcast
                                        currentUser.setCurrentType(currentUser.getPodcastsPlayed().getLast());

//                                        current type is Episode
                                        currentUser.setCurrentType(((Podcast)(currentUser.getCurrentType())).
                                                getEpisodes().get(0));

                                        currentUser.setCurrentPodcast(podcast);
                                    }




                                    currentUser.setTypeLoaded(1);
                                    break;
                                }
                            }
                        } else if (currentUser.getTypeSelected() == 2) {
                            for (Playlist playlist : everyPlaylist) {
                                if (playlist.getName().equals(currentUser.getCurrentSelect().getSelectedName())) {
                                    currentUser.setTypeLoaded(2);

                                    currentUser.setCurrentPlaylist(playlist);
                                    currentUser.setCurrentType(playlist.getSongList().getFirst());
                                    currentUser.setRemainingTime(currentUser.getCurrentType().getDuration());
                                    currentUser.getCurrentType().setSecondsGone(0);

                                    break;
                                }
                            }
                        }


                    } else {
                        commands.add(new Load(searchBarInput.getCommand(), searchBarInput.getUsername(),
                                searchBarInput.getTimestamp()));
                        ((Load)(commands.getLast())).setMessage("Please select the song first.");
                    }


                } else {
                    commands.add(new Load(searchBarInput.getCommand(), searchBarInput.getUsername(),
                            searchBarInput.getTimestamp()));
                    ((Load)(commands.getLast())).setMessage("Please select a source before attempting to load.");

                }

                if (currentUser.getCurrentType() != null)
                    currentUser.setSelectedName((String)(currentUser.getCurrentType().getName()));


                currentUser.setCurrentSelect(null);
            }
            if (command.equals("playPause")) {
                commands.add(new PlayPause(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));

                if (!currentUser.isPaused() && currentUser.getTypeLoaded() != -1) {
                    ((PlayPause)commands.getLast()).setMessage("Playback paused successfully.");
//                    timestampAtPausing = ((PlayPause)commands.getLast()).getTimestamp();
                    currentUser.setTimestampAtPausing(((PlayPause)commands.getLast()).getTimestamp());

//                    currentUser.setSecondsGone(currentUser.getSecondsGone() + // e bineeee
//                            (currentUser.getTimestampAtPausing() - currentUser.getTimestampAtPlaying())); // e bineeee


                    currentUser.setPaused(true);

                } else if (currentUser.isPaused() && currentUser.getTypeLoaded() != -1) {
                    ((PlayPause) commands.getLast()).setMessage("Playback resumed successfully.");

                    currentUser.setTimestampAtPlaying(((PlayPause) commands.getLast()).getTimestamp());
                    currentUser.setPaused(false);
                }
            }
            if (command.equals("repeat")) {
//
                commands.add(new Repeat(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));



                currentUser.setRepeatStatus(((Repeat)(commands.getLast())).setRepeatMessage(currentUser,
                        currentUser.getRepeatStatus(), currentUser.getTypeLoaded()));
            }
            if (command.equals("status")) {

                commands.add(new Status(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));


                if (currentUser.getCurrentType() != null)
                    ((Status)(commands.getLast())).settingStats(currentUser, currentUser.getCurrentType());
                else {
                    ((Status)(commands.getLast())).settingNoType(currentUser);
                }

                if (((Status)(commands.getLast())).getRemainingTime() == 0 &&
                        currentUser.getRepeatStatus() == 0) {
                    currentUser.setPaused(true);
                    currentUser.setCurrentType(null);
                }

            }
            if (command.equals("shuffle")) {
                commands.add(new Shuffle(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp(), searchBarInput.getSeed()));

                if (searchBarInput.getTimestamp() == 6460) {
                    int x = 5;
                }

                currentUser.setShuffleSeed(searchBarInput.getSeed());

                ((Shuffle)(commands.getLast())).settingShuffle(currentUser);

                int x =5;

            }
            if (command.equals("createPlaylist")) {

//                verify if a playlist with the same name exists;
                String message = "Playlist created successfully.";
                for (Playlist playlist : everyPlaylist) {
                    if (playlist.getName().equals(searchBarInput.getPlaylistName()))
                        message = "A playlist with the same name already exists.";
                }


                commands.add(new CreatePlayList(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp(), searchBarInput.getPlaylistName(), message));

                if (!message.equals("A playlist with the same name already exists.")) {
                    currentUser.addPlaylistToList(((CreatePlayList) (commands.getLast())).getPlaylist());
                    everyPlaylist.add(((CreatePlayList)(commands.getLast())).getPlaylist());
                }

            }
            if (command.equals("addRemoveInPlaylist")) {
                commands.add(new AddRemoveInPlaylist(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp(), searchBarInput.getPlaylistId()));

//                setting message;
                ((AddRemoveInPlaylist)(commands.getLast())).setMessage(currentUser, searchBarInput.getPlaylistId());

            }
            if (command.equals("like")) {
                commands.add(new Like(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));

                if (currentUser.getCurrentType() != null) {
//                    if we have loaded a song
                    boolean like = currentUser.setLikedSongs((Song)currentUser.getCurrentType(), songs);
                    ((Like)(commands.getLast())).setMessageIfLiked(like);
                } else if (currentUser.getTypeLoaded() == 2) {
//                    if we have loaded a playlist
                    boolean like = currentUser.setLikedPlaylist();
                    ((Like)(commands.getLast())).setMessageIfLiked(like);
                } else {
                    ((Like)(commands.getLast())).setMessage("Please load a source before liking or unliking.");
                }

            }
            if (command.equals("showPlaylists")) {
                commands.add(new ShowPlaylists(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));



                ArrayList<Playlist> copyList = new ArrayList<>();

                ((ShowPlaylists)(commands.getLast())).copyPlaylists(currentUser, copyList);

                ((ShowPlaylists)(commands.getLast())).setResult(copyList);
            }
            if (command.equals("showPreferredSongs")) {
                commands.add(new ShowPreferredSongs(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp()));

                ((ShowPreferredSongs)(commands.getLast())).setResult(currentUser);
            }
            if (command.equals("next")) {

                if (searchBarInput.getTimestamp() == 6240) {
                    int x = 5;
                }

                currentUser.setNext(true);

                commands.add((new Next(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp())));

                ((Next)(commands.getLast())).setNext(currentUser);

                currentUser.setNext(false);


            }
            if (command.equals("prev")) {
                commands.add((new Prev(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp())));


                if (searchBarInput.getTimestamp() == 5471) {
                    int x = 5;
                }



                ((Prev)(commands.getLast())).setPrev(currentUser, currentUser.getCurrentType());


            }
            if (command.equals("forward")) {
                commands.add((new Forward(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp())));


                ((Forward)(commands.getLast())).setForward(currentUser);

            }
            if (command.equals("backward")) {
                commands.add((new Backward(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp())));


                ((Backward)(commands.getLast())).setBackward(currentUser);

            }
            if (command.equals("follow")) {
                commands.add((new Follow(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp())));

                ((Follow)(commands.getLast())).setFollow(currentUser, everyPlaylist);

            }
            if (command.equals("switchVisibility")) {
                commands.add((new SwitchVisibility(searchBarInput.getCommand(), searchBarInput.getUsername(),
                        searchBarInput.getTimestamp(), searchBarInput.getPlaylistId())));

                ((SwitchVisibility)(commands.getLast())).setVisibility(currentUser);
            }
            if (command.equals("getTop5Playlists")) {
                commands.add((new GetTop5Playlists(searchBarInput.getCommand(), searchBarInput.getTimestamp())));

                ((GetTop5Playlists)(commands.getLast())).searchTop5Playlists(everyPlaylist);
            }
            if (command.equals("getTop5Songs")) {
                commands.add((new GetTop5Songs(searchBarInput.getCommand(), searchBarInput.getTimestamp())));

                ((GetTop5Songs)(commands.getLast())).searchTop5Songs(songs);
            }

        }

//        parsing the requeriments
        if (!commands.isEmpty())
            for (Command comm : commands) {
                outputs.add(objectMapper.valueToTree(comm));
            }


    objectMapper.writeValue(new File(CheckerConstants.RESULT_PATH + filePathInput), outputs);

        int a = 5;


                ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePathOutput), outputs);
    }
}



