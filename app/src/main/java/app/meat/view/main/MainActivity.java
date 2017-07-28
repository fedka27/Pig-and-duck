package app.meat.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import javax.inject.Inject;

import app.meat.R;
import app.meat.injection.ComponentProvider;
import app.meat.view.base.BaseActivity;
import app.meat.view.base.BaseFragment;
import app.meat.view.main.news.NewsFragment;
import app.meat.view.main.setting.SettingsFragment;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {
    @Inject
    protected MainContract.Presenter presenter;
    @BindView(R.id.fragment_container_view_group)
    FrameLayout fragmentContainerViewGroup;
    @BindView(R.id.tabs_radio_group)
    RadioGroup tabsRadioGroup;
    private NewsFragment newsFragment = NewsFragment.newInstance();
    private app.meat.view.main.setting.SettingsFragment settingsFragment = SettingsFragment.newInstance();

    public static void start(BaseActivity activity) {
        Intent starter = new Intent(activity, MainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(starter);
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
        initPresenter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment(newsFragment);
        initFragment(settingsFragment);

        tabsRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.news_radio_button:
                    presenter.onNewsPressed();
                    break;
                case R.id.settings_radio_button:
                    presenter.onSettingsPressed();
                    break;
            }
        });

        presenter.onStart();
    }

    @Override
    public void checkNewsTab() {
        tabsRadioGroup.check(R.id.news_radio_button);
    }

    @Override
    public void checkSettingsTab() {
        tabsRadioGroup.check(R.id.settings_radio_button);
    }

    @Override
    public void openNewsFragment() {
        attachNewFragment(newsFragment);
    }

    @Override
    public void openSettingsFragment() {
        attachNewFragment(settingsFragment);
    }

    private void initFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(fragmentContainerViewGroup.getId(), fragment)
                .detach(fragment)
                .commitAllowingStateLoss();

    }

    private void attachNewFragment(BaseFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment prevFragment = fragmentManager.findFragmentById(fragmentContainerViewGroup.getId());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (prevFragment != null) {
            transaction.detach(prevFragment);
        }
        transaction.attach(fragment).commitAllowingStateLoss();
    }
}
