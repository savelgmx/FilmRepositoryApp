package com.example.filmrepositoryapp.di;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.example.alien.course04task02.ui.common.ViewModelCustomFactory;
import com.example.alien.course04task02.ui.search.SearchByNameViewModel;

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