package com.example.todomvp.di.component;

import android.app.Application;
import android.content.Context;

import com.example.todomvp.BaseApplication;
import com.example.todomvp.di.module.AppModule;
import com.example.todomvp.di.module.ContextModule;
import com.example.todomvp.model.realm.RealmService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        ContextModule.class})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    Context context();

    RealmService realmService();

    Application application();
}
