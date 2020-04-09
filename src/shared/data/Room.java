package shared.data;

public class Room {

    private String roomName, roomType, roomCapacity;

    public Room(String name, String type, String capacity){
        roomName = name;
        roomType = type;
        roomCapacity = capacity;
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
}
