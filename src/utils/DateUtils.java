package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static final boolean areValidDates(String ... dates){
        for(String date : dates){
            //If the date parses successfully, then continue
            try{ dateFormat.parse(date); }
            catch(ParseException parseException){
                //If the date is not in a valid format, return false
                return false;
            }
        }
        //If all dates are valid, return true;
        return true;
    }

    public static final boolean isValidDateOrder(String firstStart, String firstEnd, String secondStart, String secondEnd){
        Date firStart, firEnd, secStart, secEnd;
        try{
             firStart = dateFormat.parse(firstStart);
             firEnd = dateFormat.parse(firstEnd);
             secStart = dateFormat.parse(secondStart);
             secEnd = dateFormat.parse(secondEnd);
        }
        //If the dates are  not in a valid format (dd/mm/yyyy), return false
        catch(ParseException parseException) {
            return false;
        }

        //Enforces that this date must come at the start: pos 1/4
        //If the first start date comes after any other date, return false
        if(firStart.after(firEnd) || firStart.after(secStart) || firStart.after(secEnd)){
            return false;
        }
        //Enforces that this date must come in middle: pos 2/4
        //If the first end date comes before the first start or after the second dates return false
        if(firEnd.before(firStart) || firEnd.after(secStart) || firEnd.after(secEnd)){
            return false;
        }
        //Enforces that this date must come  in the middle: pos 3/4
        //if the second start date comes after second end date or before first dates, return false
        if(secStart.after(secEnd) || secStart.before(firEnd) || secStart.before(firStart)){
            return false;
        }
        //Enforces that this date must come at the end: pos 4/4
        //if the second end date comes before any other dates, return false
        if(secEnd.before(secStart) || secEnd.before(firStart) || secEnd.before(firEnd)){
            return false;
        }

        //Returns true if the order of dates is correct
        return true;




    }
}
