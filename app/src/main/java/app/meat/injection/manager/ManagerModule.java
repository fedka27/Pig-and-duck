package app.meat.injection.manager;

import javax.inject.Singleton;

import app.meat.model.data.repository.CategoryRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class ManagerModule {

    @Provides
    @Singleton
    CategoryRepository provideCategoryRepository() {
        return new CategoryRepository();
    }

}
