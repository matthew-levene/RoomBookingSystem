package shared.data;

public class Availability {

    private String fromTime, toTime, date, fromTimeScale, toTimeScale;
    private boolean isAvailable;

    public Availability(String date, String from, String to, String fromSelected, String toSelected){
        this.date = date;
        fromTime = from;
        toTime = to;
        fromTimeScale = fromSelected; //Stores whether AM/PM was selected from the 'from' time
        toTimeScale = toSelected; //Stores whether AM/PM was selected from the 'to' time
        isAvailable = true; //isAvailable starts as true by default
    }

    public String getDate(){
        return  date;
    }

    public String getFromTime(){
        return fromTime;
    }

    public String getFromTimeScale(){
        return fromTimeScale;
    }

    public String getToTime(){
        return toTime;
    }

    public String getToTimeScale(){
        return toTimeScale;
    }

    public void setAvailability(boolean option){ isAvailable = option; }

    public boolean isAvailable(){ return isAvailable; }


}
