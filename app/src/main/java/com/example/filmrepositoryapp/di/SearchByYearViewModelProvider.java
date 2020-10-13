package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.ui.search.SearchByYearViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByYearViewModelProvider implements Provider<SearchByYearViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByYearViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByYearViewModel.class);
    }
}