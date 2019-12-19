package com.example.todomvp.di.module;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.save.SaveContract;
import com.example.todomvp.ui.save.SavePresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SaveModule {

    private SaveContract.View view;

    public SaveModule(SaveContract.View view) {
        this.view = view;
    }

    @Provides
    public SaveContract.View provideView() {
        return view;
    }

    @Provides
    public SaveContract.Presenter providePresenter(SaveContract.View view, final RealmService realmService) {
        return new SavePresenter(view, realmService);
    }

}
