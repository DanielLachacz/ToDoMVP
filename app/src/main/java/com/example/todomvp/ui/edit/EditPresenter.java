package com.example.todomvp.ui.edit;

import android.util.Log;

import com.example.todomvp.model.realm.RealmService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditPresenter implements EditContract.Presenter, RealmService.OnTransactionCallback {

    private EditContract.View contractView;
    private final RealmService realmService;

    public EditPresenter(EditContract.View contractView, RealmService realmService) {
        this.contractView = contractView;
        this.realmService = realmService;
    }

    @Override
    public void editTask(final int id, final String text, final String shortDate, final String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH);
        try {
            Date longDate = dateFormat.parse(shortDate);

           // Log.e("Save Presenter", String.valueOf(date) + " / " + String.valueOf(longDate));
            realmService.updateTask(id, text, shortDate, longDate, time, this);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        contractView = null;
    }

    @Override
    public void closeRealm() {
        realmService.closeRealm();
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
}
