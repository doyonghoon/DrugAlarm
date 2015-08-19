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
  private int count;

  /**
   * The date that will be alarmed.
   * */
  private long milliseconds;

  public long getMilliseconds() {
    return milliseconds;
  }

  public void setMilliseconds(long milliseconds) {
    this.milliseconds = milliseconds;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCount() {
    return count;
  }

  public String getName() {
    return name;
  }
}
