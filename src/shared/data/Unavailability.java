package shared.data;

public class Unavailability {

    private String reason, time, timescale;

    public Unavailability(String reason, String time, String timescale){
        this.reason = reason;
        this.time = time;
        this.timescale = timescale;
    }

    public String getReason() {
        return reason;
    }

    public String getTime() {
        return time;
    }

    public String getTimescale() {
        return timescale;
    }
}
