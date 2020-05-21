package com.example.filmrepositoryapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;

import com.example.filmrepositoryapp.model.Film;

public class DetailFilmFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static DetailFilmFragment newInstance(Film film) {
        Bundle args = new Bundle();
        DetailFilmFragment fragment = new DetailFilmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //TODO добавить отображение детальных сведений о фильме
    @Override
    public void onRefresh() {
        
    }
}
