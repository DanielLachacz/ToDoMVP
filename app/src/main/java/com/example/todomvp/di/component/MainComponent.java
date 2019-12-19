package com.example.todomvp.di.component;

import com.example.todomvp.di.module.MainModule;
import com.example.todomvp.di.scope.ActivityScope;
import com.example.todomvp.ui.main.MainActivity;
import com.example.todomvp.ui.main.MainContract;
import com.example.todomvp.ui.save.SaveFragment;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);

    MainContract.Presenter getMainPresenter();

}
