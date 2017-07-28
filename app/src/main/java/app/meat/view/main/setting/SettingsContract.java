package app.meat.view.main.setting;

import java.util.List;

import app.meat.model.data.repository.Category;
import app.meat.view.base.BasePresenter;
import app.meat.view.base.BaseView;


public interface SettingsContract {

    interface View extends BaseView<Presenter> {
        void setList(List<Category> categoriesEnumList);
    }

    interface Presenter extends BasePresenter<View>, AdapterPresenter {
    }

    interface AdapterPresenter {
        void actionTopicPressed(Category categoriesEnum, boolean checked);
    }
}
