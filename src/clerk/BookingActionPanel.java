package clerk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingActionPanel extends JPanel implements ActionListener {
    private JButton createBtn, viewBtn;
    private BookingActionListener listener;

    public BookingActionPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));

        createBtn = new JButton("Create Booking");
        createBtn.addActionListener(this);

        viewBtn = new JButton("View Bookings");
        viewBtn.addActionListener(this);

        add(createBtn);
        add(viewBtn);
    }

    public void setBookingActionListener(BookingActionListener listener){
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (listener != null) {
            listener.bookingEventOccurred(actionEvent);
        }
    }

    public JButton getCreateButton(){
        return createBtn;
    }

    public JButton getViewButton(){
        return viewBtn;
    }
}
