package Main;

import java.awt.EventQueue;

import FirstScreen.FirstScreen;
import HandleCSV.CompleteStoreCSV;
import HandleCSV.ReadCSV;
import HandleCSV.TimSort;

public class Main {
	public static void main(String[] args) {
		CompleteStoreCSV.completeStoreSpotifyAndYoutubeCSV();
		CompleteStoreCSV.completeTopSpotifySongs();
		String[][] spotifyAndYoutubeData = CompleteStoreCSV.completeStoreSpotifyAndYoutubeCSV();
		String[][] topSpotifySongsData = CompleteStoreCSV.completeTopSpotifySongs();
		System.out.println(spotifyAndYoutubeData[100][1]);
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
