package manager;

import javax.swing.*;
import java.awt.*;

public class RoomActionPanel extends JPanel {

    private JButton addBtn, removeBtn, availabilityBtn, termDatesBtn;
    private GridBagConstraints cons;

    public RoomActionPanel(){

        addBtn = new JButton("Add Room");
        removeBtn = new JButton("Remove Room");
        availabilityBtn = new JButton("Manage Availability");
        termDatesBtn = new JButton("Set Term Dates");

        drawUI();
    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Room Actions"));
        cons = new GridBagConstraints();

        cons.weighty = 0.1;
        cons.weightx = 0.1;
        cons.insets = new Insets(15,10,10,10);
        cons.fill = GridBagConstraints.BOTH;

        //Add the add room button
        cons.gridx = 0;
        cons.gridy = 0;
        add(addBtn, cons);

        //Add the remove room button
        cons.gridx = 1;
        cons.gridy = 0;
        add(removeBtn, cons);

        //Add the manage availability button
        cons.gridx = 0;
        cons.gridy = 1;
        add(availabilityBtn, cons);

        //Add the set term dates button
        cons.gridx = 1;
        cons.gridy = 1;
        add(termDatesBtn, cons);
    }
}
