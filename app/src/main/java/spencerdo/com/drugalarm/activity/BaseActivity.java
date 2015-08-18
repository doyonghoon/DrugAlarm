package spencerdo.com.drugalarm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;

/**
 * Created by doyonghoon on 2015. 8. 17..
 */
abstract public class BaseActivity extends AppCompatActivity {

  protected abstract int getLayoutId();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);
  }
}
