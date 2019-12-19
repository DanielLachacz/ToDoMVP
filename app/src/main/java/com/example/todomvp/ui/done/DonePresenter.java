package com.example.todomvp.ui.done;

import com.example.todomvp.model.realm.RealmService;

public class DonePresenter implements DoneContract.Presenter {

    private DoneContract.View contractView;
    private final RealmService realmService;

    public DonePresenter(DoneContract.View contractView, RealmService realmService) {
        this.contractView = contractView;
        this.realmService = realmService;
    }

    private void getDoneTasks() {
        contractView.showDoneTasks(realmService.getAllTasks().where().equalTo("status", true).findAll());
    }

    @Override
    public void setTasks() {
        getDoneTasks();
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
