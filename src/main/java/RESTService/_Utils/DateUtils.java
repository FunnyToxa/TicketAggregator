package RESTService._Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * Получение даты из строки
     * @param stringDate формата yyyy-MM-dd
     * @return
     * @throws ParseException
     */
    public static Date getDateFromString(String stringDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(stringDate);
    }
}
