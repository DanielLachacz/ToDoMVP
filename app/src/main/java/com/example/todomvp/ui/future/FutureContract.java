package com.example.todomvp.ui.future;

import com.example.todomvp.BasePresenter;
import com.example.todomvp.model.Task;

import io.realm.RealmResults;

public interface FutureContract {

    interface View {
        void showFutureTasks(RealmResults<Task> tasks);
    }

    interface Presenter extends BasePresenter {
        void getTodayDate();
        void setTasks();
        void updateTaskStatus(int id, boolean status);
    }
}
