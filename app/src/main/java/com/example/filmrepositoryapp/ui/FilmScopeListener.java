package com.example.filmrepositoryapp.ui;

import androidx.fragment.app.Fragment;

import com.example.filmrepositoryapp.FilmPresenter;
import com.example.filmrepositoryapp.model.FilmRepository;


public class FilmScopeListener extends Fragment {

    FilmPresenter filmsPresenter;

    public FilmScopeListener() {
        setRetainInstance(true);
        FilmRepository.incrementCount();
        filmsPresenter = new FilmPresenter();
    }

    @Override
    public void onDestroy() {
         FilmRepository.decrementCount();
        super.onDestroy();
    }

    public FilmPresenter getPresenter() {
        return filmsPresenter;
    }

}
