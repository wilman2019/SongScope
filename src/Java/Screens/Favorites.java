package Screens;

import java.util.ArrayList;

public class Favorites {
	private static ArrayList<String[]> favorites = new ArrayList<String[]>();
	
	public static void addToFavorites(ArrayList<String[]> arrayList) {
	    for (String[] array : arrayList) {
	        boolean exists = false;
	        for (String[] favorite : favorites) {
	            if (favorite[0].equals(array[0])) {
	                exists = true;
	                break;
	            }
	        }
	        if (!exists) {
	            favorites.add(array);
	        }
	    }
	}
	
	public static ArrayList<String[]> getFavorites() {
		return favorites;
	}
}
