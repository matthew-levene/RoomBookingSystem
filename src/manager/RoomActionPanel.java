package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomActionPanel extends JPanel implements ActionListener {

    private JButton addBtn, removeBtn, availabilityBtn, termDatesBtn;
    private GridBagConstraints cons;
    private RoomActionListener listener;

    public RoomActionPanel(){

        addBtn = new JButton("Add Room");
        addBtn.addActionListener(this);
        removeBtn = new JButton("Remove Room");
        removeBtn.addActionListener(this);
        availabilityBtn = new JButton("Manage Availability");
        availabilityBtn.addActionListener(this);
        termDatesBtn = new JButton("Set Term Dates");
        termDatesBtn.addActionListener(this);

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

    public void setActionListener(RoomActionListener actionListener){
        listener = actionListener;
    }

    public JButton getAddButton(){ return addBtn; }
    public JButton getRemoveButton(){ return removeBtn; }
    public JButton getAvailabilityButton(){ return availabilityBtn; }
    public JButton getTermDateButton(){ return termDatesBtn; }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(listener != null) {
            listener.roomEventOccurred(actionEvent);
        }
    }
}
