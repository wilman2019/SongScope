package Main;

import java.awt.EventQueue;

import FirstScreen.FirstScreen;
import HandleCSV.CompleteStoreCSV;
import HandleCSV.ReadCSV;

public class Main {
	public static void main(String[] args) {
		CompleteStoreCSV.completeStoreSpotifyAndYoutubeCSV();
		CompleteStoreCSV.completeTopSpotifySongs();
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
