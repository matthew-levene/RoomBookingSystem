package manager;

import manager.dialogs.AddRoomDialog;
import manager.dialogs.AvailabilityDialog;
import shared.data.Availability;
import shared.data.Room;
import shared.data.SharedRooms;
import shared.data.Unavailability;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class RoomController implements Observer {

    private SharedRooms sharedRooms;
    private RoomManagerUI GUI;

    public RoomController(RoomManagerUI GUI) {
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);

        this.GUI = GUI;
    }

    public void handleButtonEvent(RoomActionPanel actionPanel, ActionEvent event) {
        Object eventSource = event.getSource();
        if (eventSource == actionPanel.getAddButton()) {
            //Method call to add a new room to the system
            addRoom();
        } else if (eventSource == actionPanel.getRemoveButton()) {
            //Method call to remove existing room from system
            removeRoom();
        } else if (eventSource == actionPanel.getAvailabilityButton()) {
            //Method call to change the availability status of a room
            manageRoomAvailability();
        } else {
            //Manage Term Dates functionality
            setTermDates();
        }
    }

    private void addRoom() {
        AddRoomDialog addRoomWindow = new AddRoomDialog();
        //If the action is equal to one, the submit button was pressed
        if (addRoomWindow.getAction() == 1) {
            //Get the room availability array
            ArrayList<Availability> availabilities = addRoomWindow.getAvailability();
            //Get the new room information
            String name = addRoomWindow.getRoomName();
            String type = addRoomWindow.getRoomType();
            String capacity = addRoomWindow.getRoomCapacity();

            //TODO check if room already exists in data structure using roomname key
            if (sharedRooms.keyExists(name)) {
                JOptionPane.showMessageDialog(
                        new JFrame(),
                        "Unable to create new room; " + name + " already exists");
            } else {
                //Create a new room and add it to the shared data structure
                sharedRooms.addRoom(name, new Room(name, type, capacity, availabilities));
            }
        }
    }

    private void removeRoom() {
        //Get the selected row from the table
        int selectedRow = GUI.getTableDisplayPanel().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(new JFrame(), "Please select a room to remove from the table");
            return;
        }
        //Get the room name from the table
        String roomName = (String) GUI.getTableDisplayPanel().getRoomName(selectedRow);
        //Remove the room from the shared rooms data structure
        sharedRooms.removeRoom(roomName);
    }

    private void manageRoomAvailability() {
        //Get the title of the tab selected
        String tabTitle = GUI.getTableDisplayPanel().getTabTitle();
        String roomName;
        int selectedRow;
        if (tabTitle.equals("Available")) {
            //Get the selected row from the available table
            selectedRow = GUI.getTableDisplayPanel().getSelectedRow();
            //If a row is not selected
            if (selectedRow == -1) {
                //Show error message to user
                JOptionPane.showMessageDialog(new JFrame(), "Please select a row from the table");
                return;
            }
            //Get the room name from the selected row
            roomName = (String) GUI.getTableDisplayPanel().getRoomName(selectedRow);
        } else {
            //Get the selected row from the unavailable rooms table
            selectedRow = GUI.getTableDisplayPanel().getSelectedUnavailRow();
            //If a row is not selected
            if (selectedRow == -1) {
                //Show error message to user
                JOptionPane.showMessageDialog(new JFrame(), "Please select a row from the table");
                return;
            }
            //Get the room name from the selected row
            roomName = (String) GUI.getTableDisplayPanel().getUnavailRoomName(selectedRow);
        }

        //Open the availability dialog window
        AvailabilityDialog availWindow = new AvailabilityDialog();

        switch (availWindow.getAction()) {
            ////Request to make the room available was selected
            //Room state transition: Unavailable -> Available
            case 0:
                //If the tab title equals available, then return
                if (tabTitle.equals("Available")) return;

                //Get the room object
                Room r = sharedRooms.getRoom(roomName);
                //Set the room as available
                r.setAvailable();
                //Replace the room in the data structure
                sharedRooms.updateRoom(roomName, r, tabTitle);

                // TODO Handle notification on update() -- Where observers will access
                break;
            //Request to make room unavailable was selected
            //Room state transition: Available -> Unavailable
            case 1:
                //if the tab title is unavailable, then return
                if (tabTitle.equals("Unavailable")) return;

                //Get the room unavailability information
                String reason = availWindow.getReason();
                String time = availWindow.getTimeframe();
                String timeFrame = availWindow.getTimeframeSelection();

                //Get the room from the shared data structure
                Room room = sharedRooms.getRoom(roomName);
                //Set the room as unavailable
                room.setUnavailable(reason, time, timeFrame);

                //Replace the room in the data structure
                sharedRooms.updateRoom(roomName, room, tabTitle);
                break;
        }
    }

    private void setTermDates() {

    }

    private void removeFromAvailabilityTable(Object[] message) {
        //Get the total number of rows in the table
        int rowCount = GUI.getTableDisplayPanel().getRowCount();
        //For each row in the table
        for (int i = 0; i < GUI.getTableDisplayPanel().getRowCount(); i++) {
            //Get the room name attached to the row
            String name = (String) GUI.getTableDisplayPanel().getRoomName(i);
            //if the room name in the row is equal to the name notified
            if (name.equals(message[1])) {
                //Remove it from the table
                GUI.getTableDisplayPanel().removeRow(i);
                i--;
            }
        }
    }
    private void addToAvailabilityTable(Room room){
        for (Availability av : room.getAvailabilities()) {
            String avTimings = av.getFromTime() + av.getFromTimeScale()
                    + " - " + av.getToTime() + av.getToTimeScale();
            Object[] rowData = {
                    room.getRoomName(),
                    room.getRoomType(),
                    room.getRoomCapacity(), avTimings};

            GUI.getTableDisplayPanel().addRow(rowData);
        }
    }
    private void removeFromUnavailablilityTable(Object[] message){
        for(int i = 0; i < GUI.getTableDisplayPanel().getUnavailRowCount(); i++){
            if(GUI.getTableDisplayPanel().getUnavailRoomName(i).equals(message[1]))
            {
                GUI.getTableDisplayPanel().removeUnavailRow(i);
                break;
            }
        }
    }
    private void addToUnavailabilityTable(Object[] message, Unavailability unav){
        GUI.getTableDisplayPanel().addUnavailRow(new Object[]{
                message[1], unav.getReason(),
                unav.getTime() + " " + unav.getTimescale()
        });
    }


    @Override
    public void update(Observable observable, Object o) {
        Object[] message = (Object[]) o;
        if (message[0].equals("Add")) {
            //Get the room by key
            Room room = sharedRooms.getRoom((String) message[1]);
            //Add room entry to room availability table
            addToAvailabilityTable(room);
        }
        else if (message[0].equals("Remove")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            removeFromAvailabilityTable(message);
        }

        else if (message[0].equals("Update")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            removeFromAvailabilityTable(message);

            //Get the room object
            Room room = sharedRooms.getRoom((String) message[1]);

            //If message contains request to display room as available
            if (message[2].equals("Available")) {
                //If the room is not available
                if (!room.isAvailable()) {
                    //Get the unavailability object
                    Unavailability unav = room.getUnavailability();
                    //Add the room information to the unavailable rooms table
                    addToUnavailabilityTable(message, unav);
                }
            }
            //Message contained request to display room as available
            else if(message[2].equals("Unavailable")){
                //If the room is available
                if (room.isAvailable()) {
                    //Write the availability information to the available rooms table
                    addToAvailabilityTable(room);
                    //Remove the room entry from the unavailable rooms table
                    removeFromUnavailablilityTable(message);
                }
            }
        }
    }
}
