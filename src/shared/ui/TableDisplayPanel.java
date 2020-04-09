package shared.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class TableDisplayPanel extends JPanel {

    private JTable roomTable;
    private DefaultTableModel tableModel;

    public TableDisplayPanel(){

        String[] tableHeaders = {"Name", "Type", "Capacity", "Availability"};
        tableModel = new DefaultTableModel(tableHeaders, 0);

        roomTable = new JTable(tableModel){
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JScrollPane scrollPane = new JScrollPane(roomTable);
        scrollPane.setPreferredSize(new Dimension(280, 124));
        add(scrollPane);
    }

    public void addRow(Object[] rowData){
        tableModel.addRow(rowData);
    }

    public int getSelectedRow(){
        //Check if row is selected
        return roomTable.getSelectedRow();
    }

    public Object getRoomName(int row){
        return  tableModel.getValueAt(row, 0);
    }

    public void removeRow(int index){
        tableModel.removeRow(index);
    }

    public int getRowCount(){
        return tableModel.getRowCount();
    }


    public void clearTable(){
        tableModel.setRowCount(0);
    }


}
