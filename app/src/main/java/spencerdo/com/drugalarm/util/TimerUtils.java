package spencerdo.com.drugalarm.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by doyonghoon on 2015. 8. 19..
 */
public class TimerUtils {

  public static long createNextAlarmDate(int minutes) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.MINUTE, minutes);
    return c.getTime().getTime();
  }
}
