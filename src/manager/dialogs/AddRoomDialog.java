package manager.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoomDialog extends JDialog implements ActionListener {

    private JLabel nameLbl, typeLbl, capacityLbl, availFromLbl, availToLbl, availLbl;
    private JTextField nameTxt, capacityTxt, availFromTxt, availToTxt;
    private JComboBox<String> typeCbo, dayCbo;
    private JCheckBox fromAMChk, toAMChk, fromPMChk, toPMChk;
    private JButton addBtn, submitBtn, cancelBtn;

    private GridBagConstraints cons;

    public AddRoomDialog() {
        super(new JFrame(), "Add Room Dialog", true);
        setSize(new Dimension(500,280));
        setLocationRelativeTo(null);

        initUI();
        drawUI();

        setVisible(true);
    }

    private void initUI(){
        nameLbl = new JLabel("Name: ");
        typeLbl = new JLabel("Type: ");
        capacityLbl = new JLabel("Capacity: ");
        availFromLbl = new JLabel("From: ");
        availToLbl = new JLabel("To: ");
        availLbl = new JLabel("Availability");

        nameTxt = new JTextField(10);
        capacityTxt = new JTextField(10);
        availFromTxt = new JTextField(10);
        availToTxt = new JTextField(10);

        String[] types = {"Computer Lab", "Tutorial Room", "Lecture Theatre"};
        typeCbo = new JComboBox<>(types);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayCbo = new JComboBox<>(days);

        fromAMChk = new JCheckBox("AM");
        toAMChk = new JCheckBox("AM");

        fromPMChk = new JCheckBox("PM");
        toPMChk = new JCheckBox("PM");

        addBtn = new JButton("Add");
        submitBtn = new JButton("Submit");
        cancelBtn = new JButton("Cancel");

    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        cons = new GridBagConstraints();
        cons.weightx = 0.1;
        cons.weighty = 0.1;
        cons.insets = new Insets(5,5,5,5);

        //Add the name label and name text components
        cons.gridx = 0;
        cons.gridy = 0;
        add(nameLbl, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        add(nameTxt, cons);

        //Add the type label and type combobox components
        cons.gridx = 0;
        cons.gridy = 1;
        add(typeLbl, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        add(typeCbo, cons);

        //Add the capacity label and capacity text components
        cons.gridx = 0;
        cons.gridy = 2;
        add(capacityLbl, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        add(capacityTxt, cons);

        //Add the availability label
        cons.gridx = 0;
        cons.gridy = 3;
        add(availLbl, cons);


        // Add the from availability row
        cons.gridx = 0;
        cons.gridy = 4;
        add(availFromLbl, cons);
        cons.gridx = 1;
        add(availFromTxt, cons);
        cons.gridx = 2;
        add(fromAMChk, cons);
        cons.gridx = 3;
        add(fromPMChk, cons);
        cons.gridx = 4;
        add(dayCbo, cons);

        //Add the to availability row
        cons.gridx = 0;
        cons.gridy = 5;
        add(availToLbl, cons);
        cons.gridx = 1;
        add(availToTxt, cons);
        cons.gridx = 2;
        add(toAMChk, cons);
        cons.gridx = 3;
        add(toPMChk, cons);
        cons.gridx = 4;
        add(addBtn, cons);

        //Add the submit and cancel buttons
        cons.gridx = 0;
        cons.gridy = 6;
        add(submitBtn, cons);
        cons.gridx = 1;
        add(cancelBtn, cons);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
