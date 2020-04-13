package clerk;

import shared.data.SharedRooms;

import java.util.Observable;
import java.util.Observer;

public class BookingController implements Observer {
    private SharedRooms sharedRooms;
    public BookingController(){
        sharedRooms = SharedRooms.getInstance();
        sharedRooms.addObserver(this);
    }

    public void processBookingAction(){
    }

    public void findRoom(){

    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
