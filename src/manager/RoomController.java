package manager;

import manager.dialogs.AddRoomDialog;
import shared.data.Availability;
import shared.data.Room;
import shared.data.SharedRooms;
import shared.ui.TableDisplayPanel;
import shared.ui.TableSearchPanel;

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
            //Manage Availability functionality
            manageAvailability();
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

    private void manageAvailability(){

    }

    private void setTermDates(){

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


    }


}
