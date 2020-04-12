package shared.data;

import java.util.Date;

public class TermDate {

    private Date startDate, endDate;

    public TermDate(Date startDate, Date endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
