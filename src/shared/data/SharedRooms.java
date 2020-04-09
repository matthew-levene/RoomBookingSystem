package shared.data;

import java.util.HashMap;
import java.util.Observable;

public class SharedRooms extends Observable {
    private static SharedRooms instance;
    private HashMap<String, Room> sharedRooms;
    private Object[] notification = {};

    private SharedRooms(){
        sharedRooms = new HashMap<>();
    }

    public static synchronized SharedRooms getInstance(){
        if(instance == null){ instance = new SharedRooms(); }
        return instance;
    }

    public boolean keyExists(String key){
        return sharedRooms.containsKey(key);
    }

    public void addRoom(String key, Room value){
        sharedRooms.put(key, value);
        //TODO implement update for booking clerk
        Object[] notification = {"Add", key};
        setChanged();
        notifyObservers(notification);
    }

    public void removeRoom(String key){
        if(sharedRooms.containsKey(key)){
            sharedRooms.remove(key);
        }

        //TODO Notify observers that room has been removed
    }

    public synchronized Room getRoom(String key){
        if(sharedRooms.containsKey(key)){
            return sharedRooms.get(key);
        }
        return null;
    }
}
