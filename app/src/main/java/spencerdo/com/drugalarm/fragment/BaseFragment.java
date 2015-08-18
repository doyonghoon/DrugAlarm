package spencerdo.com.drugalarm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import spencerdo.com.drugalarm.activity.MainActivity;

/**
 * Created by doyonghoon on 2015. 8. 17..
 */
abstract public class BaseFragment extends Fragment {

  abstract int getLayoutId();

  protected boolean hasBackNavigationButton() {
    return false;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View v = null;
    if (getLayoutId() > 0) {
      v = inflater.inflate(getLayoutId(), container, false);
    }
    ButterKnife.bind(this, v);
    return v;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupToolbar();
  }

  private void setupToolbar() {
    ActionBar actionBar = getMainActivity().getSupportActionBar();
    if (actionBar != null) {
      getMainActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(hasBackNavigationButton());
      getMainActivity().getSupportActionBar().setDisplayShowHomeEnabled(hasBackNavigationButton());
    }
  }

  protected MainActivity getMainActivity() {
    return (MainActivity) getActivity();
  }
}
