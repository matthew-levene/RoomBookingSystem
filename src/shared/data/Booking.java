package shared.data;

public class Booking {
    private String organisation;
    private String phone;
    private String notes;
    private String roomName;
    private String time;
    private String date;

    public Booking(String organisation, String phone, String notes, String roomName, String time, String date){
        this.organisation = organisation;
        this.phone = phone;
        this.notes = notes;

        this.roomName = roomName;
        this.time = time;
        this.date = date;
    }

    public String getOrganisation() {
        return organisation;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    public String getRoomName(){
        return roomName;
    }

    public String getTime(){
        return time;
    }

    public String getDate() { return date; }
}
