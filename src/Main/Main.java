package Main;

import java.awt.EventQueue;

import CombineCSV.CombineCSV;
import FirstScreen.FirstScreen;
import HandleCSV.CompleteStoreCSV;
import HandleCSV.ReadCSV;
import HandleCSV.Sort;

public class Main {
	public static void main(String[] args) {
		String[][] spotifyAndYoutubeData = CompleteStoreCSV.completeSpotifyAndYoutubeCSV();
		String[][] topSpotifySongsData = CompleteStoreCSV.completeTopSpotifySongs();
		String[][] songData = CombineCSV.combineCSV();
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
