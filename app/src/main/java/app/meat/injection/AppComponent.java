package app.meat.injection;

import android.content.Context;

import javax.inject.Singleton;

import app.meat.injection.api.ApiModule;
import app.meat.injection.manager.ManagerModule;
import app.meat.injection.manager.UtilsModule;
import app.meat.injection.presenters.PresenterModule;
import app.meat.injection.presenters.PresentersComponent;
import app.meat.injection.services.DataServiceModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class,
        ApiModule.class,
        ManagerModule.class,
        UtilsModule.class,
        DataServiceModule.class})
public interface AppComponent {

    Context getContext();

    PresentersComponent plus(PresenterModule module);
}
