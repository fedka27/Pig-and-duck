package app.meat.injection.presenters;

import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.model.data.repository.CategoryRepository;
import app.meat.util.rx.RxSchedulersAbs;
import app.meat.view.main.MainContract;
import app.meat.view.main.MainPresenter;
import app.meat.view.main.news.NewsContract;
import app.meat.view.main.news.NewsPresenter;
import app.meat.view.main.setting.SettingsContract;
import app.meat.view.main.setting.SettingsPresenter;
import app.meat.view.splash.SplashContract;
import app.meat.view.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PresenterModule {

    @Provides
    NewsContract.Presenter provideNewsPresenter(NewsFirebaseDB newsFirebaseDB) {
        return new NewsPresenter(newsFirebaseDB);
    }

    @Provides
    MainContract.Presenter provideMainPresenter(CategoryRepository categoryRepository) {
        return new MainPresenter(categoryRepository);
    }

    @Provides
    SettingsContract.Presenter provideSettingsPresenter(CategoryRepository categoryRepository){
        return new SettingsPresenter(categoryRepository);
    }

    @Provides
    SplashContract.Presenter provideSplashPresenter(CategoryRepository categoryRepository,
                                                    RxSchedulersAbs rxSchedulersAbs,
                                                    CompositeDisposable compositeDisposable){
        return new SplashPresenter(categoryRepository, rxSchedulersAbs, compositeDisposable);
    }
}
