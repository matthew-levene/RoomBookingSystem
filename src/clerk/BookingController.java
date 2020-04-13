package clerk;

import clerk.dialogs.BookingDialog;
import shared.QueryController;
import shared.data.Room;
import shared.data.SharedRooms;
import shared.data.Unavailability;

import javax.swing.*;
import java.awt.print.Book;
import java.util.Observable;
import java.util.Observer;

public class BookingController implements Observer {
    private SharedRooms sharedRooms;
    private BookingClerkUI GUI;
    private QueryController queryController;
    public BookingController(BookingClerkUI GUI){
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);

        this.GUI = GUI;
        queryController = new QueryController(GUI.getTableSearchPanel(),GUI.getTableDisplayPanel());
    }

    public void processBookingAction(){
        //Check if tab title equals Available
        String tabTitle = GUI.getTableDisplayPanel().getTabTitle();
        if(!tabTitle.equals("Available")){
            JOptionPane.showMessageDialog(new JFrame(), "Available rooms table must be active before booking a slot");
            return;
        }
        //Check if availability slot on table has been selected
        int selected = GUI.getTableDisplayPanel().getSelectedRow();
        if(selected == -1){
            JOptionPane.showMessageDialog(new JFrame(), "Please select a row from the table to book");
            return;
        }
        //Open Booking Dialog
        BookingDialog bookingWindow = new BookingDialog();

        //If the action set in the dialog window is equal to 1
        if(bookingWindow.getAction() == 1){
            //Get the booking information
            String organisation = bookingWindow.getOrganisation();
            String phone = bookingWindow.getPhone();
            String notes = bookingWindow.getNotes();

            //TODO create new booking object and store it to shared booking data structure
        }
    }

    public void findRoom(){
        queryController.findRoom();
    }

    @Override
    public void update(Observable observable, Object o) {
        Object[] message = (Object[]) o;
        if (message[0].equals("Add")) {
            //Get the room by key
            Room room = sharedRooms.getRoom((String) message[1]);
            //Add room entry to room availability table
            queryController.addRoomToAvailableTable(room);
        }
        else if (message[0].equals("Remove")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            queryController.removeRoomFromAvailableTable(message);
        }

        else if (message[0].equals("Update")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            queryController.removeRoomFromAvailableTable(message);

            //Get the room object
            Room room = sharedRooms.getRoom((String) message[1]);

            //Message contained requested to change room state: Available -> Unavailable
            if (message[2].equals("Available")) {
                //If the room is not available
                if (!room.isAvailable()) {
                    //Get the unavailability object
                    Unavailability unav = room.getUnavailability();
                    //Add the room information to the unavailable rooms table
                    queryController.addToUnavailableTable(message, unav);
                }
            }
            //Message contained request to change room state: Unavilable -> Available
            else if(message[2].equals("Unavailable")){
                //If the room is available
                if (room.isAvailable()) {
                    //Write the availability information to the available rooms table
                    queryController.addRoomToAvailableTable(room);
                    //Remove the room entry from the unavailable rooms table
                    queryController.removeFromUnavailableTable(message);
                }
            }
        }
    }
}
