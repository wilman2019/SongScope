package Java.Screens;

public class TopSongs {
    private static String[][] topSongs;

    public static void setTopSongs(String[][] data) {
        topSongs = data;
    }

    public static String[][] getTopSongs() {
        return topSongs;
    }
}
