package com.example.todomvp;

public  interface BasePresenter<T> {

    void onDestroy();

    void closeRealm();
}
