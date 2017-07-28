package app.meat.view.main.news;

import java.util.List;

import app.meat.model.dto.News;
import app.meat.view.base.BasePresenter;
import app.meat.view.base.BaseView;

public interface NewsContract {
    interface View extends BaseView<Presenter> {
        void showProgressDialog();

        void hideProgressDialog();

        void setNews(List<News> list);

    }

    interface Presenter extends BasePresenter<View> {

    }
}
