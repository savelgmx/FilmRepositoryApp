package com.example.filmrepositoryapp.di;



import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.filmrepositoryapp.ui.common.ViewModelCustomFactory;
import com.example.filmrepositoryapp.ui.filmList.ListAllViewModel;

import javax.inject.Inject;
import javax.inject.Provider;

class ListAllViewModelProvider implements Provider<ListAllViewModel> {
    @Inject
    protected AppCompatActivity mActivity;
    @Inject
    protected ViewModelCustomFactory mFactory;


    @Override
    public ListAllViewModel get() {
        return ViewModelProviders.of(mActivity, mFactory).get(ListAllViewModel.class);
    }
}