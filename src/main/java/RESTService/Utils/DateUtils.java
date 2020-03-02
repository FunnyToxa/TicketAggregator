package RESTService.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Утильный класс для операций с датой
 */
public class DateUtils
{
     /**
     * метод сдвигания даты на определенное кол-во минут
     * @param date
     * @param minutes (можно указываьть отризательное значение)
     * @return
     */
    public static Date rollMinutes(Date date, int minutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.roll(Calendar.MINUTE, minutes);
        System.out.println(cal.getTime());
        return cal.getTime();
    }
}
