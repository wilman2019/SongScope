package Java.Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShowFavorites extends JFrame {
    private JPanel contentPane;
    private ArrayList<String[]> favorites;
    private DefaultListModel<String> listModel;
    private JList<String> favoritesList;

    public ShowFavorites(ArrayList<String[]> favorites) {
        setTitle("Favorites");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        this.favorites = favorites;
        initComponents();
    }

    private void initComponents() {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        listModel = new DefaultListModel<>();
        for (String[] favorite : favorites) {
        	String item = "";
        	if (favorite[3] != null)
        		item += favorite[3] + " by ";
        	else if (favorite[30] != null)
        		item += favorite[30] + " by ";
        	
        	if (favorite[1] != null)
        		item += favorite[1];
        	else if (favorite[31] != null)
        		item += favorite[31];
        	
            listModel.addElement(item);
        }

        favoritesList = new JList<>(listModel);
        favoritesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(favoritesList);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton removeButton = new JButton("Remove Selected");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = favoritesList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    listModel.remove(selectedIndices[i]);
                    favorites.remove(selectedIndices[i]);
                }
            }
        });
        buttonPanel.add(removeButton);
        
        JButton viewStatsButton = new JButton("View Statistics of all favorites");
        viewStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String message = "";
            	
            	message += averageStat("Danceability", 7, 42);
            	message += minStat("Danceability", 7, 42);
            	message += maxStat("Danceability", 7, 42);
            	message += averageStat("Energy", 8, 43);
            	message += minStat("Energy", 8, 43);
            	message += maxStat("Energy", 8, 43);
            	message += averageStat("Loudness", 10, 45);
            	message += minStat("loudness", 10, 45);
            	message += maxStat("loudness", 10, 45);
            	message += averageStat("Speechiness", 11, 47);
            	message += minStat("Speechiness", 11, 47);
            	message += maxStat("Speechiness", 11, 47);
            	message += averageStat("Acousticness", 12, 48);
            	message += minStat("Acousticness", 12, 48);
            	message += maxStat("Acousticness", 12, 48);
            	message += averageStat("Instrumentalness", 13, 49);
            	message += minStat("Instrumentalness", 13, 49);
            	message += maxStat("Instrumentalness", 13, 49);
            	message += averageStat("Liveness", 14, 50);
            	message += minStat("Liveness", 14, 50);
            	message += maxStat("Livness", 14, 50);
            	message += averageStat("Duration (ms)", 17, 39);
            	message += minStat("Duration (ms)", 17, 39);
            	message += maxStat("Duration (ms)", 17, 39);
            	
            	if (message == "")
            		message += "Please add songs to favorites";
            	
            	JOptionPane.showMessageDialog(ShowFavorites.this,
                        message,
                        "Statistics", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(viewStatsButton);
        
        JButton viewStatsButtonOfOneSong = new JButton("View Statistic (one song)");
        viewStatsButtonOfOneSong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "";
                int[] selectedIndices = favoritesList.getSelectedIndices();
                if (selectedIndices.length > 0) {
                    String[] selectedSong = favorites.get(selectedIndices[0]);
                    if (selectedSong[7] != null)
                        message += "Danceability: " + selectedSong[7] + "\n";
                    else if (selectedSong[42] != null)
                        message += "Danceability: " + selectedSong[42] + "\n";
                    if (selectedSong[8] != null)
                        message += "Energy: " + selectedSong[8] + "\n";
                    else if (selectedSong[43] != null)
                        message += "Energy: " + selectedSong[43] + "\n";
                    if (selectedSong[10] != null)
                        message += "Loudness: " + selectedSong[10] + "\n";
                    else if (selectedSong[45] != null)
                        message += "Loudness: " + selectedSong[45] + "\n";
                    if (selectedSong[11] != null)
                        message += "Speechiness: " + selectedSong[11] + "\n";
                    else if (selectedSong[47] != null)
                        message += "Speechiness: " + selectedSong[47] + "\n";
                    if (selectedSong[12] != null)
                        message += "Acousticness: " + selectedSong[12] + "\n";
                    else if (selectedSong[48] != null)
                        message += "Acousticness: " + selectedSong[48] + "\n";
                    if (selectedSong[13] != null)
                        message += "Instrumentalness: " + selectedSong[13] + "\n";
                    else if (selectedSong[49] != null)
                        message += "Instrumentalness: " + selectedSong[49] + "\n";
                    if (selectedSong[14] != null)
                        message += "Liveness: " + selectedSong[14] + "\n";
                    else if (selectedSong[50] != null)
                        message += "Liveness: " + selectedSong[50] + "\n";
                    if (selectedSong[17] != null)
                        message += "Duration (ms): " + selectedSong[17] + "\n";
                    else if (selectedSong[39] != null)
                        message += "Duration (ms): " + selectedSong[39] + "\n";

                    JOptionPane.showMessageDialog(ShowFavorites.this, message, "Statistics", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        buttonPanel.add(viewStatsButtonOfOneSong);
        
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public String averageStat(String initialMessage, int firstColumn, int secondColumn) {
    	double sum = 0;
    	double amount = 0;
    	String message = "";
    	
    	for (String[] favorite : favorites) {
    		// 7 42
    		if (favorite[firstColumn] != null) {
    			sum += Double.parseDouble(favorite[firstColumn].replaceAll("\"", ""));
    			amount++;
    		}
    		else if (favorite[secondColumn] != null) {
    			sum += Double.parseDouble(favorite[secondColumn].replaceAll("\"", ""));
    			amount++;
    		}
    	}
    	
    	double average = 0;
		
		try {
			average = sum / amount;
		}
		catch (ArithmeticException E) {
			average = -1;
		}
		
		if (average == -1) {
			message += "Average " + initialMessage + ": NA\n";
		}
		else {
			message += "Average " + initialMessage + ": " + average + "\n";
		}
    	
		return message;
    }
    
    public String minStat(String initialMessage, int firstColumn, int secondColumn) {
    	double min = Double.MAX_VALUE;
    	double temp = Double.MAX_VALUE;
    	String message = "";
    	
    	for (String[] favorite : favorites) {
    		if (favorite[firstColumn] != null) 
    			temp = Double.parseDouble(favorite[firstColumn].replaceAll("\"", ""));
    		else if (favorite[secondColumn] != null)
    			temp = Double.parseDouble(favorite[secondColumn].replaceAll("\"", ""));
    		
    		if (temp < min)
    			min = temp;
    	}
    	
		
		if (min == Double.MAX_VALUE) {
			message += "Minimum " + initialMessage + ": NA\n";
		}
		else {
			message += "Minimum " + initialMessage + ": " + min + "\n";
		}
    	
		return message;
    }
    
    public String maxStat(String initialMessage, int firstColumn, int secondColumn) {
    	double max = Double.MIN_VALUE;
    	double temp = Double.MIN_VALUE;
    	String message = "";
    	
    	for (String[] favorite : favorites) {
    		if (favorite[firstColumn] != null) 
    			temp = Double.parseDouble(favorite[firstColumn].replaceAll("\"", ""));
    		else if (favorite[secondColumn] != null)
    			temp = Double.parseDouble(favorite[secondColumn].replaceAll("\"", ""));
    		
    		if (temp > max)
    			max = temp;
    	}
    	
		
		if (max == Double.MIN_VALUE) {
			message += "Maximum " + initialMessage + ": NA\n";
		}
		else {
			message += "Maximum " + initialMessage + ": " + max + "\n";
		}
    	
		return message;
    }
}
