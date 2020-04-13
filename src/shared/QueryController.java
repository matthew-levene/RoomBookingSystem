package shared;

import clerk.BookingClerkUI;
import manager.RoomManagerUI;
import shared.data.*;
import shared.ui.TableDisplayPanel;
import shared.ui.TableSearchPanel;
import utils.DateUtils;

import javax.swing.*;
import java.util.Date;
import java.util.Set;

public class QueryController {
   private TableSearchPanel tableSearchPanel;
   private TableDisplayPanel tableDisplayPanel;
   private SharedRooms sharedRooms;
   private SharedTermDates sharedTermDates;

    public QueryController(TableSearchPanel tsp, TableDisplayPanel tdp){
        this.tableSearchPanel = tsp;
        this.tableDisplayPanel = tdp;
        sharedRooms = SharedRooms.getInstance();
        sharedTermDates = SharedTermDates.getInstance();
    }

    public void findRoom(){
        //Check if a room type has been selected
        int selected = tableSearchPanel.roomTypeSelected();
        //If selected equals -1, a room type has not been selected
        if(selected == -1){
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    "Please select a room type from the list");
            return;
        }
        //Get the selected value from the room type list
        String roomType = tableSearchPanel.getRoomType();
        //Get the date from the date text field
        String searchDate = tableSearchPanel.getDate();
        //If the value from the date field is empty
        if(searchDate.isEmpty()){
            JOptionPane.showMessageDialog(new JFrame(), "Please enter a date value in format dd/mm/yyyy or dd/mm/yy");
            return;
        }
        //Check if the date is in a valid format e.g. dd-mm-yyyy
        boolean dateValid = DateUtils.areValidDates(searchDate);
        //If the date is invalid
        if(!dateValid){
            JOptionPane.showMessageDialog(
                    new JFrame(),
                    "Date entered must be in the format of dd-mm-yyyy or dd-mm-yy");
            return;
        }
        //Check if the date is before or during the term in shared term dates
        TermDate termDate = sharedTermDates.peekAtTermDate();
        Date sDate = DateUtils.toDate(searchDate);
        //Get a list of keys from the shared room data structure
        Set<String> keys = sharedRooms.getKeys();
        //Clear the availability table
        tableDisplayPanel.clearAvailabilityTable();

        //If search date is before term starts or after term ends (holidays)
        if(sDate.before(termDate.getStartDate()) || sDate.after(termDate.getEndDate())){
            findMatchesInHolidays(keys, roomType, searchDate);
        }
        //If search date is after term starts and before term ends (term-time)
        else if(sDate.after(termDate.getStartDate()) && sDate.before(termDate.getEndDate())){
            findMatchesInTerm(keys, roomType, searchDate);
        }
    }
    private void findMatchesInTerm(Set<String> keys, String roomType, String searchDate){
        //For each key in the shared rooms structure
        for(String key : keys){
            //Get the room
            Room room = sharedRooms.getRoom(key);
            //If the room is available
            if(room.isAvailable()){
                //Check if the room type matches the selected room type
                if(room.getRoomType().equals(roomType)) {
                    for (Availability av : room.getAvailabilities()) {
                        //If the availability is available
                        if (av.isAvailable() && (av.getDate().equals(searchDate))) {
                            //If the availability is from PM - PM
                            if (av.getFromTimeScale().equals("PM") && av.getToTimeScale().equals("PM")) {
                                //Add the entry to the availability table
                                addToTable(room, av);
                            }
                        }
                    }
                }
            }
        }
        //Prompt user that due to term time policy, only PM availabilities are allowed
        JOptionPane.showMessageDialog(
                new JFrame(),
                "Search date is within term time. " +
                        "\n Only PM availability slots are available"
        );
    }
    private void findMatchesInHolidays(Set<String> keys, String roomType, String searchDate){
        //Find out which checkboxes were selected
        boolean isAMChecked = tableSearchPanel.isAMSelected();
        boolean isPMChecked = tableSearchPanel.isPMSelected();

        for(String key : keys) {
            //Get the room
            Room room = sharedRooms.getRoom(key);
            //Check if the room is available
            if (room.isAvailable()) {
                //Check if the room type matches the selected room type
                if (room.getRoomType().equals(roomType)) {
                    for (Availability av : room.getAvailabilities()) {
                        //If the availability is available
                        if (av.isAvailable() && (av.getDate().equals(searchDate))) {
                            //If both AM and PM are checked
                            if((isAMChecked && isPMChecked)){
                                addToTable(room, av);
                            }
                            //If only AM are checked
                            else if(isAMChecked && av.getFromTimeScale().equals("AM")){
                                addToTable(room, av);
                            }
                            //If AM and PM are checked
                            else if(isPMChecked && av.getFromTimeScale().equals("PM")){
                                addToTable(room, av);
                            }

                        }
                    }
                }
            }
        }
    }

    //TableDisplayPanel table manipulation methods
    public void removeRoomFromAvailableTable(Object[] message) {
        //For each row in the table
        for (int i = 0; i < tableDisplayPanel.getRowCount(); i++) {
            //Get the room name attached to the row
            String name = (String) tableDisplayPanel.getRoomName(i);
            //if the room name in the row is equal to the name notified
            if (name.equals(message[1])) {
                //Remove it from the table
                tableDisplayPanel.removeRow(i);
                i--;
            }
        }
    }
    public void addRoomToAvailableTable(Room room){
        for (Availability av : room.getAvailabilities()) {
            if(av.isAvailable()) {
                addToTable(room, av);
            }
        }
    }
    public void removeFromUnavailableTable(Object[] message){
        for(int i = 0; i < tableDisplayPanel.getUnavailRowCount(); i++){
            if(tableDisplayPanel.getUnavailRoomName(i).equals(message[1]))
            {
                tableDisplayPanel.removeUnavailRow(i);
                break;
            }
        }
    }
    public void addToUnavailableTable(Object[] message, Unavailability unav){
        tableDisplayPanel.addUnavailRow(new Object[]{
                message[1], unav.getReason(),
                unav.getTime() + " " + unav.getTimescale()
        });
    }
    private void addToTable(Room room, Availability av){
        String avTimings = av.getFromTime() + av.getFromTimeScale()
                + "-" + av.getToTime() + av.getToTimeScale();
        Object[] rowData = {
                room.getRoomName(),
                room.getRoomType(),
                room.getRoomCapacity(), avTimings, av.getDate()
        };
        tableDisplayPanel.addRow(rowData);
    }
}
