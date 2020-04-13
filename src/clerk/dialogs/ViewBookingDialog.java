package clerk.dialogs;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBookingDialog extends JDialog implements ActionListener {


    private JTable bookingTable;
    private DefaultTableModel bookingTableModel;
    private JButton okBtn, cancelBtn;
    private JScrollPane scrollPane;

    public ViewBookingDialog(){
        super(new JFrame(), "View Bookings Dialog", true);
        setSize(680,180);
        setLocationRelativeTo(null);

        initUI();
        drawUI();
    }

    private void initUI(){
        String[] headers = {"Organisation", "Phone", "Notes", "Room", "Time", "Date"};
        bookingTableModel = new DefaultTableModel(headers,0);
        bookingTable = new JTable(bookingTableModel){
            public boolean isCellEditable(int row, int column) { return false; }
        };

        scrollPane = new JScrollPane(bookingTable);
        scrollPane.setPreferredSize(new Dimension(650,100));

        okBtn = new JButton("OK");
        okBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);

    }
    private void drawUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.weighty = 0.1;
        cons.weightx = 0.1;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(5,5,5,5);

        //Add the table
        cons.gridwidth = 2;
        cons.gridx = 0;
        cons.gridy = 0;
        add(scrollPane, cons);

        //Add the ok and cancel buttons
        cons.anchor = GridBagConstraints.FIRST_LINE_END;
        cons.gridwidth = 1;
        cons.gridx = 0;
        cons.gridy = 1;
        add(okBtn, cons);

        cons.anchor = GridBagConstraints.FIRST_LINE_START;
        cons.gridx = 1;
        add(cancelBtn, cons);

    }

    public void addRowData(Object[] rowData){
        bookingTableModel.addRow(rowData);
    }

    public void setVisible(){
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //For any button clicked, close the dialog
        this.dispose();
    }
}
