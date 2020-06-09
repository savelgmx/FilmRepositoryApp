package com.example.filmrepositoryapp.ui;

import androidx.fragment.app.Fragment;

import com.example.filmrepositoryapp.FilmPresenter;
import com.example.filmrepositoryapp.model.RealmManager;

public class FilmScopeListener extends Fragment {

    FilmPresenter filmsPresenter;

    public FilmScopeListener() {
        setRetainInstance(true);
        RealmManager.incrementCount();
        filmsPresenter = new FilmPresenter();
    }

    @Override
    public void onDestroy() {
        RealmManager.decrementCount();
        super.onDestroy();
    }

    public FilmPresenter getPresenter() {
        return filmsPresenter;
    }

}
