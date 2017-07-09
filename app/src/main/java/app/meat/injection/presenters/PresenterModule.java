package app.meat.injection.presenters;

import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.model.data.repository.CategoryRepository;
import app.meat.util.rx.RxSchedulersAbs;
import app.meat.view.main.MainContract;
import app.meat.view.main.MainPresenter;
import app.meat.view.setting.SettingsContract;
import app.meat.view.setting.SettingsPresenter;
import app.meat.view.splash.SplashContract;
import app.meat.view.splash.SplashPresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class PresenterModule {

    @Provides
    MainContract.Presenter provideMainPresenter(NewsFirebaseDB newsFirebaseDB) {
        return new MainPresenter(newsFirebaseDB);
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
