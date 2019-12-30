package com.example.todomvp.ui.save;


import android.util.Log;

import com.example.todomvp.model.realm.RealmService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class SavePresenter implements SaveContract.Presenter, RealmService.OnTransactionCallback {

    private SaveContract.View contractView;
    private final RealmService realmService;

    public SavePresenter(SaveContract.View view, final RealmService realmService) {
        this.contractView = view;
        this.realmService = realmService;
    }

    @Override
    public void addTaskClick(String text, String date, String time, boolean status) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        try {
            Date longDate = dateFormat.parse(date);
            realmService.addTask(text, date, longDate, time, status, this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRealmSuccess() {
        contractView.addTaskSuccess();
    }

    @Override
    public void onRealmError(Throwable error) {
        contractView.addTaskError();
        error.printStackTrace();
    }

    @Override
    public void onDestroy() {
        contractView = null;
    }

    @Override
    public void closeRealm() {
        realmService.closeRealm();
    }
}
