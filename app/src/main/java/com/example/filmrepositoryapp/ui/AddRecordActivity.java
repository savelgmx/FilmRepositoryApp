package com.example.filmrepositoryapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.filmrepositoryapp.R;

public class AddRecordActivity  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_realm_test);
    }

    public static void start(MainActivity mainActivity) {

        Intent intent = new Intent(mainActivity, AddRecordActivity.class);
        mainActivity.startActivity(intent);

    }
}
