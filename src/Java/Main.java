package Java;

import java.awt.EventQueue;

import Java.CombineCSV.CombineCSV;
import Java.Screens.LoginScreen;
import Java.HandleCSV.CompleteStoreCSV;
import Java.HandleCSV.ReadCSV;
import Java.HandleCSV.Sort;

public class Main {
	public static void main(String[] args) {
		String[][] songData = CombineCSV.combineCSV();
		Java.Screens.SongData.setSongData(songData);
		
		String[][] topSongs = CompleteStoreCSV.completeTopSpotifySongs();
		Java.Screens.TopSongs.setTopSongs(topSongs);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
					frame.pack();
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
