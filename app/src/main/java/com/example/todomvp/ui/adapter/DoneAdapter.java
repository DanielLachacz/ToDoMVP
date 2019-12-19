package com.example.todomvp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvp.R;
import com.example.todomvp.model.Task;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.DoneViewHolder>
        implements RealmChangeListener<RealmResults<Task>> {

    private RealmResults<Task> mTasks;


    public class DoneViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textTextView, timeTextView, dateTextView;

        public DoneViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
            textTextView = itemView.findViewById(R.id.text_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            dateTextView = itemView.findViewById(R.id.date_text_view);
        }
    }

    @NonNull
    @Override
    public DoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_item, parent, false);
        return new DoneViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DoneViewHolder holder, int position) {
        final Task task = mTasks.get(position);
        holder.textTextView.setText(task.getText());
        holder.timeTextView.setText(task.getTime());
        holder.dateTextView.setText(task.getShortDate());
        holder.checkBox.setChecked(task.getStatus().booleanValue());
    }

    @Override
    public void onChange(RealmResults<Task> tasks) {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTasks == null ? 0 : mTasks.size();
    }

    public void setTasks(RealmResults<Task> tasks) {
        mTasks = tasks;
        tasks.addChangeListener(this);
        notifyDataSetChanged();
    }
}
