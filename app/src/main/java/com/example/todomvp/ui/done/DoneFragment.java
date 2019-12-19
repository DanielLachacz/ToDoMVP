package com.example.todomvp.ui.done;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvp.BaseApplication;
import com.example.todomvp.R;
import com.example.todomvp.di.component.DaggerDoneComponent;
import com.example.todomvp.di.module.DoneModule;
import com.example.todomvp.model.Task;
import com.example.todomvp.ui.adapter.DoneAdapter;

import javax.inject.Inject;

import io.realm.RealmResults;

public class DoneFragment extends Fragment implements DoneContract.View {

    @Inject
    DoneContract.Presenter donePresenter;

    @Inject
    Context mContext;

    private DoneAdapter doneAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);

        recyclerView = view.findViewById(R.id.done_recycler_view);

        DaggerDoneComponent.builder()
                .appComponent(BaseApplication.get(getActivity()).component())
                .doneModule(new DoneModule(this))
                .build()
                .inject(this);

        initList(); 

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        donePresenter.setTasks();
    }

    private void initList() {
        doneAdapter = new DoneAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(doneAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showDoneTasks(RealmResults<Task> tasks) {
        doneAdapter.setTasks(tasks);
    }
}