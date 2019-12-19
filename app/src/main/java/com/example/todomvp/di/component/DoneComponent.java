package com.example.todomvp.di.component;

import com.example.todomvp.di.module.DoneModule;
import com.example.todomvp.di.scope.FragmentScope;
import com.example.todomvp.ui.done.DoneContract;
import com.example.todomvp.ui.done.DoneFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = DoneModule.class)
public interface DoneComponent {
    void inject(DoneFragment doneFragment);

   DoneContract.Presenter getDonePresenter();
}
