package app.meat.injection.presenters;

import app.meat.model.data.db.NewsFirebaseDB;
import app.meat.view.main.MainContract;
import app.meat.view.main.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    MainContract.Presenter provideMainPresenter(NewsFirebaseDB newsFirebaseDB) {
        return new MainPresenter(newsFirebaseDB);
    }
}
