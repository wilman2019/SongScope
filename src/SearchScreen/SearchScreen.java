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
        ImageIcon imageIcon = new ImageIcon("SongScope2.jpg");
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
        int advancedSearchWidth = 200;
        int advancedSearchHeight = 25;
        int advancedSearchX = searchX;
        int advancedSearchY = searchY + searchHeight;
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
                advancedSearchButton.setBounds(x, y + searchHeight, 200, 25);

                // For advanced search panel
                advancedSearchPanel.setBounds(x + 200, y + searchHeight, 550, 200);

            }
        });
        
	}
}

