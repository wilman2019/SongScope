package Java.Screens;

import Java.TextFields.TextField;
import Java.TextFields.PasswordField;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;


public class MainScreen extends JFrame {
    

    // Variables declaration
    private JPanel mainPanel;
    private JPanel sidePanel;
    private JPanel mainScreenPanel;
    private JLabel welcomeLabel;
    private JLabel nameLabel;
    private JSeparator seperator;
    private JButton homeButton;
    private JButton searchButton;
    private JButton mapButton;
    private JButton compareButton;
    private JButton settingsButton;
    private JButton songStatsButton;
    private ImageIcon homeIcon;
    private ImageIcon searchIcon;
    private ImageIcon mapIcon;
    private ImageIcon blankIcon;
    private ImageIcon settingsIcon;
    private ImageIcon songStatsIcon;


    // Main Constructor
    public MainScreen(String email) {
        // Set Look and Feel for Buttons
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
                    e.printStackTrace();
        }

        

        // Initialize Components
        initComponents();
    }

    // Initialize All Components
    private void initComponents() {

        // Main Jframe settings (Window)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        // Main Panel (Big Panel that holds everything)
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, 1200, 800);
        add(mainPanel);

        // Sidebar Panel (Panel on left side)
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(21,170,180));
        sidePanel.setPreferredSize(new Dimension(200, 800));
        mainPanel.add(sidePanel, BorderLayout.WEST);

        // Welcome Label (Welcome message at top of sidebar)
        welcomeLabel = new JLabel("Welcome Back");
        welcomeLabel.setFont(new Font("Helvetica Neue", 0, 24));
        welcomeLabel.setForeground(new Color(255,255,255));

        // Name Label (Name of user will be changed to variabel later)
        nameLabel = new JLabel("Colin!");
        nameLabel.setFont(new Font("Helvetica Neue", 0, 24));
        nameLabel.setForeground(new Color(255,255,255));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);


        // Seperator (Line under name)
        seperator = new JSeparator();
        seperator.setPreferredSize(new Dimension(170, 1));

        // Button Group (Group of buttons that will be added to sidebar)
        ButtonGroup buttonGroup = new ButtonGroup();

        // Home button 
        homeButton = new JButton("Home");
        homeIcon = new ImageIcon("src/img/house.fill.png");
        homeButton.setIcon(homeIcon);
        homeButton.setIconTextGap(20);
        homeButton.setHorizontalAlignment(JButton.LEFT);
        homeButton.setBackground(new Color(21, 170, 180));
        homeButton.setForeground(new Color(255,255,255));
        homeButton.setFont(new Font("Helvetica Neue", 0, 18));
        homeButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        homeButton.setFocusPainted(false);
        homeButton.setModel(new JToggleButton.ToggleButtonModel()); 
        homeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!homeButton.isSelected()) {
                    homeButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!homeButton.isSelected()) {
                    homeButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        homeButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    homeButton.setBackground(new Color(10,114,121)); 
                } else {
                    homeButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Home");
            }
        });
        buttonGroup.add(homeButton); 
        sidePanel.add(homeButton);

        // Search Button
        searchButton = new JButton("Search");
        searchIcon = new ImageIcon("src/img/magnifyingglass.png");
        searchButton.setIcon(searchIcon);
        searchButton.setIconTextGap(20);
        searchButton.setHorizontalAlignment(JButton.LEFT);
        searchButton.setBackground(new Color(21, 170, 180));
        searchButton.setForeground(new Color(255,255,255));
        searchButton.setFont(new Font("Helvetica Neue", 0, 18));
        searchButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        searchButton.setFocusPainted(false);
        searchButton.setModel(new JToggleButton.ToggleButtonModel()); 
        searchButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!searchButton.isSelected()) {
                    searchButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!searchButton.isSelected()) {
                    searchButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        searchButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    searchButton.setBackground(new Color(10,114,121)); 
                } else {
                    searchButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Search");
            }
        });
        buttonGroup.add(searchButton); 
        sidePanel.add(searchButton);
        
        // Map Button
        mapButton = new JButton("Map");
        mapIcon = new ImageIcon("src/img/globe.png");
        mapButton.setIcon(mapIcon);
        mapButton.setIconTextGap(20);
        mapButton.setHorizontalAlignment(JButton.LEFT);
        mapButton.setBackground(new Color(21, 170, 180));
        mapButton.setForeground(new Color(255,255,255));
        mapButton.setFont(new Font("Helvetica Neue", 0, 18));
        mapButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        mapButton.setFocusPainted(false);
        mapButton.setModel(new JToggleButton.ToggleButtonModel()); 
        mapButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!mapButton.isSelected()) {
                    mapButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!mapButton.isSelected()) {
                    mapButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        mapButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    mapButton.setBackground(new Color(10,114,121)); 
                } else {
                    mapButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        mapButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Map");
            }
        });
        buttonGroup.add(mapButton); 
        sidePanel.add(mapButton);

        // Blank Button
        compareButton = new JButton("Compare");
        blankIcon = new ImageIcon("src/img/compare.png");
        compareButton.setIcon(blankIcon);
        compareButton.setIconTextGap(20);
        compareButton.setHorizontalAlignment(JButton.LEFT);
        compareButton.setBackground(new Color(21, 170, 180));
        compareButton.setForeground(new Color(255,255,255));
        compareButton.setFont(new Font("Helvetica Neue", 0, 18));
        compareButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        compareButton.setFocusPainted(false);
        compareButton.setModel(new JToggleButton.ToggleButtonModel()); 
        compareButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!compareButton.isSelected()) {
                    compareButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!compareButton.isSelected()) {
                    compareButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        compareButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    compareButton.setBackground(new Color(10,114,121)); 
                } else {
                    compareButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Compare");
            }
        });
        buttonGroup.add(compareButton); 
        sidePanel.add(compareButton);

        // Settings Button
        settingsButton = new JButton("Settings");
        settingsIcon = new ImageIcon("src/img/gearshape.fill.png");
        settingsButton.setIcon(settingsIcon);
        settingsButton.setIconTextGap(20);
        settingsButton.setHorizontalAlignment(JButton.LEFT);
        settingsButton.setBackground(new Color(21, 170, 180));
        settingsButton.setForeground(new Color(255,255,255));
        settingsButton.setFont(new Font("Helvetica Neue", 0, 18));
        settingsButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        settingsButton.setFocusPainted(false);
        settingsButton.setModel(new JToggleButton.ToggleButtonModel()); 
        settingsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!settingsButton.isSelected()) {
                    settingsButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!settingsButton.isSelected()) {
                    settingsButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        settingsButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    settingsButton.setBackground(new Color(10,114,121)); 
                } else {
                    settingsButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Settings");
            }
        });
        buttonGroup.add(settingsButton); 
        sidePanel.add(settingsButton);

        // Song Stats Button
        songStatsButton = new JButton("Song Stats");
        songStatsIcon = new ImageIcon("src/img/chart.bar.fill.png");
        songStatsButton.setIcon(songStatsIcon);
        songStatsButton.setIconTextGap(15);
        songStatsButton.setHorizontalAlignment(JButton.LEFT);
        songStatsButton.setBackground(new Color(21, 170, 180));
        songStatsButton.setForeground(new Color(255,255,255));
        songStatsButton.setFont(new Font("Helvetica Neue", 0, 18));
        songStatsButton.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
        songStatsButton.setFocusPainted(false);
        songStatsButton.setModel(new JToggleButton.ToggleButtonModel());
        songStatsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (!songStatsButton.isSelected()) {
                    songStatsButton.setBackground(new Color(10,114,121)); 
                }
            }
        
            public void mouseExited(MouseEvent evt) {
                if (!songStatsButton.isSelected()) {
                    songStatsButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        songStatsButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                if (evt.getStateChange() == ItemEvent.SELECTED) {
                    songStatsButton.setBackground(new Color(10,114,121)); 
                } else {
                    songStatsButton.setBackground(new Color(21, 170, 180)); 
                }
            }
        });
        songStatsButton.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Song Stats");
            }
        });
        buttonGroup.add(songStatsButton);
        sidePanel.add(songStatsButton);

        // Creates a GroupLayout for the sidePanel 
        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);

        // Sets horizontal Grouping for sidePanel
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(seperator, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(homeButton, 200,200,200)
            .addComponent(searchButton, 200,200,200)
            .addComponent(mapButton, 200,200,200)
            .addComponent(compareButton, 200,200,200)
            .addComponent(settingsButton, 200,200,200)
            .addComponent(songStatsButton, 200,200,200)
        );

        // Sets vertical Grouping for sidePanel
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(25)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seperator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100)
                .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(mapButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(compareButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(songStatsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(330)
                .addComponent(settingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        // Main Screen Panel (Panel in the middle of the screen that holds different screens)
        mainScreenPanel = new JPanel(new CardLayout());
        mainScreenPanel.setBackground(new Color(204,204,204));
        mainScreenPanel.setPreferredSize(new Dimension(1000, 800));
        mainPanel.add(mainScreenPanel, BorderLayout.CENTER);

        // Home panel
        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(204, 204, 204)); 
        
        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 204, 204)); 
        
        // Map panel
        JPanel mapPanel = new JPanel();
        mapPanel.setBackground(new Color(204, 255, 204)); 
        
        // Compare panel
        JPanel comparePanel = new JPanel();
        comparePanel.setBackground(new Color(204, 204, 255)); 
        
        // Song Stats panel
        JPanel songStatsPanel = new JPanel();
        songStatsPanel.setBackground(new Color(255, 255, 204)); 
        
        // Settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(new Color(204, 255, 255));

        // Add each JPanel to the mainScreenPanel
        mainScreenPanel.add(homePanel, "Home");
        mainScreenPanel.add(searchPanel, "Search");
        mainScreenPanel.add(mapPanel, "Map");
        mainScreenPanel.add(comparePanel, "Compare");
        mainScreenPanel.add(songStatsPanel, "Song Stats");
        mainScreenPanel.add(settingsPanel, "Settings");


        // Pack and set location of frame
        pack();
    }

}
