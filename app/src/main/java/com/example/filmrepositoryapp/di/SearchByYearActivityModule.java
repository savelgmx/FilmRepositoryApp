package com.example.filmrepositoryapp.di;


import androidx.appcompat.app.AppCompatActivity;

import com.example.filmrepositoryapp.R;
import com.example.filmrepositoryapp.common.BaseViewModel;

public class SearchByYearActivityModule extends CommonActivityModule {


    public SearchByYearActivityModule(AppCompatActivity activity, String scopeName, int type) {
       super(activity, scopeName, type);
        //todo сделать интерфейсы

        bind(BaseViewModel.class).toProvider(SearchByYearViewModelProvider.class).providesSingletonInScope();
        //bind(SearchByYearViewModel.class).toProvider(SearchByYearViewModelProvider.class).providesSingletonInScope();
        bind(Integer.class).withName("TitleId").toInstance(R.string.year_search_title);
    }

}
