package app.meat.injection.api;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import app.meat.R;
import app.meat.model.data.api.Api;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {
    private static final int CONNECT_TIMEOUT_SECONDS = 60;
    private static final int READ_TIMEOUT_SECONDS = 60;
    private static final int WRITE_TIMEOUT_SECONDS = 60;

    public static Api getInstance(Context context) {

        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient(context))
                .build()
                .create(Api.class);
    }

    private static Cache getCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(httpCacheDirectory, cacheSize);
    }

    public static OkHttpClient getClient(Context context) {
        return new OkHttpClient.Builder()
                .cache(getCache(context))
                .retryOnConnectionFailure(true)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .build();
    }
}
