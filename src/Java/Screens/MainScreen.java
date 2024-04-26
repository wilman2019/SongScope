package Java.Screens;

import Java.TextFields.SearchField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;

import Java.Database.DatabaseManager;




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
    private Connection connection;
    private String userName;
    private JPanel advancedSearchPanel;
    private SearchField searchField;


    // Main Constructor
    public MainScreen(String email) {
        // Set Look and Feel for Buttons
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
                    e.printStackTrace();
        }

        // Get User Name
        connection = DatabaseManager.getConnection();
        userName = getUserName(email);


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
        nameLabel = new JLabel(userName + "!");
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

        // Compare Button
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

        //#region Creates a GroupLayout for the sidePanel 
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
        //#endregion




        //#region Main Screen Panel (Panel in the middle of the screen that holds different screens)
        mainScreenPanel = new JPanel(new CardLayout());
        mainScreenPanel.setBackground(new Color(204,204,204));
        mainScreenPanel.setPreferredSize(new Dimension(1000, 800));
        mainPanel.add(mainScreenPanel, BorderLayout.CENTER);
        //#endregion

        //#region Home panel
        JPanel homePanel = new JPanel();
        homePanel.setBackground(new Color(204, 204, 204)); 

        DefaultTableModel newmodel = new DefaultTableModel(new Object[]{"playlist name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };;










        //#region Create GroupLayout for homePanel
        GroupLayout homeLayout = new GroupLayout(homePanel);
        homePanel.setLayout(homeLayout);

        // Horizontal group
        homeLayout.setHorizontalGroup(
            homeLayout.createSequentialGroup()
                .addGap(100)
                .addGap(400)
        );

        // Vertical group
        homeLayout.setVerticalGroup(
            homeLayout.createSequentialGroup()
                .addGap(200)
                .addGap(400)
        );





        //#endregion
        
        //#region Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 204, 204));



        //#region Create advanced search menu
        advancedSearchPanel = new JPanel();
        advancedSearchPanel.setBorder(new RoundedBorder(30));
        advancedSearchPanel.setOpaque(false);
        advancedSearchPanel.setPreferredSize(new Dimension(600, 200));
        advancedSearchPanel.setVisible(false);

        // artist name
        JLabel artistNameField = new JLabel("Artist Name");
        JTextField artistName = new JTextField();


        // song name
        JLabel songNameField = new JLabel("Song Name");
        JTextField songName = new JTextField();


        // album name
        JLabel albumNameField = new JLabel("Album Name");
        JTextField albumName = new JTextField();


        // danceability
        JLabel danceabilityField = new JLabel("Danceability (0...1)");
        JTextField danceabilityMin = new JTextField();
        JTextField danceabilityMax = new JTextField();



        // energy
        JLabel energyField = new JLabel("Energy (0...1)");
        JTextField energyMin = new JTextField();
        JTextField energyMax = new JTextField();


        // loudness
        JLabel loudnessField = new JLabel("Loudness (-20...0)");
        JTextField loudnessMin = new JTextField();
        JTextField loudnessMax = new JTextField();


        // speechiness
        JLabel speechinessField = new JLabel("Speechiness (0...1)");
        JTextField speechinessMin = new JTextField();
        JTextField speechinessMax = new JTextField();


        // acousticness
        JLabel acousticnessField = new JLabel("Acousticness (0...1)");
        JTextField acousticnessMin = new JTextField();
        JTextField acousticnessMax = new JTextField();


        // instrumentalness
        JLabel instrumentalnessField = new JLabel("Instrumentalness (0...1)");
        JTextField instrumentalnessMin = new JTextField();
        JTextField instrumentalnessMax = new JTextField();


        // liveness
        JLabel livenessField = new JLabel("Liveness (0...1)");
        JTextField livenessMin = new JTextField();
        JTextField livenessMax = new JTextField();


        // duration
        JLabel durationField = new JLabel("Duration (ms)");
        JTextField durationMin = new JTextField();
        JTextField durationMax = new JTextField();


        // popularity
        JLabel popularityField = new JLabel("Popularity (0...100)");
        JTextField popularityMin = new JTextField();
        JTextField popularityMax = new JTextField();
        //#endregion

        //#region Create GroupLayout for advancedSearchPanel
        GroupLayout advancedSearchLayout = new GroupLayout(advancedSearchPanel);
        advancedSearchPanel.setLayout(advancedSearchLayout);

        // Horizontal group
        advancedSearchLayout.setHorizontalGroup(
            advancedSearchLayout.createSequentialGroup()
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(artistNameField)
                    .addComponent(songNameField)
                    .addComponent(albumNameField)
                    .addComponent(danceabilityField)
                    .addComponent(energyField)
                    .addComponent(loudnessField)
                    .addComponent(speechinessField)
                    .addComponent(acousticnessField)
                    .addComponent(instrumentalnessField)
                    .addComponent(livenessField)
                    .addComponent(durationField)
                    .addComponent(popularityField))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(artistName)
                    .addComponent(songName)
                    .addComponent(albumName)
                    .addComponent(danceabilityMin)
                    .addComponent(energyMin)
                    .addComponent(loudnessMin)
                    .addComponent(speechinessMin)
                    .addComponent(acousticnessMin)
                    .addComponent(instrumentalnessMin)
                    .addComponent(livenessMin)
                    .addComponent(durationMin)
                    .addComponent(popularityMin))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(danceabilityMax)
                    .addComponent(energyMax)
                    .addComponent(loudnessMax)
                    .addComponent(speechinessMax)
                    .addComponent(acousticnessMax)
                    .addComponent(instrumentalnessMax)
                    .addComponent(livenessMax)
                    .addComponent(durationMax)
                    .addComponent(popularityMax))
        );

        // Vertical group
        advancedSearchLayout.setVerticalGroup(
            advancedSearchLayout.createSequentialGroup()
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(artistNameField)
                    .addComponent(artistName))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(songNameField)
                    .addComponent(songName))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(albumNameField)
                    .addComponent(albumName))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(danceabilityField)
                    .addComponent(danceabilityMin)
                    .addComponent(danceabilityMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(energyField)
                    .addComponent(energyMin)
                    .addComponent(energyMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loudnessField)
                    .addComponent(loudnessMin)
                    .addComponent(loudnessMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(speechinessField)
                    .addComponent(speechinessMin)
                    .addComponent(speechinessMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(acousticnessField)
                    .addComponent(acousticnessMin)
                    .addComponent(acousticnessMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(instrumentalnessField)
                    .addComponent(instrumentalnessMin)
                    .addComponent(instrumentalnessMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(livenessField)
                    .addComponent(livenessMin)
                    .addComponent(livenessMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(durationField)
                    .addComponent(durationMin)
                    .addComponent(durationMax))
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(popularityField)
                    .addComponent(popularityMin)
                    .addComponent(popularityMax))
        );
        //#endregion

        //#region JTable Settings
        // Create DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Song", "Artist", "Album", "Duration"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };;

        // Create JTable
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        table.setFillsViewportHeight(true);

        // Set column widths and make them non-resizable
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(250); // Song
        columnModel.getColumn(0).setResizable(false);
        columnModel.getColumn(1).setPreferredWidth(250); // Artist
        columnModel.getColumn(1).setResizable(false);
        columnModel.getColumn(2).setPreferredWidth(200); // Album
        columnModel.getColumn(2).setResizable(false);
        columnModel.getColumn(3).setPreferredWidth(100); // Duration
        columnModel.getColumn(3).setResizable(false);

        // Create JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        //#endregion


        // Search Field 
        searchField = new SearchField(advancedSearchPanel);
        searchField.setPreferredSize(new Dimension(600, 30));
        searchField.setShowAndHide(true);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Check status of advancedSearchPanel
                if (!advancedSearchPanel.isVisible()) {
                    // if closed, search for song or artist or album using searchField.getText() and display results in table
                    normalSearch(model);
                    return;
                } 

                searchField.getText();
                advancedSearch(model);

            }
        });

        //#region Create GroupLayout for searchPanel
        GroupLayout searchLayout = new GroupLayout(searchPanel); 
        searchPanel.setLayout(searchLayout); 

        searchLayout.setHorizontalGroup(
            searchLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(searchLayout.createSequentialGroup()
                    .addGap(200)
                    .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(200))
                .addGroup(searchLayout.createSequentialGroup()
                    .addGap(200)
                    .addComponent(advancedSearchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(200))
                .addGroup(searchLayout.createSequentialGroup()
                    .addGap(100)
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(100))
        );

        searchLayout.setVerticalGroup(
            searchLayout.createSequentialGroup()
                .addGap(50)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10) // Adjust this value to add more space between the searchField and the advancedSearchPanel
                .addComponent(advancedSearchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        //#endregion

        //#endregion


        
        




        
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



    // Get user id
    public String getUserName(String email) {
        String query = "SELECT name FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = DatabaseManager.query(stmt);
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }






    public class RoundedBorder extends AbstractBorder {
        private int radius;
    
        RoundedBorder(int radius) {
            this.radius = radius;
        }
    
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(5, 15, 5, 30);
        }
    
        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.right = insets.top = insets.bottom = this.radius;
            return insets;
        }
    }

    public void normalSearch(DefaultTableModel model) {
        // search for song or artist or album using searchField.getText() and display results in table
        searchField.getText();

        try {
            String sql = "SELECT s.song_name, s.song_artist, s.duration, a.album_name "
                        + "FROM song as s "
                        + "JOIN album as a ON s.album_id = a.album_id "
                        + "WHERE s.song_name LIKE ? OR s.song_artist LIKE ? OR a.album_name LIKE ?";                                                
                                    
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + searchField.getText() + "%");
            stmt.setString(2, "%" + searchField.getText() + "%");
            stmt.setString(3, "%" + searchField.getText() + "%");
            ResultSet rs = DatabaseManager.query(stmt);

            model.setRowCount(0);
            while (rs.next()) {
                long durationMs = rs.getLong("duration");
                long minutes = TimeUnit.MILLISECONDS.toMinutes(durationMs);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(durationMs) % 60;
                String durationStr = String.format("%02d:%02d", minutes, seconds);

                model.addRow(new Object[]{rs.getString("song_name"), rs.getString("song_artist"), rs.getString("album_name"), durationStr});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void advancedSearch(DefaultTableModel model) {


        String sql = "SELECT * FROM song_characteristics as info, song as s WHERE info.song_id = s.song_id";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = DatabaseManager.query(stmt);
            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("danceability"), rs.getString("energy"), rs.getString("loudness"), rs.getString("speechiness"), rs.getString("acousticness"), rs.getString("instrumentalness"), rs.getString("liveness"), rs.getString("duration")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MainScreen test = new MainScreen(null);
        test.setVisible(true);
        test.setLocationRelativeTo(null);
        
        
    }
}
