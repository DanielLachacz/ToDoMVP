package com.example.todomvp.ui.today;

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
import com.example.todomvp.di.component.DaggerTodayComponent;
import com.example.todomvp.di.module.TodayModule;
import com.example.todomvp.model.Task;
import com.example.todomvp.ui.adapter.TodayAdapter;
import com.example.todomvp.ui.edit.EditFragment;

import javax.inject.Inject;

import io.realm.RealmResults;

public class TodayFragment extends Fragment implements TodayContract.View {

    @Inject
    TodayContract.Presenter todayPresenter;

    @Inject
    Context mContext;

    private TodayAdapter todayAdapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        recyclerView = view.findViewById(R.id.today_recycler_view);

        DaggerTodayComponent.builder()
                .appComponent(BaseApplication.get(getActivity()).component())
                .todayModule(new TodayModule(this))
                .build()
                .inject(this);

        initList();

        todayAdapter.setOnCheckItemClickListener(new TodayAdapter.OnCheckItemClickListener() {
            @Override
            public void onCheckBoxClick(int id, boolean status) {
                todayPresenter.updateTaskStatus(id, status);
            }
        });

        todayAdapter.setOnItemClickListener(new TodayAdapter.OnItemClickListener() {
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
        todayPresenter.getTodayDate();
        todayPresenter.setTasks();
    }

    private void initList() {
        todayAdapter = new TodayAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(todayAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        todayPresenter.closeRealm();
    }

    @Override
    public void showTodayTasks(RealmResults<Task> tasks) {
        todayAdapter.setTasks(tasks);
    }

}