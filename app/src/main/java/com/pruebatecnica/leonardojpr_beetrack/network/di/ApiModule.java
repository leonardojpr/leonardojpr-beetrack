package com.pruebatecnica.leonardojpr_beetrack.network.di;

import com.pruebatecnica.leonardojpr_beetrack.app.di.AppScope;
import com.pruebatecnica.leonardojpr_beetrack.network.api.ArticleApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Leonardojpr on 06/05/18.
 */
@Module(includes = RetrofitModule.class)
public class ApiModule {

    private static final String TAG = ApiModule.class.getSimpleName();

    @Provides
    @AppScope
    public static ArticleApi provideArticleApi(Retrofit retrofit) {
        return retrofit.create(ArticleApi.class);
    }

}