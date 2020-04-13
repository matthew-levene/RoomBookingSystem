package shared.data;

import java.util.ArrayList;

public class SharedBookings {
    private static SharedBookings instance;
    private ArrayList<Booking> sharedBookings;

    private SharedBookings(){
        sharedBookings = new ArrayList<>();
    }
    public static synchronized SharedBookings getInstance(){
        if(instance == null){ instance = new SharedBookings(); }
        return instance;
    }

    public void addBooking(Booking booking){
        sharedBookings.add(booking);
    }

    public Booking getBooking(int index){
        return sharedBookings.get(index);
    }

    public ArrayList<Booking> getAllBookings(){
        return sharedBookings;
    }

    //Implemented but never called
    //Prototype does not call for bookings to be deleted
    private void removeBooking(int index){
        //Remove the element at the specified index
        sharedBookings.remove(index);
    }
}
