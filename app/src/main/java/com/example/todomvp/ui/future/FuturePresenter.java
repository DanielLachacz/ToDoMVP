package com.example.todomvp.ui.future;

import android.util.Log;

import com.example.todomvp.model.realm.RealmService;

import java.util.Calendar;
import java.util.Date;

public class FuturePresenter implements FutureContract.Presenter {

    private FutureContract.View contractView;
    private final RealmService realmService;
    private Date today;

    public FuturePresenter(FutureContract.View contractView, RealmService realmService) {
        this.contractView = contractView;
        this.realmService = realmService;
    }

    @Override
    public void getTodayDate() {
        today = Calendar.getInstance().getTime();
        Log.e("Future Presenter", String.valueOf(today));
    }

    private void getFutureTasks() {
        if (today == null) {
            getTodayDate();
        }
        contractView.showFutureTasks(realmService.getAllTasks().where()
                .greaterThan("longDate", today).and().equalTo("status", false).findAll());
    }

    @Override
    public void setTasks() {
        getFutureTasks();
    }

    @Override
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
