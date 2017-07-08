package app.meat.injection.api;

import android.content.Context;

import javax.inject.Singleton;

import app.meat.model.data.api.Api;
import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {
    @Provides
    @Singleton
    Api provideApiInterface(Context context) {
        return ApiInstance.getInstance(context);
    }

}
