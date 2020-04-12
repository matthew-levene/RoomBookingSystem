package shared.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class TableDisplayPanel extends JPanel {

    private JTable availRoomTable, unavailRoomTable;
    private DefaultTableModel availTableModel, unavailTableModel;
    private JTabbedPane tabbedPane;

    public TableDisplayPanel(){
        String[] availHeaders = {"Name", "Type", "Size", "Time", "Date"};
        //Define the available rooms table
        availTableModel = new DefaultTableModel(availHeaders, 0);
        availRoomTable = new JTable(availTableModel){
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JScrollPane scrollPane1 = new JScrollPane(availRoomTable);
        scrollPane1.setPreferredSize(new Dimension(280, 100));

        //Define the unavailable rooms table
        String[] unavailHeaders = {"Name", "Reason", "Timescale"};
        unavailTableModel = new DefaultTableModel(unavailHeaders, 0);
        unavailRoomTable = new JTable(unavailTableModel){
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JScrollPane scrollPane2 = new JScrollPane(unavailRoomTable);
        scrollPane2.setPreferredSize(new Dimension(280, 100));

        //Add the two tables to the tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Available", scrollPane1);
        tabbedPane.addTab("Unavailable", scrollPane2);
        add(tabbedPane);
    }
    public void addRow(Object[] rowData){ availTableModel.addRow(rowData); }
    public void addUnavailRow(Object[] rowData){ unavailTableModel.addRow(rowData); }

    public void removeRow(int index){ availTableModel.removeRow(index); }
    public void removeUnavailRow(int index){ unavailTableModel.removeRow(index); }

    public int getSelectedRow(){ return availRoomTable.getSelectedRow(); }
    public int getSelectedUnavailRow(){ return unavailRoomTable.getSelectedRow(); }

    public Object getRoomName(int row){ return  availTableModel.getValueAt(row, 0); }
    public Object getUnavailRoomName(int row){
        return unavailTableModel.getValueAt(row, 0);
    }

    public int getRowCount(){
        return availTableModel.getRowCount();
    }
    public int getUnavailRowCount(){
        return unavailTableModel.getRowCount();
    }

    public void clearAvailabilityTable(){
        availTableModel.setRowCount(0);
    }
    public String getTabTitle(){
        return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
    }
}
