package com.example.todomvp.ui.today;


import android.util.Log;

import com.example.todomvp.model.realm.RealmService;

import java.util.Calendar;
import java.util.Date;


public class TodayPresenter implements TodayContract.Presenter {

    private TodayContract.View contractView;
    private final RealmService realmService;
    private String today;

    public TodayPresenter(TodayContract.View view, final RealmService realmService) {
        this.contractView = view;
        this.realmService = realmService;
    }

    public void getTodayDate() {
        Date getDate = Calendar.getInstance().getTime();
        today = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM).format(getDate);
    }

    private void getTodayTasks() {
        if (today == null) {
            getTodayDate();
        }
        contractView.showTodayTasks(realmService.getAllTasks().where().equalTo("shortDate", today).and().equalTo("status", false).findAll());
        //check date and status
    }

    @Override
    public void setTasks() {
        getTodayTasks();
    }

    public void updateTaskStatus(int id, boolean status) {
        if (status == false) {
            status = true;
        }
        else {
            status = false;
        }
        realmService.updateTaskStatus(id, status);

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
