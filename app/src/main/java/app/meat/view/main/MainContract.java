package app.meat.view.main;

import app.meat.view.base.BasePresenter;
import app.meat.view.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void openNewsFragment();

        void openSettingsFragment();

        void checkNewsTab();

        void checkSettingsTab();
    }

    interface Presenter extends BasePresenter<View> {

        void onNewsPressed();

        void onSettingsPressed();
    }
}
