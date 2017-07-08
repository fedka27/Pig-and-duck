package app.meat.injection.services;

import javax.inject.Singleton;

import app.meat.model.data.db.NewsFirebaseDB;
import dagger.Module;
import dagger.Provides;


@Module
public class DataServiceModule {
    @Provides
    @Singleton
    NewsFirebaseDB provideNewsFirebaseDB(){
        return new NewsFirebaseDB();
    }
}
