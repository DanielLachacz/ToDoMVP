package com.example.todomvp;

import android.app.Application;
import android.content.Context;

import com.example.todomvp.di.component.AppComponent;
import com.example.todomvp.di.component.DaggerAppComponent;
import com.example.todomvp.di.module.AppModule;
import com.example.todomvp.di.module.ContextModule;


import io.realm.Realm;
import io.realm.RealmConfiguration;

public class BaseApplication extends Application {

    private static AppComponent appComponent;
    private Application application;
    private Context context;


    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;
        context = getApplicationContext();
        configureDagger();
        configureRealm();
    }

    private void configureDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .contextModule(new ContextModule(this))
                .build();
    }

    private void configureRealm() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public AppComponent component() {
        return appComponent;
    }

    public Application getApp() {
        return application;
    }

    public Context getContext() {
        return this.context;
    }
}
