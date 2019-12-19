package com.example.todomvp.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
