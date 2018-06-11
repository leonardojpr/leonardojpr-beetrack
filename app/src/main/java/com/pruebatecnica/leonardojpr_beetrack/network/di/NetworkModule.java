package com.pruebatecnica.leonardojpr_beetrack.network.di;

import android.content.Context;

import com.pruebatecnica.leonardojpr_beetrack.app.App;
import com.pruebatecnica.leonardojpr_beetrack.app.di.AppScope;
import com.pruebatecnica.leonardojpr_beetrack.network.interceptor.ConnectivityInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Leonardojpr on 06/05/18.
 */
@Module
public class NetworkModule {

    @Provides
    @AppScope
    public HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @AppScope
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); //10MB Cahe
    }

    @Provides
    @AppScope
    public File provideCacheFile(Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @AppScope
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ConnectivityInterceptor(App.getAppContext()))
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build();
    }

}
