package spencerdo.com.drugalarm.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import spencerdo.com.drugalarm.R;
import spencerdo.com.drugalarm.activity.CreateTimerActivity;
import spencerdo.com.drugalarm.model.DrugTimer;
import spencerdo.com.drugalarm.util.WLog;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

  private static final int CREATE_TIMER = 0;

  @Bind(R.id.main_create) FloatingActionButton mActionButton;
  @Bind(R.id.main_list) RecyclerView mList;

  @Override int getLayoutId() {
    return R.layout.frag_main;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.main_create) void onClickCreateDrug() {
    Intent i = new Intent(getMainActivity(), CreateTimerActivity.class);
    startActivityForResult(i, CREATE_TIMER);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CREATE_TIMER && resultCode == Activity.RESULT_OK) {
      Realm realm = Realm.getInstance(getActivity());
      RealmResults<DrugTimer> query = realm.where(DrugTimer.class)
          .findAll();

      for (DrugTimer t : query) {
        WLog.i("name: " + t.getName() + ", time: " + t.getMilliseconds());
      }
    }
  }
}
