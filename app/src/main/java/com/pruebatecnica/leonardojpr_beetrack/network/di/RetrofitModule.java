package com.pruebatecnica.leonardojpr_beetrack.network.di;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pruebatecnica.leonardojpr_beetrack.R;
import com.pruebatecnica.leonardojpr_beetrack.app.di.AppScope;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leonardojpr on 06/05/18.
 */
@Module(includes = NetworkModule.class)
public class RetrofitModule {

    @Provides
    @AppScope
    public String provideBaseUrl(Resources resources) {
        return resources.getString(R.string.api_url);
    }

    @Provides
    @AppScope
    public Gson provideGson() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson;
    }

    @Provides
    @AppScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,
                                    Gson gson,
                                    String url) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .baseUrl(url)
                .build();
    }

}
