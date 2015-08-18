package spencerdo.com.drugalarm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import spencerdo.com.drugalarm.util.WLog;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends BaseFragment {

  @Bind(R.id.main_create) FloatingActionButton mActionButton;
  @Bind(R.id.main_list) RecyclerView mList;

  @Override int getLayoutId() {
    return R.layout.frag_main;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @OnClick(R.id.main_create) void onClickCreateDrug() {
    WLog.i("add create drug fragment..");
    CreateDrugFragment f = new CreateDrugFragment();
    getFragmentManager()
        .beginTransaction()
        .add(R.id.fragment, f, "create")
        .addToBackStack("create")
        .commit();
  }
}
