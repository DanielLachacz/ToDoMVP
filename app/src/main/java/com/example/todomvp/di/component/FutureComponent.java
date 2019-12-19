package com.example.todomvp.di.component;

import com.example.todomvp.di.module.FutureModule;
import com.example.todomvp.di.scope.FragmentScope;
import com.example.todomvp.ui.future.FutureContract;
import com.example.todomvp.ui.future.FutureFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FutureModule.class)
public interface FutureComponent {
    void inject(FutureFragment doneFragment);

    FutureContract.Presenter getFuturePresenter();
}
