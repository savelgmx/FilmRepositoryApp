package com.example.filmrepositoryapp.di;

import androidx.fragment.app.DialogFragment;

import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailViewModel;
import com.example.filmrepositoryapp.ui.filmDetail.FilmDetailViewModelCustomFactory;

import toothpick.config.Module;

public class FilmDetailDialogFragmentModule extends Module {

    private DialogFragment mFragment;
    private Long mFilmId;

    public FilmDetailDialogFragmentModule(DialogFragment fragment, long filmId) {
        this.mFragment = fragment;
        this.mFilmId = filmId;

        bind(DialogFragment.class).toInstance(mFragment);
        bind(FilmDetailViewModel.class).toProvider(FilmDetailViewModelProvider.class);
        bind(Long.class).withName("FilmId").toInstance(mFilmId);
        bind(FilmDetailViewModelCustomFactory.class).toProvider(FilmDetailViewModelCustomFactoryProvider.class);
    }
}
