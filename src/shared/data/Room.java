package shared.data;

import java.util.ArrayList;

public class Room {

    private String roomName, roomType, roomCapacity;
    private ArrayList<Availability> availabilities;
    private Unavailability unavailability;

    public Room(String name, String type, String capacity, ArrayList<Availability> availabilities){
        roomName = name;
        roomType = type;
        roomCapacity = capacity;
        this.availabilities = availabilities;
    }

    public String getRoomName(){
        return roomName;
    }

    public String getRoomType(){
        return roomType;
    }

    public String getRoomCapacity(){
        return roomCapacity;
    }

    public ArrayList<Availability> getAvailabilities() {return availabilities; }

    public void setUnavailable(String reason, String time, String timescale){
        unavailability = new Unavailability(reason, time, timescale);
    }

    public void setAvailable(){
        unavailability = null;
    }

    public boolean isAvailable(){
        if(unavailability == null){ return true; }
        return false;
    }
}
