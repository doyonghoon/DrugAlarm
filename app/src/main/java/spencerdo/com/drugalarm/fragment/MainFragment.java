package spencerdo.com.drugalarm.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import java.util.List;
import spencerdo.com.drugalarm.R;
import spencerdo.com.drugalarm.activity.CreateTimerActivity;
import spencerdo.com.drugalarm.adapter.MainAdapter;
import spencerdo.com.drugalarm.model.DrugTimer;
import spencerdo.com.drugalarm.util.WLog;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

  private static final int CREATE_TIMER = 0;

  @Bind(R.id.main_content) CoordinatorLayout mLayout;
  @Bind(R.id.main_create) FloatingActionButton mActionButton;
  @Bind(R.id.main_list) RecyclerView mList;

  private MainAdapter mAdapter;

  @Override int getLayoutId() {
    return R.layout.frag_main;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mAdapter = new MainAdapter(this);
    setupCardRecyclerView();
  }

  @OnClick(R.id.main_create) void onClickCreateDrug() {
    Intent i = new Intent(getMainActivity(), CreateTimerActivity.class);
    startActivityForResult(i, CREATE_TIMER);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CREATE_TIMER && resultCode == Activity.RESULT_OK) {
      // update timers
      mAdapter.setItems(getTimers());
    }
  }

  private void setupCardRecyclerView() {
    mAdapter.setItems(getTimers());
    mList.setHasFixedSize(true);
    LinearLayoutManager linearLayoutManager =
        new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    mList.setLayoutManager(linearLayoutManager);
    mList.setAdapter(mAdapter);
  }

  public void onRemoveTimer(final String name) {
    WLog.i("removedName: " + name);
    getActivity().runOnUiThread(new Runnable() {
      @Override public void run() {
        Snackbar.make(mLayout, name + " has been removed.", Snackbar.LENGTH_LONG).show();
      }
    });
  }

  private List<DrugTimer> getTimers() {
    Realm realm = Realm.getInstance(getActivity());
    return realm.where(DrugTimer.class).findAllSorted("nextAlarmTime", true);
  }
}
