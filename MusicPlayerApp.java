
import java.util.ArrayList;
import java.util.LinkedList;

import java.util.List;
import java.util.Scanner;



// Define the Player interface for abstraction
interface Player {
    void play();
}

public class MusicPlayerApp implements Player {
    private List<Playlist> playlists; // List to store playlists
    private Playlist currentPlaylist; // Currently selected playlist
    private Song currentSong; // Currently playing song
    private boolean repeat; // Flag to indicate repeat mode


    // Constructor to initialize the MusicPlayerApp
    public MusicPlayerApp() {
        playlists = new ArrayList<>(); // Initialize playlists as ArrayList
        repeat = false; // Repeat mode is initially off
    }

    // Method to create a new playlist
    public void createPlaylist(String name) {
        playlists.add(new Playlist(name)); 
    }


    // Method to add a song to a playlist
    public void addSongToPlaylist(String playlistName, Song song) {
        for (Playlist playlist : playlists) { // Iterate through the playlists
            if (playlist.getName().equals(playlistName)) { // Check if the playlist name matches
                playlist.addSong(song);
                return;
            }
        }
        System.out.println("Playlist not found: " + playlistName); // Print error if playlist not found
    }

    // Method to play a playlist
    public void playPlaylist(String playlistName) {
        for (Playlist playlist : playlists) { 
            if (playlist.getName().equals(playlistName)) { // Check if the playlist name matches
                currentPlaylist = playlist; // Set the current playlist
                currentSong = currentPlaylist.getFirstSong();
                System.out.println("Now playing playlist: " + playlistName); 
                play(); 
                return;
            }
        }
        System.out.println("Playlist not found: " + playlistName); // Print error if playlist not found
    }


    // Method to toggle repeat mode
    public void toggleRepeat() {
        repeat = !repeat; // Toggle the repeat mode
        System.out.println("Repeat is now " + (repeat ? "ON" : "OFF")); // Print the current state
    }


    // Method to play the next song
    public void playNextSong() {
        if (currentPlaylist != null) { // Check if a playlist is selected
            currentSong = currentPlaylist.getNextSong(currentSong); 
            if (currentSong != null) { // If there is a next song
                play(); 
            } else { // If end of playlist is reached
                if (repeat) { // Check if repeat mode is on
                    currentSong = currentPlaylist.getFirstSong(); 
                    play();
                } else {
                    System.out.println("End of playlist reached.");
                }
            }
        } else {
            System.out.println("Please select a playlist first."); 
        }
    }


    // Method to play the previous song
    public void playPreviousSong() {
        if (currentPlaylist != null) { 
            currentSong = currentPlaylist.getPreviousSong(currentSong); // Get the previous song
            if (currentSong != null) { 
                play(); // Start playing the previous song
            } else {
                System.out.println("Beginning of playlist reached.");
            }
        } else {
            System.out.println("Please select a playlist first."); // Error if no playlist is selected
        }
    }



    // Implementing play method from the Player interface
    @Override
    public void play() {
        if (currentSong != null) { // Check if there is a song playing
            System.out.println("Now playing: " + currentSong.getTitle() + " - " + currentSong.getArtist()); // Print the song details
        } else {
            System.out.println("No song is currently playing."); // Print if no song is playing
        }
    }

    // Getter for currentPlaylist
    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    // Main method to start the application
    public static void main(String[] args) {
        MusicPlayerApp player = new MusicPlayerApp(); // Create a new instance of MusicPlayerApp
        MusicPlayerUI ui = new MusicPlayerUI(player); // Create a new instance of MusicPlayerUI
        ui.displayMainMenu(); // Display the main menu
    }
}

// Class representing a Playlist
class Playlist {
    private String name; // Name of the playlist
    private LinkedList<Song> songs; // List of songs in the playlist

    // Constructor to initialize a playlist
    public Playlist(String name) {
        this.name = name; // Set the name of the playlist
        songs = new LinkedList<>(); // Initialize the list of songs
    }

    // Method to add a song to the playlist
    public void addSong(Song song) {
        songs.add(song); // Add the song to the playlist
    }

    // Getter for the name of the playlist
    public String getName() {
        return name;
    }

    // Method to get the first song in the playlist
    public Song getFirstSong() {
        return songs.isEmpty() ? null : songs.getFirst(); // Return the first song, or null if the playlist is empty
    }

    // Method to get the next song in the playlist
    public Song getNextSong(Song currentSong) {
        int currentIndex = songs.indexOf(currentSong); // Get the index of the current song
        return currentIndex == -1 || currentIndex == songs.size() - 1 ? null : songs.get(currentIndex + 1); // Return the next song, or null if at the end of the playlist
    }

    // Method to get the previous song in the playlist
    public Song getPreviousSong(Song currentSong) {
        int currentIndex = songs.indexOf(currentSong); // Get the index of the current song
        return currentIndex <= 0 ? null : songs.get(currentIndex - 1); // Return the previous song, or null if at the beginning of the playlist
    }
}

// Class representing a Song
class Song {
    private String title; // Title of the song
    private String artist; // Artist of the song

    // Constructor to initialize a song
    public Song(String title, String artist) {
        this.title = title; // Set the title of the song
        this.artist = artist; // Set the artist of the song
    }

    // Getter for the title of the song
    public String getTitle() {
        return title;
    }

    // Getter for the artist of the song
    public String getArtist() {
        return artist;
    }
}



// Class representing the user interface of the music player
class MusicPlayerUI {
    private MusicPlayerApp musicPlayer; // Reference to the MusicPlayerApp instance
    private Scanner scanner; // Scanner object for user input




    // Constructor to initialize the MusicPlayerUI

    public MusicPlayerUI(MusicPlayerApp player) {
        musicPlayer = player; // Set the MusicPlayerApp instance
        scanner = new Scanner(System.in); // Initialize the scanner for user input
    }

    // Method to display the main menu and handle user input
    public void displayMainMenu() {
        boolean quit = false;
        while (!quit) {
            System.out.println("===== Music Player Menu =====");
            System.out.println("1. Create Playlist");
            System.out.println("2. Add Song to Playlist");
            System.out.println("3. Play Playlist");
            System.out.println("4. Next Song");
            System.out.println("5. Previous Song");
            System.out.println("6. Toggle Repeat");
            System.out.println("0. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    addSongToPlaylist();
                    break;
                case 3:
                    playPlaylist();
                    break;
                case 4:
                    playNextSong();
                    break;
                case 5:
                    playPreviousSong();
                    break;
                case 6:
                    toggleRepeat();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close(); // Close the scanner when done
    }


    // Method to create a new playlist
    
    private void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        musicPlayer.createPlaylist(playlistName);
        System.out.println("Playlist created: " + playlistName);
    }



    // Method to add a song to a playlist

    private void addSongToPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        System.out.print("Enter song title: ");

        String title = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        musicPlayer.addSongToPlaylist(playlistName, new Song(title, artist));
        System.out.println("Song added to playlist: " + playlistName);
    }

    // Method to play a playlist
    private void playPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        musicPlayer.playPlaylist(playlistName);
    }

    // Method to play the next song
    private void playNextSong() {
        musicPlayer.playNextSong();
    }

    // Method to play the previous song


    private void playPreviousSong() {
        musicPlayer.playPreviousSong();
    }



    // Method to toggle repeat mode
    private void toggleRepeat() {
        musicPlayer.toggleRepeat();
    }
}



