package spencerdo.com.drugalarm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Created by doyonghoon on 2015. 8. 17..
 */
abstract public class BaseFragment extends Fragment {
  abstract int getLayoutId();

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
}
