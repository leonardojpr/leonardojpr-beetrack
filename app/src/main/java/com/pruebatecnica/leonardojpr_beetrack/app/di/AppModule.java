package com.pruebatecnica.leonardojpr_beetrack.app.di;

import android.content.Context;
import android.content.res.Resources;

import com.pruebatecnica.leonardojpr_beetrack.app.App;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leonardojpr on 06/05/18.
 */

@Module
public class AppModule {

    public App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @AppScope
    public App provideApp() {
        return app;
    }

    @Provides
    @AppScope
    public Resources provideResources() {
        return app.getResources();
    }

    @Provides
    @AppScope
    public Context provideApplicationContext() {
        return app;
    }
}
