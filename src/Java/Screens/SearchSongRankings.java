package Java.Screens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SearchSongRankings {
	public static ArrayList<String[]> searchSongRankings(String[][] topSpotifySongs, String country, String date) {
		country = country.substring(country.length() - 3, country.length() - 1); 
		String[][] result = search(topSpotifySongs, country, 6);
		result = search(result, date, 7);
		
		ArrayList<String[]> resultList = new ArrayList<String[]>();
	    for (int i = 0; i < result.length; i++) {
	        resultList.add(result[i]);
	    }

	    return resultList;
	}
	
	public static String[][] search(String[][] topSpotifySongs, String s1, int column) {
		String[][] array = new String[topSpotifySongs.length][topSpotifySongs[0].length];
		
		int index = 0;
		for (int i = 0; i < topSpotifySongs.length; i++) {
			if (topSpotifySongs[i][3] != null) {
				if (topSpotifySongs[i][column].contains(s1)) { 
					for (int j = 0; j < topSpotifySongs[0].length; j++) {
						array[index][j] = topSpotifySongs[i][j];
					}
					index++;
				}
			}
		}
		
		String[][] result = array;
				
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result[0].length; j++)
			  	result[i][j] = array[i][j];
			    
		ArrayList<String[]> resultList = new ArrayList<>(Arrays.asList(result));
		for (int i = 0; i < resultList.size() - 1; i++) {
			if (resultList.get(i)[3] == null) {
				resultList.remove(i);
				i--;
			}
		}
		result = resultList.toArray(new String[0][]);
		
		Arrays.sort(result, Comparator.comparingDouble((String[] a) -> a[3] != null ? Double.parseDouble(a[3].replace("\"", "")) : Double.NaN));
		
		return result;
	}
}
