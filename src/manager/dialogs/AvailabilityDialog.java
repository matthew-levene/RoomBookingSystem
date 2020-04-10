package manager.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvailabilityDialog extends JDialog implements ActionListener {

    private JRadioButton availRad, unavailRad;
    private ButtonGroup btnGrp;
    private JLabel reasonLbl, timeframeLbl;
    private JTextField reasonTxt, timeframeTxt;
    private JButton submitBtn, cancelBtn;
    private JComboBox<String> timeframeCbo;

    private GridBagConstraints cons;

    private int action;



    private String reason, timeframe, timeframeSelection;

    public AvailabilityDialog() {
        super(new JFrame(), "Manage Availability Dialog", true);
        setSize(350,200);
        setLocationRelativeTo(null);

        initUI();
        drawUI();

        setVisible(true);
    }

    private void initUI(){
        availRad = new JRadioButton("Available");
        availRad.addActionListener(this);
        unavailRad = new JRadioButton("Unavailable");
        unavailRad.addActionListener(this);
        btnGrp = new ButtonGroup();
        btnGrp.add(availRad);
        btnGrp.add(unavailRad);

        reasonLbl = new JLabel("Reason: ");
        timeframeLbl = new JLabel("Timeframe: ");

        reasonTxt = new JTextField(10);
        timeframeTxt = new JTextField(10);

        String[] timeframes = {"Days", "Weeks"};
        timeframeCbo = new JComboBox<>(timeframes);

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);

    }

    private void drawUI(){
        setLayout(new GridBagLayout());
        cons = new GridBagConstraints();
        cons.weightx = 0.1;
        cons.weighty = 0.1;
        cons.insets = new Insets(5,5,5,5);

        //Add the available and unavailable radio buttons
        cons.gridx = 0;
        cons.gridy = 0;
        add(availRad, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        add(unavailRad, cons);

        //Add the reason label and text field
        cons.gridx = 0;
        cons.gridy = 1;
        add(reasonLbl, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        add(reasonTxt, cons);

        //Add the timeframe label, textfield and dropdown
        cons.gridx = 0;
        cons.gridy = 2;
        add(timeframeLbl, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        add(timeframeTxt, cons);

        cons.gridx = 2;
        cons.gridy = 2;
        add(timeframeCbo, cons);

        //Add submit and cancel buttons
        cons.anchor = GridBagConstraints.CENTER;
        cons.gridwidth = 2;

        cons.gridx = 0;
        cons.gridy = 3;
        add(submitBtn, cons);

        cons.gridx = 1;
        cons.gridy = 3;
        add(cancelBtn, cons);
    }

    private void setAction(int action){
        this.action = action;
    }

    public int getAction(){
        return action;
    }

    public String getReason() {
        return reason;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public String getTimeframeSelection() {
        return timeframeSelection;
    }

    private void disableControls(){
        reasonTxt.setEditable(false);
        timeframeTxt.setEditable(false);
        timeframeCbo.setEnabled(false);
    }

    private void enableControls(){
        reasonTxt.setEditable(true);
        timeframeTxt.setEditable(true);
        timeframeCbo.setEnabled(true);
    }

    private boolean isEmpty(String ... args){
        for(String value : args){
            if(value.isEmpty()){
                return true;
            }
        }
        return false;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == submitBtn){
            //If the 'available' radio button was selected
            if(availRad.isSelected()){
                //Disable input controls
                disableControls();
                //Set the action to 0
                setAction(0);
                //Close the dialog window
                this.dispose();
            }
            else if(unavailRad.isSelected()){
                //Enable input controls
                enableControls();
                //Get the input from the user
                reason = reasonTxt.getText();
                timeframe = timeframeTxt.getText();
                timeframeSelection = (String) timeframeCbo.getSelectedItem();

                //Check if the input values are empty
                if(isEmpty(reason, timeframe)){
                    JOptionPane.showMessageDialog(this, "Please enter the unavailability information");
                    return;
                }

                //Check if timeframe is a numeric value
                try{
                    int time = Integer.parseInt(timeframe);
                }catch(NumberFormatException nfe){
                    JOptionPane.showMessageDialog(this, "Timeframe is not a numeric value");
                    return;
                }

                //Set the action to 1
                setAction(1);
                //Close the dialog window
                this.dispose();
            }
            else if(!availRad.isSelected() && !unavailRad.isSelected()){
                JOptionPane.showMessageDialog(this, "Please select an availability option");
                return;
            }
        }
        else if(actionEvent.getSource() == availRad){
            //Disable input controls
            disableControls();
        }
        else if(actionEvent.getSource() == unavailRad){
            //Enable input controls
            enableControls();
        }
        else {
            //Cancel button was pressed
            this.dispose();
        }
    }
}
