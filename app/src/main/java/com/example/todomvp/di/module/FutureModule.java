package com.example.todomvp.di.module;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.future.FutureContract;
import com.example.todomvp.ui.future.FuturePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FutureModule {

    private FutureContract.View view;

    public FutureModule(FutureContract.View view) {
        this.view = view;
    }

    @Provides
    public FutureContract.View provideView() {
        return view;
    }

    @Provides
    public FutureContract.Presenter providePresenter(FutureContract.View view, final RealmService realmService) {
        return new FuturePresenter(view, realmService);
    }
}
