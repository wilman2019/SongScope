package Java.Screens;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ShowSongRankings extends JFrame {
    private JPanel contentPane;
    private ArrayList<String[]> topSongs;
    private DefaultListModel<String> listModel;
    private JList<String> songsList;

    public ShowSongRankings(ArrayList<String[]> topSongs) {
        setTitle("Top songs");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        this.topSongs = topSongs;
        initComponents();
    }

    private void initComponents() {
        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        listModel = new DefaultListModel<>();
        for (String[] topSong : topSongs) {
        	String item = "";
        	item += topSong[3] + ", " + topSong[1] + " by " + topSong[2];
        	
            listModel.addElement(item);
        }

        songsList = new JList<>(listModel);
        songsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane = new JScrollPane(songsList);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
    }
}
