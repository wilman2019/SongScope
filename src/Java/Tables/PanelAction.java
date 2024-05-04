package Java.Tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

public class PanelAction extends JPanel {

    private String selectedAction;

    private ActionButton cmdAddToPlaylist;
    private ActionButton cmdRemovePlaylist;
    private ActionButton cmdAddToCompareTable;
    private ActionButton cmdRemoveFromPlaylist;


    public PanelAction(String selectedAction) {
        this.selectedAction = selectedAction;
        initComponents();
        

    }

    public void initEvent(TableActionEvent event, int row) {

        cmdAddToPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.playlistAdd(row);

            }
        });
        cmdRemovePlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.playlistRemove(row);
            }
        });
        cmdAddToCompareTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.compareTableAdd(row);
            }
        });
        cmdRemoveFromPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.removeFromPlaylist(row);
            }
        });
    }

    private void initComponents() {

        cmdAddToPlaylist = new ActionButton();
        cmdRemovePlaylist = new ActionButton();
        cmdAddToCompareTable = new ActionButton();
        cmdRemoveFromPlaylist = new ActionButton();

        cmdAddToPlaylist.setIcon(new ImageIcon("src/img/plus-8.png"));
        cmdRemovePlaylist.setIcon(new ImageIcon("src/img/minus-8.png"));
        cmdAddToCompareTable.setIcon(new ImageIcon("src/img/plus-8.png"));
        cmdRemoveFromPlaylist.setIcon(new ImageIcon("src/img/minus-8.png"));

        if (selectedAction.equals("Add to Playlist")) {
            cmdAddToPlaylist.setVisible(true);
            cmdRemovePlaylist.setVisible(false);
            cmdAddToCompareTable.setVisible(false);
            cmdRemoveFromPlaylist.setVisible(false);
        } else if (selectedAction.equals("Remove Playlist")){
            cmdAddToPlaylist.setVisible(false);
            cmdRemovePlaylist.setVisible(true);
            cmdAddToCompareTable.setVisible(false);
            cmdRemoveFromPlaylist.setVisible(false);
        } else if (selectedAction.equals("Add to Compare Table")) {
            cmdAddToPlaylist.setVisible(false);
            cmdRemovePlaylist.setVisible(false);
            cmdAddToCompareTable.setVisible(true);
            cmdRemoveFromPlaylist.setVisible(false);
        } else if (selectedAction.equals("Remove from Playlist")) {
            cmdAddToPlaylist.setVisible(false);
            cmdRemovePlaylist.setVisible(false);
            cmdAddToCompareTable.setVisible(false);
            cmdRemoveFromPlaylist.setVisible(true);
        } else {
            cmdAddToPlaylist.setVisible(false);
            cmdRemovePlaylist.setVisible(false);
            cmdAddToCompareTable.setVisible(false);
            cmdRemoveFromPlaylist.setVisible(false);
        }

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cmdAddToPlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRemovePlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdAddToCompareTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdRemoveFromPlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7)
                .addComponent(cmdAddToPlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdRemovePlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdAddToCompareTable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(cmdRemoveFromPlaylist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(7))
        );

    }


}