package com.pruebatecnica.leonardojpr_beetrack.app.di;

import com.pruebatecnica.leonardojpr_beetrack.network.di.ApiModule;
import com.pruebatecnica.leonardojpr_beetrack.network.api.ArticleApi;

import dagger.Component;

/**
 * Created by Leonardojpr on 06/05/18.
 */

@AppScope
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {

    ArticleApi articleApi();
}
