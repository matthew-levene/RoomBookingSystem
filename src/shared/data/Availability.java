package shared.data;

public class Availability {

    private String fromTime, toTime, date, fromTimeScale, toTimeScale, day;

    public Availability(String date, String from, String to, String fromSelected, String toSelected, String daySelected){
        this.date = date;
        fromTime = from;
        toTime = to;
        fromTimeScale = fromSelected; //Stores whether AM/PM was selected from the 'from' time
        toTimeScale = toSelected; //Stores whether AM/PM was selected from the 'to' time
        day = daySelected;
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

    public  String getToTimeScale(){
        return toTimeScale;
    }

    public String getDay(){
        return day;
    }


}
