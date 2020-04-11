package shared.data;

public class TermDate {

    private String startDate, endDate;

    public TermDate(String startDate, String endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
