package spencerdo.com.drugalarm.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.Bind;
import spencerdo.com.drugalarm.R;

/**
 * Created by doyonghoon on 2015. 8. 17..
 */
public class CreateDrugActivity extends BaseActivity {

  @Bind(R.id.my_awesome_toolbar) Toolbar mToolbar;

  @Override protected int getLayoutId() {
    return R.layout.act_create_drug;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActionbar();
  }

  private void setupActionbar() {
    mToolbar.setTitle("Create");
    mToolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
    mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_text));
    setSupportActionBar(mToolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
    }
    return false;
  }
}
