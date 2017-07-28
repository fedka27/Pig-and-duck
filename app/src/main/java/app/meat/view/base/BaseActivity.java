package app.meat.view.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private static boolean isAppWentToBg = false;

    private static boolean isWindowFocused = false;

    @Override
    protected void onStart() {
        if (isAppWentToBg && !isWindowFocused) {
            isAppWentToBg = false;
            onStartAppFromBg();
        }
        super.onStart();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        isWindowFocused = hasFocus;
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isWindowFocused) {
            isAppWentToBg = true;
        }
    }

    protected void onStartAppFromBg() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreenOrientation();
        pushScreenNameToGoogleAnalytics();
    }

    private void initScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    // TODO: Включить после интеграции гугл аналитики
    private void pushScreenNameToGoogleAnalytics() {
//        Tracker tracker = AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);
//        tracker.setScreenName(this.getClass().getSimpleName());
//        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    protected void showFragment(@IdRes int container, Fragment fragment) {
        try {
            ((FrameLayout) findViewById(container)).removeAllViews();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragment.getClass().getSimpleName())
                    .commitNow();
            getSupportFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
