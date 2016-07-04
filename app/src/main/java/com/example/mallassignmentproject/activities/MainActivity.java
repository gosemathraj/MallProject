package com.example.mallassignmentproject.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mallassignmentproject.R;
import com.example.mallassignmentproject.fragments.Fragment_Login;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment= getSupportFragmentManager().findFragmentById(R.id.parent_linear_layout);
        if(fragment == null){

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.parent_linear_layout,new Fragment_Login()).commit();
        }
    }
}
