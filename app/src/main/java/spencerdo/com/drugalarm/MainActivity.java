package spencerdo.com.drugalarm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

  private Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_main);

    mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
    mToolbar.setTitle("");
    mToolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
    mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_text));
    setSupportActionBar(mToolbar);
  }

  @Override public void onBackPressed() {
    if (getFragmentManager().getBackStackEntryCount() > 1) {
      getFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
