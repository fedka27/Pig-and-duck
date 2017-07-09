package app.meat.view.splash;

import app.meat.view.base.BasePresenter;
import app.meat.view.base.BaseView;

public interface SplashContract {

    interface View extends BaseView<Presenter> {

        void openMainActivity();

        void openSettingsActivity(int requestCode);
    }

    interface Presenter extends BasePresenter<View> {

        void onActivityResult(int requestCode);
    }
}
