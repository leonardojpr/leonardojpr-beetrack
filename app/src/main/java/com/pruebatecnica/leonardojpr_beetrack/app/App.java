package com.pruebatecnica.leonardojpr_beetrack.app;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.multidex.MultiDex;

import com.pruebatecnica.leonardojpr_beetrack.app.di.AppComponent;
import com.pruebatecnica.leonardojpr_beetrack.app.di.AppModule;
import com.pruebatecnica.leonardojpr_beetrack.app.di.DaggerAppComponent;

/**
 * Created by Leonardojpr on 06/05/18.
 */
public class App extends Application {

    private static App INSTANCE;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static App get() {
        return INSTANCE;
    }

    public static Context getAppContext() {
        return INSTANCE.getBaseContext();
    }

    public AppComponent component() {
        return component;
    }
}
