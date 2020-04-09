package manager.dialogs;

import javafx.scene.control.RadioButton;
import shared.data.Availability;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRoomDialog extends JDialog implements ActionListener {

    private JLabel nameLbl, typeLbl, capacityLbl, availFromLbl, availToLbl, availLbl, dateLbl;
    private JTextField nameTxt, capacityTxt, availFromTxt, availToTxt, dateTxt;
    private JComboBox<String> typeCbo, dayCbo;
    private JRadioButton fromAMRad, toAMRad, fromPMRad, toPMRad;
    private JButton addBtn, submitBtn, cancelBtn;
    private ButtonGroup fromBtnGroup, toBtnGroup;

    private ArrayList<Availability> availabilities = new ArrayList<>();
    private int action;
    private String roomName, roomType, roomCapacity;

    public AddRoomDialog() {
        super(new JFrame(), "Add Room Dialog", true);
        setSize(new Dimension(550,230));
        //setResizable(false);
        setLocationRelativeTo(null);

        initUI();
        drawUI();
        setVisible(true);
    }

    private void initUI(){
        //Initialise labels
        nameLbl = new JLabel("Name: ");
        typeLbl = new JLabel("Type: ");
        capacityLbl = new JLabel("Capacity: ");
        availFromLbl = new JLabel("From: ");
        availToLbl = new JLabel("To: ");
        availLbl = new JLabel("Availability");
        dateLbl = new JLabel("Date: ");

        //Initialise text fields
        nameTxt = new JTextField(5);
        capacityTxt = new JTextField(5);
        availFromTxt = new JTextField(5);
        availToTxt = new JTextField(5);
        dateTxt = new JTextField(5);

        //Initialise combo boxes and their options
        String[] types = {"Computer Lab", "Tutorial Room", "Lecture Theatre"};
        typeCbo = new JComboBox<>(types);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayCbo = new JComboBox<>(days);

        //Initialse Radio Buttons and their Button Groups
        fromAMRad = new JRadioButton("AM");
        toAMRad = new JRadioButton("AM");

        fromPMRad = new JRadioButton("PM");
        toPMRad = new JRadioButton("PM");

        fromBtnGroup = new ButtonGroup();
        fromBtnGroup.add(fromAMRad);
        fromBtnGroup.add(fromPMRad);

        toBtnGroup = new ButtonGroup();
        toBtnGroup.add(toAMRad);
        toBtnGroup.add(toPMRad);

        //Add Action Listeners to buttons
        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);

    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
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
        cons.gridx = 2;
        cons.gridy = 0;
        add(typeLbl, cons);

        cons.gridx = 3;
        cons.gridy = 0;
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

        cons.gridx = 1;
        cons.gridy = 3;
        add(dateLbl, cons);

        cons.gridx = 2;
        cons.gridy = 3;
        add(dateTxt, cons);

        // Add the from availability row
        cons.gridx = 0;
        cons.gridy = 4;
        add(availFromLbl, cons);
        cons.gridx = 1;
        add(availFromTxt, cons);
        cons.gridx = 2;
        add(fromAMRad, cons);
        cons.gridx = 3;
        add(fromPMRad, cons);
        cons.gridx = 4;
        add(dayCbo, cons);

        //Add the to availability row
        cons.gridx = 0;
        cons.gridy = 5;
        add(availToLbl, cons);
        cons.gridx = 1;
        add(availToTxt, cons);
        cons.gridx = 2;
        add(toAMRad, cons);
        cons.gridx = 3;
        add(toPMRad, cons);
        cons.gridx = 4;
        add(addBtn, cons);

        //Add the submit and cancel buttons
        cons.gridwidth = 4;
        cons.anchor = GridBagConstraints.CENTER;
        cons.gridx = 0;
        cons.gridy = 6;
        add(submitBtn, cons);
        cons.gridx = 1;
        add(cancelBtn, cons);

    }

    private void setAction(int index){
        this.action = index;
    }
    public int getAction(){
        return action;
    }

    //TODO: Check to ensure that there are no duplicate availabilities
    private void addAvailability(String date, String from, String to, String fromSelected, String toSelected, String day){
        availabilities.add(new Availability(date, from, to, fromSelected, toSelected, day));
    }

    public ArrayList<Availability> getAvailability(){
        return availabilities;
    }

    private void resetAvailability(){
        availabilities.clear();
    }

    private void setRoomName(String name){
        roomName = name;
    }

    public String getRoomName(){
        return roomName;
    }

    private void setRoomType(String type){
        roomType = type;
    }

    public String getRoomType(){
        return roomType;
    }

    private void setRoomCapacity(String capacity){
        roomCapacity = capacity;
    }

    public String getRoomCapacity(){
        return roomCapacity;
    }

    public boolean isEmpty(String ... args){
        for(String value : args){
            if(value.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == addBtn){
            String date = dateTxt.getText();
            String fromTime = availFromTxt.getText();
            String toTime = availToTxt.getText();
            String fromSelected = "";
            String toSelected = "";
            String day = (String) dayCbo.getSelectedItem();

            //Check if the 'from' radio buttons have been selected
            if(fromAMRad.isSelected()){ fromSelected = fromAMRad.getText(); }
            else if(fromPMRad.isSelected()){fromSelected = fromPMRad.getText(); }

            //Check if the 'to' radio buttons have been selected
            if(toAMRad.isSelected()){ toSelected = toAMRad.getText();}
            else if(toPMRad.isSelected()){toSelected = toPMRad.getText();}

            //Check if the text fields are empty
            if(isEmpty(date, fromTime, toTime, day, fromSelected, toSelected)){
                JOptionPane.showMessageDialog(this, "Please enter/select the room's availability");
                return;
            }
            addAvailability(date, fromTime, toTime, fromSelected, toSelected, day);
        }
        else if(actionEvent.getSource() == submitBtn){
            //Get the room description values from the dialog
            String roomName = nameTxt.getText();
            String roomType = (String) typeCbo.getSelectedItem();
            String roomCapacity = capacityTxt.getText();

            //Check if room capacity input is a numeric type
            try{
                int capacity = Integer.parseInt(roomCapacity);
            }catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(this, "Room capacity is not a numeric value");
                return;
            }
            //Check if the room description value are empty
            if(isEmpty(roomName, roomType, roomCapacity)){
                JOptionPane.showMessageDialog(this, "Please enter room information");
                return;
            }
            //Check if at least one availability timing has been set
            if(availabilities.size() < 1){
                JOptionPane.showMessageDialog(this, "Please enter at least one availability timing");
                return;
            }

            setRoomName(roomName);
            setRoomType(roomType);
            setRoomCapacity(roomCapacity);
            setAction(1);
            //Close the window
            this.dispose();

        }
        else{
            //Cancel button was pressed
            setAction(0);
            resetAvailability();
            this.dispose();
        }
    }
}
