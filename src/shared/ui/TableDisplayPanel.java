package shared.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class TableDisplayPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JTable roomTable;
    private DefaultTableModel tableModel;

    private GridBagConstraints cons;
    public TableDisplayPanel(){

        String[] tableHeaders = {"Name", "Type", "Capacity", "Availability"};
        tableModel = new DefaultTableModel(tableHeaders, 1);

        roomTable = new JTable(tableModel){
            public boolean isCellEditable(int row, int column) { return false; }
        };

        //roomTable.setPreferredSize(new Dimension(200,50));
        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setPreferredSize(new Dimension(280, 124));
        add(scrollPane, cons);

    }

}
