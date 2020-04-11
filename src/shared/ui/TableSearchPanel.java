package shared.ui;

import javax.swing.*;
import java.awt.*;


public class TableSearchPanel extends JPanel {

    private JList<String> roomTypeList;
    private JCheckBox morningChk, eveningChk;
    private JButton searchBtn;
    private JTextField dateTxt;
    private JLabel dateLbl;
    private GridBagConstraints cons;

    public TableSearchPanel(){

        String[] types = {"Computer Lab", "Tutorial Room", "Lecture Theatre"};
        roomTypeList = new JList<>(types);

        morningChk = new JCheckBox("AM");
        eveningChk = new JCheckBox("PM");

        searchBtn = new JButton("Search");

        dateLbl = new JLabel("Date:");
        dateTxt = new JTextField(5);

        drawUI();
    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Room Search"));
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
        cons.insets = new Insets(5,5,5,5);
        add(eveningChk, cons);

        cons.gridwidth = 2;

        //Add the date controls
        cons.gridx = 1;
        cons.gridy = 1;
        add(dateLbl, cons);

        cons.gridx = 2;
        cons.gridy = 1;
        add(dateTxt, cons);

        //Add the search button
        cons.gridx = 1;
        cons.gridy = 2;
        add(searchBtn, cons);

    }
}
