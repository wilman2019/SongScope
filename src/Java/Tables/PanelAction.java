package Java.Tables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;

public class PanelAction extends JPanel {

    private ActionButton cmdAdd;


    public PanelAction() {
        initComponents();
        

    }

    public void initEvent(TableActionEvent event, int row) {
        cmdAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                event.playlsitAdd(row);
            }
        });
    }

    private void initComponents() {

        cmdAdd = new ActionButton();

        cmdAdd.setIcon(new ImageIcon("src/img/plus-8.png")); 

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmdAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2)
                .addComponent(cmdAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(2))
        );
    }


}