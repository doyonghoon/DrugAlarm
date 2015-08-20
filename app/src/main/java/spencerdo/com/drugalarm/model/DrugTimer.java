package spencerdo.com.drugalarm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by doyonghoon on 2015. 8. 18..
 */
public class DrugTimer extends RealmObject {

  /**
   * auto increment
   * */
  @PrimaryKey
  private int primaryKey;

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

  public int getPrimaryKey() {
    return primaryKey;
  }

  public void setPrimaryKey(int primaryKey) {
    this.primaryKey = primaryKey;
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
