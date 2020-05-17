package com.example.filmrepositoryapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class AddRecordActivity  extends SingleFragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    setContentView(R.layout.ac_realm_test);
    }

    protected Fragment getFragment() {
        return AddRecordFragment.newInstance();
    }

/*

    public static void start(MainActivity mainActivity) {

        Intent intent = new Intent(mainActivity, AddRecordActivity.class);
        mainActivity.startActivity(intent);

    }
*/
}
