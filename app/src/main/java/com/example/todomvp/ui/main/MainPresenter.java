package com.example.todomvp.ui.main;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View contractView;

    public MainPresenter(MainContract.View view) {
        this.contractView = view;
    }

    @Override
    public void getCurrentDate() {
        Date getDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = dateFormat.format(getDate);

        Log.e("MainPresenter", date);
        contractView.showDate(date);
    }

    @Override
    public void onDestroy() {
        contractView = null;
    }

    @Override
    public void closeRealm() {

    }
}
