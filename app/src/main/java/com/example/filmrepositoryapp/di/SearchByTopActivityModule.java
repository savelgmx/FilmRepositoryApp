package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.common.BaseViewModel;

public class SearchByTopActivityModule extends CommonActivityModule {


    public SearchByTopActivityModule(AppCompatActivity activity, String scopeName, int type) {
        super(activity, scopeName, type);
        //todo сделать интерфейсы
        bind(BaseViewModel.class).toProvider(SearchByTopViewModelProvider.class).providesSingletonInScope();
        //bind(SearchByTopViewModel.class).toProvider(SearchByTopViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.top_search_title);
    }

}
