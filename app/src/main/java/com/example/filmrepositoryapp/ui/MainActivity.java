package com.example.filmrepositoryapp.ui;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.filmrepositoryapp.R;


//TODO SingleFragmentActivity унаследовать


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
