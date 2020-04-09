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
            //Add Room functionality
            addRoom();
        }
        else if(eventSource == actionPanel.getRemoveButton()) {
            //Remove Room functionality
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

        }


    }


}
