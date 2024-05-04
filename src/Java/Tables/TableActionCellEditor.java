package Java.Tables;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;
    private String selectedAction;

    public TableActionCellEditor(TableActionEvent event, String selectedAction) {
        super(new JCheckBox());
        this.event = event;
        this.selectedAction = selectedAction;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction action = new PanelAction(selectedAction);
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
