package shared.data;

import java.util.HashMap;
import java.util.Observable;
import java.util.Set;

public class SharedRooms extends Observable {
    private static SharedRooms instance;
    private HashMap<String, Room> sharedRooms;

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
        setChanged();
        notifyObservers(new Object[]{"Add", key});
    }

    public void removeRoom(String key){
        sharedRooms.remove(key);
        //TODO implement update for booking clerk
        setChanged();
        notifyObservers(new Object[]{"Remove", key});
    }

    public synchronized void updateRoom(String key, Room room, String action){
        //TODO implement update for booking clerk
        sharedRooms.replace(key, room);
        setChanged();
        notifyObservers(new Object[]{"Update", key, action});
    }

    public synchronized Room getRoom(String key){
        if(sharedRooms.containsKey(key)){
            return sharedRooms.get(key);
        }
        return null;
    }

    public Set<String> getKeys(){
        return sharedRooms.keySet();
    }
}
