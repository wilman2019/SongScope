package Main;

import java.awt.EventQueue;

import FirstScreen.FirstScreen;
import HandleCSV.CompleteStoreCSV;
import HandleCSV.ReadCSV;
import HandleCSV.TimSort;

public class Main {
	public static void main(String[] args) {
		String[][] spotifyAndYoutubeData = CompleteStoreCSV.completeSpotifyAndYoutubeCSV();
		String[][] topSpotifySongsData = CompleteStoreCSV.completeTopSpotifySongs();
		int nSpotifyAndYoutubeData = spotifyAndYoutubeData.length;
		int nTopSpotifySongsData = topSpotifySongsData.length;
		TimSort.timSort(spotifyAndYoutubeData, nSpotifyAndYoutubeData);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstScreen frame = new FirstScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

// test change for git commit