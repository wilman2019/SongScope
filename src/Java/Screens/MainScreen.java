package Java.Screens;

import Java.TextFields.SearchField;
import javafx.scene.control.Tab;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;

import Java.Database.DatabaseManager;
import Java.Tables.PanelAction;
import Java.Tables.TableActionCellEditor;
import Java.Tables.TableActionCellRender;
import Java.Tables.TableActionEvent;




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
    private JButton compareButton;
    private JButton settingsButton;
    private JButton songStatsButton;
    private ImageIcon homeIcon;
    private ImageIcon searchIcon;
    private ImageIcon blankIcon;
    private ImageIcon settingsIcon;
    private ImageIcon songStatsIcon;
    private Connection connection;
    private String userName;
    private JPanel advancedSearchPanel;
    private SearchField searchField;
    private int user_id;
    private DefaultTableModel homePlaylistModel;
    private JTextField artistName;
    private JTextField songName;
    private JTextField albumName;
    private JTextField danceabilityMin;
    private JTextField danceabilityMax;
    private JTextField energyMin;
    private JTextField energyMax;
    private JTextField loudnessMin;
    private JTextField loudnessMax;
    private JTextField speechinessMin;
    private JTextField speechinessMax;
    private JTextField acousticnessMin;
    private JTextField acousticnessMax;
    private JTextField instrumentalnessMin;
    private JTextField instrumentalnessMax;
    private JTextField livenessMin;
    private JTextField livenessMax;
    private JTextField durationMin;
    private JTextField durationMax;
    private JTextField popularityMin;
    private JTextField popularityMax;
    private DefaultTableModel searchModel;
    private JLabel artistNameField;
    private JLabel songNameField;
    private JLabel albumNameField;
    private JLabel danceabilityField;
    private JLabel energyField;
    private JLabel loudnessField;
    private JLabel speechinessField;
    private JLabel acousticnessField;
    private JLabel instrumentalnessField;
    private JLabel livenessField;
    private JLabel durationField;
    private JLabel popularityField;
    private JPanel searchPanel;
    private JTable searchTable;
    private TableColumnModel searchColumnModel;
    private JScrollPane searchScrollPane;
    private JButton logoutButton;



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
        user_id = getUserId(email);


        // Initialize Components
        initComponents();

        homePlaylistModel.setRowCount(0);
        getPlaylists(homePlaylistModel);

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void playlsitAdd(int row) {
                String[] playlists = returnUserPlaylists();

                // display an option pane with the playlists
                if (playlists.length == 0) {
                    JOptionPane.showMessageDialog(null, "You have no playlists to add the song to", "No Playlists", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String playlist = (String) JOptionPane.showInputDialog(null, "Select a playlist to add the song to", "Add to Playlist", JOptionPane.QUESTION_MESSAGE, null, playlists, playlists[0]);
                if (playlist == null) {
                    // user pressed cancel
                    return;
                }

                // get the playlist_id
                int playlist_id = getPlaylistId(playlist, user_id);
                System.out.println(playlist_id);

                // get the song_id
                int song_id = getSongId((String) searchModel.getValueAt(row, 0), (String) searchModel.getValueAt(row, 1), (String) searchModel.getValueAt(row, 2));
                System.out.println(song_id);


                // check if the song is already in the playlist
                if (songInPlaylist(song_id, playlist_id)) {
                    JOptionPane.showMessageDialog(null, "The song is already in the playlist", "Song Already in Playlist", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // // add the song to the playlist
                addSongToPlaylist(song_id, playlist_id);


            }
        
        };

        searchTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        searchTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));

    }

    // Initialize All Components
    private void initComponents() {

        // Main Jframe settings (Window)
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SongScope");
        setResizable(false);

        // Main Panel (Big Panel that holds everything)
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(0, 0, 1200, 800);
        add(mainPanel);

        //#region Sidebar Panel (Panel on left side)
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

        //#region Button Group (Group of buttons that will be added to sidebar)
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
            public void actionPerformed(ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Home");
                homePlaylistModel.setRowCount(0);
                getPlaylists(homePlaylistModel);

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
            public void actionPerformed(ActionEvent evt) {
                CardLayout cardLayout = (CardLayout) mainScreenPanel.getLayout();
                cardLayout.show(mainScreenPanel, "Search");
            }
        });
        buttonGroup.add(searchButton); 
        sidePanel.add(searchButton);
        

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
            public void actionPerformed(ActionEvent evt) {
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
        //#endregion

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
            .addComponent(compareButton, 200,200,200)
            .addComponent(settingsButton, 200,200,200)
            .addComponent(songStatsButton, 200,200,200)
        );

        // Sets vertical Grouping for sidePanel
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(25)
                .addComponent(welcomeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(seperator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
                .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addComponent(compareButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addComponent(songStatsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(400)
                .addComponent(settingsButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        //#endregion
        
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

        homePlaylistModel = new DefaultTableModel(new Object[]{"playlist name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        //#region JTable Settings
        JTable homePlaylistTable = new JTable(homePlaylistModel);
        homePlaylistTable.setPreferredScrollableViewportSize(new Dimension(350, 200));
        homePlaylistTable.setFillsViewportHeight(true);
        homePlaylistTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel homeColumnModel = homePlaylistTable.getColumnModel();
        homeColumnModel.getColumn(0).setPreferredWidth(350);
        homeColumnModel.getColumn(0).setResizable(false);
        

        JScrollPane homeScrollPane = new JScrollPane(homePlaylistTable);
        homeScrollPane.setPreferredSize(new Dimension(350, 200));//#endregion


        //#region Create GroupLayout for homePanel
        GroupLayout homeLayout = new GroupLayout(homePanel);
        homePanel.setLayout(homeLayout);

        // Horizontal group
        homeLayout.setHorizontalGroup(
            homeLayout.createSequentialGroup()
                .addGap(600)
                .addComponent(homeScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
        );

        // Vertical group
        homeLayout.setVerticalGroup(
            homeLayout.createSequentialGroup()
                .addGap(100)
                .addComponent(homeScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(400)
        );//#endregion

        //#endregion
        
        //#region Search panel
        searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 204, 204));



        //#region Create advanced search menu
        advancedSearchPanel = new JPanel();
        advancedSearchPanel.setBorder(new RoundedBorder(30));
        advancedSearchPanel.setOpaque(false);
        advancedSearchPanel.setPreferredSize(new Dimension(600, 200));
        advancedSearchPanel.setVisible(false);

        // artist name
        artistNameField = new JLabel("Artist Name");
        artistName = new JTextField();
        artistName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // song name
        songNameField = new JLabel("Song Name");
        songName = new JTextField();
        songName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // album name
        albumNameField = new JLabel("Album Name");
        albumName = new JTextField();
        albumName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // danceability
        danceabilityField = new JLabel("Danceability (0...1)");
        danceabilityMin = new JTextField();
        danceabilityMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        danceabilityMax = new JTextField();
        danceabilityMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // energy
        energyField = new JLabel("Energy (0...1)");
        energyMin = new JTextField();
        energyMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        energyMax = new JTextField();
        energyMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        
        // loudness
        loudnessField = new JLabel("Loudness (-20...0)");
        loudnessMin = new JTextField();
        loudnessMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        loudnessMax = new JTextField();
        loudnessMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // speechiness
        speechinessField = new JLabel("Speechiness (0...1)");
        speechinessMin = new JTextField();
        speechinessMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        speechinessMax = new JTextField();
        speechinessMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // acousticness
        acousticnessField = new JLabel("Acousticness (0...1)");
        acousticnessMin = new JTextField();
        acousticnessMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        acousticnessMax = new JTextField();
        acousticnessMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // instrumentalness
        instrumentalnessField = new JLabel("Instrumentalness (0...1)");
        instrumentalnessMin = new JTextField();
        instrumentalnessMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        instrumentalnessMax = new JTextField();
        instrumentalnessMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // liveness
        livenessField = new JLabel("Liveness (0...1)");
        livenessMin = new JTextField();
        livenessMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        livenessMax = new JTextField();
        livenessMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // duration
        durationField = new JLabel("Duration (seconds)");
        durationMin = new JTextField();
        durationMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        durationMax = new JTextField();
        durationMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });

        // popularity
        popularityField = new JLabel("Popularity (0...100)");
        popularityMin = new JTextField();
        popularityMin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });
        popularityMax = new JTextField();
        popularityMax.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                advancedSearch(searchModel);
            }
        });//#endregion

        //#region Create GroupLayout for advancedSearchPanel
        GroupLayout advancedSearchLayout = new GroupLayout(advancedSearchPanel);
        advancedSearchPanel.setLayout(advancedSearchLayout);

        // Horizontal group
        advancedSearchLayout.setHorizontalGroup(
            advancedSearchLayout.createSequentialGroup()
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(artistNameField)
                    .addComponent(songNameField)
                    .addComponent(albumNameField)
                    .addComponent(danceabilityField)
                    .addComponent(energyField)
                    .addComponent(loudnessField))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(artistName, 150, 150, 150))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(songName, 150, 150, 150))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(albumName, 150, 150, 150))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(danceabilityMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(danceabilityMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(energyMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(energyMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(loudnessMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(loudnessMax, 50, 50, 50))
                )
                .addGap(30)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(speechinessField)
                .addComponent(acousticnessField)
                .addComponent(instrumentalnessField)
                .addComponent(livenessField)
                .addComponent(durationField)
                .addComponent(popularityField))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(speechinessMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(speechinessMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(acousticnessMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(acousticnessMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(instrumentalnessMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(instrumentalnessMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(livenessMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(livenessMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(durationMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(durationMax, 50, 50, 50))
                    .addGroup(advancedSearchLayout.createSequentialGroup()
                        .addComponent(popularityMin, 50, 50, 50)
                        .addGap(10)
                        .addComponent(popularityMax, 50, 50, 50))
                )
        );
        

        // Vertical group
        advancedSearchLayout.setVerticalGroup(
            advancedSearchLayout.createSequentialGroup()
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(artistNameField)
                    .addComponent(artistName)
                    .addComponent(speechinessField)
                    .addComponent(speechinessMin)
                    .addComponent(speechinessMax))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(songNameField)
                    .addComponent(songName)
                    .addComponent(acousticnessField)
                    .addComponent(acousticnessMin)
                    .addComponent(acousticnessMax))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(albumNameField)
                    .addComponent(albumName)
                    .addComponent(instrumentalnessField)
                    .addComponent(instrumentalnessMin)
                    .addComponent(instrumentalnessMax))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(danceabilityField)
                    .addComponent(danceabilityMin)
                    .addComponent(danceabilityMax)
                    .addComponent(livenessField)
                    .addComponent(livenessMin)
                    .addComponent(livenessMax))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(energyField)
                    .addComponent(energyMin)
                    .addComponent(energyMax)
                    .addComponent(durationField)
                    .addComponent(durationMin)
                    .addComponent(durationMax))
                .addGap(10)
                .addGroup(advancedSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(loudnessField)
                    .addComponent(loudnessMin)
                    .addComponent(loudnessMax)
                    .addComponent(popularityField)
                    .addComponent(popularityMin)
                    .addComponent(popularityMax))
        );
        //#endregion

        //#region JTable Settings
        // Create DefaultTableModel
        // Create JTable
        searchTable = new JTable();
        searchTable.setPreferredScrollableViewportSize(new Dimension(900, 400));
        searchTable.setFillsViewportHeight(true);
        searchTable.getTableHeader().setReorderingAllowed(false);
        searchTable.setSelectionBackground(new Color(21, 170, 180));
        searchTable.setRowHeight(20);


        searchModel = new DefaultTableModel(
            new Object[]{"Song", "Artist", "Album", "Duration", "Add to playlist"}, 0) 
            
            
            {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
        };;

        searchTable.setModel(searchModel);

        
        // Set column widths and make them non-resizable
        searchColumnModel = searchTable.getColumnModel();
        searchColumnModel.getColumn(0).setPreferredWidth(225); // Song
        searchColumnModel.getColumn(0).setResizable(false);
        searchColumnModel.getColumn(1).setPreferredWidth(225); // Artist
        searchColumnModel.getColumn(1).setResizable(false);
        searchColumnModel.getColumn(2).setPreferredWidth(200); // Album
        searchColumnModel.getColumn(2).setResizable(false);
        searchColumnModel.getColumn(3).setPreferredWidth(50); // Duration
        searchColumnModel.getColumn(3).setResizable(false);

        // Create JScrollPane
        searchScrollPane = new JScrollPane(searchTable);
        searchScrollPane.setPreferredSize(new Dimension(900, 400));
        //#endregion

        //#region Search Field 
        searchField = new SearchField(advancedSearchPanel);
        searchField.setPreferredSize(new Dimension(600, 30));
        searchField.setShowAndHide(true);
        searchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Check status of advancedSearchPanel
                if (!advancedSearchPanel.isVisible()) {
                    // if closed, search for song or artist or album using searchField.getText() and display results in table
                    normalSearch(searchModel);
                    return;
                } 
                advancedSearch(searchModel);

            }
        }); //#endregion

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
                    .addGap(50)
                    .addComponent(searchScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(50))
        );

        searchLayout.setVerticalGroup(
            searchLayout.createSequentialGroup()
                .addGap(50)
                .addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(10) 
                .addComponent(advancedSearchPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
                .addComponent(searchScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        //#endregion

        //#endregion

        //#region Compare panel
        JPanel comparePanel = new JPanel();
        comparePanel.setBackground(new Color(204, 204, 255)); 
        //#endregion
        
        //#region Song Stats panel
        JPanel songStatsPanel = new JPanel();
        songStatsPanel.setBackground(new Color(255, 255, 204)); 
        //#endregion

        //#region Settings panel
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(new Color(204, 255, 255));

        // Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(21, 170, 180));
        logoutButton.setForeground(new Color(255,255,255));
        logoutButton.setFont(new Font("Helvetica Neue", 0, 18));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                LoginScreen loginScreen = new LoginScreen();
                loginScreen.setVisible(true);
                loginScreen.setLocationRelativeTo(null);
                dispose();
            }
        });

        // Create GroupLayout for settingsPanel
        GroupLayout settingsLayout = new GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsLayout);

        // Horizontal group
        settingsLayout.setHorizontalGroup(
            settingsLayout.createSequentialGroup()
                .addGap(450)
                .addComponent(logoutButton, 100,100,100)
                .addGap(450)
        );

        // Vertical group
        settingsLayout.setVerticalGroup(
            settingsLayout.createSequentialGroup()
                .addGap(400)
                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(400)
        );
        //#endregion


        // Add each JPanel to the mainScreenPanel
        mainScreenPanel.add(homePanel, "Home");
        mainScreenPanel.add(searchPanel, "Search");
        mainScreenPanel.add(comparePanel, "Compare");
        mainScreenPanel.add(songStatsPanel, "Song Stats");
        mainScreenPanel.add(settingsPanel, "Settings");


        // Pack and set location of frame
        pack();
    }



    // Get user name
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

    // get user id
    public int getUserId(String email) {
        String query = "SELECT user_id FROM user WHERE email = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = DatabaseManager.query(stmt);
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

        // get all fields from advanced search panel (not sure if they can be null)
        // search in song characteristics table and get song id
        // use the song id to get song info from song table
        String artist = artistName.getText().isEmpty() ? "" : artistName.getText();
        String song = songName.getText().isEmpty() ? "" : songName.getText();
        String album = albumName.getText().isEmpty() ? "" : albumName.getText();
        double danceabilityLow = danceabilityMin.getText().isEmpty() ? 0.0 : Double.parseDouble(danceabilityMin.getText());
        double danceabilityHigh = danceabilityMax.getText().isEmpty() ? 1.0 : Double.parseDouble(danceabilityMax.getText());
        double energyLow = energyMin.getText().isEmpty() ? 0.0 : Double.parseDouble(energyMin.getText());
        double energyHigh = energyMax.getText().isEmpty() ? 1.0 : Double.parseDouble(energyMax.getText());
        double loudnessLow = loudnessMin.getText().isEmpty() ? -20.0 : Double.parseDouble(loudnessMin.getText());
        double loudnessHigh = loudnessMax.getText().isEmpty() ? 0.0 : Double.parseDouble(loudnessMax.getText());
        double speechinessLow = speechinessMin.getText().isEmpty() ? 0.0 : Double.parseDouble(speechinessMin.getText());
        double speechinessHigh = speechinessMax.getText().isEmpty() ? 1.0 : Double.parseDouble(speechinessMax.getText());
        double acousticnessLow = acousticnessMin.getText().isEmpty() ? 0.0 : Double.parseDouble(acousticnessMin.getText());
        double acousticnessHigh = acousticnessMax.getText().isEmpty() ? 1.0 : Double.parseDouble(acousticnessMax.getText());
        double instrumentalnessLow = instrumentalnessMin.getText().isEmpty() ? 0.0 : Double.parseDouble(instrumentalnessMin.getText());
        double instrumentalnessHigh = instrumentalnessMax.getText().isEmpty() ? 1.0 : Double.parseDouble(instrumentalnessMax.getText());
        double livenessLow = livenessMin.getText().isEmpty() ? 0.0 : Double.parseDouble(livenessMin.getText());
        double livenessHigh = livenessMax.getText().isEmpty() ? 1.0 : Double.parseDouble(livenessMax.getText());
        int durationLow = durationMin.getText().isEmpty() ? 0 : Integer.parseInt(durationMin.getText());
        durationLow *= 1000;
        int durationHigh = durationMax.getText().isEmpty() ? 10000 : Integer.parseInt(durationMax.getText());
        durationHigh *= 1000;
        int popularityLow = popularityMin.getText().isEmpty() ? 0 : Integer.parseInt(popularityMin.getText());
        int popularityHigh = popularityMax.getText().isEmpty() ? 100 : Integer.parseInt(popularityMax.getText());

        try {
            String sql = "SELECT s.song_name, s.song_artist, s.duration, a.album_name "
                        + "FROM song as s "
                        + "JOIN album as a ON s.album_id = a.album_id "
                        + "JOIN song_characteristics as sc ON s.song_id = sc.song_id "
                        + "JOIN song_popularity as sp ON s.song_id = sp.song_id "
                        + "WHERE s.song_artist LIKE ? AND s.song_name LIKE ? AND a.album_name LIKE ? "
                        + "AND sc.danceability BETWEEN ? AND ? "
                        + "AND sc.energy BETWEEN ? AND ? "
                        + "AND sc.loudness BETWEEN ? AND ? "
                        + "AND sc.speechiness BETWEEN ? AND ? "
                        + "AND sc.acousticness BETWEEN ? AND ? "
                        + "AND sc.instrumentalness BETWEEN ? AND ? "
                        + "AND sc.liveness BETWEEN ? AND ? "
                        + "AND s.duration BETWEEN ? AND ? "
                        + "AND sp.popularity BETWEEN ? AND ?";                                                
                                    
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, "%" + artist + "%");
            stmt.setString(2, "%" + song + "%");
            stmt.setString(3, "%" + album + "%");
            stmt.setDouble(4, danceabilityLow);
            stmt.setDouble(5, danceabilityHigh);
            stmt.setDouble(6, energyLow);
            stmt.setDouble(7, energyHigh);
            stmt.setDouble(8, loudnessLow);
            stmt.setDouble(9, loudnessHigh);
            stmt.setDouble(10, speechinessLow);
            stmt.setDouble(11, speechinessHigh);
            stmt.setDouble(12, acousticnessLow);
            stmt.setDouble(13, acousticnessHigh);
            stmt.setDouble(14, instrumentalnessLow);
            stmt.setDouble(15, instrumentalnessHigh);
            stmt.setDouble(16, livenessLow);
            stmt.setDouble(17, livenessHigh);
            stmt.setInt(18, durationLow);
            stmt.setInt(19, durationHigh);
            stmt.setInt(20, popularityLow);
            stmt.setInt(21, popularityHigh);
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

    public void getPlaylists(DefaultTableModel model) {
        String sql = "SELECT playlist_name FROM playlist WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user_id);

            ResultSet rs = DatabaseManager.query(stmt);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("playlist_name")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String[] returnUserPlaylists() {
        String sql = "SELECT playlist_name FROM playlist WHERE user_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user_id);

            ResultSet rs = DatabaseManager.query(stmt);
            ArrayList<String> playlists = new ArrayList<>();
            while (rs.next()) {
                playlists.add(rs.getString("playlist_name"));
            }
            return playlists.toArray(new String[playlists.size()]);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getPlaylistId(String playlistName, int user_id) {
        String sql = "SELECT playlist_id FROM playlist WHERE user_id = ? AND playlist_name = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setString(2, playlistName);

            ResultSet rs = DatabaseManager.query(stmt);
            if (rs.next()) {
                return rs.getInt("playlist_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    
    }

    public int getSongId(String songName, String artistName, String albumName) {
        String sql = "SELECT song_id " +
                     "FROM song as s " +
                     "JOIN album as a ON s.album_id = a.album_id " +
                     "WHERE s.song_name = ? AND s.song_artist = ? AND a.album_name = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, songName);
            stmt.setString(2, artistName);
            stmt.setString(3, albumName);

            ResultSet rs = DatabaseManager.query(stmt);
            if (rs.next()) {
                return rs.getInt("song_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean songInPlaylist(int song_id, int playlist_id) {
        String sql = "SELECT * FROM playlist_has_song WHERE song_id = ? AND playlist_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, song_id);
            stmt.setInt(2, playlist_id);

            ResultSet rs = DatabaseManager.query(stmt);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addSongToPlaylist(int song_id, int playlist_id) {
        String sql = "INSERT INTO playlist_has_song (song_id, playlist_id) VALUES (?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, song_id);
            stmt.setInt(2, playlist_id);

            DatabaseManager.update(stmt);
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
