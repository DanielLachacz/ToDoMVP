package com.example.todomvp.model.realm;

import android.util.Log;

import com.example.todomvp.model.Task;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmService {

    private final Realm realm;

    public RealmService(final Realm realm) {
        this.realm = realm;
    }

    public void closeRealm() {
        realm.close();
    }

    public RealmResults<Task> getAllTasks() {
        return realm.where(Task.class).findAll();
    }

    public void addTask(final String text, final String shortDate, final Date longDate, final String time, final boolean status,
                        final OnTransactionCallback onTransactionCallback) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task task = new Task();
                task.setId(realm.where(Task.class).findAll().size());
                task.setText(text);
                task.setShortDate(shortDate);
                task.setLongDate(longDate);
                task.setTime(time);
                task.setStatus(status);
                realm.copyToRealm(task);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                    Log.e("Realm Service", "Success");
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (onTransactionCallback == null) {
                    onTransactionCallback.onRealmError(error);
                    Log.e("Realm Service", "Error");
                }
            }
    });
    }

    public void updateTask(final int id, final String text, final String shortDate, final Date longDate, final String time,
                           final OnTransactionCallback onTransactionCallback) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task task = realm.where(Task.class).equalTo("id", id).findFirst();
                if (task == null) {
                    task = realm.createObject(Task.class, id);
                }
                task.setText(text);
                task.setShortDate(shortDate);
                task.setLongDate(longDate);
                task.setTime(time);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (onTransactionCallback != null) {
                    onTransactionCallback.onRealmSuccess();
                    Log.e("Realm Service", "Success");
                }
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                if (onTransactionCallback == null) {
                    onTransactionCallback.onRealmError(error);
                    Log.e("Realm Service", "Error");
                }
            }

        });
    }

    public void updateTaskStatus(final int id, final boolean status) {
       realm.executeTransactionAsync(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {
               Task task = realm.where(Task.class).equalTo("id", id).findFirst();
               if (task == null) {
                   task = realm.createObject(Task.class, id);
               }
               task.setStatus(status);
               realm.insertOrUpdate(task);
           }
       });
    }

    public interface OnTransactionCallback {
        void onRealmSuccess();
        void onRealmError(final Throwable error);
    }
}
