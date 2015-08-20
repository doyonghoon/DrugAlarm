package spencerdo.com.drugalarm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import spencerdo.com.drugalarm.R;
import spencerdo.com.drugalarm.model.DrugTimer;
import spencerdo.com.drugalarm.util.TimerUtils;

/**
 * Created by doyonghoon on 2015. 8. 19..
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.TimerHolder> {

  private List<DrugTimer> mTimers;

  public MainAdapter() {
    mTimers = new ArrayList<>();
  }

  public void setItems(List<DrugTimer> timers) {
    if (!mTimers.isEmpty()) {
      mTimers.clear();
    }
    mTimers.addAll(timers);
    notifyDataSetChanged();
  }

  @Override public TimerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_timer, parent, false);
    return new TimerHolder(v);
  }

  @Override public void onBindViewHolder(TimerHolder holder, final int position) {
    holder.mName.setText(mTimers.get(position).getName());
    holder.mMinutes.setText(getMinites(mTimers.get(position).getNextAlarmTime()));
    holder.mFixedMinutes.setText(mTimers.get(position).getFixedMinutes() + " fixed minutes");
    holder.mRepeatedCount.setText(mTimers.get(position).getRepeatedCount() + " repeated");
    holder.mRefreshButton.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        DrugTimer refreshed = TimerUtils.refreshTimer(v.getContext(), mTimers.get(position));
        TimerUtils.startAlarm(v.getContext(), refreshed.getNextAlarmTime());
        notifyDataSetChanged();
      }
    });
  }

  @Override public int getItemCount() {
    return mTimers.size();
  }

  private String getMinites(long milliseconds) {
    long now = Calendar.getInstance().getTime().getTime();
    long minutes = now - milliseconds;
    boolean alreadyOver = now > milliseconds;
    minutes = Math.abs(minutes);
    return String.format("%d %s", TimeUnit.MILLISECONDS.toMinutes(minutes), (alreadyOver
        ? " Minutes Over" : "Minutes Left"));
  }

  public static class TimerHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.card_timer_name) TextView mName;
    @Bind(R.id.card_timer_minutes) TextView mMinutes;
    @Bind(R.id.card_timer_refresh) Button mRefreshButton;
    @Bind(R.id.card_timer_repeat_count) TextView mRepeatedCount;
    @Bind(R.id.card_timer_fixed_minutes) TextView mFixedMinutes;

    public TimerHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
