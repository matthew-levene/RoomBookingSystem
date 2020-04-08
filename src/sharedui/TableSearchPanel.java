package sharedui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableSearchPanel extends JPanel {

    private JList<String> roomTypeList;
    private DefaultListModel<String> roomTypes;
    private JCheckBox morningChk, eveningChk;
    private JComboBox<String> daySelection;
    private JButton searchBtn;

    private GridBagConstraints cons;

    public TableSearchPanel(){

        String[] types = {"Computer Lab", "Tutorial Room", "Lecture Theatre"};
        roomTypeList = new JList<>(types);

        morningChk = new JCheckBox("AM");
        eveningChk = new JCheckBox("PM");

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        daySelection = new JComboBox<>(days);

        searchBtn = new JButton("Search");

        drawUI();
    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Search"));
        cons = new GridBagConstraints();
        cons.weighty = 0.1;
        cons.weighty = 0.1;
        cons.insets = new Insets(5,5,5,5);

        //Add the list UI control
        cons.gridx = 0;
        cons.gridy = 0;
        cons.fill = GridBagConstraints.BOTH;
        cons.gridheight = 3;
        add(roomTypeList, cons);

        cons.gridheight = 1;

        //Add the AM/PM selection controls
        cons.gridwidth = 1;
        cons.gridx = 1;
        cons.gridy = 0;
        add(morningChk, cons);

        cons.gridx = 2;
        cons.gridy = 0;
        cons.insets = new Insets(5,5,5,0);
        add(eveningChk, cons);

        cons.gridwidth = 2;
        //Add the day selection control
        cons.gridx = 1;
        cons.gridy = 1;
        add(daySelection, cons);

        //Add the search button
        cons.gridx = 1;
        cons.gridy = 2;
        add(searchBtn, cons);

    }
}
