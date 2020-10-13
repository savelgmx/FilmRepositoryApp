package com.example.filmrepositoryapp.di;


import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailViewModel;
import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailViewModelCustomFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

class FilmDetailViewModelProvider implements Provider<FilmDetailViewModel> {
    @Inject
    protected DialogFragment mFragment;
    @Inject
    protected FilmDetailViewModelCustomFactory mFactory;
    @Inject
    @Named("FilmId")
    Long mFilmId;


    @Override
    public FilmDetailViewModel get() {
        return ViewModelProviders.of(mFragment, mFactory).get(String.valueOf(mFilmId),FilmDetailViewModel.class);
    }
}