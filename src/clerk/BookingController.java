package clerk;

import shared.QueryController;
import shared.data.Room;
import shared.data.SharedRooms;
import shared.data.Unavailability;

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
