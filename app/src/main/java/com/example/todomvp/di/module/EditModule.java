package com.example.todomvp.di.module;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.edit.EditContract;
import com.example.todomvp.ui.edit.EditPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EditModule {

    private EditContract.View view;

    public EditModule(EditContract.View view) {
        this.view = view;
    }

    @Provides
    public EditContract.View provideView() {
        return view;
    }

    @Provides
    public EditContract.Presenter providePresenter(EditContract.View view, final RealmService realmService) {
        return new EditPresenter(view, realmService);
    }
}
