package com.example.todomvp.ui.save;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.todomvp.BaseApplication;
import com.example.todomvp.R;
import com.example.todomvp.di.component.DaggerSaveComponent;
import com.example.todomvp.di.module.SaveModule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Objects;

import javax.inject.Inject;



public class SaveFragment extends DialogFragment implements SaveContract.View {

    private FloatingActionButton saveButton;
    private EditText textEditTest;
    private TextView dateEditText, timeEditText;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    @Inject
    SaveContract.Presenter savePresenter;

    @Inject
    Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        DaggerSaveComponent.builder()
                .appComponent(BaseApplication.get(getActivity()).component())
                .saveModule(new SaveModule(this))
                .build()
                .inject(this);

        saveButton = view.findViewById(R.id.save_button);
        textEditTest = view.findViewById(R.id.text_edit_text);
        dateEditText = view.findViewById(R.id.date_text_view);
        timeEditText = view.findViewById(R.id.time_text_view);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textEditTest.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Text Field is empty!", Toast.LENGTH_SHORT).show();
                }
                else if (dateEditText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Date Field is empty!", Toast.LENGTH_SHORT).show();
                }
                else if (textEditTest.getText().toString().trim().isEmpty() && dateEditText.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Text and Date Fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String text = textEditTest.getText().toString();
                    String date = dateEditText.getText().toString();
                    String time = timeEditText.getText().toString();
                    boolean status = false;
                    savePresenter.addTaskClick(text, date, time, status);
                }
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = java.text.DateFormat.getDateInstance(java.text.DateFormat.MEDIUM).format(calendar.getTime());

                Log.e("SaveFragment", date);
                dateEditText.setText(date);
            }
        };

        timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                checkDigit(minute);
                String time = hourOfDay + ":" + checkDigit(minute);
                timeEditText.setText(time);
            }
        };

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        return view;
    }

    private String checkDigit(int number) { //If minute is 9 or lower put 0 before minute number.
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = Objects.requireNonNull(getActivity()).getWindow();
        assert window != null;
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener,
                year, month, day);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(Objects.requireNonNull(getActivity()), android.R.style.Theme_Holo_Light_Dialog_MinWidth, timeSetListener,
                hourOfDay, minute, DateFormat.is24HourFormat(getActivity()));
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void addTaskSuccess() {
        Toast.makeText(getActivity(), "Task Added!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTaskError() {
        Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        savePresenter.closeRealm();
    }


}
