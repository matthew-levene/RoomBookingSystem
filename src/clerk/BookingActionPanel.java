package clerk;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class BookingActionPanel extends JPanel {
    private JButton createBtn;
    private BookingActionListener listener;

    public BookingActionPanel(){
        setLayout(new FlowLayout(FlowLayout.CENTER));
        createBtn = new JButton("Create Booking");
        createBtn.addActionListener(event -> {
            if(listener != null){
                listener.bookingEventOccurred();
            }
        });

        add(createBtn);
    }

    public void setBookingActionListener(BookingActionListener listener){
        this.listener = listener;
    }
}
