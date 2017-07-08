package app.meat.injection.presenters;


import app.meat.services.MessageService;
import app.meat.view.main.MainActivity;
import dagger.Subcomponent;

@Subcomponent(modules = PresenterModule.class)
public interface PresentersComponent {

    void inject(MainActivity mainActivity);

    void inject(MessageService messageService);
}
