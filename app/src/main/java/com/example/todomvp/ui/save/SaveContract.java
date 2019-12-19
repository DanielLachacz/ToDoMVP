package com.example.todomvp.ui.save;

import com.example.todomvp.BasePresenter;


public interface SaveContract {

    interface View {
        void addTaskSuccess();
        void addTaskError();
    }

    interface Presenter extends BasePresenter {
        void addTaskClick(final String text, final String date, final String time, boolean status);
    }
}
