package spencerdo.com.drugalarm.model;

import io.realm.RealmObject;

/**
 * Created by doyonghoon on 2015. 8. 18..
 */
public class DrugTimer extends RealmObject {

  /**
   * Name of the drug.
   * */
  private String name;

  /**
   * Number of alarms made.
   * */
  private int repeatedCount;

  /**
   * The date that will be alarmed.
   * */
  private long nextAlarmTime;

  private int fixedMinutes;

  public int getFixedMinutes() {
    return fixedMinutes;
  }

  public void setFixedMinutes(int fixedMinutes) {
    this.fixedMinutes = fixedMinutes;
  }

  public long getNextAlarmTime() {
    return nextAlarmTime;
  }

  public void setNextAlarmTime(long milliseconds) {
    this.nextAlarmTime = milliseconds;
  }

  public void setRepeatedCount(int repeatedCount) {
    this.repeatedCount = repeatedCount;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getRepeatedCount() {
    return repeatedCount;
  }

  public String getName() {
    return name;
  }
}
