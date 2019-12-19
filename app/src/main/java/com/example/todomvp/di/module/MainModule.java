package com.example.todomvp.di.module;

import com.example.todomvp.ui.main.MainContract;
import com.example.todomvp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    public MainContract.View provideView() {
        return view;
    }

    @Provides
    public MainContract.Presenter providePresenter(MainContract.View view) {
        return new MainPresenter(view);
    }
    
}
