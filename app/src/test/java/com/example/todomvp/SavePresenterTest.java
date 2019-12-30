package com.example.todomvp;

import com.example.todomvp.model.realm.RealmService;
import com.example.todomvp.ui.save.SaveContract;
import com.example.todomvp.ui.save.SaveFragment;
import com.example.todomvp.ui.save.SavePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SavePresenterTest {

    @Mock
    private SaveContract.View view;
    @Mock
    private RealmService service;

    private SavePresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new SavePresenter(view, service);
    }

    @Test
    public void saveTaskSuccessfully() throws Exception {
        presenter.addTaskClick("One", "Dec 25, 2019", "13:00", false);

        verify(view).addTaskError();
    }
}
