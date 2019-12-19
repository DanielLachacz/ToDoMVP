package com.example.todomvp.ui.edit;

import com.example.todomvp.BasePresenter;

import java.util.Date;

public interface EditContract {

    interface View {
        void addTaskSuccess();
        void addTaskError();
    }

    interface Presenter extends BasePresenter {
        void editTask(int id, final String text, final String shortDate, final String time);
    }
}
