package app.meat.view.main;

import java.util.List;

import app.meat.model.dto.News;
import app.meat.view.base.BasePresenter;
import app.meat.view.base.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void showProgressDialog();

        void hideProgressDialog();

        void setNews(List<News> list);

        void openSettingsActivity();
    }

    interface Presenter extends BasePresenter<View> {

        void settingsPressed();
    }
}
