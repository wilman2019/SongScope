package HandleCSV;

public class CompleteStoreCSV {
	public static String[][] completeStoreSpotifyAndYoutubeCSV() {
		ReadCSV spotifyAndYoutube = new ReadCSV("Spotify_Youtube.csv");
		
		String[][] spotifyAndYoutubeData = spotifyAndYoutube.storeCSV();
		
		// Add key to spotifyAndYoutubeData
		for (int i = 0; i < spotifyAndYoutubeData.length; i++)
			spotifyAndYoutubeData[i][spotifyAndYoutubeData[i].length - 1] = spotifyAndYoutubeData[i][11] + "-"+ spotifyAndYoutubeData[i][12];
		
		return spotifyAndYoutubeData;
	}
	
	public static String[][] completeTopSpotifySongs() {
		ReadCSV topSpotifySongs = new ReadCSV("universal_top_spotify_songs.csv");
		String[][] topSpotifySongsData = topSpotifySongs.storeCSV();
		
		// Add key to topSpotifySongs
		for (int i = 0; i < topSpotifySongsData.length; i++)
			topSpotifySongsData[i][topSpotifySongsData[i].length - 1] = topSpotifySongsData[i][16] + "-"+ topSpotifySongsData[i][18];
		
		return topSpotifySongsData;
	}
}
