package com.example.filmrepositoryapp.ui.filmDetail;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.filmrepositoryapp.model.FRepository;
import com.google.gson.Gson;

public class FilmDetailViewModelCustomFactory implements ViewModelProvider.Factory {
    private FRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    public FilmDetailViewModelCustomFactory(FRepository repository, Gson gson, Long filmId) {

        this.mRepository = repository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FilmDetailViewModel(mRepository, mGson, mFilmId);

    }
}
