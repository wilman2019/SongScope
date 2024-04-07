package Java.Screens;

public class SongData {
    private static String[][] songData;

    public static void setSongData(String[][] data) {
        songData = data;
    }

    public static String[][] getSongData() {
        return songData;
    }
}
