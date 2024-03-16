package HandleCSV;

public class CompleteStoreCSV {
	public static String[][] completeSpotifyAndYoutubeCSV() {
		ReadCSV spotifyAndYoutube = new ReadCSV("Spotify_Youtube.csv");
		
		String[][] spotifyAndYoutubeData = spotifyAndYoutube.storeCSVNonCSVFormat();
		
		// Add key to spotifyAndYoutubeData
		for (int i = 0; i < spotifyAndYoutubeData.length; i++)
			spotifyAndYoutubeData[i][spotifyAndYoutubeData[i].length - 1] = spotifyAndYoutubeData[i][7] + " " + spotifyAndYoutubeData[i][8];
		
		return spotifyAndYoutubeData;
	}
	
	public static String[][] completeTopSpotifySongs() {
		ReadCSV topSpotifySongs = new ReadCSV("universal_top_spotify_songs.csv");
		String[][] topSpotifySongsData = topSpotifySongs.storeCSV();
		
		// Add key to topSpotifySongs
		for (int i = 0; i < topSpotifySongsData.length; i++)
			topSpotifySongsData[i][topSpotifySongsData[i].length - 1] = topSpotifySongsData[i][13] + " " + topSpotifySongsData[i][14];
		
		return topSpotifySongsData;
	}
}
