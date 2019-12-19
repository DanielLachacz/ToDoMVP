package com.example.todomvp.di.component;

import com.example.todomvp.di.module.SaveModule;
import com.example.todomvp.di.scope.FragmentScope;
import com.example.todomvp.ui.save.SaveContract;
import com.example.todomvp.ui.save.SaveFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = {SaveModule.class})
public interface SaveComponent {
    void inject(SaveFragment saveFragment);

    SaveContract.Presenter getSavePresenter();
}
