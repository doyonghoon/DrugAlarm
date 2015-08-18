package spencerdo.com.drugalarm.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import butterknife.Bind;
import spencerdo.com.drugalarm.R;

public class MainActivity extends BaseActivity {

  @Bind(R.id.my_awesome_toolbar) Toolbar mToolbar;

  @Override protected int getLayoutId() {
    return R.layout.act_main;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActionbar();
  }

  private void setupActionbar() {
    mToolbar.setTitle("");
    mToolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
    mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_text));
    setSupportActionBar(mToolbar);
  }

  public Toolbar getToolbar() {
    return mToolbar;
  }
}
