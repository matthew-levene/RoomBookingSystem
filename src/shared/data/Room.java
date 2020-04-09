package shared.data;

import java.util.ArrayList;

public class Room {

    private String roomName, roomType, roomCapacity;
    private ArrayList<Availability> availabilities;

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
}
