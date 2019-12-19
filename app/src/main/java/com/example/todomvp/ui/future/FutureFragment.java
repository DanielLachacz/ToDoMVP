package com.example.todomvp.ui.future;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvp.BaseApplication;
import com.example.todomvp.R;
import com.example.todomvp.di.component.DaggerFutureComponent;
import com.example.todomvp.di.module.FutureModule;
import com.example.todomvp.model.Task;
import com.example.todomvp.ui.adapter.FutureAdapter;
import com.example.todomvp.ui.adapter.TodayAdapter;
import com.example.todomvp.ui.edit.EditFragment;

import javax.inject.Inject;

import io.realm.RealmResults;

public class FutureFragment extends Fragment implements FutureContract.View {

    @Inject
    FutureContract.Presenter futurePresenter;

    @Inject
    Context mContext;

    private FutureAdapter futureAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_future, container, false);

        recyclerView = view.findViewById(R.id.future_recycler_view);

        DaggerFutureComponent.builder()
                .appComponent(BaseApplication.get(getActivity()).component())
                .futureModule(new FutureModule(this))
                .build()
                .inject(this);

        initList();

        futureAdapter.setOnCheckItemClickListener(new FutureAdapter.OnCheckItemClickListener() {
            @Override
            public void onCheckBoxClick(int id, boolean status) {
                futurePresenter.updateTaskStatus(id, status);
            }
        });

        futureAdapter.setOnItemClickListener(new FutureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", task.getId());
                bundle.putString("text", task.getText());
                bundle.putString("date", task.getShortDate());
                bundle.putString("time", task.getTime());
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                EditFragment editFragment = new EditFragment();
                editFragment.setArguments(bundle);
                editFragment.show(fragmentManager, "EditFragment");
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        futurePresenter.getTodayDate();
        futurePresenter.setTasks();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        futurePresenter.closeRealm();
    }

    private void initList() {
        futureAdapter = new FutureAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(futureAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void showFutureTasks(RealmResults<Task> tasks) {
        futureAdapter.setTasks(tasks);
    }
}