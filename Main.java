public class Main {
    public static void main(String[] args) {
        // Create a new instance of the MusicPlayerApp
        MusicPlayerApp musicPlayer = new MusicPlayerApp();

        // Create some playlists and add songs to them
        musicPlayer.createPlaylist("Relaxing Evening");
        musicPlayer.addSongToPlaylist("Relaxing Evening", new Song("Chasing Cars", "Snow Patrol"));
        musicPlayer.addSongToPlaylist("Relaxing Evening", new Song("Someone Like You", "Adele"));
        musicPlayer.addSongToPlaylist("Relaxing Evening", new Song("Wonderwall", "Oasis"));

        musicPlayer.createPlaylist("Workout Mix");
        musicPlayer.addSongToPlaylist("Workout Mix", new Song("Eye of the Tiger", "Survivor"));
        musicPlayer.addSongToPlaylist("Workout Mix", new Song("Stronger", "Kanye West"));

        musicPlayer.createPlaylist("Throwback Jams");
        musicPlayer.addSongToPlaylist("Throwback Jams", new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars"));
        musicPlayer.addSongToPlaylist("Throwback Jams", new Song("Billie Jean", "Michael Jackson"));

        // Add one more playlist with 5 songs to it
        musicPlayer.createPlaylist("Road Trip");
        musicPlayer.addSongToPlaylist("Road Trip", new Song("Life is a Highway", "Tom Cochrane"));
        musicPlayer.addSongToPlaylist("Road Trip", new Song("Born to Run", "Bruce Springsteen"));
        musicPlayer.addSongToPlaylist("Road Trip", new Song("Take Me Home, Country Roads", "John Denver"));
        musicPlayer.addSongToPlaylist("Road Trip", new Song("Hotel California", "Eagles"));
        musicPlayer.addSongToPlaylist("Road Trip", new Song("Sweet Home Alabama", "Lynyrd Skynyrd"));

        // Display the main menu
        MusicPlayerUI ui = new MusicPlayerUI(musicPlayer);
        ui.displayMainMenu();
    }
}
