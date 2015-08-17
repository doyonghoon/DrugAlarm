package spencerdo.com.drugalarm.model;

import java.util.Date;

/**
 * Created by doyonghoon on 2015. 8. 14..
 */
public class Counter {

  private int mCount;
  private Date mDate;

  public Date getDate() {
    return mDate;
  }

  public void setDate(Date date) {
    this.mDate = date;
  }

  public int getCount() {
    return mCount;
  }

  public void setmCount(int count) {
    this.mCount = count;
  }
}
