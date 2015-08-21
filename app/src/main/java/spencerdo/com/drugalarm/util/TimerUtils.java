package spencerdo.com.drugalarm.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import io.realm.Realm;
import java.util.Calendar;
import java.util.Date;
import spencerdo.com.drugalarm.DrugAlarmReceiver;
import spencerdo.com.drugalarm.model.DrugTimer;

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

  public static DrugTimer refreshTimer(Context c, DrugTimer t) {
    Realm realm = Realm.getInstance(c);
    realm.where(DrugTimer.class).equalTo("name", t.getName()).findFirst();
    realm.beginTransaction();
    DrugTimer timer = realm.where(DrugTimer.class).equalTo("name", t.getName()).findFirst();
    timer.setRepeatedCount(timer.getRepeatedCount() + 1);
    timer.setNextAlarmTime(TimerUtils.createNextAlarmDate(timer.getFixedMinutes()));
    realm.commitTransaction();
    return timer;
  }

  public static void startAlarm(Context c, long milliseconds) {
    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(milliseconds);
    WLog.i("nextAlarm: " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE));
    Intent intent = new Intent(c, DrugAlarmReceiver.class);
    intent.putExtra("time", milliseconds);
    PendingIntent
        alarmIntent = PendingIntent.getBroadcast(c, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager m = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
    m.set(AlarmManager.RTC_WAKEUP, milliseconds, alarmIntent);
  }

  public static DrugTimer removeTimer(Context c, DrugTimer t) {
    Realm realm = Realm.getInstance(c);
    realm.beginTransaction();
    realm.where(DrugTimer.class).equalTo("name", t.getName()).findFirst().removeFromRealm();
    realm.commitTransaction();
    return t;
  }
}
