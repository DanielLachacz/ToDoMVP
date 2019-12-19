package com.example.todomvp.ui.main;

import com.example.todomvp.BasePresenter;

public interface MainContract {

    interface View {
        void showDate(String date);
    }

    interface Presenter extends BasePresenter {
        void getCurrentDate();
    }
}
