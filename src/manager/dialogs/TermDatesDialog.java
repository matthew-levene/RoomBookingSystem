package manager.dialogs;

import utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TermDatesDialog extends JDialog implements ActionListener {

    private JLabel firstTermLbl, scndTermLbl, startLbl1, startLbl2, endLbl1, endLbl2;
    private JTextField firstStartTxt, firstEndTxt, scndStartTxt, scndEndTxt;
    private JButton submitBtn, cancelBtn;

    private String firstStartDate, firstEndDate, scndStartDate, scndEndDate;
    private int action;

    public TermDatesDialog(){
        super(new JDialog(), "Set Term Dates Dialog", true);
        setSize(370,200);
        setLocationRelativeTo(null);

        initUI();
        drawUI();

        setVisible(true);

    }

    public void initUI() {
        firstTermLbl = new JLabel("Term 1");
        startLbl1 = new JLabel("Start: ");
        endLbl1 = new JLabel("End: ");
        scndTermLbl = new JLabel("Term 2");
        startLbl2 = new JLabel("Start: ");
        endLbl2 = new JLabel("End: ");

        firstStartTxt = new JTextField(5);
        firstEndTxt = new JTextField(5);
        scndStartTxt = new JTextField(5);
        scndEndTxt = new JTextField(5);

        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);
        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(this);
    }

    public void drawUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.weightx = 0.1;
        cons.weighty = 0.1;
        cons.insets = new Insets(5,5,5,5);

        //Add the Term 1 (T1) label
        cons.gridx = 0;
        cons.gridy = 0;
        add(firstTermLbl, cons);

        //Add T1 start label
        cons.gridx = 0;
        cons.gridy = 1;
        add(startLbl1, cons);
        //Add T1 start text field
        cons.gridx = 1;
        add(firstStartTxt, cons);
        //Add T1 end label
        cons.gridx = 2;
        add(endLbl1, cons);
        //Add T1 end text field
        cons.gridx = 3;
        add(firstEndTxt, cons);

        //Add the Term 2 (T2) label
        cons.gridx = 0;
        cons.gridy = 2;
        add(scndTermLbl, cons);

        //Add T2 start label
        cons.gridx = 0;
        cons.gridy = 3;
        add(startLbl2, cons);
        //Add T2 start text field
        cons.gridx = 1;
        add(scndStartTxt, cons);
        //Add T2 end label
        cons.gridx = 2;
        add(endLbl2, cons);
        //Add T2 end text field
        cons.gridx = 3;
        add(scndEndTxt, cons);

        //Add the submit and cancel buttons
        cons.gridwidth = 3;
        cons.anchor = GridBagConstraints.CENTER;
        cons.insets = new Insets(20,5,5,5);

        cons.gridx = 0;
        cons.gridy = 4;
        add(submitBtn, cons);

        cons.gridx = 1;
        add(cancelBtn, cons);
    }

    public String getFirstStartDate() {
        return firstStartDate;
    }

    public String getFirstEndDate() {
        return firstEndDate;
    }

    public String getScndStartDate() {
        return scndStartDate;
    }

    public String getScndEndDate() {
        return scndEndDate;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action){
        this.action = action;
    }

    public boolean isEmpty(String ... args){
        for(String value : args){
            if(value.isEmpty()) return true;
         }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == submitBtn){
            //Get the input from the text fields
            firstStartDate = firstStartTxt.getText();
            firstEndDate = firstEndTxt.getText();
            scndStartDate = scndStartTxt.getText();
            scndEndDate = scndEndTxt.getText();

            //check if the input is empty
            if(isEmpty(firstStartDate, firstEndDate, scndEndDate, scndEndDate)){
               JOptionPane.showMessageDialog(this, "Please enter input into the term date fields");
               return;
            }

            //Check if the inputs are in a valid format (dd/MM/yyyy)
            boolean areDatesValid = DateUtils.areValidDates(firstStartDate, firstEndDate, scndStartDate, scndEndDate);
            if(!areDatesValid){
                JOptionPane.showMessageDialog(this,"Dates entered are not valid, dates must be in format dd/mm/yyyy");
                return;
            }

            //Check if the input dates are in linear order
            // e.g. second end date doesn't come before first start date
            boolean isOrderValid = DateUtils.isValidDateOrder(firstStartDate, firstEndDate, scndStartDate, scndEndDate);
            if(!isOrderValid){
                JOptionPane.showMessageDialog(this, "Dates are not in linear order");
                return;
            }

            //Set the action equal to one
            setAction(1);

            //Close dialog window
            this.dispose();
        }
        //Cancel button was pressed
        else{
            //Close dialog window
            this.dispose();
        }
    }
}
