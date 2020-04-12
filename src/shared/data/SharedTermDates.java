package shared.data;

import java.util.LinkedList;
import java.util.Queue;

public class SharedTermDates {
    private static SharedTermDates instance;
    private final Queue<TermDate> sharedTermDates;

    private SharedTermDates(){
        //Polymorphic assignment to Queue using linked list
        // (which implements, Queue and List Collection interfaces)
        //Shared term dates can now 'behave' as a Queue.
        sharedTermDates = new LinkedList<>();
    }
    public static SharedTermDates getInstance(){
        if(instance == null){ instance = new SharedTermDates();}
        return instance;
    }

    public boolean isEmpty(){
        return sharedTermDates.isEmpty();
    }

    public void addTermDate(TermDate termDate){
        //Add a term date object to the end of the queeu
        sharedTermDates.add(termDate);
    }

    public synchronized TermDate peekAtTermDate(){
        //Return the first element at the head of the queue.
        return sharedTermDates.peek();
    }

    //Implemented but never called
    //Prototype does not call for term dates to be deleted
    private void removeHead(){
        //Remove the first element at the head of the queue.
        sharedTermDates.remove();
    }
}
