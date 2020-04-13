package clerk;

import clerk.dialogs.BookingDialog;
import shared.QueryController;
import shared.data.*;

import javax.swing.*;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BookingController implements Observer {
    private SharedRooms sharedRooms;
    private SharedBookings sharedBookings;
    private BookingClerkUI GUI;
    private QueryController queryController;
    public BookingController(BookingClerkUI GUI){
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);
        sharedBookings = SharedBookings.getInstance();

        queryController = new QueryController(GUI.getTableSearchPanel(),GUI.getTableDisplayPanel());
        this.GUI = GUI;
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

            //Get the room name from the selected table entry
            String roomName = (String) GUI.getTableDisplayPanel().getRoomName(selected);
            //Get the room object
            Room room = sharedRooms.getRoom(roomName);
            //Get selected time from the table
            String selectedTime = GUI.getTableDisplayPanel().getSelectedValueAt(selected, 3);
            String selectedDate = GUI.getTableDisplayPanel().getSelectedValueAt(selected, 4);
            //match selected time against each availability
            String avTiming = "";
            for(Availability av : room.getAvailabilities()){
                //Build the availability timing
                avTiming = av.getFromTime() + av.getFromTimeScale()
                        + "-" + av.getToTime() + av.getToTimeScale();
                if(avTiming.equals(selectedTime) && av.getDate().equals(selectedDate)){
                    av.setAvailability(false);
                    break;
                }
            }
            //Write the updated availabilities in the room to the shared data structure
            sharedRooms.updateRoom(roomName, room, "Unavailable");
            //Create a new booking object and save it to the shared data structure
            sharedBookings.addBooking(new Booking(organisation, phone, notes, roomName, avTiming));

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
