package com.example.todomvp.di.component;

import com.example.todomvp.di.module.EditModule;
import com.example.todomvp.di.scope.FragmentScope;
import com.example.todomvp.ui.edit.EditContract;
import com.example.todomvp.ui.edit.EditFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = {EditModule.class})
public interface EditComponent {
    void inject(EditFragment editFragment);

    EditContract.Presenter getEditPresenter();
}
