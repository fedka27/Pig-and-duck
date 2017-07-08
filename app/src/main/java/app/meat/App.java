package app.meat;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.messaging.FirebaseMessaging;

import app.meat.injection.AppComponent;
import app.meat.injection.AppModule;
import app.meat.injection.ComponentProvider;
import app.meat.injection.DaggerAppComponent;
import app.meat.injection.presenters.PresenterModule;
import app.meat.injection.presenters.PresentersComponent;
import io.paperdb.Paper;

public class App extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        initComponentProvider();
        initPaper();
        initNotificationService();
    }

    private void initNotificationService() {
        FirebaseMessaging.getInstance().subscribeToTopic("all");
    }

    protected void initComponentProvider() {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        PresentersComponent presentersComponent = appComponent.plus(new PresenterModule());

        ComponentProvider.getInstance().init(appComponent, presentersComponent);
    }

    private void initPaper() {
        Paper.init(this);
    }

}
