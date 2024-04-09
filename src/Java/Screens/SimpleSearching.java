package Java.Screens;

import java.util.ArrayList;
import java.util.Arrays;

import Java.CombineCSV.CombineCSV;

public class SimpleSearching {
	public static void main(String[] args) {
		String[][] songData = CombineCSV.combineCSV();
		SongData.setSongData(songData);
		simpleSearch(SongData.getSongData(), "Love Taylor Swift");
	}
	public static String[][] simpleSearch(String[][] songData, String s1) {
		String[][] array = new String[songData.length][songData[0].length];
		
		for (int i = 0; i < array.length; i++) {
	        Arrays.fill(array[i], null);
	    }
		
		// Split string into multiple strings that each represent a word (ex. thesnake -> the snake)
		s1 = s1.trim().replaceAll("\\s+", " ");
	    
	    String[] words = s1.split(" ");
	    
	    int index = 0;
	    int matchRating = 0;
	    
	    if (words.length == 1) {
	    	for (int i = 0; i < songData.length; i++) {
	    		if (i == 0) {
	    			if (songData[i][1] != null) {
			    		if (songData[i][1].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
			    	else if (songData[i][31] != null) {
			    		if (songData[i][31].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
			    	else if (songData[i][3] != null) {
			    		if (songData[i][3].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
			    	else if (songData[i][30] != null) {
			    		if (songData[i][30].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
			    	else if (songData[i][4] != null) {
			    		if (songData[i][4].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
			    	else if (songData[i][40] != null) {
			    		if (songData[i][40].toLowerCase().contains(words[0].toLowerCase())) {
		    				for (int k = 0; k < songData[0].length; k++)
		    					array[index][k]= songData[i][k];
		    				index++;
			    		}
			    	}
	    		}
	    		else {
	    			if (songData[i][3] != null) {
	    				if (!songData[i][3].equals(songData[i + 1][3])) {
	    					if (songData[i][1] != null) {
	    			    		if (songData[i][1].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][31] != null) {
	    			    		if (songData[i][31].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][3] != null) {
	    			    		if (songData[i][3].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][30] != null) {
	    			    		if (songData[i][30].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][4] != null) {
	    			    		if (songData[i][4].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][40] != null) {
	    			    		if (songData[i][40].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    				}
	    			}
	    			else if (songData[i][30] != null) {
	    				if (!songData[i][30].equals(songData[i + 1][30])) {
	    					if (songData[i][1] != null) {
	    			    		if (songData[i][1].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][31] != null) {
	    			    		if (songData[i][31].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][3] != null) {
	    			    		if (songData[i][3].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][30] != null) {
	    			    		if (songData[i][30].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][4] != null) {
	    			    		if (songData[i][4].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    			    	else if (songData[i][40] != null) {
	    			    		if (songData[i][40].toLowerCase().contains(words[0].toLowerCase())) {
	    		    				for (int k = 0; k < songData[0].length; k++)
	    		    					array[index][k]= songData[i][k];
	    		    				index++;
	    			    		}
	    			    	}
	    				}
	    			}
	    		}
	    	}
	    }
	    else {
	    	for (int i = 0; i < songData.length; i++) {
	    		matchRating = 0;
	    		if (i == 0) {
	    			if (songData[i][1] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][1].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
			    	else if (songData[i][31] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][31].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
			    	if (songData[i][3] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][3].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
			    	else if (songData[i][30] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][30].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
			    	if (songData[i][4] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][4].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
			    	else if (songData[i][40] != null) {
			    		for (int j = 0; j < words.length; j++) {
			    			if (songData[i][40].toLowerCase().contains(words[j].toLowerCase())) {
			    				matchRating++;
				    		}
			    		}
			    	}
	    		}
	    		else {
	    			if (songData[i][3] != null) {
	    				if (!songData[i][3].equals(songData[i + 1][3])) {
	    					if (songData[i][1] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][1].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][31] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][31].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	if (songData[i][3] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][3].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][30] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][30].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	if (songData[i][4] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][4].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][40] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][40].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    				}
	    			}
	    			else if (songData[i][30] != null) {
	    				if (!songData[i][30].equals(songData[i + 1][30])) {
	    					if (songData[i][1] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][1].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][31] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][31].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	if (songData[i][3] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][3].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][30] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][30].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	if (songData[i][4] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][4].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    			    	else if (songData[i][40] != null) {
	    			    		for (int j = 0; j < words.length; j++) {
	    			    			if (songData[i][40].toLowerCase().contains(words[j].toLowerCase())) {
	    			    				matchRating++;
	    				    		}
	    			    		}
	    			    	}
	    				}
	    			}
	    		}
		    	
		    	if (matchRating / words.length >= 1) {
		    		for (int k = 0; k < songData[0].length; k++)
    					array[index][k] = songData[i][k];
		    		index++;
		    	}
		    	
		    	matchRating = 0;
	    	}
	    }
	    
	    int rows = 0;
	    for (int i = 0; i < array.length; i++) {
	    	if (array[i][3] == null && array[i][30] == null) {
	    		rows = i;
	    		break;
	    	}
	    }
	    
	    String[][] result = new String[rows][array[0].length];
	    
	    for (int i = 0; i < result.length; i++)
	    	for (int j = 0; j < result[0].length; j++)
	    		result[i][j] = array[i][j];
	    
	    ArrayList<String[]> resultList = new ArrayList<>(Arrays.asList(result));
	    for (int i = 0; i < resultList.size() - 1; i++) {
	        if (resultList.get(i + 1)[0].equals(resultList.get(i)[0])) {
	            resultList.remove(i);
	            i--; 
	        }
	    }
	    result = resultList.toArray(new String[0][]);

	    return result;
	}
}
