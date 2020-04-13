package manager;

import manager.dialogs.AddRoomDialog;
import manager.dialogs.AvailabilityDialog;
import manager.dialogs.TermDatesDialog;
import shared.QueryController;
import shared.data.*;
import utils.DateUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class RoomController implements Observer {

    private SharedRooms sharedRooms;
    private SharedTermDates sharedTermDates;
    private RoomManagerUI GUI;
    QueryController qController;

    public RoomController(RoomManagerUI GUI) {
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);
        sharedTermDates = SharedTermDates.getInstance();
        this.GUI = GUI;

         qController = new QueryController(GUI.getTableSearchPanel(), GUI.getTableDisplayPanel());
    }
    //TODO Also implement method for BookingController

    public void processRoomAction(RoomActionPanel actionPanel, ActionEvent event) {
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
            //Method call to set the term dates in the system
            setTermDates();
        }
    }

    private void addRoom() {
        //Check if term dates have been set before adding a room
        if(sharedTermDates.isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Please set the term dates before adding a room");
            return;
        }

        //Open Add Room Dialog
        AddRoomDialog addRoomWindow = new AddRoomDialog();
        //If the action is equal to one, the submit button was pressed
        if (addRoomWindow.getAction() == 1) {
            //Get the room availability array
            ArrayList<Availability> availabilities = addRoomWindow.getAvailability();
            //Get the new room information
            String name = addRoomWindow.getRoomName();
            String type = addRoomWindow.getRoomType();
            String capacity = addRoomWindow.getRoomCapacity();

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
        //Open the term dates dialog
        TermDatesDialog termDatesWindow = new TermDatesDialog();

        //If the action is equal to 1
        if(termDatesWindow.getAction() == 1){
            String firstStart, firstEnd, secondStart, secondEnd;

            //Get the term dates from the window
            firstStart = termDatesWindow.getFirstStartDate();
            firstEnd = termDatesWindow.getFirstEndDate();
            secondStart = termDatesWindow.getScndStartDate();
            secondEnd = termDatesWindow.getScndEndDate();

            Date firStart = DateUtils.toDate(firstStart);
            Date firEnd = DateUtils.toDate(firstEnd);
            Date secStart = DateUtils.toDate(secondStart);
            Date secEnd = DateUtils.toDate(secondEnd);
            //Add a first term
            sharedTermDates.addTermDate(new TermDate(firStart, firEnd));
            //Add a second term
            sharedTermDates.addTermDate(new TermDate(secStart, secEnd));
        }


    }

    //TODO Also implement method for BookingController
    //TableSearchPanel search execution methods
    public void findRoom(){
        qController.findRoom();
    }

    //TODO Implement for BookingController
    @Override
    public void update(Observable observable, Object o) {
        Object[] message = (Object[]) o;
        if (message[0].equals("Add")) {
            //Get the room by key
            Room room = sharedRooms.getRoom((String) message[1]);
            //Add room entry to room availability table
            qController.addRoomToAvailableTable(room);
        }
        else if (message[0].equals("Remove")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            qController.removeRoomFromAvailableTable(message);
        }

        else if (message[0].equals("Update")) {
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            qController.removeRoomFromAvailableTable(message);

            //Get the room object
            Room room = sharedRooms.getRoom((String) message[1]);

            //Message contained requested to change room state: Available -> Unavailable
            if (message[2].equals("Available")) {
                //If the room is not available
                if (!room.isAvailable()) {
                    //Get the unavailability object
                    Unavailability unav = room.getUnavailability();
                    //Add the room information to the unavailable rooms table
                    qController.addToUnavailableTable(message, unav);
                }
            }
            //Message contained request to change room state: Unavilable -> Available
            else if(message[2].equals("Unavailable")){
                //If the room is available
                if (room.isAvailable()) {
                    //Write the availability information to the available rooms table
                    qController.addRoomToAvailableTable(room);
                    //Remove the room entry from the unavailable rooms table
                    qController.removeFromUnavailableTable(message);
                }
            }
        }
    }
}
