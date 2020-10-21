package com.example.filmrepositoryapp.di;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.ui.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.ui.search.SearchByTopViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class SearchByTopViewModelProvider implements Provider<SearchByTopViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public SearchByTopViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(SearchByTopViewModel.class);
    }
}