package com.example.todomvp.di.module;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.done.DoneContract;
import com.example.todomvp.ui.done.DonePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DoneModule {

    private DoneContract.View view;

    public DoneModule(DoneContract.View view) {
        this.view = view;
    }

    @Provides
    public DoneContract.View provideView() {
        return view;
    }

    @Provides
    public DoneContract.Presenter providePresenter(DoneContract.View view, final RealmService realmService) {
        return new DonePresenter(view, realmService);
    }
}
