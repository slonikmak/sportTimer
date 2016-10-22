package utils;

import javafx.util.converter.NumberStringConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by anton on 18.10.16.
 */
public class Utils {
    public static class CustomStringConverter extends NumberStringConverter{
        @Override
        public String toString(Number value){
            long newValue = value.longValue()/1000;
            return super.toString(newValue);
        }
    }

    public static  String prepareTime(long millis){
        DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        return formatter.format(calendar.getTime());
    }

}
