package shared.data;

import java.util.HashMap;
import java.util.Observable;

public class SharedRooms extends Observable {
    private static SharedRooms instance;
    private HashMap<String, Room> sharedRooms;
    private Object[] notification = new Object[2];

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

    public void updateRoom(String key, Room room){
        sharedRooms.replace(key, room);
        setChanged();
        notifyObservers(new Object[]{"Update", key});
    }

    public synchronized Room getRoom(String key){
        if(sharedRooms.containsKey(key)){
            return sharedRooms.get(key);
        }
        return null;
    }
}
