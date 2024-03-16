package HandleCSV;

import java.io.*;

public class ReadCSV {
	private String CSVSource;
	
	public ReadCSV(String CSVSource){
		this.CSVSource = CSVSource;
	}
	
	// Method used to store CSV universal top spotify songs which is in proper csv format
	public String[][] storeCSV() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int rows = getRowCountCSVFormat();
		int columns = getColumnCountCSVFormat();
		
		// One added to columns so that key can be made in final column for each row
		String[][] data = new String[rows][columns + 1];
			
		try {
			reader = new BufferedReader(new FileReader(file));
			int i = 0;
			while((line = reader.readLine()) != null) {
				 String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				 for (int j = 0; j < columns && j < row.length; j++) {
					 data[i][j] = row[j];
				 }
				i++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	// Method used to store CSV Spotify and Youtube which has extra info thats not in csv format
	public String[][] storeCSVNonCSVFormat() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int rows = getRowCountNonCSVFormat();
		int columns = getColumnCountNonCSVFormat();
		
		// One added to columns so that key can be made in final column for each row
		String[][] data = new String[rows][columns + 1];
		
		try {
			reader = new BufferedReader(new FileReader(file));
			int i = 0;
			while((line = reader.readLine()) != null) {
				if (line.matches("\\d+,[^\\s].*")) {
					String[] row = line.split(",(?=\\S)");
					for (int j = 0; j < columns && j < row.length; j++) {
						data[i][j] = "\"" + row[j] + "\"";
					}
					i++;
				}
				else
					continue;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public int getRowCountNonCSVFormat() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int rowCount = 0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				if (line.matches("\\d+,[^\\s].*")) {
	                rowCount++;
	            }
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	public int getRowCountCSVFormat() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int rowCount = 0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				rowCount++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}
	
	public int getColumnCountNonCSVFormat() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int columnCount = 0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				String[] row = line.split(",(?=\\S)");
				columnCount = row.length;
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return columnCount;
	}
	
	public int getColumnCountCSVFormat() {
		String file = CSVSource;
		BufferedReader reader = null;
		String line = "";
		int columnCount = 0;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null) {
				String[] row = line.split(",");
				columnCount = row.length;
				break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return columnCount;
	}
}
