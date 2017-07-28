package app.meat.injection.presenters;


import app.meat.services.MessageService;
import app.meat.view.main.MainActivity;
import app.meat.view.main.news.NewsFragment;
import app.meat.view.main.setting.SettingsFragment;
import app.meat.view.splash.SplashActivity;
import dagger.Subcomponent;

@Subcomponent(modules = PresenterModule.class)
public interface PresentersComponent {

    void inject(MainActivity mainActivity);

    void inject(MessageService messageService);

    void inject(SettingsFragment settingsFragment);

    void inject(SplashActivity splashActivity);

    void inject(NewsFragment newsFragment);
}
