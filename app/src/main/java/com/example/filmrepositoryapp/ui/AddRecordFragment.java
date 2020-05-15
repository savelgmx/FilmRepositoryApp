package com.example.filmrepositoryapp.ui;


import android.support.v4.app.Fragment;
import android.util.Log;

public class AddRecordFragment extends Fragment {
    public static Fragment newInstance() {
        Log.d("FilmRepositoryApp","Add record fragment");
        return new AddRecordFragment();
    }
}
