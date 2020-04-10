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

    public RoomController(RoomManagerUI GUI){
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);

        this.GUI = GUI;
    }

    public void handleButtonEvent(RoomActionPanel actionPanel, ActionEvent event){
        Object eventSource = event.getSource();
        if(eventSource == actionPanel.getAddButton()){
            //Method call to add a new room to the system
            addRoom();
        }
        else if(eventSource == actionPanel.getRemoveButton()) {
            //Method call to remove existing room from system
            removeRoom();
        }
        else if(eventSource == actionPanel.getAvailabilityButton()){
            //Method call to change the availability status of a room
            manageRoomAvailability();
        }
        else {
            //Manage Term Dates functionality
            setTermDates();
        }
    }

    private void addRoom(){
        AddRoomDialog addRoomWindow = new AddRoomDialog();
        //If the action is equal to one, the submit button was pressed
        if(addRoomWindow.getAction() == 1){
            //Get the room availability array
            ArrayList<Availability> availabilities = addRoomWindow.getAvailability();
            //Get the new room information
            String name = addRoomWindow.getRoomName();
            String type = addRoomWindow.getRoomType();
            String capacity = addRoomWindow.getRoomCapacity();

            //TODO check if room already exists in data structure using roomname key
            if(sharedRooms.keyExists(name)){
                JOptionPane.showMessageDialog(
                        new JFrame(),
                        "Unable to create new room; " + name + " already exists");
            }
            else {
                //Create a new room and add it to the shared data structure
                sharedRooms.addRoom(name, new Room(name, type, capacity, availabilities));
            }
        }
    }

    private void removeRoom(){
        //Get the selected row from the table
        int selectedRow = GUI.getTableDisplayPanel().getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(new JFrame(), "Please select a room to remove from the table");
            return;
        }
        //Get the room name from the table
        String roomName = (String) GUI.getTableDisplayPanel().getRoomName(selectedRow);
        //Remove the room from the shared rooms data structure
        sharedRooms.removeRoom(roomName);
    }

    private void manageRoomAvailability(){
        //Get the title of the tab selected
        String tabTitle = GUI.getTableDisplayPanel().getTabTitle();
        String roomName;
        int selectedRow;
        if(tabTitle.equals("Available")){
            //Get the selected row from the available table
            selectedRow = GUI.getTableDisplayPanel().getSelectedRow();
            //If a row is not selected
            if(selectedRow == -1){
                //Show error message to user
                JOptionPane.showMessageDialog(new JFrame(), "Please select a row from the table");
                return;
            }
            //Get the room name from the selected row
            roomName = (String) GUI.getTableDisplayPanel().getRoomName(selectedRow);
        }
        else{
            //Get the selected row from the unavailable rooms table
            selectedRow = GUI.getTableDisplayPanel().getSelectedUnavailRow();
            //If a row is not selected
            if(selectedRow == -1){
                //Show error message to user
                JOptionPane.showMessageDialog(new JFrame(), "Please select a row from the table");
                return;
            }
            //Get the room name from the selected row
            roomName = (String) GUI.getTableDisplayPanel().getUnavailRoomName(selectedRow);
        }

        //Open the availability dialog window
        AvailabilityDialog availWindow = new AvailabilityDialog();

        switch(availWindow.getAction()){
            ////Request to make room available was selected
            case 0:
                //If the tab title is available, then return
                if(tabTitle.equals("Available")) return;

                //TODO - Implement functionality to make room available
                // Get the room, update it to available, update the room
                // Handle notification on update() -- Where observers will access
                break;
            //Request to make room unavailable was selected
            case 1:
                //if the tab title is unavailable, then return
                if(tabTitle.equals("Unavailable")) return;

                //Get the room unavailability information
                String reason = availWindow.getReason();
                String time = availWindow.getTimeframe();
                String timeFrame = availWindow.getTimeframeSelection();

                //Get the room from the shared data structure
                Room room = sharedRooms.getRoom(roomName);
                room.setUnavailable(reason,time,timeFrame);

                //Replace the room in the data strucutre
                sharedRooms.updateRoom(roomName, room);
                break;
        }
    }


    private void setTermDates(){

    }

    private void updateDisplayTable(Object[] message){
        //Get the total number of rows in the table
        int rowCount = GUI.getTableDisplayPanel().getRowCount();
        //For each row in the table
        for(int i = 0; i < GUI.getTableDisplayPanel().getRowCount(); i++){
            //Get the room name attached to the row
            String name = (String) GUI.getTableDisplayPanel().getRoomName(i);
            //if the room name in the row is equal to the name notified
            if(name.equals(message[1])){
                //Remove it from the table
                GUI.getTableDisplayPanel().removeRow(i);
                i--;
            }
        }
    }


    @Override
    public void update(Observable observable, Object o) {
        Object[] message = (Object[]) o;
        if(message[0].equals("Add")){
            //Get the room by key
            Room room = sharedRooms.getRoom((String) message[1]);
            //Extract the room information into an array
            for(Availability av : room.getAvailabilities()){
                String  avTimings = av.getFromTime() + av.getFromTimeScale()
                        + " - " + av.getToTime() + av.getToTimeScale();
                Object[] rowData = {
                        room.getRoomName(),
                        room.getRoomType(),
                        room.getRoomCapacity(), avTimings};

                //Add the rows to the table
                GUI.getTableDisplayPanel().addRow(rowData);
            }
        }
        else if(message[0].equals("Remove")){
            /* Update the TableDisplayPanel to remove
            * any entries containing the same room name*/
            updateDisplayTable(message);
        }
        else if(message[0].equals("Update")){
            /* Update the TableDisplayPanel to remove
             * any entries containing the same room name*/
            updateDisplayTable(message);

            //Get the room object that was changed
            Room room = sharedRooms.getRoom((String) message[1]);
            //If the room is unavailable
            if(!room.isAvailable()) {
                //Get the unavailability object
                Unavailability unav = room.getUnavailability();
                //Add the room unavailability information to the unavailable rooms table
                GUI.getTableDisplayPanel().addUnavailRow(new Object[]{
                        message[1], unav.getReason(),
                        unav.getTime()  + " " + unav.getTimescale()
                });

            }




        }
    }
}
