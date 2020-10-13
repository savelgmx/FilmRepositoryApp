package com.example.filmrepositoryapp.di;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.ui.search.SearchByNameViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByNameViewModelProvider implements Provider<SearchByNameViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByNameViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByNameViewModel.class);
    }
}