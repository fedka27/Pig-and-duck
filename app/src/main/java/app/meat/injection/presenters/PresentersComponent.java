package app.meat.injection.presenters;


import app.meat.services.MessageService;
import app.meat.view.main.MainActivity;
import app.meat.view.setting.SettingsActivity;
import app.meat.view.splash.SplashActivity;
import dagger.Subcomponent;

@Subcomponent(modules = PresenterModule.class)
public interface PresentersComponent {

    void inject(MainActivity mainActivity);

    void inject(MessageService messageService);

    void inject(SettingsActivity settingsActivity);

    void inject(SplashActivity splashActivity);
}
