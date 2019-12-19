package com.example.todomvp.di.module;

import android.content.Context;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.today.TodayContract;
import com.example.todomvp.ui.today.TodayPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class TodayModule {

    private TodayContract.View view;

    public TodayModule(TodayContract.View view) {
        this.view = view;
    }

    @Provides
    public TodayContract.View provideView() {
        return view;
    }

    @Provides
    public TodayContract.Presenter providePresenter(TodayContract.View view, final RealmService realmService) {
        return new TodayPresenter(view, realmService);
    }
}
