package Screens;

import java.util.ArrayList;
import java.util.Arrays;

public class Searching {
	public static String[][] searchString(String[][] songData, String s1, int column1, int column2) {
	    String[][] array = new String[songData.length][];
	    int j = 0;

	    for (int i = 0; i < songData.length; i++) {
	    	boolean added = false;
	        if (i == 0) {
	            if (songData[i] != null && songData[i][column1] != null)
	            	try {
	            		if (songData[i][column1].toLowerCase().contains((s1).toLowerCase())) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    j++;
		                    added = true;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}

	            if (songData[i] != null && songData[i][column2] != null)
	            	try {
	            		if (songData[i][column2].toLowerCase().contains(s1.toLowerCase())) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    if (!added) 
		                    	j++;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	        }
	        
	        added = false;

	        if (i >= 1) {
	            if (songData[i] != null && songData[i][column1] != null) {
	            	try {
	            		if (songData[i][column1].toLowerCase().contains(s1.toLowerCase()) && !songData[i][0].equals(songData[i - 1][0])) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    j++;
		                    added = true;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            }
	            
	            if (songData[i] != null && songData[i][column2] != null) {
	            	try {
	            		if (songData[i][column2].toLowerCase().contains(s1.toLowerCase()) && !songData[i][0].equals(songData[i - 1][0])) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    if (!added) 
		                    	j++;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            }
	        }
	    }
	    
	    int rows = 0;
	    for (int i = 0; i < array.length; i++) {
	    	if (array[i] == null) {
	    		rows = i;
	    		break;
	    	}
	    }
	    
	    String[][] result = new String[rows][array[0].length];
	    
	    for (int i = 0; i < result.length; i++)
	    	for (j = 0; j < result[0].length; j++)
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
	
	public static String[][] searchStats(String[][] songData, String min, String max, int column1, int column2) {
		String[][] array = new String[songData.length][];
	    int j = 0;
	    
	    for (int i = 0; i < songData.length; i++) {
	    	boolean added = false;
	        if (i == 0) {
	            if (songData[i] != null && songData[i][column1] != null) {
	            	try {
	            		if (Double.parseDouble(songData[i][column1].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][7].replaceAll("\"", "")) < Double.parseDouble(max)) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    j++;
		                    added = true;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            	
	            }

	            if (songData[i] != null && songData[i][column2] != null)
	            	try {
	            		if (Double.parseDouble(songData[i][column2].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][42].replaceAll("\"", "")) < Double.parseDouble(max)) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    if (!added)
		                    	j++;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	        }
	        
	        added = false;

	        if (i >= 1) {
	            if (songData[i] != null && songData[i][column1] != null) {
	            	try {
	            		if (Double.parseDouble(songData[i][column1].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][7].replaceAll("\"", "")) < Double.parseDouble(max) && !songData[i][0].equals(songData[i - 1][0])) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    j++;
		                    added = true;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            }

	            if (songData[i] != null && songData[i][column2] != null)
	            	try {
	            		if (Double.parseDouble(songData[i][column2].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][42].replaceAll("\"", "")) < Double.parseDouble(max) && !songData[i][0].equals(songData[i - 1][0])) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    if (!added)
		                    	j++;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	        }
	    }
	    
	    int rows = 0;
	    for (int i = 0; i < array.length; i++) {
	    	if (array[i] == null) {
	    		rows = i;
	    		break;
	    	}
	    }
	    
	    String[][] result = new String[rows][array[0].length];
	    
	    for (int i = 0; i < result.length; i++)
	    	for (j = 0; j < result[0].length; j++)
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
	
	public static String[][] searchStats(String[][] songData, String min, String max, int column) {
		String[][] array = new String[songData.length][];
	    int j = 0;
	    
	    for (int i = 0; i < songData.length; i++) {
	    	boolean added = false;
	    	
	        if (i == 0) {
	            if (songData[i] != null && songData[i][column] != null) {
	            	try {
	            		if (Double.parseDouble(songData[i][column].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][37].replaceAll("\"", "")) < Double.parseDouble(max)) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    j++;
		                    added = true;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            }
	        }

	        if (i >= 1) {
	            if (songData[i] != null && songData[i][column] != null) {
	            	try {
	            		if (Double.parseDouble(songData[i][column].replaceAll("\"", "")) > Double.parseDouble(min) && Double.parseDouble(songData[i][37].replaceAll("\"", "")) < Double.parseDouble(max) && !songData[i][0].equals(songData[i - 1][0])) {
		                    array[j] = new String[songData[0].length]; 
		                    for (int k = 0; k < songData[0].length; k++)
		                        array[j][k] = songData[i][k];
		                    if (!added)
		                    	j++;
		                }
	            	} catch (NumberFormatException E) {
	            		
	            	}
	            }
	        }
	    }
	    
	    int rows = 0;
	    for (int i = 0; i < array.length; i++) {
	    	if (array[i] == null) {
	    		rows = i;
	    		break;
	    	}
	    }
	    
	    String[][] result = new String[rows][array[0].length];
	    
	    for (int i = 0; i < result.length; i++)
	    	for (j = 0; j < result[0].length; j++)
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
