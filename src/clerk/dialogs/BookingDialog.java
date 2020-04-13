package clerk.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingDialog extends JDialog implements ActionListener {

    private JLabel orgNameLbl, phoneLbl, notesLbl;
    private JTextField orgNameTxt, phoneTxt;
    private JTextArea notesArea;
    private JButton submitBtn, cancelBtn;

    private String  organisation, phone, notes;

    private int action;

    public BookingDialog(){
        super(new JFrame(), "Add a Booking Dialog", true);
        setSize(230,180);
        setLocationRelativeTo(null);

        initUI();
        drawUI();

        setVisible(true);
    }

    private void initUI(){
        orgNameLbl = new JLabel("Organisation: ");
        phoneLbl = new JLabel("Phone: ");
        notesLbl = new JLabel("Notes: ");

        orgNameTxt = new JTextField(8);
        phoneTxt = new JTextField(8);
        notesArea = new JTextArea(3,8);
        notesArea.setLineWrap(true);

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

        //Add organisation name label and text field
        cons.gridx = 0;
        cons.gridy = 0;
        add(orgNameLbl, cons);

        cons.gridx = 1;
        add(orgNameTxt, cons);

        //Add phone number label and text field
        cons.gridx = 0;
        cons.gridy = 1;
        add(phoneLbl, cons);

        cons.gridx = 1;
        add(phoneTxt, cons);

        //Add notes label

        cons.gridx = 0;
        cons.gridy = 2;
        add(notesLbl, cons);

        //Add notes area
        //cons.anchor = GridBagConstraints.CENTER;
        //cons.fill = GridBagConstraints.BOTH;
        cons.gridwidth = 2;

        cons.gridx = 1;
        cons.gridy = 2;
        add(new JScrollPane(notesArea), cons);

        //Add submit and cancel buttons
        cons.fill = GridBagConstraints.NONE;
        cons.gridwidth = 1;
        cons.gridx = 0;
        cons.gridy = 4;
        add(submitBtn, cons);

        cons.gridx = 1;
        add(cancelBtn, cons);
    }

    public boolean isEmpty(String ... args){
        for(String value : args){
            if(value.isEmpty()) return true;
        }
        return false;
    }

    public String getOrganisation(){
        return organisation;
    }

    public String getPhone(){
        return phone;
    }

    public String getNotes(){
        return notes;
    }

    public void setAction(){
        action = 1;
    }

    public int getAction(){
        return action;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == submitBtn){
            //Get the input from the user
            organisation = orgNameTxt.getText();
            phone = phoneTxt.getText();
            notes = notesArea.getText();
            //If the values are empty, prompt the user
            if(isEmpty(organisation, phone, notes)){
                JOptionPane.showMessageDialog(this, "Please enter the booking information");
                return;
            }
            //set action equal to 1
            setAction();
        }
        //Close dialog window
        this.dispose();
    }
}
