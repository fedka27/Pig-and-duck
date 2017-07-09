package app.meat.view.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import app.meat.R;
import app.meat.injection.ComponentProvider;
import app.meat.view.base.BaseActivity;
import app.meat.view.main.MainActivity;
import app.meat.view.setting.SettingsActivity;

public class SplashActivity extends BaseActivity
        implements SplashContract.View {
    @Inject
    protected SplashContract.Presenter presenter;

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, SplashActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    private void initPresenter() {
        ComponentProvider.getInstance().getPresentersComponent().inject(this);
        presenter.bindView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();

        setContentView(R.layout.activity_splash);

    }

    @Override
    public void openMainActivity() {
        MainActivity.start(this);
    }

    @Override
    public void openSettingsActivity(int requestCode) {
        SettingsActivity.start(this);
    }
}
