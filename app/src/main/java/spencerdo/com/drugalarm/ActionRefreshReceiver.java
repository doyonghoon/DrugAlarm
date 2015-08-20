package spencerdo.com.drugalarm;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import io.realm.Realm;
import spencerdo.com.drugalarm.model.DrugTimer;
import spencerdo.com.drugalarm.util.TimerUtils;

/**
 * Created by doyonghoon on 2015. 8. 19..
 */
public class ActionRefreshReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    long milliseconds = intent.getLongExtra("time", 0);
    DrugTimer timer = TimerUtils.refreshTimer(context, getTimer(context, milliseconds));
    TimerUtils.startAlarm(context, timer.getNextAlarmTime());

    NotificationManager m =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    m.cancelAll();
  }

  private DrugTimer getTimer(Context c, long milliseconds) {
    return Realm.getInstance(c)
        .where(DrugTimer.class)
        .equalTo("nextAlarmTime", milliseconds)
        .findFirst();
  }
}
