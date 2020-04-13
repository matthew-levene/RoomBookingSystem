package shared.data;

public class Booking {
    private String organisation;
    private String phone;
    private String notes;
    private String roomName;
    private String dateTime;

    public Booking(String organisation, String phone, String notes, String roomName, String dateTime){
        this.organisation = organisation;
        this.phone = phone;
        this.notes = notes;

        this.roomName = roomName;
        this.dateTime = dateTime;
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

    public String getDateTime(){
        return dateTime;
    }
}
