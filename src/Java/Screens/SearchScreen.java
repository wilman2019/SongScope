package Java.Screens;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Java.Screens.Compare.CompareSongs;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class SearchScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel picPanel;
    private JTextField searchField = new JTextField(); 
    private JButton searchButton = new JButton("Search");
    private JButton advancedSearchButton = new JButton("Advanced Search");
    private JPanel advancedSearchPanel = new JPanel();
    private JButton viewFavorites = new JButton("Favorites");
    private JButton compareButton = new JButton("Compare favorites");
    private JButton popularButton = new JButton("Most popular songs");



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchScreen frame = new SearchScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchScreen() {
        
        // Main Panel
        picPanel = new JPanel(); // Create new panel for Login section
        picPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border for panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program on close
        setBounds(100, 100, 1354, 768); // Set size of frame
        picPanel.setLayout(null);
                
        

        // Background Image
        ImageIcon imageIcon = new ImageIcon("src/img/SongScopeSearch.png");
        Image image = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picPanel.add(picLabel);
        setContentPane(picPanel); 



        // Search Bar
        searchField.setBackground(new Color(255, 255, 255, 240));
        searchField.setLayout(null);
        searchField.setFont(new Font("Monospaced", Font.PLAIN, 20));  

        // Search Bar Dimensions
        int searchWidth = picPanel.getWidth() - 600;
        int searchHeight = 50;
        int searchX = (picPanel.getWidth() - searchWidth) / 2; 
        int searchY = (picPanel.getHeight() - searchHeight) / 4;
        searchField.setBounds(searchX, searchY, searchWidth, searchHeight);
        picPanel.add(searchField);
        picPanel.setComponentZOrder(searchField, 0);

        // View favorites button
        picPanel.add(viewFavorites);
        viewFavorites.setBounds(10, 10, 100, 100);
        
        // When View favorites is pressed
        viewFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String[]> favorites = Favorites.getFavorites();

                ShowFavorites favoritesPage = new ShowFavorites(favorites);
                favoritesPage.setVisible(true);
            }
        });


        // Regular Search Button 
        int searchButtonWidth = 55;
        int searchButtonHeight = 50;
        int searchButtonX = searchX + searchWidth;
        int searchButtonY = searchY;
        searchButton.setBounds(searchButtonX, searchButtonY, searchButtonWidth, searchButtonHeight);

        picPanel.add(searchButton);
        picPanel.setComponentZOrder(searchButton, 0);

        // Search Button Action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] result = SongData.getSongData();
                    if (!searchField.getText().isEmpty())
                        result = SimpleSearching.simpleSearch(result, searchField.getText());

                final String[][] finalResult = result;

                JFrame frame = new JFrame("Search Results");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                JPanel contentPanel = new JPanel(new BorderLayout());

                JButton backButton = new JButton("Back and submit to favorites");

                contentPanel.add(backButton, BorderLayout.NORTH);

                JPanel resultPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 0.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.insets = new Insets(5, 5, 0, 5);

                ArrayList<String[]> selectedRows = new ArrayList<>();
                HashSet<String> uniqueColumn4Values = new HashSet<>();

                for (int i = 0; i < finalResult.length; i++) {
                    final int currentRow = i;
                    String column4Value = finalResult[i][4];

                    if (column4Value == null) {
                        column4Value = finalResult[i][30];
                    }

                    if (!uniqueColumn4Values.add(column4Value)) {
                        // Skip this row if the column 4 value is a duplicate
                        continue;
                    }

                    JCheckBox rowCheckbox = new JCheckBox();
                    rowCheckbox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                selectedRows.add(finalResult[currentRow]);
                            } else {
                                selectedRows.remove(finalResult[currentRow]);
                            }
                        }
                    });

                    String column2Value = finalResult[i][1];

                    if (column2Value == null) {
                        column2Value = finalResult[i][31];
                    }

                    JPanel rowPanel = new JPanel(new GridLayout(1, 3, 5, 5));
                    JLabel label1 = new JLabel(column2Value);
                    JLabel label2 = new JLabel(column4Value);

                    rowPanel.add(label1);
                    rowPanel.add(label2);
                    rowPanel.add(rowCheckbox);

                    resultPanel.add(rowPanel, gbc);
                    gbc.gridy++;

                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Favorites.addToFavorites(selectedRows);
                            frame.dispose();
                        }
                    });
                }

                JScrollPane scrollPane = new JScrollPane(resultPanel);

                contentPanel.add(scrollPane, BorderLayout.CENTER);

                frame.add(contentPanel);
                frame.setVisible(true);
            }
        });


        // Compare songs button
        picPanel.add(compareButton);
        compareButton.setBounds(400, 300, 200, 100);
        
        // Action for compare songs button
        compareButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JPanel resultPanel = new JPanel(new GridBagLayout());
        		
        		ArrayList<String[]> list = Favorites.getFavorites();
        		String[][] result = new String[list.size()][];

        		for (int i = 0; i < list.size(); i++) {
        		    result[i] = list.get(i);
        		}
        				
        		final String[][] finalResult = result;

                JFrame frame = new JFrame("Search Results");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                JPanel contentPanel = new JPanel(new BorderLayout());

                JButton compareFavorites = new JButton("Compare 2 songs");

                contentPanel.add(compareFavorites, BorderLayout.NORTH);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 0.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.insets = new Insets(5, 5, 0, 5);

                ArrayList<String[]> selectedRows = new ArrayList<>();
                HashSet<String> uniqueColumn4Values = new HashSet<>();

                for (int i = 0; i < finalResult.length; i++) {
                    final int currentRow = i;
                    String column4Value = finalResult[i][4];

                    if (column4Value == null) {
                        column4Value = finalResult[i][30];
                    }

                    if (!uniqueColumn4Values.add(column4Value)) {
                        // Skip this row if the column 4 value is a duplicate
                        continue;
                    }

                    JCheckBox rowCheckbox = new JCheckBox();
                    rowCheckbox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                selectedRows.add(finalResult[currentRow]);
                            } else {
                                selectedRows.remove(finalResult[currentRow]);
                            }
                        }
                    });

                    String column2Value = finalResult[i][1];

                    if (column2Value == null) {
                        column2Value = finalResult[i][31];
                    }

                    JPanel rowPanel = new JPanel(new GridLayout(1, 3, 5, 5));
                    JLabel label1 = new JLabel(column2Value);
                    JLabel label2 = new JLabel(column4Value);

                    rowPanel.add(label1);
                    rowPanel.add(label2);
                    rowPanel.add(rowCheckbox);

                    resultPanel.add(rowPanel, gbc);
                    gbc.gridy++;

                    compareFavorites.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	String[] args = {};
                        	if (selectedRows.size() != 2) {
                        		JOptionPane.showMessageDialog(null, "Please select 2 songs.");
                    		}
                        	else {
                        		System.out.print(selectedRows.get(0)[3]);
                        		System.out.print(selectedRows.get(0)[30]);
                        		System.out.print(selectedRows.get(1)[3]);
                        		System.out.print(selectedRows.get(1)[30]);	
                        		StoreSelected.set(selectedRows);
                        		Application.launch(CompareSongs.class, args);
                        	}
                        }
                    });
                }

                JScrollPane scrollPane = new JScrollPane(resultPanel);

                contentPanel.add(scrollPane, BorderLayout.CENTER);

                frame.add(contentPanel);
                frame.setVisible(true);
        	}
        });
        
        picPanel.setComponentZOrder(compareButton, 0);
        
        // Popular songs button
        picPanel.add(popularButton);
        popularButton.setBounds(700, 300, 200, 100);
        
        popularButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Select Country");

                String[] countries = {"Select a country", "Overall", "Argentina (AR)", "Australia (AU)", "Austria (AT)", "Belarus (BY)", "Belgium (BE)", "Bolivia (BO)", "Brazil (BR)", "Bulgaria (BG)", "Canada (CA)", "Chile (CL)", "Colombia (CO)", "Costa rica (CR)", "Czech Republic (CZ)", "Denmark (DK)", "Dominican Republic (DO)", "Ecuador (EC)", "Egypt (EG)", "El Salvador (SV)", "Estonia (EE)", "Germany (DE)", 
                		"Finland (FI)", "France (FR)", "Greece (GR)", "Guatemala (GT)", "Hong Kong (HK)", "Honduras (HN)", "Hungary (HU)", "Iceland (IS)", "India (IN)", "Indonesia (ID)", "Ireland (IS)", "Israel (IS)", "Italy (IT)",
                		"Japan (JP)", "Korea (KR)", "Kazakstan (KZ)", "Latvia (LV)", "Lithuania (LT)", "Luxenbourg (LU)", "Malaysia (MY)", "Montenegro (MX)", "Morocco (MA)", "Netherlands (NL)", "New Zealand (NZ)", "Nicaragua (NI)", "Nigeria (NG)", "Norway (NO)", 
                		"Pakistan (PK)", "Panama (PA)", "Paraguay (PY)", "Peru (PE)", "Phillipines (PH)", "Poland (PL)", "Portugal (PT)", "Romania (RO)", 
                		"Saudi Arabia (SA)", "Singapore (SG)", "Slovakia (SK)", "Spain (ES)", "Sweden (SE)", "Switzerland (CH)", "Taiwan (TW)", "Thailand (TH)", "Turkey (TR)", "Ukraine (UA)", "United Arab Emerates (AE)", "United Kingdom (GB)", "United Nations (UN)", "United States (US)", "Uruguay (UY)", "Venezuela (VE)", "Zimbabwe (ZA)"};
                JComboBox<String> countryBox = new JComboBox<>(countries);
                JTextField dateField = new JTextField();
                
                String[][] topSpotifySongs = Java.Screens.TopSongs.getTopSongs();
                
                JButton goButton = new JButton("Go");
                
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 2));
                
                JLabel countryLabel = new JLabel("Select Country: ");
                JLabel dateLabel = new JLabel("Enter date (yyyy-mm-dd) after " + topSpotifySongs[450000][7] + " and before " + topSpotifySongs[3][7]);
                
                panel.add(countryLabel);
                panel.add(countryBox);
                panel.add(dateLabel);
                panel.add(dateField);
                
                frame.add(panel, BorderLayout.NORTH);
                
                goButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedCountry = (String) countryBox.getSelectedItem();
                        if (!selectedCountry.equals("Select a country") && !dateField.getText().isEmpty()) {
                            ArrayList<String[]> results = SearchSongRankings.searchSongRankings(topSpotifySongs, selectedCountry, dateField.getText());
                            ShowSongRankings songRankingsPage = new ShowSongRankings(results);
                            songRankingsPage.setVisible(true);
                        }
                    }
                });
                frame.add(goButton, BorderLayout.SOUTH);
                
                frame.pack();
                frame.setVisible(true);
        	}
        });
        
        picPanel.setComponentZOrder(popularButton, 0);
        
        // Advanced Search Button 
        int advancedSearchWidth = 180;
        int advancedSearchHeight = 25;
        //int advancedSearchX = searchX;
        int advancedSearchX = searchX - advancedSearchWidth;
        //int advancedSearchY = searchY + searchHeight;
        int advancedSearchY = searchY;
        advancedSearchButton.setBounds(advancedSearchX, advancedSearchY, advancedSearchWidth, advancedSearchHeight);

        picPanel.add(advancedSearchButton);
        picPanel.setComponentZOrder(advancedSearchButton, 0);

        


        // Advanced Search Panel
        advancedSearchPanel.setBackground(new Color(255, 255, 255, 200));
        advancedSearchPanel.setOpaque(true);
        advancedSearchPanel.setLayout(null);
        advancedSearchPanel.setVisible(false);

        int advancedSearchPanelX = advancedSearchX + advancedSearchWidth;
        int advancedSearchPanelY = advancedSearchY;
        int advancedSearchPanelHeight = advancedSearchHeight;
        int advancedSearchPanelWidth = advancedSearchWidth;
        advancedSearchPanel.setBounds(advancedSearchPanelX, advancedSearchPanelY, advancedSearchPanelWidth, advancedSearchPanelHeight);

        // Advanced Search Panel Components

        // artist name
                // song name
				// album name
				// 8: Danceability
				// 9: Energy
				// 11: Loudness
				// 12: Speechiness
				// 13: Acousticness
				// 14: Instrumentalness
				// 15: Liveness
				// 18: Duration_ms
				// daily rank in country
                // 37: popularity

        // artist name
        JLabel artistNameField = new JLabel("Artist Name");
        artistNameField.setBounds(10, 10, 110, 25);
        advancedSearchPanel.add(artistNameField);
        JTextField artistName = new JTextField();
        artistName.setBounds(130, 10, 200, 25);
        advancedSearchPanel.add(artistName);

        // song name
        JLabel songNameField = new JLabel("Song Name");
        songNameField.setBounds(10, 40, 110, 25);
        advancedSearchPanel.add(songNameField);
        JTextField songName = new JTextField();
        songName.setBounds(130, 40, 200, 25);
        advancedSearchPanel.add(songName);

        // album name
        JLabel albumNameField = new JLabel("Album Name");
        albumNameField.setBounds(10, 70, 110, 25);
        advancedSearchPanel.add(albumNameField);
        JTextField albumName = new JTextField();
        albumName.setBounds(130, 70, 200, 25);
        advancedSearchPanel.add(albumName);

        // danceability
        JLabel danceabilityField = new JLabel("Danceability (0...1)");
        danceabilityField.setBounds(10, 100, 110, 25);
        advancedSearchPanel.add(danceabilityField);

        JTextField danceabilityMin = new JTextField();
        danceabilityMin.setBounds(130, 100, 50, 25);
        advancedSearchPanel.add(danceabilityMin);

        JTextField danceabilityMax = new JTextField();
        danceabilityMax.setBounds(190, 100, 50, 25);
        advancedSearchPanel.add(danceabilityMax);


        // energy
        JLabel energyField = new JLabel("Energy (0...1)");
        energyField.setBounds(10, 130, 110, 25);
        advancedSearchPanel.add(energyField);

        JTextField energyMin = new JTextField();
        energyMin.setBounds(130, 130, 50, 25);
        advancedSearchPanel.add(energyMin);

        JTextField energyMax = new JTextField();
        energyMax.setBounds(190, 130, 50, 25);
        advancedSearchPanel.add(energyMax);

        // loudness
        JLabel loudnessField = new JLabel("Loudness (-20...0)");
        loudnessField.setBounds(10, 160, 110, 25);
        advancedSearchPanel.add(loudnessField);

        JTextField loudnessMin = new JTextField();
        loudnessMin.setBounds(130, 160, 50, 25);
        advancedSearchPanel.add(loudnessMin);

        JTextField loudnessMax = new JTextField();
        loudnessMax.setBounds(190, 160, 50, 25);
        advancedSearchPanel.add(loudnessMax);

        // speechiness
        JLabel speechinessField = new JLabel("Speechiness (0...1)");
        speechinessField.setBounds(340, 10, 110, 25);
        advancedSearchPanel.add(speechinessField);

        JTextField speechinessMin = new JTextField();
        speechinessMin.setBounds(485, 10, 50, 25);
        advancedSearchPanel.add(speechinessMin);

        JTextField speechinessMax = new JTextField();
        speechinessMax.setBounds(545, 10, 50, 25);
        advancedSearchPanel.add(speechinessMax);

        // acousticness
        JLabel acousticnessField = new JLabel("Acousticness (0...1)");
        acousticnessField.setBounds(340, 40, 110, 25);
        advancedSearchPanel.add(acousticnessField);

        JTextField acousticnessMin = new JTextField();
        acousticnessMin.setBounds(485, 40, 50, 25);
        advancedSearchPanel.add(acousticnessMin);

        JTextField acousticnessMax = new JTextField();
        acousticnessMax.setBounds(545, 40, 50, 25);
        advancedSearchPanel.add(acousticnessMax);

        // instrumentalness
        JLabel instrumentalnessField = new JLabel("Instrumentalness (0...1)");
        instrumentalnessField.setBounds(340, 70, 140, 25);
        advancedSearchPanel.add(instrumentalnessField);

        JTextField instrumentalnessMin = new JTextField();
        instrumentalnessMin.setBounds(485, 70, 50, 25);
        advancedSearchPanel.add(instrumentalnessMin);

        JTextField instrumentalnessMax = new JTextField();
        instrumentalnessMax.setBounds(545, 70, 50, 25);
        advancedSearchPanel.add(instrumentalnessMax);

        // liveness
        JLabel livenessField = new JLabel("Liveness (0...1)");
        livenessField.setBounds(340, 100, 110, 25);
        advancedSearchPanel.add(livenessField);

        JTextField livenessMin = new JTextField();
        livenessMin.setBounds(485, 100, 50, 25);
        advancedSearchPanel.add(livenessMin);

        JTextField livenessMax = new JTextField();
        livenessMax.setBounds(545, 100, 50, 25);
        advancedSearchPanel.add(livenessMax);

        // duration
        JLabel durationField = new JLabel("Duration (ms)");
        durationField.setBounds(340, 130, 110, 25);
        advancedSearchPanel.add(durationField);

        JTextField durationMin = new JTextField();
        durationMin.setBounds(485, 130, 50, 25);
        advancedSearchPanel.add(durationMin);
        
        JTextField durationMax = new JTextField();
        durationMax.setBounds(545, 130, 50, 25);
        advancedSearchPanel.add(durationMax);

        // popularity
        JLabel popularityField = new JLabel("Popularity (0...100)");
        popularityField.setBounds(340, 160, 110, 25);
        advancedSearchPanel.add(popularityField);

        JTextField popularityMin = new JTextField();
        popularityMin.setBounds(485, 160, 50, 25);
        advancedSearchPanel.add(popularityMin);

        JTextField popularityMax = new JTextField();
        popularityMax.setBounds(545, 160, 50, 25);
        advancedSearchPanel.add(popularityMax);
        
        // Advanced search button 
        JButton search = new JButton("Search");
        search.setBounds(250, 210, 100, 25);   
        advancedSearchPanel.add(search);
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[][] result = SongData.getSongData();
                try {
                    if (!artistName.getText().isEmpty())
                        result = Searching.searchString(result, artistName.getText(), 1, 31);

                    if (!songName.getText().isEmpty())
                        result = Searching.searchString(result, songName.getText(), 3, 30);

                    if (!albumName.getText().isEmpty())
                        result = Searching.searchString(result, albumName.getText(), 4, 40);

                    if (!danceabilityMin.getText().isEmpty() && !danceabilityMax.getText().isEmpty())
                        result = Searching.searchStats(result, danceabilityMin.getText(), danceabilityMax.getText(), 7, 42);

                    if (!energyMin.getText().isEmpty() && !energyMax.getText().isEmpty())
                        result = Searching.searchStats(result, energyMin.getText(), energyMax.getText(), 8, 43);

                    if (!loudnessMin.getText().isEmpty() && !loudnessMax.getText().isEmpty())
                        result = Searching.searchStats(result, loudnessMin.getText(), loudnessMax.getText(), 10, 45);

                    if (!speechinessMin.getText().isEmpty() && !speechinessMax.getText().isEmpty())
                        result = Searching.searchStats(result, speechinessMin.getText(), speechinessMax.getText(), 11, 47);

                    if (!acousticnessMin.getText().isEmpty() && !acousticnessMax.getText().isEmpty())
                        result = Searching.searchStats(result, acousticnessMin.getText(), acousticnessMax.getText(), 12, 48);

                    if (!instrumentalnessMin.getText().isEmpty() && !instrumentalnessMax.getText().isEmpty())
                        result = Searching.searchStats(result, instrumentalnessMin.getText(), instrumentalnessMax.getText(), 13, 49);

                    if (!livenessMin.getText().isEmpty() && !livenessMax.getText().isEmpty())
                        result = Searching.searchStats(result, livenessMin.getText(), livenessMax.getText(), 14, 50);

                    if (!durationMin.getText().isEmpty() && !durationMax.getText().isEmpty())
                        result = Searching.searchStats(result, durationMin.getText(), durationMax.getText(), 17, 39);

                    if (!popularityMin.getText().isEmpty() && !popularityMax.getText().isEmpty())
                        result = Searching.searchStats(result, popularityMin.getText(), popularityMax.getText(), 37);
                } catch (NullPointerException E) {
                    System.out.println("No songs found");
                }

                final String[][] finalResult = result;

                JFrame frame = new JFrame("Search Results");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                JPanel contentPanel = new JPanel(new BorderLayout());

                JButton backButton = new JButton("Back and submit to favorites");

                contentPanel.add(backButton, BorderLayout.NORTH);

                JPanel resultPanel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.weighty = 0.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.insets = new Insets(5, 5, 0, 5);

                ArrayList<String[]> selectedRows = new ArrayList<>();
                HashSet<String> uniqueColumn4Values = new HashSet<>();

                for (int i = 0; i < finalResult.length; i++) {
                    final int currentRow = i;
                    String column4Value = finalResult[i][4];

                    if (column4Value == null) {
                        column4Value = finalResult[i][30];
                    }

                    if (!uniqueColumn4Values.add(column4Value)) {
                        // Skip this row if the column 4 value is a duplicate
                        continue;
                    }

                    JCheckBox rowCheckbox = new JCheckBox();
                    rowCheckbox.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent e) {
                            if (e.getStateChange() == ItemEvent.SELECTED) {
                                selectedRows.add(finalResult[currentRow]);
                            } else {
                                selectedRows.remove(finalResult[currentRow]);
                            }
                        }
                    });

                    String column2Value = finalResult[i][1];

                    if (column2Value == null) {
                        column2Value = finalResult[i][31];
                    }

                    JPanel rowPanel = new JPanel(new GridLayout(1, 3, 5, 5));
                    JLabel label1 = new JLabel(column2Value);
                    JLabel label2 = new JLabel(column4Value);

                    rowPanel.add(label1);
                    rowPanel.add(label2);
                    rowPanel.add(rowCheckbox);

                    resultPanel.add(rowPanel, gbc);
                    gbc.gridy++;

                    backButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Favorites.addToFavorites(selectedRows);
                            frame.dispose();
                        }
                    });
                }

                JScrollPane scrollPane = new JScrollPane(resultPanel);

                contentPanel.add(scrollPane, BorderLayout.CENTER);

                frame.add(contentPanel);
                frame.setVisible(true);
            }
        });
        
        

        picPanel.add(advancedSearchPanel);
        picPanel.setComponentZOrder(advancedSearchPanel, 0);



        // Advanced Search Button Action
        advancedSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (advancedSearchPanel.isVisible()) {
                    advancedSearchPanel.setVisible(false);
                } else {
                    advancedSearchPanel.setVisible(true);
                }
            }
        });







        // Allows for resizing
        picPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                // For background image
                Image newImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                picLabel.setIcon(new ImageIcon(newImage));
                picLabel.setBounds(0, 0, getWidth(), getHeight());

                // For search bar
                int searchWidth = picPanel.getWidth() - 600;  
                int searchHeight = 50;  
                int x = (picPanel.getWidth() - searchWidth) / 2;
                int y = (picPanel.getHeight() - searchHeight) / 4;
                searchField.setBounds(x, y, searchWidth, searchHeight);

                // For regular search button
                searchButton.setBounds(x + searchWidth, y, 55, 50);
                
                // For advanced search button
                advancedSearchButton.setBounds(x, y + searchHeight, 180, 25);

                // For advanced search panel
                advancedSearchPanel.setBounds(x + 180, y + searchHeight, 613, 250);
            }
        });
        
	}
}


