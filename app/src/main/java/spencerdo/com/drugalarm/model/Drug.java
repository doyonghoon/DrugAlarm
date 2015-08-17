package spencerdo.com.drugalarm.model;

import java.util.List;

/**
 * Created by doyonghoon on 2015. 8. 14..
 */
public class Drug {

  private String mName;
  private List<Counter> mCounters;

  public List<Counter> getCounters() {
    return mCounters;
  }

  public void setCounters(List<Counter> counters) {
    this.mCounters = counters;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    this.mName = name;
  }
}
