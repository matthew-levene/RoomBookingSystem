package manager;

import java.awt.event.ActionEvent;

public class RoomController {

    public RoomController(){ }

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

    }

    private void removeRoom(){

    }

    private void manageAvailability(){

    }

    private void setTermDates(){

    }
}
