package com.example.filmrepositoryapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filmrepositoryapp.R;


import io.realm.Realm;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionAddRecord:
                AddRecordActivity.start(this);
                return true;
            case R.id.actionEditRecord:
                EditRecordActivity.start(this);
                return true;
            case R.id.actionExit:
              //  getActivity().finish(); //убиваем текущую Активити
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
