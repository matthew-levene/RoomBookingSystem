import clerk.BookingClerkUI;
import clerk.dialogs.ViewBookingDialog;
import manager.RoomManagerUI;
import manager.dialogs.AddRoomDialog;

import javax.swing.*;
import java.awt.*;

public class BookingSystemUI extends JPanel {
    public BookingSystemUI() {
        //Set the layout
        setLayout(new BorderLayout());

        //Initialise the room manager interface and add it to the frame
        Thread rmThread = new Thread(() -> {
            RoomManagerUI managerUI = RoomManagerUI.getInstance();
            add(managerUI, BorderLayout.NORTH);
        });
        rmThread.start();

        //Initialise the booking clerk interface and add it to the frame
        Thread bcThread = new Thread(() -> {
            BookingClerkUI bookingClerkUI = new BookingClerkUI();
            add(bookingClerkUI, BorderLayout.WEST);
        });
        bcThread.start();
    }

    public static void main(String[] args) {
        //Setup the frame for the application
        JFrame frame = new JFrame("Room Booking System");
        frame.setSize(940, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BookingSystemUI());
        frame.setVisible(true);
    }
}
