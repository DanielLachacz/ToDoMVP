package com.example.todomvp.ui.main;

import android.content.Context;
import android.os.Bundle;

import com.example.todomvp.BaseApplication;
import com.example.todomvp.R;
import com.example.todomvp.di.component.DaggerMainComponent;
import com.example.todomvp.di.module.MainModule;
import com.example.todomvp.ui.save.SaveFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    private AppBarConfiguration mAppBarConfiguration;

    @Inject
    MainContract.Presenter mainPresenter;

    @Inject
    Context mContext;

    TextView timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .appComponent(BaseApplication.get(getApplication()).component())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSaveFragment();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        View headView = navigationView.getHeaderView(0);
        timeTextView = headView.findViewById(R.id.head_date_text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.getCurrentDate();
    }

    @Override
    public void showDate(String date) {
        timeTextView.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void openSaveFragment() {
        SaveFragment saveFragment = new SaveFragment();
        saveFragment.show(getSupportFragmentManager(), "SaveFragment");
    }


}
