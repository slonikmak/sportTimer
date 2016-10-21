package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by anton on 18.10.16.
 */
public class Utils {
    public static  String prepareTime(long millis){
        DateFormat formatter = new SimpleDateFormat("mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        return formatter.format(calendar.getTime());
    }

}
