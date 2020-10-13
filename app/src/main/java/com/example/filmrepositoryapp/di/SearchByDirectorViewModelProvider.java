package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.ui.search.SearchByDirectorViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByDirectorViewModelProvider implements Provider<SearchByDirectorViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByDirectorViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByDirectorViewModel.class);
    }
}