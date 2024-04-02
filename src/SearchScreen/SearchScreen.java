package SearchScreen;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import FirstScreen.FirstScreen;
import SignupScreen.HandleSignup;
import SignupScreen.HidePassword;
import SignupScreen.SignupScreen;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.JButton;

public class SearchScreen extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel picPanel;
    private JTextField searchField = new JTextField(); 
    private JButton searchButton = new JButton("Search");
    private JButton advancedSearchButton = new JButton("Advanced Search");
    private JPanel advancedSearchPanel = new JPanel();



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
        ImageIcon imageIcon = new ImageIcon("SongScopeSearch.png");
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
                JOptionPane.showMessageDialog(null, "Searching...");
            }
        });




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
        artistNameField.setBounds(10, 10, 100, 25);
        advancedSearchPanel.add(artistNameField);
        JTextField artistName = new JTextField();
        artistName.setBounds(120, 10, 200, 25);
        advancedSearchPanel.add(artistName);

        // song name
        JLabel songNameField = new JLabel("Song Name");
        songNameField.setBounds(10, 40, 100, 25);
        advancedSearchPanel.add(songNameField);
        JTextField songName = new JTextField();
        songName.setBounds(120, 40, 200, 25);
        advancedSearchPanel.add(songName);

        // album name
        JLabel albumNameField = new JLabel("Album Name");
        albumNameField.setBounds(10, 70, 100, 25);
        advancedSearchPanel.add(albumNameField);
        JTextField albumName = new JTextField();
        albumName.setBounds(120, 70, 200, 25);
        advancedSearchPanel.add(albumName);

        // danceability
        JLabel danceabilityField = new JLabel("Danceability");
        danceabilityField.setBounds(10, 100, 100, 25);
        advancedSearchPanel.add(danceabilityField);

        JTextField danceabilityMin = new JTextField();
        danceabilityMin.setBounds(120, 100, 50, 25);
        advancedSearchPanel.add(danceabilityMin);

        JTextField danceabilityMax = new JTextField();
        danceabilityMax.setBounds(180, 100, 50, 25);
        advancedSearchPanel.add(danceabilityMax);


        // energy
        JLabel energyField = new JLabel("Energy");
        energyField.setBounds(10, 130, 100, 25);
        advancedSearchPanel.add(energyField);

        JTextField energyMin = new JTextField();
        energyMin.setBounds(120, 130, 50, 25);
        advancedSearchPanel.add(energyMin);

        JTextField energyMax = new JTextField();
        energyMax.setBounds(180, 130, 50, 25);
        advancedSearchPanel.add(energyMax);

        // loudness
        JLabel loudnessField = new JLabel("Loudness");
        loudnessField.setBounds(10, 160, 100, 25);
        advancedSearchPanel.add(loudnessField);

        JTextField loudnessMin = new JTextField();
        loudnessMin.setBounds(120, 160, 50, 25);
        advancedSearchPanel.add(loudnessMin);

        JTextField loudnessMax = new JTextField();
        loudnessMax.setBounds(180, 160, 50, 25);
        advancedSearchPanel.add(loudnessMax);

        // speechiness
        JLabel speechinessField = new JLabel("Speechiness");
        speechinessField.setBounds(340, 10, 100, 25);
        advancedSearchPanel.add(speechinessField);

        JTextField speechinessMin = new JTextField();
        speechinessMin.setBounds(450, 10, 50, 25);
        advancedSearchPanel.add(speechinessMin);

        JTextField speechinessMax = new JTextField();
        speechinessMax.setBounds(510, 10, 50, 25);
        advancedSearchPanel.add(speechinessMax);

        // acousticness
        JLabel acousticnessField = new JLabel("Acousticness");
        acousticnessField.setBounds(340, 40, 100, 25);
        advancedSearchPanel.add(acousticnessField);

        JTextField acousticnessMin = new JTextField();
        acousticnessMin.setBounds(450, 40, 50, 25);
        advancedSearchPanel.add(acousticnessMin);

        JTextField acousticnessMax = new JTextField();
        acousticnessMax.setBounds(510, 40, 50, 25);
        advancedSearchPanel.add(acousticnessMax);

        // instrumentalness
        JLabel instrumentalnessField = new JLabel("Instrumentalness");
        instrumentalnessField.setBounds(340, 70, 100, 25);
        advancedSearchPanel.add(instrumentalnessField);

        JTextField instrumentalnessMin = new JTextField();
        instrumentalnessMin.setBounds(450, 70, 50, 25);
        advancedSearchPanel.add(instrumentalnessMin);

        JTextField instrumentalnessMax = new JTextField();
        instrumentalnessMax.setBounds(510, 70, 50, 25);
        advancedSearchPanel.add(instrumentalnessMax);

        // liveness
        JLabel livenessField = new JLabel("Liveness");
        livenessField.setBounds(340, 100, 100, 25);
        advancedSearchPanel.add(livenessField);

        JTextField livenessMin = new JTextField();
        livenessMin.setBounds(450, 100, 50, 25);
        advancedSearchPanel.add(livenessMin);

        JTextField livenessMax = new JTextField();
        livenessMax.setBounds(510, 100, 50, 25);
        advancedSearchPanel.add(livenessMax);

        // duration
        JLabel durationField = new JLabel("Duration");
        durationField.setBounds(340, 130, 100, 25);
        advancedSearchPanel.add(durationField);

        JTextField durationMin = new JTextField();
        durationMin.setBounds(450, 130, 50, 25);
        advancedSearchPanel.add(durationMin);
        
        JTextField durationMax = new JTextField();
        durationMax.setBounds(510, 130, 50, 25);
        advancedSearchPanel.add(durationMax);

        // popularity
        JLabel popularityField = new JLabel("Popularity");
        popularityField.setBounds(340, 160, 100, 25);
        advancedSearchPanel.add(popularityField);

        JTextField popularityMin = new JTextField();
        popularityMin.setBounds(450, 160, 50, 25);
        advancedSearchPanel.add(popularityMin);

        JTextField popularityMax = new JTextField();
        popularityMax.setBounds(510, 160, 50, 25);
        advancedSearchPanel.add(popularityMax);

        


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
                advancedSearchPanel.setBounds(x + 180, y + searchHeight, 571, 200);

            }
        });
        
	}
}

