package spencerdo.com.drugalarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import io.realm.Realm;
import spencerdo.com.drugalarm.model.DrugTimer;
import spencerdo.com.drugalarm.util.WLog;

/**
 * Created by doyonghoon on 2015. 8. 19..
 */
public class DrugAlarmReceiver extends BroadcastReceiver {

  @Override public void onReceive(Context context, Intent intent) {
    DrugTimer t = getTimer(context, intent.getLongExtra("time", 0));
    WLog.i("name: " + t.getName() + ", fixedMinutes: " + t.getFixedMinutes());
    NotificationCompat.Style style = new NotificationCompat.BigTextStyle();
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context).setStyle(style)
        .setSmallIcon(R.mipmap.ic_alarm_add_white_24dp)
        .setContentTitle(t.getName())
        .setContentText("time to take!")
        .setTicker(t.getName())
        .addAction(R.mipmap.ic_done_black_24dp, "REFRESH", getActionRefresh(context, t))
        .setAutoCancel(false);
    NotificationManager m = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    m.notify(1, builder.build());
  }

  private PendingIntent getActionRefresh(Context c, DrugTimer t) {
    Intent intent = new Intent(c, ActionRefreshReceiver.class);
    intent.putExtra("time", t.getNextAlarmTime());
    return PendingIntent.getBroadcast(c, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
  }

  private DrugTimer getTimer(Context c, long milliseconds) {
    return Realm.getInstance(c)
        .where(DrugTimer.class)
        .equalTo("nextAlarmTime", milliseconds)
        .findFirst();
  }
}
