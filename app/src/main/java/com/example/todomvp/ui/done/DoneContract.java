package com.example.todomvp.ui.done;

import com.example.todomvp.BasePresenter;
import com.example.todomvp.model.Task;

import io.realm.RealmResults;

public interface DoneContract {

    interface View {
        void showDoneTasks(RealmResults<Task> tasks);
    }

    interface Presenter extends BasePresenter {
        void setTasks();
    }
}
