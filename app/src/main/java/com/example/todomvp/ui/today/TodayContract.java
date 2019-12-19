package com.example.todomvp.ui.today;

import com.example.todomvp.BasePresenter;
import com.example.todomvp.model.Task;

import io.realm.RealmResults;

public interface TodayContract {

    interface View {
        void showTodayTasks(RealmResults<Task> tasks);
    }

    interface Presenter extends BasePresenter {
        void getTodayDate();
        void setTasks();
        void updateTaskStatus(int id, boolean status);
    }
}
