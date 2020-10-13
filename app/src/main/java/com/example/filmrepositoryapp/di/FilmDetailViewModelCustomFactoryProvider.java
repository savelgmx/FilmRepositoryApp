package com.example.filmrepositoryapp.di;


import com.example.filmrepositoryapp.model.FRepository;
import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailViewModelCustomFactory;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class FilmDetailViewModelCustomFactoryProvider implements Provider<FilmDetailViewModelCustomFactory> {

    protected FRepository mRepository;
    private Gson mGson;
    private Long mFilmId;

    @Inject
    public FilmDetailViewModelCustomFactoryProvider(FRepository mRepository, Gson gson, @Named("FilmId") Long filmId) {
        this.mRepository = mRepository;
        this.mGson = gson;
        this.mFilmId = filmId;
    }

    @Override
    public FilmDetailViewModelCustomFactory get() {
        return new FilmDetailViewModelCustomFactory(mRepository, mGson, mFilmId);
    }
}
