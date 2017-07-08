package app.meat.injection.manager;

import android.content.Context;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Singleton;

import app.meat.model.data.mapper.ApiResponseMapper;
import app.meat.util.connection.ConnectionUtil;
import app.meat.util.connection.ConnectionUtilAbs;
import app.meat.util.rx.RxSchedulers;
import app.meat.util.rx.RxSchedulersAbs;
import app.meat.view.base.BaseActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    RxSchedulersAbs provideRxSchedulersAbs() {
        return new RxSchedulers();
    }

    @Singleton
    @Provides
    RxPermissions provideRxPermissions(BaseActivity baseActivity) {
        return new RxPermissions(baseActivity);
    }

    @Provides
    @Singleton
    ConnectionUtilAbs provideConnectionUtilAbs(Context context) {
        return new ConnectionUtil(context);
    }

    @Provides
    @Singleton
    ApiResponseMapper provideApiResponseMapper(ConnectionUtilAbs connectionUtil) {
        return new ApiResponseMapper(connectionUtil);
    }
}
