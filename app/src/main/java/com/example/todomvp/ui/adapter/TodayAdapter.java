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

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.MainViewHolder>
        implements RealmChangeListener<RealmResults<Task>> {

    private RealmResults<Task> mTasks;
    private OnCheckItemClickListener mOnCheckItemClickListener;
    private OnItemClickListener mOnItemClickListener;


    public class MainViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textTextView, timeTextView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
            textTextView = itemView.findViewById(R.id.text_text_view);
            timeTextView = itemView.findViewById(R.id.time_text_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mOnItemClickListener != null && position != RecyclerView.NO_POSITION) {
                        mOnItemClickListener.onItemClick(mTasks.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Task task = mTasks.get(position);
        holder.textTextView.setText(task.getText());
        holder.timeTextView.setText(task.getTime());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCheckItemClickListener.onCheckBoxClick(task.getId(), task.getStatus());
            }
        });

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

    public interface OnCheckItemClickListener {
        void onCheckBoxClick(int id, boolean status);
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
    }

    public void setOnCheckItemClickListener(OnCheckItemClickListener onCheckItemClickListener) {
        mOnCheckItemClickListener = onCheckItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}


