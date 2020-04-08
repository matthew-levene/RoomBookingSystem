import manager.RoomManagerUI;

import javax.swing.*;
import java.awt.*;

public class BookingSystemUI extends JPanel{
    public BookingSystemUI(){
        //Set the layout
        setLayout(new BorderLayout());

        RoomManagerUI managerUI = RoomManagerUI.getInstance();
        add(managerUI, BorderLayout.NORTH);

    }
    public static void main(String[] args){
        //Setup the frame for the application
       JFrame frame = new JFrame("Room Booking System");
       frame.setSize(400,400);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.add(new BookingSystemUI());
       frame.setVisible(true);
    }
}
