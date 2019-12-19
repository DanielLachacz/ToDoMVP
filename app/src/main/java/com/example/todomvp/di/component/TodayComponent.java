package com.example.todomvp.di.component;


import com.example.todomvp.di.module.TodayModule;
import com.example.todomvp.di.scope.FragmentScope;
import com.example.todomvp.ui.today.TodayContract;
import com.example.todomvp.ui.today.TodayFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = TodayModule.class)
public interface TodayComponent {
    void inject(TodayFragment todayFragment);

    TodayContract.Presenter getTodayPresenter();
}
