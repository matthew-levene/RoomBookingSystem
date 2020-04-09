import manager.RoomManagerUI;
import manager.dialogs.AddRoomDialog;

import javax.swing.*;
import java.awt.*;

public class BookingSystemUI extends JPanel {
    public BookingSystemUI() {
        //Set the layout
        setLayout(new BorderLayout());

        Thread rmThread = new Thread(() -> {
            RoomManagerUI managerUI = RoomManagerUI.getInstance();
            add(managerUI, BorderLayout.NORTH);
        });
        rmThread.start();
    }

    public static void main(String[] args) {
        //Setup the frame for the application
        JFrame frame = new JFrame("Room Booking System");
        frame.setSize(950, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new BookingSystemUI());
        frame.setVisible(true);
    }
}
