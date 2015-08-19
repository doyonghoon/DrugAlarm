package spencerdo.com.drugalarm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.OnClick;
import com.afollestad.materialdialogs.MaterialDialog;
import io.realm.Realm;
import java.util.Calendar;
import java.util.Date;
import spencerdo.com.drugalarm.R;
import spencerdo.com.drugalarm.model.DrugTimer;

/**
 * Created by doyonghoon on 2015. 8. 17..
 */
public class CreateTimerActivity extends BaseActivity {

  @Bind(R.id.create_root) View mRoot;
  @Bind(R.id.my_awesome_toolbar) Toolbar mToolbar;
  @Bind(R.id.create_name) EditText mCreateName;
  @Bind(R.id.create_timer_group) RadioGroup mRadioGroup;
  @Bind(R.id.create_custom_time) Button mCustomTime;
  private int mMinutes = 0;

  @Override protected int getLayoutId() {
    return R.layout.act_create_drug;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActionbar();

    mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(RadioGroup group, int id) {
        switch (id) {
          case R.id.min10:
            mMinutes = 10;
            break;
          case R.id.min15:
            mMinutes = 15;
            break;
          case R.id.min30:
            mMinutes = 30;
            break;
          case R.id.min60:
            mMinutes = 60;
            break;
        }
      }
    });
  }

  private void setupActionbar() {
    mToolbar.setTitle("Create Timer");
    mToolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
    mToolbar.setSubtitleTextColor(getResources().getColor(R.color.secondary_text));
    setSupportActionBar(mToolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setDisplayShowHomeEnabled(true);
    }
  }

  @Override public boolean onPrepareOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_create_drug, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        return true;
      case R.id.create_timer:
        if (canCreateTimer() && createDrugTimer(mCreateName.getText().toString(), mMinutes)) {
          setResult(Activity.RESULT_OK);
          finish();
          return true;
        }
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.create_custom_time)
  void onClickCustomTimer() {
    showCustomTimeDialog();
  }

  private boolean canCreateTimer() {
    if (TextUtils.isEmpty(mCreateName.getText())) {
      mCreateName.requestFocus();
      showErrorMessage("Please provide name of the drug.");
      return false;
    }

    if (mMinutes < 1) {
      showErrorMessage("Please set your timer.");
      return false;
    }

    return true;
  }

  private void showErrorMessage(String message) {
    Snackbar.make(mRoot, message, Snackbar.LENGTH_LONG).show();
  }

  private void showCustomTimeDialog() {
    new MaterialDialog.Builder(this).title("Set Custom Timer")
        .inputType(InputType.TYPE_NUMBER_VARIATION_NORMAL)
        .input("Custom Time", null, new MaterialDialog.InputCallback() {
          @Override public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
            if (TextUtils.isDigitsOnly(charSequence)) {
              mMinutes = Integer.parseInt(charSequence + "");
            }
          }
        })
        .build()
        .show();
  }

  private boolean createDrugTimer(String name, int minutes) {
    Realm realm = Realm.getInstance(this);
    // Transactions give you easy thread-safety
    realm.beginTransaction();
    DrugTimer timer = realm.createObject(DrugTimer.class);
    timer.setName(name);
    timer.setCount(0);
    timer.setMilliseconds(createNextAlarmDate(minutes));
    realm.commitTransaction();
    return true;
  }

  private long createNextAlarmDate(int minutes) {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.MINUTE, minutes);
    return c.getTime().getTime();
  }
}
